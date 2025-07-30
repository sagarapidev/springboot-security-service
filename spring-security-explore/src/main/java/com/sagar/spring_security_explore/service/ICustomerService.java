package com.sagar.spring_security_explore.service;

import com.sagar.spring_security_explore.dto.request.CustomerPostRequest;
import com.sagar.spring_security_explore.dto.response.CustomerGetResponseDto;
import com.sagar.spring_security_explore.dto.response.CustomerPostResponse;

import java.util.List;

public interface ICustomerService {
    CustomerPostResponse createCustomer(CustomerPostRequest request);
    CustomerGetResponseDto getCustomerById(Long id);
    List<CustomerGetResponseDto> getAllCustomers();
}
