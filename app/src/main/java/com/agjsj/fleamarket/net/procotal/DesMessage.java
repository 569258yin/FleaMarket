package com.agjsj.fleamarket.net.procotal;

import java.io.Serializable;

/**
 *
 * 加密后的消息
 * 对于heander 不进行加密
 * 对于body根据henader进行加密，解密
 *
 * Created by YH on 2016/12/24.
 */

public class DesMessage implements Serializable{


    private Header header;
    private String body;

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
