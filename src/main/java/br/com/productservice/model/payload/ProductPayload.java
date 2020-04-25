package br.com.productservice.model.payload;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.productservice.model.enums.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductPayload {
	
	@NotNull(message = "{ProductPayload.category.notNull}")
	private Category category;
	
	@NotBlank(message = "{ProductPayload.name.notBlank}")
	@Size(min = 5, max = 100, message = "{ProductPayload.name.invalidSize}")
	private String name;
	
}
