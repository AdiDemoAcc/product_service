package com.microservice.product_service.service;

import java.util.List;

import com.microservice.product_service.model.Product;
import com.microservice.product_service.payload.ProductDTO;

public interface ProductService {
	
	public List<ProductDTO> getAllProducts();
	
	public ProductDTO saveProduct(ProductDTO product);
	
	public boolean deleteProduct(ProductDTO product);

}
