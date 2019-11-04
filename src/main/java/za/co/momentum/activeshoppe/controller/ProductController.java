package za.co.momentum.activeshoppe.controller;

import za.co.momentum.activeshoppe.dto.ProductDTO;
import za.co.momentum.activeshoppe.entity.Product;
import za.co.momentum.activeshoppe.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.co.momentum.activeshoppe.util.ObjectMapperUtils;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/mas/")
public class ProductController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    ProductService productService;

    @GetMapping(value = "/product/all")
    @ResponseBody
    public List<ProductDTO> getAllProduct() {
        LOGGER.info("Get All the Products.");
        return ObjectMapperUtils.mapAll(productService.getAllProduct(), ProductDTO.class);
    }

    @GetMapping(value = "/product/{id}")
    @ResponseBody
    public ProductDTO findProductById(@PathVariable("id") Long id) {
        return convertToDto(productService.findProductById(id));
    }

    @PostMapping("/product")
    @ResponseBody
    public void addProduct(@RequestBody ProductDTO productDTO) throws ParseException {
        productService.addProduct(convertToEntity(productDTO));
    }

    @DeleteMapping("/product/{id}")
    @ResponseBody
    public String deleteProduct(@PathVariable("id") Long id) {
        int result = productService.deleteProduct(id);
        if(result == 1)
            return "Successful Deletion.";
        return "Failed to delete.";
    }


    private Product convertToEntity(ProductDTO productDTO) throws ParseException {
        return ObjectMapperUtils.map(productDTO, Product.class);
    }

    private ProductDTO convertToDto(Product product) {
        return ObjectMapperUtils.map(product, ProductDTO.class);
    }
}
