package me.akkad.customer.service;

import me.akkad.clients.fraud.FraudCheckResponse;
import me.akkad.clients.fraud.FraudClient;
import me.akkad.customer.doa.Customer;
import me.akkad.customer.dto.CustomerRegisterRequest;
import me.akkad.customer.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public record CustomerService(CustomerRepository customerRepository,
                              FraudClient fraudClient
) {
    public void register(CustomerRegisterRequest registerRequest) {
        Customer customer = Customer.builder()
                .firstname(registerRequest.firstname())
                .lastname(registerRequest.lastname())
                .email(registerRequest.email())
                .build();
        customerRepository.saveAndFlush(customer);
        FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(customer.getId());

        if (fraudCheckResponse.isFraudster()) {
            throw new IllegalStateException("fraudster");
        }
    }
}
