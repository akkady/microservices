package me.akkad.customer.controller;

import lombok.extern.slf4j.Slf4j;
import me.akkad.customer.dto.CustomerRegisterRequest;
import me.akkad.customer.service.CustomerService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/customers")
public record CustomerController(CustomerService customerService) {

    @PostMapping
    public void register(@RequestBody CustomerRegisterRequest customerRegisterRequest) {
        log.info("Request to register customer : {}" ,customerRegisterRequest);
        customerService.register(customerRegisterRequest);
    }
}
