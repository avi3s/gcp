package com.test.gcp.payload;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class EmployeeDTO {

	private String employeeId;
	
    // name should not be null or empty
    @NotEmpty(message = "Name should not be null or empty")
    private String name;
    
    private String password;

    // email should not be null or empty
    // email field validation
    @NotEmpty(message = "Email should not be null or empty")
    @Email
    private String email;

    // comment body should not be bull or empty
    // Comment body must be minimum 10 characters
    @NotEmpty
    @Size(min = 10, message = "Address must be minimum 10 characters")
    private String address;
    
    private Set<Role> roles;
}