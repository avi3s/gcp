package com.test.gcp.util;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.test.gcp.exception.ResourceFoundException;
import com.test.gcp.exception.ResourceNotFoundException;
import com.test.gcp.payload.EmployeeDTO;
import com.test.gcp.service.EmployeeService;

@Component
public class EmployeeValidation {

	public void validateAddEmployee(EmployeeDTO employeeDTO) {

		Optional<EmployeeDTO> optionalEmployeeDTO = EmployeeService.EMPLOYEES.stream()
				.filter(e -> e.getEmail().equalsIgnoreCase(employeeDTO.getEmail())).findFirst();
		if (optionalEmployeeDTO.isPresent()) {
			throw new ResourceFoundException("Email", employeeDTO.getEmail());
		}

	}

	public EmployeeDTO validateUpdateEmployee(EmployeeDTO employeeDTO, String employeeId) {

		Optional<EmployeeDTO> presentEmployeeDTO = EmployeeService.EMPLOYEES.stream()
				.filter(e -> e.getEmployeeId().equals(employeeId)).findFirst();
		if (!presentEmployeeDTO.isPresent()) {
			throw new ResourceNotFoundException("employeeId", employeeId);
		} else {
			Optional<EmployeeDTO> optionalEmployeeDTO = EmployeeService.EMPLOYEES.stream()
					.filter(e -> e.getEmail().equalsIgnoreCase(employeeDTO.getEmail()) && !e.getEmployeeId().equals(employeeId)).findFirst();
			if (optionalEmployeeDTO.isPresent()) {
				throw new ResourceFoundException("Email", employeeDTO.getEmail());
			}
		}
		return presentEmployeeDTO.get();
	}
}