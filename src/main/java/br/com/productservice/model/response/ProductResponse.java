package br.com.productservice.model.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.productservice.model.Product;
import br.com.productservice.model.enums.Category;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@ApiModel(description = "Product data")
public class ProductResponse {
	
	@JsonIgnore
	private final Product entity;
	
	@ApiModelProperty(value = "Product id", example = "1")
	public Long getProductNumber() {
		return entity.getProductNumber();
	}
	
	@ApiModelProperty(value = "Product category", example = "FOODS")
	public Category getCategory() {
		return entity.getCategory();
	}
	
	@ApiModelProperty(value = "Product name", example = "POTATO")
	public String getName() {
		return entity.getName();
	}
	
	@ApiModelProperty(value = "Product price", example = "15.50")
	public BigDecimal getPrice() {
		return entity.getPrice();
	}
	
	@ApiModelProperty(value = "Product creation date", example = "2020-04-25T15:53:46.673577")
	public LocalDateTime getCreationDate() {
		return entity.getCreationDate();
	}
	
	@ApiModelProperty(value = "Product last modified date", example = "2020-04-25T15:55:46.673577")
	public LocalDateTime getLastModifiedDate() {
		return entity.getLastModifiedDate();
	}

}
