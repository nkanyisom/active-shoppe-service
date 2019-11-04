package za.co.momentum.activeshoppe.repository;

import za.co.momentum.activeshoppe.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ProductRepository extends JpaRepository<Product, Long>{

	@Modifying
    @Transactional
    @Query("delete from Product products where products.id = :id ")
    int deleteById(@Param("id") Long id);

}
