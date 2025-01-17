package com.management.client;

import com.management.client.create.CreateCustomerService;
import com.management.client.create.CustomerDto;
import com.management.client.get.GetCustomerService;
import com.management.client.metric.Metric;
import com.management.client.metric.MetricService;
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
    private final MetricService metricService;

    public CustomerController(CreateCustomerService createCustomerService, GetCustomerService getCustomerService, MetricService metricService){
        this.createCustomerService = createCustomerService;
        this.getCustomerService = getCustomerService;
        this.metricService = metricService;
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

    @GetMapping("/metric")
    public ResponseEntity<Metric> calculate(){
        var metrics = metricService.get();
        return ResponseEntity.status(HttpStatus.OK).body(metrics);
    }

}
