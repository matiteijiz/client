package com.management.client.get;

import com.management.client.CustomerController;
import com.management.client.CustomerRepository;
import com.management.client.ResponseCustomerDto;
import com.management.client.create.CustomerMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetCustomerService {

    private CustomerRepository customerRepository;
    private CustomerMapper customerMapper;

    public GetCustomerService(CustomerRepository customerRepository, CustomerMapper customerMapper){
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    public List<ResponseCustomerDto> getAll() {
        return customerRepository.findAll()
                .stream()
                .map(customer -> customerMapper.toResponseCustomerDto(customer))
                .collect(Collectors.toList());
    }
}
