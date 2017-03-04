package com.agjsj.fleamarket.net.procotal;


import org.json.JSONObject;

import java.io.Serializable;

/**
 * body明文中的内容
 *
 * @author yh
 */
public class Body implements Serializable {

    private Oelement oelement;
    private String elements;
    private String token;

    public Oelement getOelement() {
        return oelement;
    }

    public void setOelement(Oelement oelement) {
        this.oelement = oelement;
    }

    public String getElements() {
        return elements;
    }

    public void setElements(String elements) {
        this.elements = elements;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    /**
     * 封装对象
     *
     * @return
     */
    // public JSONObject serializableBody() {
    // JSONObject object = new JSONObject();
    // JSONObject beanObject = (JSONObject) JSONObject.toJSON(this.object);
    // object.put("elements", beanObject);
    // this.bodyStr = object.toJSONString();
    // System.out.println(bodyStr);
    // return object;
    // }

//    /**
//     * 解析Oelement
//     */
//    public Oelement serializableOelement() {
//        JSONObject object = JSONObject.parseObject(bodyStr);
//        String oelementinfo = object.getString("oelement");
//        return JSONObject.toJavaObject(JSONObject.parseObject(oelementinfo),
//                Oelement.class);
//    }
//
//    /**
//     * 当element为JsonObject时，调用此方法解析 当element为jsonArray时，谁用谁解析
//     */
//    @SuppressWarnings("unchecked")
//    public Object serializableElement(Class clazz) {
//        JSONObject object = JSONObject.parseObject(bodyStr);
//        String oelementinfo = object.getString("element");
//        return JSONObject.toJavaObject(JSONObject.parseObject(oelementinfo),
//                clazz);
//    }
//
//    public String getBodyStr() {
//        return bodyStr;
//    }
//
//    public void setBodyStr(String bodyStr) {
//        this.bodyStr = bodyStr;
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("elements", JSONObject.parse(this.bodyStr));
//        this.bodyStr = jsonObject.toJSONString();
//    }
//
//    public void setBodyStr(Object object) {
//
//        this.bodyStr = JSONObject.toJSONString(object);
//        this.setBodyStr(this.bodyStr);
//
//    }
//


    @Override
    public String toString() {
        return "Body{" +
                "oelement=" + oelement +
                ", elements='" + elements + '\'' +
                '}';
    }
}
