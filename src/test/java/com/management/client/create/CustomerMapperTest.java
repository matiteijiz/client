package com.management.client.create;

import com.management.client.Customer;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerMapperTest {

    private CustomerMapper customerMapper = new CustomerMapper();

    @Test
    public void shouldReturnResponseCustomerDtoSuccessfully(){
        var customer = new Customer();
        customer.setId(1L);
        customer.setName("Juan");
        customer.setLastName("Gonzales");
        customer.setDateOfBirth(LocalDate.of(1993,10,30));
        var responseCustomer = customerMapper.toResponseCustomerDto(customer);
        assertEquals(1L, responseCustomer.getId());
        assertEquals("Juan", responseCustomer.getName());
        assertEquals("Gonzales", responseCustomer.getLastName());
        assertEquals(LocalDate.of(1993,10,30), responseCustomer.getDateOfBirth());
        assertEquals(31, responseCustomer.getAge());
    }

}
