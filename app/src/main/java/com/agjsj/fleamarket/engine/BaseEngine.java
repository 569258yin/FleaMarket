package com.agjsj.fleamarket.engine;

import com.agjsj.fleamarket.bean.MessageEvent;
import com.agjsj.fleamarket.params.OelementType;
import com.agjsj.fleamarket.view.base.BaseApplication;
import com.agjsj.fleamarket.net.HttpClient;
import com.agjsj.fleamarket.params.ConstantValue;
import com.agjsj.fleamarket.params.GlobalParams;
import com.agjsj.fleamarket.net.procotal.Body;
import com.agjsj.fleamarket.net.procotal.DesMessage;
import com.agjsj.fleamarket.net.procotal.Header;
import com.agjsj.fleamarket.net.procotal.IMessage;
import com.agjsj.fleamarket.util.DES;
import com.agjsj.fleamarket.util.GsonUtil;
import com.agjsj.fleamarket.util.LogUtil;
import com.agjsj.fleamarket.util.OkHttpUtils;

import org.apache.commons.codec.digest.DigestUtils;
import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * 1.创建并格式化body
 * 2.调用getMessageToJson（）将要发送的数据格式化成json字符串 此时传递body和请求码
 * 3.调用httpclient向服务器发送数据
 * 4.将获取的数据格式化调用getResult（）
 * 
 * @author yh
 * 
 */
public abstract class BaseEngine {

	private static DES des = new DES();
	private IMessage iMessage = null;
	/**
	 * 将服务器端发来的信息进行解析，并数据校验
	 * @param resposeStr
	 * @return
	 */
	public IMessage getResult(String resposeStr) {
		if (resposeStr != null) {
			try {
				//将json字符串转换为对象bean
				DesMessage desMsg = (DesMessage) GsonUtil.stringToObjectByType(resposeStr,DesMessage.class);
				// 解析出字符串
				String bodyinfo = desMsg.getBody();
				Header header = desMsg.getHeader();
				// 将body解密
				String orgbody = des.authcode(bodyinfo, "ENCODE",
						GlobalParams.DESPASSWORD);
				// 拿出时间戳
				String md5info = header.getTimestamp() + header.getIpaddress()
						+ orgbody;
				String digest = DigestUtils.md5Hex(md5info);
				// 数据校验
				Body body = null;
				if (digest.equals(header.getDigest())) {
					IMessage result = new IMessage();
					body = (Body) GsonUtil.stringToObjectByBean(orgbody,Body.class);
					EventBus.getDefault().post(new MessageEvent(body.getOelement().getCode()));
					result.setHeader(header);
					result.setBody(body);
					return result;
				}else{
					EventBus.getDefault().post(new MessageEvent(OelementType.LOCAL_CHECK_MD5_ERROR));
					LogUtil.error("BaseEngine getResult 数据校验失败---》"+resposeStr);
				}
			} catch (Exception e) {
				e.printStackTrace();
				LogUtil.error("BaseEngine getResult "+resposeStr);
			}
		}
		return null;
	}

	/**
	 * 对要发送给服务器的信息进行加密
	 * @param requestjson  请求内容
	 * @return
	 */
	public String getMessageToJson(String requestjson){
		Body body = new Body();
		body.setOelement(null);
		body.setElements(requestjson);
		return getMessageToJson(body,"");
	}

