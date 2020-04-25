package br.com.productservice.service;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import br.com.productservice.model.mapper.ProductMapper;
import br.com.productservice.model.payload.ProductPayload;
import br.com.productservice.model.response.ProductResponse;
import br.com.productservice.repository.ProductRepository;

@Service
public class ProductService {

	private final ProductRepository repository;

	public ProductService(final ProductRepository repository) {
		this.repository = repository;
	}

	public ProductResponse create(@Valid final ProductPayload payload) {
		final var entity = repository.save(ProductMapper.toEntity(payload));
		
		return new ProductResponse(entity);
	}

}
