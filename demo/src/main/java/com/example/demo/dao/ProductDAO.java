package com.example.demo.dao;

import java.util.List;

import com.example.demo.model.Product;

public interface ProductDAO {

	public boolean saveProduct(Product product);

	public List<Product> getProducts();

	public boolean deleteProduct(Product product);

	public List<Product> getProductByID(Product product);

	public boolean updateProduct(Product product);
}
