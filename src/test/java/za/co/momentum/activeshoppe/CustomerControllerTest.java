package za.co.momentum.activeshoppe;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import za.co.momentum.activeshoppe.controller.CustomerController;
import za.co.momentum.activeshoppe.controller.ProductController;
import za.co.momentum.activeshoppe.dto.CustomerDTO;
import za.co.momentum.activeshoppe.entity.Customer;
import za.co.momentum.activeshoppe.entity.Product;
import za.co.momentum.activeshoppe.service.CustomerService;
import za.co.momentum.activeshoppe.util.ObjectMapperUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(Application.class)
public class CustomerControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Mock
	CustomerService customerService;
	
	@MockBean
	CustomerController target;
	
	List<Customer> customerList;

	Customer c1;

	private Map<String, String> allRequstParam;
	
	ObjectMapper objectMapper;

	@Before
	public void setUp() throws Exception {
		c1 = new Customer();
		c1.setId(new Long(101));
		c1.setName("some customer name");
		c1.setCurrentPointsBalance(105);
		
		customerList = new ArrayList<>();
		customerList.add(c1);
		
		objectMapper = new ObjectMapper();
	}

	
	@Test
	public void testGetAllCustomers() throws Exception{
		given(target.getAllCustomers()).willReturn(ObjectMapperUtils.mapAll(customerList, CustomerDTO.class));
		
		mockMvc.perform(get("/mas/customer/all"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$", hasSize(1)));
	}
	

}