	/**
	 * 获取发送数据的字符串
	 * @param body   已经设置好json数据了的body，还未经过加密
	 * @param code   本次请求编号
	 * @return
	 */
	public String getMessageToJson(Body body, String code) {
		if(null != body) {
			try {
				body.setToken(BaseApplication.INSTANCE().getToken());
				// 1.封装body内容
				String bodyInfo = GsonUtil.objectToString(body);
				// 2.封装header内容
				Header header = new Header();
				header.setCompress("DES");
				header.setSource("Client");
				header.serializableHeader(bodyInfo);
				// 3.设置请求码
				header.setTransactiontype(code);
				// 4.加密body信息
				String desbody = des.authcode(bodyInfo, "DECODE",
						GlobalParams.DESPASSWORD);
				// 5.封装成加密后的Message
				DesMessage desMessage = new DesMessage();
				desMessage.setHeader(header);
				desMessage.setBody(desbody);
				return GsonUtil.objectToString(desMessage);
			} catch (Exception e) {
				LogUtil.error("BaseEngine getMessageToJson body:"+body.toString(),e);
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 向服务器发送json字符串，返回结果json字符串
	 * @param o 请求内容bean需能转换为json字符串
	 * @param type 请求类型
	 * @return
	 */
	public void sendJsonToService(Object o,String type,final serviceCallBack callBack) {
		if(o == null){
			callBack.serviceResponse(null);;
		}
		if(type == null || type.length() == 0){
			callBack.serviceResponse(null);;
		}
		Body body = new Body();
		body.setOelement(null);
		body.setElements(GsonUtil.objectToString(o));
		// 获取发送的数据
		String sendinfo = getMessageToJson(body, type);
		LogUtil.info("sendinfo----->" + sendinfo);
		OkHttpUtils.getInstance().syncPostJsonStringByURL(getURLByType(type), sendinfo, new OkHttpUtils.FuncJsonString() {
			@Override
			public void onResponse(String result) {
				LogUtil.info("responseStr----->" + result);
					if(result != null){
						IMessage message = getResult(result);
						if(message != null) {
							callBack.serviceResponse(message.getBody());
						}
					}
			}
		});



	}




	/**
	 *	不使用回调函数，不使用okhttp
	 * @param requestjson
	 * @param type
     * @return
     */
	public Body sendJsonToService(String requestjson,String type){
		if(requestjson == null){
			return null;
		}
		if(type == null || type.length() == 0){
			return null;
		}
		EventBus.getDefault().post(new MessageEvent(OelementType.PROGRESS_START));
		Body body = new Body();
		body.setOelement(null);
		body.setElements(requestjson);
		// 获取发送的数据
		String sendInfo = getMessageToJson(body, type);
		String result = HttpClient.getInstance().submitRequestBlock(sendInfo, getURLByType(type));
		if(result != null){
			IMessage message = getResult(result);
			if(message != null) {
				EventBus.getDefault().post(new MessageEvent(OelementType.PROGRESS_END));
				return message.getBody();
			}
		}
		EventBus.getDefault().post(new MessageEvent(OelementType.PROGRESS_END));
		return null;
	}


	private String getURLByType(String type){
		String URL = ConstantValue.URL_ROOT;
		switch (type){
			case ConstantValue.TYPE_LOGIN:
				URL += ConstantValue.URL_USER_LOGIN;
				break;
			case ConstantValue.TYPE_CHECK_TOKEN:
				URL += ConstantValue.URL_CHECK_TOKEN;
				break;
			case ConstantValue.TYPE_GET_USER:
				URL += ConstantValue.URL_GET_USER;
				break;
			case ConstantValue.TYPE_SEND_GOODS:
				URL += ConstantValue.URL_SEND_GOODS;
				break;
			case ConstantValue.TYPE_GET_GOODSTYPE:
				URL += ConstantValue.URL_GET_GOODSTYPE;
				break;
			case ConstantValue.TYPE_GET_GOODS_BY_PAGE:
				URL += ConstantValue.URL_GET_GOODS_BY_PAGE;
				break;
			case ConstantValue.TYPE_SEND_GOODS_REPLAY:
				URL += ConstantValue.URL_SEND_GOODS_REPLAY;
				break;
			case ConstantValue.TYPR_GET_GOODS_REPLAY:
				URL += ConstantValue.URL_GET_GOODS_REPLAY;
				break;
			default:
				break;
		}
		return URL;
	}

	public interface serviceCallBack{
		public void serviceResponse(Body body);
	}


}
