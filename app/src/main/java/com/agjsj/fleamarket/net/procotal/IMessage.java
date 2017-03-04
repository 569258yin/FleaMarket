package com.agjsj.fleamarket.net.procotal;

import java.io.Serializable;

public class IMessage implements Serializable{
	private Header header = new Header();
	private Body body = new Body();

	public IMessage() {
	}

	public Header getHeader() {
		return header;
	}

	public void setHeader(Header header) {
		this.header = header;
	}

	public Body getBody() {
		return body;
	}

	public void setBody(Body body) {
		this.body = body;
	}

	@Override
	public String toString() {
		return "IMessage{" +
				"header=" + header +
				", body=" + body +
				'}';
	}
}
