package com.management.client.create;

import com.management.client.Customer;
import com.management.client.CustomerRepository;
import com.management.client.ResponseCustomerDto;
import org.springframework.stereotype.Service;

@Service
public class CreateCustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerDtoMapper customerDtoMapper;
    private final CustomerMapper customerMapper;

    public CreateCustomerService(CustomerRepository customerRepository, CustomerDtoMapper customerDtoMapper, CustomerMapper customerMapper){
        this.customerRepository = customerRepository;
        this.customerDtoMapper = customerDtoMapper;
        this.customerMapper = customerMapper;
    }

    public ResponseCustomerDto save(CustomerDto customerDto){
        var customer = customerDtoMapper.toCustomer(customerDto);
        var savedCustomer = customerRepository.save(customer);
        return customerMapper.toResponseCustomerDto(savedCustomer);
    }

}
