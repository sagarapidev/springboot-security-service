package com.sagar.spring_security_explore.controller;

import com.sagar.spring_security_explore.constant.SwaggerConstant;
import com.sagar.spring_security_explore.dto.request.CustomerPostRequest;
import com.sagar.spring_security_explore.dto.response.CustomerGetResponseDto;
import com.sagar.spring_security_explore.dto.response.CustomerPostResponse;
import com.sagar.spring_security_explore.dto.response.SecurityServiceExplorerResponse;
import com.sagar.spring_security_explore.service.ICustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@Tag(name = SwaggerConstant.TAG_CUSTOMER, description = SwaggerConstant.TAG_CUSTOMER_DESC)
@RequiredArgsConstructor
public class CustomerController {
    private final ICustomerService customerService;

    @Operation(summary = SwaggerConstant.CREATE_CUSTOMER_SUMMARY, description = SwaggerConstant.CREATE_CUSTOMER_DESC)
    @ApiResponses({
        @ApiResponse(responseCode = SwaggerConstant.HTTP_200, description = SwaggerConstant.CREATE_CUSTOMER_200)
    })
    @PostMapping
    public ResponseEntity<SecurityServiceExplorerResponse<CustomerPostResponse>> createCustomer(
            @RequestBody
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = SwaggerConstant.CREATE_CUSTOMER_REQUEST_DESC)
            CustomerPostRequest request) {
        CustomerPostResponse response = customerService.createCustomer(request);
        return ResponseEntity.ok(new SecurityServiceExplorerResponse<>(true, SwaggerConstant.CREATE_CUSTOMER_200, response));
    }

    @Operation(summary = SwaggerConstant.GET_CUSTOMER_BY_ID_SUMMARY, description = SwaggerConstant.GET_CUSTOMER_BY_ID_DESC)
    @ApiResponses({
        @ApiResponse(responseCode = SwaggerConstant.HTTP_200, description = SwaggerConstant.GET_CUSTOMER_BY_ID_200),
        @ApiResponse(responseCode = "404", description = SwaggerConstant.GET_CUSTOMER_BY_ID_404)
    })
    @GetMapping("/{id}")
    public ResponseEntity<SecurityServiceExplorerResponse<CustomerGetResponseDto>> getCustomerById(@PathVariable Long id) {
        CustomerGetResponseDto response = customerService.getCustomerById(id);
        return ResponseEntity.ok(new SecurityServiceExplorerResponse<>(true, SwaggerConstant.GET_CUSTOMER_BY_ID_200, response));
    }

    @Operation(summary = SwaggerConstant.GET_ALL_CUSTOMERS_SUMMARY, description = SwaggerConstant.GET_ALL_CUSTOMERS_DESC)
    @ApiResponses({
        @ApiResponse(responseCode = SwaggerConstant.HTTP_200, description = SwaggerConstant.GET_ALL_CUSTOMERS_200)
    })
    @GetMapping
    public ResponseEntity<SecurityServiceExplorerResponse<List<CustomerGetResponseDto>>> getAllCustomers() {
        List<CustomerGetResponseDto> response = customerService.getAllCustomers();
        return ResponseEntity.ok(new SecurityServiceExplorerResponse<>(true, SwaggerConstant.GET_ALL_CUSTOMERS_200, response));
    }
}
