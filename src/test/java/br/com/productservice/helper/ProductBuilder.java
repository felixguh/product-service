package br.com.productservice.helper;

import static br.com.productservice.model.enums.Category.CLEANING;

import java.time.LocalDateTime;
import java.util.Optional;

import org.bson.types.ObjectId;

import br.com.productservice.model.Product;

public class ProductBuilder {

	private Product dataToMock;

	private ProductBuilder() {
		this.dataToMock = Product.builder().id(new ObjectId("5ea6ea3041e7c7598c82da37")).name("Veja 100ML")
				.category(CLEANING).creationDate(LocalDateTime.now()).lastModifiedDate(LocalDateTime.now())
				.productNumber(1L).build();
	}

	public static ProductBuilder create() {
		return new ProductBuilder();
	}

	public Optional<Product> optional() {
		return Optional.of(this.dataToMock);
	}
	
	public Product now() {
		return this.dataToMock;
	}

}
