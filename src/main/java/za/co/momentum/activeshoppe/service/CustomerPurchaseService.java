package za.co.momentum.activeshoppe.service;

import za.co.momentum.activeshoppe.entity.CustomerPurchase;

import java.util.List;

public interface CustomerPurchaseService {

	List<CustomerPurchase> getAllCustomerPurchases();

	CustomerPurchase findPurchaseById(Long id);

	List<CustomerPurchase> findPurchasesByCustomerId(Long id);

	void addPurchase(CustomerPurchase customerPurchase);

}