package br.com.productservice.model;

import java.time.LocalDateTime;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import br.com.productservice.model.enums.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

	@Transient
	public static final String PRODUCT_SEQUENCE = "product_sequence";

	@Id
	private ObjectId id;

	private Long productNumber;

	private Category category;

	private String name;

	@CreatedDate
	private LocalDateTime creationDate;

	@LastModifiedDate
	private LocalDateTime lastModifiedDate;

}
