package br.com.productservice.helper;

import br.com.productservice.model.response.ProductResponse;

public class ProductResponseBuilder {

	private ProductResponse dataToMock;

	private ProductResponseBuilder() {
		this.dataToMock = ProductResponse.builder().entity(ProductBuilder.create().now()).build();
	}

	public static ProductResponseBuilder create() {
		return new ProductResponseBuilder();
	}

	public ProductResponse now() {
		return this.dataToMock;
	}

}
