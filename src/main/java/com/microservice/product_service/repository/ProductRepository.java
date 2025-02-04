package com.microservice.product_service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.microservice.product_service.model.Product;

public interface ProductRepository extends MongoRepository<Product, String>{

	public boolean existsById(String id);
	
}
