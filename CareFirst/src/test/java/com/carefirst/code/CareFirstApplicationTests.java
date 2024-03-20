package com.carefirst.code;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.carefirst.code.entity.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class CareFirstApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Autowired
	CareFirstController careFirstController;
	
	@Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetEmployees() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/employees")).andReturn();
        String jsonResponse = result.getResponse().getContentAsString();
        ObjectMapper objectMapper = new ObjectMapper();
        List<Employee> employees = objectMapper.readValue(jsonResponse, new TypeReference<List<Employee>>() {});
        assertNotNull("The employee list must not be null", employees);
        assertFalse("The employee list must not be empty", employees.isEmpty());
    }
    
    @Test
    public void whenGetRequestToEmployeeAndValidEmployeer_thenCorrectReponse() throws Exception {
        String expected = "{\"employeeId\":1,\"firstName\":\"Luis\",\"lastName\":\"Gutierrez\",\"emailAddress\":\"luisgutierrez@at.com\",\"phone\":\"5555444411\",\"birthdate\":\"1991-05-06\","
        		+ "\"jobTitle\":\"Full Stack\",\"department\":\"Dev\",\"location\":\"USA\",\"startDate\":\"2022-05-06\"}";
        mockMvc.perform(MockMvcRequestBuilders.get("/employees/1")).andExpect(MockMvcResultMatchers.content().json(expected));
    }
    
    @Test
    public void whenDeleteRequestToEmployeesAndValidEmployee_thenCorrectResponse() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/employees/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    
    @Test
    public void whenDeleteRequestToEmployeesAndInValidEmployee_thenCorrectResponse() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/employees/2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
    
    @Test
    public void whenPostRequestToUsersAndValidUser_thenCorrectResponse() throws Exception {
        String employee = "{\"employeeId\": \"1\", \"firstName\": \"Luis\", \"lastName\" : \"Gutierrez\", \"emailAddress\": \"luisgutierrez@at.com\", \"phone\": \"5555444411\", \"birthdate\" : \"1991-05-06\", "
        		+ "\"jobTitle\" : \"Full stack developer\", \"department\": \"Dev\", \"location\": \"USA\", \"startDate\" : \"2023-05-06\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/employees")
                .content(employee)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }
    
    @Test
    public void whenPostRequestToUsersAndInValidUser_thenCorrectReponse() throws Exception {
        String employee = "{\"employeeId\": \"1\", \"firstName\": \"\", \"lastName\" : \"Gutierrez\", \"emailAddress\": \"luisgutierrez@at.com\", \"phone\": \"5555444411\", \"birthdate\" : \"1991-05-06\", "
        		+ "\"jobTitle\" : \"Full stack developer\", \"department\": \"Dev\", \"location\": \"USA\", \"startDate\" : \"2023-05-06\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/employees")
                .content(employee)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }
    
    @Test
    public void whenPutRequestToEmployeeAndValidEmployee_thenCorrectResponse() throws Exception {
        String employee = "{\"employeeId\": \"1\", \"firstName\": \"Luis Alberto\", \"lastName\" : \"Gutierrez\", \"emailAddress\": \"luisgutierrez@at.com\", \"phone\": \"5555444411\", \"birthdate\" : \"1995-06-08\", "
        		+ "\"jobTitle\" : \"Full stack developer\", \"department\": \"Dev\", \"location\": \"USA\", \"startDate\" : \"2023-05-06\"}";
        mockMvc.perform(MockMvcRequestBuilders.put("/employees/1")
                .content(employee)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    
    @Test
    public void whenPutRequestToEmployeesAndInvalidValidEmployee_thenCorrectResponse() throws Exception {
    	String employee = "{\"employeeId\": \"1\", \"firstName\": \"Luis Alberto\", \"lastName\" : \"Gutierrez\", \"emailAddress\": \"luisgutierrez@at.com\", \"phone\": \"5555444411\", \"birthdate\" : \"1995-06-08\", "
        		+ "\"jobTitle\" : \"Full stack developer\", \"department\": \"Dev\", \"location\": \"USA\", \"startDate\" : \"2023-05-06\"}";
        mockMvc.perform(MockMvcRequestBuilders.put("/employees/2")
                .content(employee)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
    
    @Test
    public void whenPutRequestToEmployeeAndInValidEmployee_thenCorrectResponse() throws Exception {
        String employee = "{\"employeeId\": \"1\", \"firstName\": \"\", \"lastName\" : \"Gutierrez\", \"emailAddress\": \"luisgutierrez@at.com\", \"phone\": \"5555444411\", \"birthdate\" : \"1995-06-08\", "
        		+ "\"jobTitle\" : \"Full stack developer\", \"department\": \"Dev\", \"location\": \"USA\", \"startDate\" : \"2023-05-06\"}";
        mockMvc.perform(MockMvcRequestBuilders.put("/employees/1")
                .content(employee)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

}
