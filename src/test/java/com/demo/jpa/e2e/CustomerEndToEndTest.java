package com.demo.jpa.e2e;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.FileCopyUtils;

import com.demo.jpa.config.TestConfig;
import com.demo.jpa.entity.Customer;
import com.demo.jpa.service.CustomerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { TestConfig.class, CustomerService.class })
@DirtiesContext
public class CustomerEndToEndTest {

	@Value("classpath:mock_data/customer.json")
	Resource customerData;

	@Mock
	private CustomerService customerService;
	
	private static final ObjectMapper mapper = new ObjectMapper();
	
	private String resourceToString(Resource resource) throws IOException {
		try(Reader reader = new InputStreamReader(resource.getInputStream(), UTF_8)){
			return FileCopyUtils.copyToString(reader);
		}
	}
	
	private JsonNode stringToJsonNode(String payLoad) throws JsonMappingException, JsonProcessingException  {
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		
		return mapper.readTree(payLoad);
	}
	
	private Customer jsonNodeToPojo(JsonNode jsonNode) throws JsonProcessingException   {
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		
		return mapper.treeToValue(jsonNode, Customer.class);
	}
	
	@Test
	public void testCustomerGet() throws IOException {		
		Customer cust = customerService.createCustomer(jsonNodeToPojo(stringToJsonNode(resourceToString(customerData))));
		assertEquals("Ganapaya", cust.getFirstName());
	}
}
