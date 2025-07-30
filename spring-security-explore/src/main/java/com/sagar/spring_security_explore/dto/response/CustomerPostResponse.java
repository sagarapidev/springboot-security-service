package com.sagar.spring_security_explore.dto.response;

import com.sagar.spring_security_explore.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerPostResponse {
    Long id;

    public static CustomerPostResponse fromEntity(Customer customer) {
        return new CustomerPostResponse(customer.getId());
    }
}
