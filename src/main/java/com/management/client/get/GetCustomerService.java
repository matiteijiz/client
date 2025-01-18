package com.management.client.get;

import com.management.client.CustomerController;
import com.management.client.CustomerRepository;
import com.management.client.ResponseCustomerDto;
import com.management.client.create.CustomerMapper;
import com.management.client.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
        var customers = customerRepository.findAll();
        Optional.of(customers)
                .filter(customers1 -> !customers1.isEmpty())
                .orElseThrow(ResourceNotFoundException::new);
        return customers.stream()
                .map(customer -> customerMapper.toResponseCustomerDto(customer))
                .collect(Collectors.toList());
    }
}
