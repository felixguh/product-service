package br.com.productservice.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;

import br.com.productservice.common.model.ErrorResponse;
import br.com.productservice.model.payload.ProductPayload;
import br.com.productservice.model.response.ProductResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "ProductApi")
public interface ProductApi {

	@ApiOperation("Create product")
	@ApiResponses({ @ApiResponse(code = 201, message = "Product was created with success"),
			@ApiResponse(code = 400, message = "Wrong information was sent", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "An unexpected error occurred", response = ErrorResponse.class) })
	ResponseEntity<ProductResponse> create(@Valid ProductPayload payload);

	@ApiOperation("Find product by productNumber")
	@ApiResponses({ @ApiResponse(code = 200, message = "Product was found with success"),
			@ApiResponse(code = 204, message = "Product not exists"),
			@ApiResponse(code = 400, message = "Wrong information was sent", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "An unexpected error occurred", response = ErrorResponse.class) })
	ProductResponse findByProductNumber(
			@ApiParam(value = "Product number", required = true, example = "1") final Long productNumber);
}
