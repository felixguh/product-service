package br.com.productservice.model.payload;

import br.com.productservice.model.enums.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductPayload {
	
	private Category category;
	
	private String name;
	
}
