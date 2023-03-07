package com.test.gcp.service;

import java.util.LinkedList;
import java.util.List;

import com.test.gcp.payload.AdminDTO;
import com.test.gcp.payload.EmployeeDTO;

public interface EmployeeService {

	public static final List<EmployeeDTO> EMPLOYEES = new LinkedList<>();
	public static final List<AdminDTO> ADMINS = new LinkedList<>();

	public EmployeeDTO createEmployee(EmployeeDTO employeeDTO);

	public List<EmployeeDTO> getEmployees();

	public EmployeeDTO getEmployeesById(String employeeId);

	public EmployeeDTO updateEmployee(String employeeId, EmployeeDTO employeeDTO);

	public void deleteEmployee(String employeeId);
}