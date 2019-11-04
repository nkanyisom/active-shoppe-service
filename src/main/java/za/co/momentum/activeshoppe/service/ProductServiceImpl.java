package za.co.momentum.activeshoppe.service;

import za.co.momentum.activeshoppe.constants.ProductSearchResponse;
import za.co.momentum.activeshoppe.entity.Product;
import za.co.momentum.activeshoppe.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {


    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public void addProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public Product findProductById(Long id) {
        return productRepository.findOne(id);
    }

    @Override
    public int deleteProduct(Long id) {
        return productRepository.deleteById(id);
    }

}
