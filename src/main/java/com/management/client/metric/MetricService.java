package com.management.client.metric;

import com.management.client.ResponseCustomerDto;
import com.management.client.get.GetCustomerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MetricService {

    private final GetCustomerService getCustomerService;


    public MetricService(GetCustomerService getCustomerService) {
        this.getCustomerService = getCustomerService;
    }


    public Metric get() {
        var metric = new Metric();
        List<ResponseCustomerDto> customers = getCustomerService.getAll();
        var mean = calculateMean(customers);
        metric.setAgeMean(mean);
        metric.setAgeStandardDeviation(calculateStandarVariaton(customers, mean));
        return metric;
    }

    private Double calculateStandarVariaton(List<ResponseCustomerDto> customers, Double mean) {
        return customers.stream()
                .mapToDouble(customer -> Math.pow(customer.getAge() - mean, 2))
                .average()
                .getAsDouble();
    }

    private Double calculateMean(List<ResponseCustomerDto> customers) {
        return customers.stream()
                .mapToDouble(ResponseCustomerDto::getAge)
                .average()
                .getAsDouble();
    }
}
