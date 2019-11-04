package za.co.momentum.activeshoppe;

import com.fasterxml.jackson.databind.ObjectMapper;
import za.co.momentum.activeshoppe.controller.ProductController;
import za.co.momentum.activeshoppe.dto.ProductDTO;
import za.co.momentum.activeshoppe.entity.Product;
import za.co.momentum.activeshoppe.service.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import za.co.momentum.activeshoppe.util.ObjectMapperUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(Application.class)
public class ProductControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Mock
	ProductService productCatalogueService;
	
	@MockBean
	ProductController target;
	
	
	List<Product> productList;
	
	Product p1;

	private Map<String, String> allRequstParam;
	
	ObjectMapper objectMapper;

	@Before
	public void setUp() throws Exception {
		p1 = new Product();
		p1.setCode(new Long(1));
		p1.setName("some name");
		p1.setPointsCost(1);
		
		productList = new ArrayList<>();
		productList.add(p1);
		
		objectMapper = new ObjectMapper();
	}

	
	@Test
	public void testGetAllProduct() throws Exception{
		given(target.getAllProduct()).willReturn(ObjectMapperUtils.mapAll(productList, ProductDTO.class));
		
		mockMvc.perform(get("/mas/product/all"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$", hasSize(1)));
	}


}
