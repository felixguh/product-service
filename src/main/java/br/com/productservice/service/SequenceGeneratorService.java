package br.com.productservice.service;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.util.Optional;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import br.com.productservice.model.DatabaseSequence;

@Service
public class SequenceGeneratorService {

	public static final long SEQUENCE_VALUE_DEFAULT = 1L;

	private final MongoOperations mongoOperations;

	public SequenceGeneratorService(final MongoOperations mongoOperations) {
		this.mongoOperations = mongoOperations;
	}

	public Long generateSequence(final String sequenceName) {
		final var updatedCounter = mongoOperations.findAndModify(query(where("_id").is(sequenceName)),
				new Update().inc("seq", 1), options().returnNew(true).upsert(true), DatabaseSequence.class);

		return Optional.ofNullable(updatedCounter).map(DatabaseSequence::getSeq).orElse(SEQUENCE_VALUE_DEFAULT);
	}

}