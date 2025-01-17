package com.management.client.create;

import com.management.client.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerDtoMapper {

    public Customer toCustomer(CustomerDto customerDto) {
        var customer = new Customer();
        customer.setName(customerDto.getName());
        customer.setLastName(customerDto.getLastName());
        customer.setDateOfBirth(customerDto.getDateOfBirth());
        return customer;
    }

}
