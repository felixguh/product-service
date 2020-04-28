package br.com.productservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.productservice.model.DatabaseSequence;

@RunWith(SpringRunner.class)
public class SequenceGeneratorServiceTest {

	@Mock
	private MongoOperations mongoOperations;

	@InjectMocks
	private SequenceGeneratorService service;

	private static final String SEQUENCE_NAME = "teste_seq";
	private static final Long NEW_SEQUENCE = 2L;

	private Long sequence;

	@Test
	public void shouldGenerateNextNumber() {
		when(mongoOperations.findAndModify(any(Query.class), any(Update.class), any(FindAndModifyOptions.class), any()))
				.thenReturn(new DatabaseSequence("test", NEW_SEQUENCE));

		generateSequence();

		assertEquals(NEW_SEQUENCE, sequence);
	}

	@Test
	public void ifCantFindCounterUseDefaultValue() {
		when(mongoOperations.findAndModify(any(Query.class), any(Update.class), any(FindAndModifyOptions.class), any()))
				.thenReturn(null);

		generateSequence();

		assertEquals(SequenceGeneratorService.SEQUENCE_VALUE_DEFAULT, sequence);
	}

	private void generateSequence() {
		sequence = service.generateSequence(SEQUENCE_NAME);

		verify(mongoOperations).findAndModify(any(Query.class), any(Update.class), any(FindAndModifyOptions.class),
				any());
	}
}
