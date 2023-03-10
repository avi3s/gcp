package com.test.gcp.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(Include.NON_NULL)
public class EmployeeDTO {

	private String employeeId;
	
    @NotEmpty(message = "{phone.number.null.message}")
    @Pattern(regexp="(^$|[0-9]{10})", message = "{phone.number.invalid.message}")
    private String phoneNumber;

    @NotBlank(message = "{name.null.message}")
    private String name;
    
    @NotBlank(message = "{email.null.message}")
    @Email(message = "{email.format.message}")
    private String email;

    @Size(min = 10, message = "{address.null.message}", max = 50)
    private String address;
}