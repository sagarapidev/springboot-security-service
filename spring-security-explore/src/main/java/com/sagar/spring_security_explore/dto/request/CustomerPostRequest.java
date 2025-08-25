package com.sagar.spring_security_explore.dto.request;

import com.sagar.spring_security_explore.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.sagar.spring_security_explore.constant.ValidationMessage.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerPostRequest {
    @NotBlank(message = NAME_REQUIRED)
    @Size(max = 100, message = NAME_MAX)
    String name;

    @NotBlank(message = EMAIL_REQUIRED)
    @Email(message = EMAIL_VALID)
    @Size(max = 100, message = EMAIL_MAX)
    String email;

    @NotBlank(message = ROLE_REQUIRED)
    @Size(max = 50, message = ROLE_MAX)
    String role;

    @NotBlank(message = PASSWORD_HASH_REQUIRED)
    @Size(max = 100, message = PASSWORD_HASH_MAX)
    String password; // This should be the raw password, not the hash

    public static Customer toEntity(CustomerPostRequest dto, PasswordEncoder encoder) {
        Customer customer = new Customer();
        customer.setName(dto.getName());
        customer.setEmail(dto.getEmail());
        customer.setRole(dto.getRole());
        customer.setHashPwd(encoder.encode(dto.password)); // Replace with password in real app
        return customer;
    }
}
