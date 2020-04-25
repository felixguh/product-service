package br.com.productservice.config;

import org.bson.types.ObjectId;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;

import br.com.productservice.ProductServiceApplication;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.spi.DocumentationType.SWAGGER_2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(SWAGGER_2).select().apis(RequestHandlerSelectors.basePackage(ProductServiceApplication.class.getPackage().getName()))
                .build().apiInfo(apiInfo()).useDefaultResponseMessages(false).genericModelSubstitutes(ResponseEntity.class)
                .directModelSubstitute(ObjectId.class, String.class);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Product Service").build();
    }

}
