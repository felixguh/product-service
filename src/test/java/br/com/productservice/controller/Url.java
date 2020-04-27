package br.com.productservice.controller;

public enum Url {
	BASE_URL("/v1/products"), PRODUCT_NUMBER(BASE_URL.getUrl().concat("/product/1"));

	private final String url;

	private Url(final String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

}
