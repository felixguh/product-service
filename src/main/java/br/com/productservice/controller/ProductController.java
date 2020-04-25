package br.com.productservice.controller;

import static org.springframework.http.HttpStatus.OK;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.productservice.model.payload.ProductPayload;
import br.com.productservice.model.response.ProductResponse;
import br.com.productservice.service.ProductService;

@RestController
@RequestMapping("/v1/products")
@CrossOrigin(origins = "*")
public class ProductController implements ProductApi {

	private final ProductService service;

	public ProductController(final ProductService service) {
		this.service = service;
	}

	@PostMapping
	@Override
	public ResponseEntity<ProductResponse> create(@RequestBody ProductPayload payload) {
		final var response = service.create(payload);

		final var uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/product/{productNumber}")
				.buildAndExpand(response.getProductNumber()).toUri();

		return ResponseEntity.created(uri).body(response);
	}

	@GetMapping("/product/{productNumber}")
	@ResponseStatus(code = OK)
	@Override
	public ProductResponse findByProductNumber(@PathVariable("productNumber") final Long productNumber) {
		return service.findByProductNumber(productNumber);
	}

}
