package za.co.momentum.activeshoppe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.momentum.activeshoppe.entity.CustomerPurchase;
import za.co.momentum.activeshoppe.repository.CustomerPurchaseRepository;
import za.co.momentum.activeshoppe.repository.CustomerRepository;

import java.util.List;

@Service
public class CustomerPurchaseServiceImpl implements CustomerPurchaseService {


	@Autowired
	CustomerPurchaseRepository customerPurchaseRepository;

	@Autowired
	CustomerRepository customerRepository;

	@Override
	public List<CustomerPurchase> getAllCustomerPurchases() {
		return customerPurchaseRepository.findAll();
	}

	@Override
	public CustomerPurchase findPurchaseById(Long id){
		return customerPurchaseRepository.findOne(id);
	}

	@Override
	public List<CustomerPurchase> findPurchasesByCustomerId(Long id) {
		return customerPurchaseRepository.findCustomerPurchaseByCustomerId(id);
	}

	@Override
	public void addPurchase(CustomerPurchase customerPurchase){
		customerPurchaseRepository.save(customerPurchase);

	}
}
