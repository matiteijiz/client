package com.management.client;

import com.management.client.create.CreateCustomerService;
import com.management.client.create.CustomerDto;
import com.management.client.get.GetCustomerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CreateCustomerService createCustomerService;
    private final GetCustomerService getCustomerService;

    public CustomerController(CreateCustomerService createCustomerService, GetCustomerService getCustomerService){
        this.createCustomerService = createCustomerService;
        this.getCustomerService = getCustomerService;
    }

    @PostMapping("/save")
    public ResponseEntity<ResponseCustomerDto> save(@RequestBody @Valid CustomerDto customerDto){
        ResponseCustomerDto customer = createCustomerService.save(customerDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(customer);
    }

    @GetMapping("/get")
    public List<ResponseCustomerDto> getAll(){
        return getCustomerService.getAll();
    }

}
