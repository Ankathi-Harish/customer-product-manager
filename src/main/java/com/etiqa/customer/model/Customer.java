package com.etiqa.customer.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "First name cannot be blank")
    @Size(max = 50, message = "First name cannot be longer than 50 characters")
    private String firstName;

    @NotBlank(message = "Last name cannot be blank")
    @Size(max = 50, message = "Last name cannot be longer than 50 characters")
    private String lastName;

    @Email(message = "Office email should be a valid email address")
    private String emailOffice;

    @Email(message = "Personal email should be a valid email address")
    private String emailPersonal;

    @Size(max = 200, message = "Family members description cannot be longer than 200 characters")
    private String familyMembers;

    @NotBlank(message = "Date of birth cannot be blank")
    private String dateOfBirth;

    @Size(max = 255, message = "Address cannot be longer than 255 characters")
    private String address;
}
