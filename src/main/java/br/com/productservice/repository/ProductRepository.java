package br.com.productservice.repository;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.productservice.model.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product, ObjectId>{
	
	Optional<Product> findByProductNumber(final Long productNumber);

}
