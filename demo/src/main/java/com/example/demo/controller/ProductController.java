package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class ProductController {

	@Autowired
	private ProductService productService;

	@PostMapping("save-product")
	public boolean saveProduct(@RequestBody Product product) {
		return productService.saveProduct(product);
	}

	@GetMapping("products-list")
	public List<Product> allproducts() {
		return productService.getProducts();
	}

	@DeleteMapping("delete-product/{product_id}")
	public boolean deleteProduct(@PathVariable("product_id") long product_id, Product product) {
		product.setProductId(product_id);
		return productService.deleteProduct(product);
	}

	@GetMapping("product/{product_id}")
	public List<Product> allproductByID(@PathVariable("product_id") long product_id, Product product) {
		product.setProductId(product_id);
		return productService.getProductByID(product);
	}

	@PostMapping("update-product/{product_id}")
	public boolean updateProduct(@RequestBody Product product, @PathVariable("product_id") long product_id) {
		product.setProductId(product_id);
		return productService.updateProduct(product);
	}
}
