package com.agjsj.fleamarket.bean;

import java.util.List;

public class ImagePath extends HttpResult{
	private List<String> imageUrls;
	
	public ImagePath(String code, String message) {
		super(code, message);
	}

	public ImagePath(List<String> imageUrls) {
		super();
		this.imageUrls = imageUrls;
	}

	public List<String> getImageUrls() {
		return imageUrls;
	}

	public void setImageUrls(List<String> imageUrls) {
		this.imageUrls = imageUrls;
	}
	
	
}
