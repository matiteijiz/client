package com.management.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.management.client.create.CreateCustomerService;
import com.management.client.create.CustomerDto;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerControllerTest.class)
public class CustomerControllerTest {

    @MockitoBean
    private CreateCustomerService createCustomerService;

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void shouldCreateCustomerSuccessfully() throws Exception {
        var customerDto = new CustomerDto();
        customerDto.setName("Juan");
        customerDto.setLastName("Gonzales");
        customerDto.setDateOfBirth(LocalDate.of(1993,10,30));
        var responseCustomerDto = new ResponseCustomerDto(
                1L,
                "Juan",
                "Gonzales",
                LocalDate.of(1993,10,30)
        );
        Mockito.when(createCustomerService.save(customerDto)).thenReturn(responseCustomerDto);
        mockMvc.perform(post("/customer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customerDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Juan"))
                .andExpect(jsonPath("$.lastName").value("Gonzales"));
    }

}
