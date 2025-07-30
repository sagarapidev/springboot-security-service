package com.sagar.spring_security_explore.entity;

import com.sagar.spring_security_explore.constant.DatabaseTable;
import com.sagar.spring_security_explore.constant.ValidationMessage;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static com.sagar.spring_security_explore.constant.DatabaseTable.CUSTOMER;
import static com.sagar.spring_security_explore.constant.DatabaseTable.CUSTOMER_NAME;
import static com.sagar.spring_security_explore.constant.DatabaseTable.CUSTOMER_EMAIL;
import static com.sagar.spring_security_explore.constant.DatabaseTable.CUSTOMER_ROLE;
import static com.sagar.spring_security_explore.constant.DatabaseTable.CUSTOMER_HASH_PWD;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = CUSTOMER)
public class Customer extends BaseEntity {
    @NotBlank(message = ValidationMessage.NAME_REQUIRED)
    @Size(max = 100, message = ValidationMessage.NAME_MAX)
    @Column(name = CUSTOMER_NAME, nullable = false, length = 100)
    String name;

    @NotBlank(message = ValidationMessage.EMAIL_REQUIRED)
    @Email(message = ValidationMessage.EMAIL_VALID)
    @Size(max = 100, message = ValidationMessage.EMAIL_MAX)
    @Column(name = CUSTOMER_EMAIL, nullable = false, length = 100)
    String email;

    @NotBlank(message = ValidationMessage.ROLE_REQUIRED)
    @Size(max = 50, message = ValidationMessage.ROLE_MAX)
    @Column(name = CUSTOMER_ROLE, nullable = false, length = 50)
    String role;

    @NotBlank(message = ValidationMessage.PASSWORD_HASH_REQUIRED)
    @Size(max = 255, message = ValidationMessage.PASSWORD_HASH_MAX)
    @Column(name = CUSTOMER_HASH_PWD, nullable = false, length = 255)
    String hashPwd;
}
