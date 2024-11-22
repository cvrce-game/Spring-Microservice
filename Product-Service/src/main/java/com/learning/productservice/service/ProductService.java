package com.learning.productservice.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.learning.productservice.dto.ProductRequest;
import com.learning.productservice.dto.ProductResponse;
import com.learning.productservice.model.Product;
import com.learning.productservice.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {

	private final ProductRepository productRepository;

	public void createProduct(ProductRequest productRequest) {

		Product product = Product.builder().name(productRequest.getName()).description(productRequest.getDescription())
				.price(productRequest.getPrice()).build();

		productRepository.save(product);
		log.info("Product {} is saved.", product.getId());
	}

	public List<ProductResponse> getAllProducts() {
	    List<Product> products = productRepository.findAll();

	    log.info("All Products rettrived successfully");
	    if(products.size() == 0) {
	    	log.error("No Records founds");
	    }

	    // Map each Product entity to a ProductResponse and collect the results into a list
	    return products.stream()
	            .map(this::mapToProductResponse) // Use method reference for brevity
	            .collect(Collectors.toList());  // Collect results into a List
	}

	private ProductResponse mapToProductResponse(Product product) {
	    // Use Lombok's @Builder to create a ProductResponse object
	    return ProductResponse.builder()
	            .id(product.getId())
	            .name(product.getName())
	            .description(product.getDescription())
	            .price(product.getPrice())
	            .build(); // Fixed: build() was misplaced
	}

}
