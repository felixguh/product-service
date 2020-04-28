package br.com.productservice.controller;

import static org.mockito.Mockito.doThrow;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import br.com.productservice.exception.ProductNotExistsException;
import br.com.productservice.exception.handler.ExceptionHandlerController;
import br.com.productservice.service.ProductService;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = ProductController.class)
@ContextConfiguration(classes = { ProductController.class, ExceptionHandlerController.class })
public class FindProductByNumberApiTest {

	@Autowired
	private MockMvc mockmvc;

	@MockBean
	private ProductService productService;

	@Test
	public void shouldFindProductByProductNumberAndReturnOk() throws Exception {
		mockmvc.perform(get(Url.PRODUCT_NUMBER.getUrl()).accept(APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void whenFindProductByProductNumberWithNotExistsProductShouldReturnNoContent() throws Exception {
		doThrow(new ProductNotExistsException()).when(productService).findByProductNumber(1L);
		
		mockmvc.perform(get(Url.PRODUCT_NUMBER.getUrl()).accept(APPLICATION_JSON)).andExpect(status().isNoContent());
	}

}
