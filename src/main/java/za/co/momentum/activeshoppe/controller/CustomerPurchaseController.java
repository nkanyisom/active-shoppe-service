package za.co.momentum.activeshoppe.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.co.momentum.activeshoppe.dto.CustomerDTO;
import za.co.momentum.activeshoppe.dto.CustomerPurchaseDTO;
import za.co.momentum.activeshoppe.dto.ProductDTO;
import za.co.momentum.activeshoppe.entity.Customer;
import za.co.momentum.activeshoppe.entity.CustomerPurchase;
import za.co.momentum.activeshoppe.entity.Product;
import za.co.momentum.activeshoppe.exception.ActiveShoppeResourceNotFoundException;
import za.co.momentum.activeshoppe.repository.CustomerRepository;
import za.co.momentum.activeshoppe.repository.ProductRepository;
import za.co.momentum.activeshoppe.service.CustomerPurchaseService;
import za.co.momentum.activeshoppe.util.ObjectMapperUtils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/mas/")
public class CustomerPurchaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerPurchaseController.class);

    @Autowired
    CustomerPurchaseService customerPurchaseService;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

    @GetMapping(value = "/purchase/all")
    @ResponseBody
    public List<CustomerPurchaseDTO> getAllPurchases() {
        LOGGER.info("Get All the Purchases.");
        List<CustomerPurchase> customerPurchases = customerPurchaseService.getAllCustomerPurchases();

        return convertToPurchaseDTOs(customerPurchases);
    }

    @GetMapping("/purchase/{id}")
    @ResponseBody
    public CustomerPurchaseDTO findCustomerPurchaseById(@PathVariable("id") long id) {
        LOGGER.info("Finding a Customer Purchase by the Purchase ID.");
        CustomerPurchaseDTO customerPurchaseDTO = new CustomerPurchaseDTO();
        CustomerPurchase customerPurchase = customerPurchaseService.findPurchaseById(id);

        if(customerPurchase == null){
            throw new ActiveShoppeResourceNotFoundException("No Customer Purchases Found.");
        }

        List<Long> products = new ArrayList<>();

        for(Product product : customerPurchase.getProducts()) {
            products.add(product.getCode());
        }

        customerPurchaseDTO.setProductList(products);
        customerPurchaseDTO.setCustomerId(customerPurchase.getId());

        return customerPurchaseDTO;
    }

    @GetMapping(value = "/purchase/customer/{customerId}")
    @ResponseBody
    public List<CustomerPurchaseDTO> getAllCustomerPurchasesById(@PathVariable("customerId") Long customerId) {
        LOGGER.info("Getting all the Customer Purchases by the Customer's ID.");
        List<CustomerPurchase> customerPurchases = customerPurchaseService.findPurchasesByCustomerId(customerId);
        if(customerPurchases == null){
            throw new ActiveShoppeResourceNotFoundException("No Customer Purchases Found.");
        }

        return convertToPurchaseDTOs(customerPurchases);
    }

    @PostMapping(value = "/purchase/add")
    @ResponseBody
    public void addCustomerPurchase(@RequestBody final CustomerPurchaseDTO customerPurchaseDTO) throws ParseException {

        LOGGER.info("Adding the Customer Purchase and updating the Customer Balance.");
        //update the customer(points Balance) before saving the customer purchase.
        CustomerDTO customerDTO = ObjectMapperUtils.map(customerRepository.findOne(customerPurchaseDTO.getCustomerId()), CustomerDTO.class);
        List<ProductDTO> productDTOS = new ArrayList<>();

        for(Long productItemId : customerPurchaseDTO.getProductList()){
            productDTOS.add(ObjectMapperUtils.map(productRepository.findOne(productItemId), ProductDTO.class));
        }

        int totalProductPoints = 0;
        if (customerPurchaseDTO.getProductList().size() > 0) {
            for (ProductDTO productItem : productDTOS) {
                totalProductPoints += productItem.getPointsCost();
            }
        }

        customerDTO.setCurrentPointsBalance(customerDTO.getCurrentPointsBalance() - totalProductPoints);

        customerRepository.save(ObjectMapperUtils.map(customerDTO, Customer.class));

        CustomerPurchase customerPurchase = new CustomerPurchase();
        customerPurchase.setCustomer(ObjectMapperUtils.map(customerDTO, Customer.class));
        customerPurchase.setProducts(ObjectMapperUtils.mapAll(productDTOS, Product.class));

        customerPurchaseService.addPurchase(customerPurchase);
    }

    private List<CustomerPurchaseDTO> convertToPurchaseDTOs(List<CustomerPurchase> customerPurchases){
        List<Long> products = new ArrayList<>();
        List<CustomerPurchaseDTO> customerPurchaseDTOS = new ArrayList<>();

        for(CustomerPurchase customerPurchase : customerPurchases){
            CustomerPurchaseDTO customerPurchaseDTO = new CustomerPurchaseDTO();

            for(Product product : customerPurchase.getProducts()) {
                products.add(product.getCode());
            }
            customerPurchaseDTO.setCustomerId(customerPurchase.getId());
            customerPurchaseDTO.setProductList(products);
            customerPurchaseDTOS.add(customerPurchaseDTO);
        }

        return customerPurchaseDTOS;
    }

}
