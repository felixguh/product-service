package br.com.productservice.common.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@JsonInclude(Include.NON_EMPTY)
@ApiModel(description = "Represents all error responses in service.")
public class ErrorResponse implements Serializable {

	private static final long serialVersionUID = 9018278630946730011L;

	@ApiModelProperty("An important information about this error (Ex. field name).")
    private String error;

    @ApiModelProperty("Business Error code, that vary due to the context.")
    private String errorCode;

    @ApiModelProperty("A basic description about what happened.")
    private String errorDescription;

    public ErrorResponse code(String code) {
        this.errorCode = code;
        return this;
    }

    public ErrorResponse tag(String tag) {
        this.error = tag;
        return this;
    }

    public ErrorResponse description(String description) {
        this.errorDescription = description;
        return this;
    }

    public static ErrorResponse as(String description) {
        return new ErrorResponse().description(description);
    }

}
