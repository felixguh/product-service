package br.com.productservice.model.mapper;

import br.com.productservice.model.Product;
import br.com.productservice.model.payload.ProductPayload;

public class ProductMapper {

	private ProductMapper() {

	}

	public static Product toEntity(final ProductPayload payload) {
		return Product.builder().category(payload.getCategory()).name(payload.getName()).build();
	}

}
