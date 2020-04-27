package br.com.productservice.service;

import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.productservice.exception.ProductNotExistsException;
import br.com.productservice.helper.ProductBuilder;
import br.com.productservice.helper.ProductPayloadBuilder;
import br.com.productservice.model.Product;
import br.com.productservice.repository.ProductRepository;

@RunWith(SpringRunner.class)
public class ProductServiceTest {
	
	@Mock
	private ProductRepository repository;
	
	@InjectMocks
	private ProductService service;
	
	private static final Long PRODUCT_NUMBER = 1L;
	
	@Test
	public void shouldCreateProduct() {
		final var payload = ProductPayloadBuilder.create().now();
		
		service.create(payload);
	}
	
	@Test
	public void shouldFindByProductNumber() {
		final var productOptional = ProductBuilder.create().optional();
		
		theSame(PRODUCT_NUMBER, productOptional);
	}

	private void theSame(final long productNumber, final Optional<Product> productOptional) {
		when(repository.findByProductNumber(productNumber)).thenReturn(productOptional);
		
		service.findByProductNumber(productNumber);
	}
	
	@Test(expected = ProductNotExistsException.class)
	public void whenFindByProductNumberWithNotExistsProductShouldReturnProductNotExistsException() {
		theSame(PRODUCT_NUMBER, Optional.empty());
	}
	
}
