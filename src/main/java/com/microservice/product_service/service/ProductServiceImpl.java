package com.microservice.product_service.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.product_service.model.Product;
import com.microservice.product_service.payload.ProductDTO;
import com.microservice.product_service.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
	
	private static Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

	@Autowired
	private ProductRepository productRepository; 
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public List<ProductDTO> getAllProducts() {
		List<Product> prodList = new ArrayList<>();
		try {
			logger.info("Inside getAllProducts method:::::::::::::::::::::::::::::::::::::::::::::::::::::");
			prodList = productRepository.findAll();
			logger.info("Exiting getAllProducts method:::::::::::::::::::::::::::::::::::::::::::::::::::::");
		} catch (Exception e) {
			logger.error("An error occurred: "+e.getMessage());
			e.printStackTrace();
		}
		return prodList.stream().map(product -> modelMapper.map(product,ProductDTO.class)).collect(Collectors.toList());
	}

	@Override
	public ProductDTO saveProduct(ProductDTO productdto) {
		try {
			Product product = modelMapper.map(productdto, Product.class);
			logger.info("Inside saveProduct method:::::::::::::::::::::::::::::::::::::::::::::::::::::");
			logger.info("Product is : "+product.toString());
			product = productRepository.save(product);
			productdto = modelMapper.map(product, ProductDTO.class);
			logger.info("Exiting saveProduct method:::::::::::::::::::::::::::::::::::::::::::::::::::::");
			return productdto;
		} catch (Exception e) {
			logger.error("An error occurred: "+e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean deleteProduct(ProductDTO productdto) {
		try {
			logger.info("Inside deleteProduct method:::::::::::::::::::::::::::::::::::::::::::::::::::::");
			if (productRepository.existsById(productdto.getId())) {
				logger.info("Product to be deleted has Product Id: "+productdto.getId());
				productRepository.deleteById(productdto.getId());
				logger.info("Exiting deleteProduct method:::::::::::::::::::::::::::::::::::::::::::::::::::::");
				return true;
			}
			logger.info("Exiting saveProduct method: Product with Id: "+productdto.getId()+" does not exist:::::::::::::::::::::::::::::::::::::::::::::");
		} catch (Exception e) {
			logger.error("An error occurred: "+e.getMessage());
			e.printStackTrace();
		}
		return false;
	}

}
