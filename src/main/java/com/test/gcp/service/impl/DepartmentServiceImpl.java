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
import com.test.gcp.service.DepartmentService;
import com.test.gcp.util.DepartmentValidation;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	private static final Logger LOGGER = LogManager.getLogger(DepartmentServiceImpl.class);
	
	@Autowired
	private DepartmentValidation departmentValidation;
	
	@Override
	public DepartmentDTO createDepartment(DepartmentDTO departmentDTO) {
		
		LOGGER.log(Level.INFO, () -> "createDepartment method starts ===>>> "+ departmentDTO);
		
		departmentValidation.validateAddDepartment(departmentDTO);
		String departmentId = String.valueOf(DEPARTMENTS.size() + 1);
		departmentDTO.setDepartmentId(departmentId);
		DEPARTMENTS.add(departmentDTO);
		
		LOGGER.log(Level.INFO, () -> "createDepartment method ends ===>>> "+ departmentDTO);
		return departmentDTO;
	}

	@Override
	public List<DepartmentDTO> getDepartments() {
		LOGGER.log(Level.INFO, () -> "getDepartments method");
		return DEPARTMENTS;
	}

	@Override
	public DepartmentDTO getDepartmentsById(String departmentId) {
		
		LOGGER.log(Level.INFO, () -> "getDepartmentsById method starts ===>>> "+ departmentId);
		DepartmentDTO presentDepartmentDTO = null;
		Optional<DepartmentDTO> departmentDTO = DEPARTMENTS.stream().filter(e -> e.getDepartmentId().equals(departmentId)).findFirst();
		if(departmentDTO.isPresent()) {
			presentDepartmentDTO = departmentDTO.get();
		} else {
			throw new ResourceNotFoundException("departmentId", departmentId);
		}
		LOGGER.log(Level.INFO, "getDepartmentsById method ends ===>>> "+ presentDepartmentDTO);
		return presentDepartmentDTO;
	}

	@Override
	public DepartmentDTO updateDepartment(String departmentId, DepartmentDTO departmentDTO) {
		
		LOGGER.log(Level.INFO, () -> "updateDepartment method starts ===>>> "+ departmentId);
		
		DepartmentDTO departmentDTO2 = departmentValidation.validateUpdateDepartment(departmentDTO, departmentId);
		DEPARTMENTS.remove(departmentDTO2);
		departmentDTO.setDepartmentId(departmentId);
		DEPARTMENTS.add(departmentDTO);
		LOGGER.log(Level.INFO, () -> "updateDepartment method ends ===>>> "+ DEPARTMENTS);
		return departmentDTO;
	}

	@Override
	public void deleteDepartment(String departmentId) {
		
		LOGGER.log(Level.INFO, () -> "deleteDepartment method starts ===>>> "+ departmentId);
		
		Optional<DepartmentDTO> departmentDTO = DEPARTMENTS.stream().filter(e -> e.getDepartmentId().equals(departmentId)).findFirst();
		if(departmentDTO.isPresent()) {
			DEPARTMENTS.remove(departmentDTO.get());
		} else {
			throw new ResourceNotFoundException("departmentId", departmentId);
		}
		LOGGER.log(Level.INFO, () -> "deleteDepartment method ends ===>>> "+ DEPARTMENTS);
	}
}