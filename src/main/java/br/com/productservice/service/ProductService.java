package br.com.productservice.service;

import org.springframework.stereotype.Service;

import br.com.productservice.repository.ProductRepository;

@Service
public class ProductService {

	private final ProductRepository repository;

	public ProductService(final ProductRepository repository) {
		this.repository = repository;
	}

}
