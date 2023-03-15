package com.test.gcp.service;

import java.util.LinkedList;
import java.util.List;

import com.test.gcp.payload.DepartmentDTO;

public interface DepartmentService {

	public static final List<DepartmentDTO> DEPARTMENTS = new LinkedList<>();

	public DepartmentDTO createDepartment(DepartmentDTO departmentDTO);

	public List<DepartmentDTO> getDepartments();

	public DepartmentDTO getDepartmentsById(String departmentId);

	public DepartmentDTO updateDepartment(String departmentId, DepartmentDTO departmentDTO);

	public void deleteDepartment(String departmentId);
}