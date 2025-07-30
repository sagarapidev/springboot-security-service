package com.sagar.spring_security_explore.dto.response;

import com.sagar.spring_security_explore.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerGetResponseDto {
    Long id;
    String name;
    String email;
    String role;

    public static CustomerGetResponseDto fromEntity(Customer customer) {
        if (customer == null) return null;
        return new CustomerGetResponseDto(
            customer.getId(),
            customer.getName(),
            customer.getEmail(),
            customer.getRole()
        );
    }
}
