package br.com.productservice.listener;

import static br.com.productservice.model.Product.PRODUCT_SEQUENCE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

import br.com.productservice.model.Product;
import br.com.productservice.service.SequenceGeneratorService;

@Component
public class ProductListener extends AbstractMongoEventListener<Product> {

	private final SequenceGeneratorService sequenceGenerator;

	@Autowired
	public ProductListener(SequenceGeneratorService sequenceGenerator) {
		this.sequenceGenerator = sequenceGenerator;
	}

	@Override
	public void onBeforeConvert(BeforeConvertEvent<Product> event) {
		if (event.getSource().getProductNumber() == null)
			event.getSource().setProductNumber(sequenceGenerator.generateSequence(PRODUCT_SEQUENCE));
	}

}
