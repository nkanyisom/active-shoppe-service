package za.co.momentum.activeshoppe.service;

import za.co.momentum.activeshoppe.entity.Product;

import java.util.List;
import java.util.Map;

public interface ProductService {

	List<Product> getAllProduct();

	void addProduct(Product product);

	Product findProductById(Long id);

	int deleteProduct(Long id);

}