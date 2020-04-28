package br.com.productservice.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.productservice.exception.ProductNotExistsException;
import br.com.productservice.model.mapper.ProductMapper;
import br.com.productservice.model.payload.ProductPayload;
import br.com.productservice.model.response.ProductResponse;
import br.com.productservice.repository.ProductRepository;

@Service
public class ProductService {

	private final ProductRepository repository;

	@Autowired
	public ProductService(final ProductRepository repository) {
		this.repository = repository;
	}

	public ProductResponse create(@Valid final ProductPayload payload) {
		final var entity = repository.save(ProductMapper.toEntity(payload));

		return ProductResponse.builder().entity(entity).build();
	}

	public ProductResponse findByProductNumber(final Long productNumber) {
		final var entity = repository.findByProductNumber(productNumber).orElseThrow(ProductNotExistsException::new);

		return ProductResponse.builder().entity(entity).build();
	}

}
