package za.co.momentum.activeshoppe.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDTO {

    private Long id;
    private String name;
    private int currentPointsBalance;
}
