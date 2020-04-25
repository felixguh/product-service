package br.com.productservice.repository;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.productservice.model.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product, ObjectId>{

}
