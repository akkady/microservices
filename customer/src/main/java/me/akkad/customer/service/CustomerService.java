package me.akkad.customer.service;

import me.akkad.customer.doa.Customer;
import me.akkad.customer.dto.CustomerRegisterRequest;
import me.akkad.customer.dto.FraudCheckResponse;
import me.akkad.customer.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public record CustomerService (CustomerRepository customerRepository,
                               RestTemplate restTemplate
){
    public void register(CustomerRegisterRequest registerRequest) {
        Customer customer = Customer.builder()
                .firstname(registerRequest.firstname())
                .lastname(registerRequest.lastname())
                .email(registerRequest.email())
                .build();
        customerRepository.saveAndFlush(customer);
        FraudCheckResponse fraudCheckResponse = restTemplate.getForObject(
                "http://localhost:8081/api/v1/fraud-check/{customerId}",
                FraudCheckResponse.class,
                customer.getId()
        );

        if (fraudCheckResponse.isFraudster()) {
            throw new IllegalStateException("fraudster");
        }
    }
}
