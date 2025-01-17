package com.management.client.create;

import com.management.client.Customer;
import com.management.client.CustomerRepository;
import com.management.client.ResponseCustomerDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class CreateCustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CustomerDtoMapper customerDtoMapper;

    @Mock
    private CustomerMapper customerMapper;

    @InjectMocks
    private CreateCustomerService createCustomerService;

    @Test
    public void shouldCreateCustomerSuccessfully(){
        var mockCustomerDto = new CustomerDto();
        mockCustomerDto.setName("Juan");
        mockCustomerDto.setLastName("Gonzales");
        mockCustomerDto.setDateOfBirth(LocalDate.of(1993,10,30));
        var mockCustomer = new Customer();
        mockCustomer.setName("Juan");
        mockCustomer.setLastName("Gonzales");
        mockCustomer.setId(1L);
        mockCustomer.setDateOfBirth(LocalDate.of(1993,10,30));
        var mockResponseCustomerDto = new ResponseCustomerDto(
                1L,
                "Juan",
                "Gonzales",
                LocalDate.of(1993,10,30)
        );
        Mockito.when(customerDtoMapper.toCustomer(mockCustomerDto)).thenReturn(mockCustomer);
        Mockito.when(customerRepository.save(mockCustomer)).thenReturn(mockCustomer);
        Mockito.when(customerMapper.toResponseCustomerDto(mockCustomer)).thenReturn(mockResponseCustomerDto);
        var customer = createCustomerService.save(mockCustomerDto);
        assertNotNull(customer);
        assertEquals(1L, customer.getId());
        assertEquals("Juan", customer.getName());
        assertEquals("Gonzales", customer.getLastName());
        assertEquals(LocalDate.of(1993,10,30), customer.getDateOfBirth());
    }

}
