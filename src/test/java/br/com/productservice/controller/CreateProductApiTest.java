package br.com.productservice.controller;

import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.productservice.exception.handler.ExceptionHandlerController;
import br.com.productservice.helper.ProductPayloadBuilder;
import br.com.productservice.helper.ProductResponseBuilder;
import br.com.productservice.model.payload.ProductPayload;
import br.com.productservice.service.ProductService;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = ProductController.class)
@ContextConfiguration(classes = { ProductController.class, ExceptionHandlerController.class })
public class CreateProductApiTest {

	@Autowired
	private MockMvc mockmvc;

	@Autowired
	private ObjectMapper mapper;

	@MockBean
	private ProductService productService;

	@Test
	public void shouldCreateProductAndReturnCreated() throws Exception {
		final var payload = ProductPayloadBuilder.create().now();

		when(productService.create(payload)).thenReturn(ProductResponseBuilder.create().now());

		callPost(payload, 201);
	}

	@Test
	public void whenCreateProductWithNullNameShouldReturnBadRequest() throws Exception {
		final var payload = ProductPayloadBuilder.create().withName(null).now();

		callPost(payload, 400);
	}

	@Test
	public void whenCreateProductWithTheNameWithOneCharacterShouldReturnBadRequest() throws Exception {
		final var payload = ProductPayloadBuilder.create().withName("A").now();

		callPost(payload, 400);
	}

	@Test
	public void whenCreateProductWithTheNameWithHundredAndOneCharactersShouldReturnBadRequest() throws Exception {
		final var payload = ProductPayloadBuilder.create().withName(
				"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa")
				.now();

		callPost(payload, 400);
	}

	@Test
	public void whenCreateProductWithNullCategoryShouldReturnBadRequest() throws Exception {
		final var payload = ProductPayloadBuilder.create().withCategory(null).now();

		callPost(payload, 400);
	}

	@Test
	public void whenCreateProductWithNullPriceShouldReturnBadRequest() throws Exception {
		final var payload = ProductPayloadBuilder.create().withPrice(null).now();

		callPost(payload, 400);
	}

	@Test
	public void whenCreateProductWithNotPositivePriceShouldReturnBadRequest() throws Exception {
		final var payload = ProductPayloadBuilder.create().withPrice(BigDecimal.valueOf(-15.50)).now();

		callPost(payload, 400);
	}
	
	private void callPost(final ProductPayload payload, final int status) throws Exception {
		mockmvc.perform(
				post(Url.BASE_URL.getUrl()).contentType(APPLICATION_JSON).content(mapper.writeValueAsString(payload)))
				.andExpect(status().is(status));
	}

}
