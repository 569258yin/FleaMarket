package com.agjsj.fleamarket.net.procotal;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Random;

/**
 * 消息头文件
 * @author yh
 *
 */
public class Header implements Serializable{
	private String messengereid;
	private String timestamp;
	private String transactiontype;
	private String digest;
	private String ipaddress;
	private String source;
	private String compress;

	public Header() {
	}

	public void serializableHeader(String body) {
		//格式化当前的时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String currentDate = sdf.format(new Date());
		//设置到时间戳中
		this.timestamp = currentDate;
		//
		//随机6位数
		Random random = new Random();
		int num = random.nextInt(999999)+1;
		DecimalFormat decimalFormat = new DecimalFormat("000000");
		this.messengereid=currentDate+decimalFormat.format(num);
		//获取IP
		this.ipaddress = getIP();
		//MD5
		String orgInfo = currentDate + ipaddress + body;
		String md5Hex = DigestUtils.md5Hex(orgInfo);
		this.digest=md5Hex;
		
	}
	
	/**
	 * 获取IP
	 * @return
	 */
	private String getIP() {  
		String IP = null;  
		StringBuilder IPStringBuilder = new StringBuilder();  
		try {  
			Enumeration<NetworkInterface> networkInterfaceEnumeration = NetworkInterface.getNetworkInterfaces();  
			while (networkInterfaceEnumeration.hasMoreElements()) {  
				NetworkInterface networkInterface = networkInterfaceEnumeration.nextElement();  
				Enumeration<InetAddress> inetAddressEnumeration = networkInterface.getInetAddresses();  
				while (inetAddressEnumeration.hasMoreElements()) {  
					InetAddress inetAddress = inetAddressEnumeration.nextElement();  
					if (!inetAddress.isLoopbackAddress()&&   
							!inetAddress.isLinkLocalAddress()&&   
							inetAddress.isSiteLocalAddress()) {  
						IPStringBuilder.append(inetAddress.getHostAddress().toString()+"\n");  
					}  
				}  
			}  
		} catch (SocketException ex) {  
			System.out.println("获取IP出错");
		}  

		IP = IPStringBuilder.toString();  
		return IP;  
	}

	public String getMessengereid() {
		return messengereid;
	}

	public void setMessengereid(String messengereid) {
		this.messengereid = messengereid;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getTransactiontype() {
		return transactiontype;
	}

	public void setTransactiontype(String transactiontype) {
		this.transactiontype = transactiontype;
	}

	public String getDigest() {
		return digest;
	}

	public void setDigest(String digest) {
		this.digest = digest;
	}

	public String getIpaddress() {
		return ipaddress;
	}

	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getCompress() {
		return compress;
	}

	public void setCompress(String compress) {
		this.compress = compress;
	}

	@Override
	public String toString() {
		return "Header{" +
				"messengereid='" + messengereid + '\'' +
				", timestamp='" + timestamp + '\'' +
				", transactiontype='" + transactiontype + '\'' +
				", digest='" + digest + '\'' +
				", ipaddress='" + ipaddress + '\'' +
				", source='" + source + '\'' +
				", compress='" + compress + '\'' +
				'}';
	}
}
