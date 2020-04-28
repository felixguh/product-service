package br.com.productservice.config;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Configuration
public class JacksonConfig {

    @Bean
    public ObjectMapper jsonMapper() {
        final var jsonMapper = new ObjectMapper();

        jsonMapper.registerModule(new JavaTimeModule());
        jsonMapper.configure(WRITE_DATES_AS_TIMESTAMPS, false);
        jsonMapper.configure(FAIL_ON_UNKNOWN_PROPERTIES, false);

        return jsonMapper;

    }

}
