package za.co.momentum.activeshoppe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import za.co.momentum.activeshoppe.entity.CustomerPurchase;

import java.util.List;

public interface CustomerPurchaseRepository extends JpaRepository<CustomerPurchase, Long>{

    @Query("SELECT cp FROM CustomerPurchase cp WHERE cp.customer = :customer")
    public List<CustomerPurchase> findCustomerPurchaseByCustomerId(@Param("customer") Long customer);
}
