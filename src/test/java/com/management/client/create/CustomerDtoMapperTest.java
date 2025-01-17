package com.management.client.create;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomerDtoMapperTest {

    private CustomerDtoMapper customerDtoMapper = new CustomerDtoMapper();

    @Test
    public void shouldReturnCustomerSuccessfully(){
        var customerDto = new CustomerDto();
        customerDto.setName("Juan");
        customerDto.setLastName("Gonzales");
        customerDto.setDateOfBirth(LocalDate.of(1993,10,30));
        var customer = customerDtoMapper.toCustomer(customerDto);
        assertEquals("Juan", customer.getName());
        assertEquals("Gonzales", customer.getLastName());
        assertEquals(LocalDate.of(1993,10,30), customer.getDateOfBirth());
    }

}
