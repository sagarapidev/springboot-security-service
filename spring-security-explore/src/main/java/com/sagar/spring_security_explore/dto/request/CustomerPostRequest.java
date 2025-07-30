package com.sagar.spring_security_explore.dto.request;

import com.sagar.spring_security_explore.constant.ValidationMessage;
import com.sagar.spring_security_explore.entity.Customer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerPostRequest {
    @NotBlank(message = ValidationMessage.NAME_REQUIRED)
    @Size(max = 100, message = ValidationMessage.NAME_MAX)
    String name;

    @NotBlank(message = ValidationMessage.EMAIL_REQUIRED)
    @Email(message = ValidationMessage.EMAIL_VALID)
    @Size(max = 100, message = ValidationMessage.EMAIL_MAX)
    String email;

    @NotBlank(message = ValidationMessage.ROLE_REQUIRED)
    @Size(max = 50, message = ValidationMessage.ROLE_MAX)
    String role;

    public static Customer toEntity(CustomerPostRequest dto, BCryptPasswordEncoder encoder) {
        Customer customer = new Customer();
        customer.setName(dto.getName());
        customer.setEmail(dto.getEmail());
        customer.setRole(dto.getRole());
        customer.setHashPwd(encoder.encode(dto.getEmail())); // Replace with password in real app
        return customer;
    }
}
