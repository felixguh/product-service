package br.com.productservice.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import javax.print.attribute.standard.Media;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.productservice.helper.ProductBuilder;
import br.com.productservice.listener.ProductListener;
import br.com.productservice.model.Product;

@RunWith(SpringRunner.class)
public class ProductListenerTest {
	
    @Mock
    private SequenceGeneratorService sequenceGeneratorService;

    @InjectMocks
    private ProductListener productListener;
    
    public static final String MEDIA_SEQUENCE = "product_sequence";
    
    @Test
    public void shouldGenerateSequenceProductNumber() {
        var collectionName = "products";

        BeforeConvertEvent<Product> event = new BeforeConvertEvent<Product>(new Product(), collectionName);

        when(sequenceGeneratorService.generateSequence(MEDIA_SEQUENCE)).thenReturn(1L);

        productListener.onBeforeConvert(event);

        assertThat(event.getCollectionName()).isEqualTo(collectionName);
    }

    @Test
    public void shouldGenerateSequenceWithNotNullSources() {
        final var product = ProductBuilder.create().now();

        BeforeConvertEvent<Product> event = new BeforeConvertEvent<Product>(product, "products");

        productListener.onBeforeConvert(event);

        assertNotNull(product);
    }

}
