package za.co.momentum.activeshoppe.dto;

import lombok.Getter;
import lombok.Setter;
import za.co.momentum.activeshoppe.entity.Customer;
import za.co.momentum.activeshoppe.entity.CustomerPurchase;

import java.util.List;

@Getter
@Setter
public class CustomerPurchaseDTO {

    private Long customerId;
    private List<Long> productList;
}
