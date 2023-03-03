package com.test.gcp.service.impl;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.test.gcp.payload.EmployeeDTO;
import com.test.gcp.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private static final Logger LOGGER = LogManager.getLogger(EmployeeServiceImpl.class);
	
	@Override
	public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
		
		LOGGER.log(Level.INFO, () -> "createEmployee method starts ===>>> "+ employeeDTO);
		
		String emplyeeId = String.valueOf(EMPLOYEES.size() + 1);
		employeeDTO.setEmployeeId(emplyeeId);
		EMPLOYEES.add(employeeDTO);
		
		LOGGER.log(Level.INFO, () -> "createEmployee method ends ===>>> "+ employeeDTO);
		return employeeDTO;
	}

	@Override
	public List<EmployeeDTO> getEmployees() {
		LOGGER.log(Level.INFO, () -> "getEmployees method");
		return EMPLOYEES;
	}

	@Override
	public EmployeeDTO getEmployeesById(String employeeId) {
		
		LOGGER.log(Level.INFO, () -> "getEmployeesById method starts ===>>> "+ employeeId);
		EmployeeDTO presentEmployeeDTO = null;
		Optional<EmployeeDTO> employeeDTO = EMPLOYEES.stream().filter(e -> e.getEmployeeId().equals(employeeId)).findFirst();
		if(employeeDTO.isPresent()) {
			presentEmployeeDTO = employeeDTO.get();
		}
		LOGGER.log(Level.INFO, "getEmployeesById method ends ===>>> "+ presentEmployeeDTO);
		return presentEmployeeDTO;
	}

	@Override
	public EmployeeDTO updateEmployee(String employeeId, EmployeeDTO employeeDTO) {
		
		LOGGER.log(Level.INFO, () -> "updateEmployee method starts ===>>> "+ employeeId);
		
		Optional<EmployeeDTO> presentEmployeeDTO = EMPLOYEES.stream().filter(e -> e.getEmployeeId().equals(employeeId)).findFirst();
		if(presentEmployeeDTO.isPresent()) {
			EMPLOYEES.remove(presentEmployeeDTO.get());
			employeeDTO.setEmployeeId(employeeId);
			EMPLOYEES.add(employeeDTO);
		}
		LOGGER.log(Level.INFO, () -> "updateEmployee method ends ===>>> "+ EMPLOYEES);
		return employeeDTO;
	}

	@Override
	public void deleteEmployee(String employeeId) {
		
		LOGGER.log(Level.INFO, () -> "deleteEmployee method starts ===>>> "+ employeeId);
		
		Optional<EmployeeDTO> employeeDTO = EMPLOYEES.stream().filter(e -> e.getEmployeeId().equals(employeeId)).findFirst();
		if(employeeDTO.isPresent()) {
			EMPLOYEES.remove(employeeDTO.get());
		}
		LOGGER.log(Level.INFO, () -> "deleteEmployee method ends ===>>> "+ EMPLOYEES);
	}
}