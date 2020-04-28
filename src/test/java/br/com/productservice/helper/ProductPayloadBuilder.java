package br.com.productservice.helper;

import static br.com.productservice.model.enums.Category.CLEANING;

import java.math.BigDecimal;

import br.com.productservice.model.enums.Category;
import br.com.productservice.model.payload.ProductPayload;

public class ProductPayloadBuilder {

	private ProductPayload dataToMock;

	private ProductPayloadBuilder() {
		this.dataToMock = ProductPayload.builder().price(BigDecimal.valueOf(15.50)).category(CLEANING)
				.name("LIMPEZA PROFUNDA").build();
	}

	public static ProductPayloadBuilder create() {
		return new ProductPayloadBuilder();
	}

	public ProductPayloadBuilder withName(final String name) {
		this.dataToMock.setName(name);

		return this;
	}
	
	public ProductPayloadBuilder withCategory(final Category category) {
		this.dataToMock.setCategory(category);
		
		return this;
	}
	
	public ProductPayloadBuilder withPrice(final BigDecimal price) {
		this.dataToMock.setPrice(price);
		
		return this;
	}

	public ProductPayload now() {
		return this.dataToMock;
	}

}
