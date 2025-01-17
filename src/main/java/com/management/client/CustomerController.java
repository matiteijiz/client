package com.management.client;

import com.management.client.create.CreateCustomerService;
import com.management.client.create.CustomerDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CreateCustomerService createCustomerService;

    public CustomerController(CreateCustomerService createCustomerService){
        this.createCustomerService = createCustomerService;
    }

    @PostMapping("/save")
    public ResponseEntity<ResponseCustomerDto> save(@RequestBody @Valid CustomerDto customerDto){
        ResponseCustomerDto customer = createCustomerService.save(customerDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(customer);
    }

}
