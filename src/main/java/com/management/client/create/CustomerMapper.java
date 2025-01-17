package com.management.client.create;

import com.management.client.Customer;
import com.management.client.ResponseCustomerDto;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {
    public ResponseCustomerDto toResponseCustomerDto(Customer savedCustomer) {
        return new ResponseCustomerDto(
                savedCustomer.getId(),
                savedCustomer.getName(),
                savedCustomer.getLastName(),
                savedCustomer.getDateOfBirth()
        );
    }
}
