package com.test.gcp.service.impl;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.gcp.exception.ResourceNotFoundException;
import com.test.gcp.payload.DepartmentDTO;
import com.test.gcp.payload.EmployeeDTO;
import com.test.gcp.service.DepartmentService;
import com.test.gcp.service.EmployeeService;
import com.test.gcp.util.EmployeeValidation;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private static final Logger LOGGER = LogManager.getLogger(EmployeeServiceImpl.class);
	
	@Autowired
	private EmployeeValidation employeeValidation;
	
	@Override
	public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
		
		LOGGER.log(Level.INFO, () -> "createEmployee method starts ===>>> "+ employeeDTO);
		
		employeeValidation.validateAddEmployee(employeeDTO);
		String employeeId = String.valueOf(EMPLOYEES.size() + 1);
		employeeDTO.setEmployeeId(employeeId);
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
		} else {
			throw new ResourceNotFoundException("employeeId", employeeId);
		}
		LOGGER.log(Level.INFO, "getEmployeesById method ends ===>>> "+ presentEmployeeDTO);
		return presentEmployeeDTO;
	}

	@Override
	public EmployeeDTO updateEmployee(String employeeId, EmployeeDTO employeeDTO) {
		
		LOGGER.log(Level.INFO, () -> "updateEmployee method starts ===>>> "+ employeeId);
		
		EmployeeDTO employeeDTO2 = employeeValidation.validateUpdateEmployee(employeeDTO, employeeId);
		EMPLOYEES.remove(employeeDTO2);
		employeeDTO.setEmployeeId(employeeId);
		EMPLOYEES.add(employeeDTO);
		LOGGER.log(Level.INFO, () -> "updateEmployee method ends ===>>> "+ EMPLOYEES);
		return employeeDTO;
	}

	@Override
	public void deleteEmployee(String employeeId) {
		
		LOGGER.log(Level.INFO, () -> "deleteEmployee method starts ===>>> "+ employeeId);
		
		Optional<EmployeeDTO> employeeDTO = EMPLOYEES.stream().filter(e -> e.getEmployeeId().equals(employeeId)).findFirst();
		if(employeeDTO.isPresent()) {
			EMPLOYEES.remove(employeeDTO.get());
		} else {
			throw new ResourceNotFoundException("employeeId", employeeId);
		}
		LOGGER.log(Level.INFO, () -> "deleteEmployee method ends ===>>> "+ EMPLOYEES);
	}

	@Override
	public List<EmployeeDTO> getEmployeesByDepartmentId(String departmentId) {
		
		LOGGER.log(Level.INFO, () -> "getEmployeesByDepartmentId method starts ===>>> "+ departmentId);
		
		List<EmployeeDTO> employeeDTOs = null;
		Optional<DepartmentDTO> departmentDTO = DepartmentService.DEPARTMENTS.stream().filter(e -> e.getDepartmentId().equals(departmentId)).findFirst();
		if(departmentDTO.isPresent()) {
			employeeDTOs = EMPLOYEES.stream().filter(e -> e.getDepartmentId().equals(departmentId)).toList();
		} else {
			throw new ResourceNotFoundException("departmentId", departmentId);
		}
		
		LOGGER.log(Level.INFO, () -> "getEmployeesByDepartmentId method ends ===>>> "+ EMPLOYEES);
		
		return employeeDTOs;
	}
}