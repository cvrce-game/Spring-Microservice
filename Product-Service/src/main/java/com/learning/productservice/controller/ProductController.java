package com.learning.productservice.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.learning.productservice.dto.ProductRequest;
import com.learning.productservice.dto.ProductResponse;
import com.learning.productservice.service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProductController {

	private final ProductService productService;

	@PostMapping("/createProduct")
	@ResponseStatus(HttpStatus.CREATED)
	public void createProduct(@RequestBody ProductRequest productRequest) {
		productService.createProduct(productRequest);
	}

	@GetMapping("/getAllProducts")
	@ResponseStatus(HttpStatus.OK)
	public List<ProductResponse> getAllProducts() {
		return productService.getAllProducts();
	}

}
