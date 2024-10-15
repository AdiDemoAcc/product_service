package com.microservice.product_service.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.product_service.model.Product;
import com.microservice.product_service.payload.ApiResponse;
import com.microservice.product_service.payload.ProductDTO;
import com.microservice.product_service.service.ProductService;

@RestController
@RequestMapping(path = "/api/product")
@CrossOrigin(origins = "*")
public class ProductController {

	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/getAllProducts")
	public ResponseEntity<ApiResponse> getAllProducts() {
		List<ProductDTO> prodList = new ArrayList<ProductDTO>();
		ApiResponse apiResponse;
		try {
			logger.info("Inside getAllProducts method::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
			prodList = productService.getAllProducts();
			logger.info("Fetched product list with length:"+prodList.size());
			apiResponse = new ApiResponse(prodList, "000", "Success", true);
			logger.info("Exiting getAllProducts method::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
			return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.OK);
		} catch (Exception e) {
			logger.error("An error Occurred: "+e.getMessage());
			e.printStackTrace();
			apiResponse = new ApiResponse(null, "001", "Failed", false);
			return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(path = "/saveProduct")
	public ResponseEntity<ApiResponse> saveProduct(@RequestBody ProductDTO productdto) {
		ApiResponse apiResponse;
		try {
			logger.info("Inside saveProduct method::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
			productdto = productService.saveProduct(productdto);
			apiResponse = new ApiResponse(productdto, "000", "Success", true);
			return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.CREATED);
		} catch (Exception e) {
			logger.error("An error Occurred: "+e.getMessage());
			e.printStackTrace();
			apiResponse = new ApiResponse(null, "001", "Failed", false);
			return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(path = "/deleteProductById") 
	public ResponseEntity<ApiResponse> deleteProductById(@RequestBody ProductDTO productdto) {
		ApiResponse apiResponse;
		try {
			logger.info("Inside deleteProductById method::::::::::::::::::::::::::::::::::::::::::::::::");
			boolean flag = productService.deleteProduct(productdto);
			if (flag) {
				apiResponse = new ApiResponse(productdto, "000", "Product deleted", flag);
				return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.OK);
			}else {
				apiResponse = new ApiResponse(productdto, "002", "Product deletion failed", flag);
				return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.EXPECTATION_FAILED);
			}
			
		} catch (Exception e) {
			logger.error("An error Occurred: "+e.getMessage());
			e.printStackTrace();
			apiResponse = new ApiResponse(null, "001", "Failed", false);
			return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
