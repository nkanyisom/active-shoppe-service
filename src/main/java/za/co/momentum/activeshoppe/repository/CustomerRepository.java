package za.co.momentum.activeshoppe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.momentum.activeshoppe.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{

}
