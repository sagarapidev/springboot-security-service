package com.sagar.spring_security_explore.service.impl;

import com.sagar.spring_security_explore.dto.request.CustomerPostRequest;
import com.sagar.spring_security_explore.dto.response.CustomerPostResponse;
import com.sagar.spring_security_explore.dto.response.CustomerGetResponseDto;
import com.sagar.spring_security_explore.entity.Customer;
import com.sagar.spring_security_explore.repository.CustomerRepository;
import com.sagar.spring_security_explore.service.ICustomerService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class CustomerServiceImpl implements ICustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private static CustomerGetResponseDto fromEntity(Customer customer) {
        return CustomerGetResponseDto.fromEntity(customer);
    }

    @Transactional
    @Override
    public CustomerPostResponse createCustomer(CustomerPostRequest request) {
        Customer customer = CustomerPostRequest.toEntity(request, passwordEncoder);
        Customer saved = customerRepository.save(customer);
        return CustomerPostResponse.fromEntity(saved);
    }

    @Override
    public CustomerGetResponseDto getCustomerById(Long id) {
        return customerRepository.findById(id)
                .map(CustomerServiceImpl::fromEntity)
                .orElseThrow(() -> new com.sagar.spring_security_explore.exception.NotFoundException("Customer not found with id: " + id));
    }

    @Override
    public List<CustomerGetResponseDto> getAllCustomers() {
        return customerRepository.findAll().stream()
                .map(CustomerServiceImpl::fromEntity)
                .collect(Collectors.toList());
    }
}
