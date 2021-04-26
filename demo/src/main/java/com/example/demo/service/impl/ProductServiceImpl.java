package com.example.demo.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ProductDAO;
import com.example.demo.model.Product;
import com.example.demo.service.ProductService;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDAO productDAO;

	@Override
	public boolean saveProduct(Product student) {
		return productDAO.saveProduct(student);
	}

	@Override
	public List<Product> getProducts() {
		return productDAO.getProducts();
	}

	@Override
	public boolean deleteProduct(Product student) {
		return productDAO.deleteProduct(student);
	}

	@Override
	public List<Product> getProductByID(Product student) {
		return productDAO.getProductByID(student);
	}

	@Override
	public boolean updateProduct(Product student) {
		return productDAO.updateProduct(student);
	}

}
