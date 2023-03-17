package com.test.gcp.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.test.gcp.exception.ResourceNotFoundException;
import com.test.gcp.payload.DepartmentDTO;
import com.test.gcp.service.DepartmentService;
import com.test.gcp.util.DepartmentValidation;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceImplTest {

	@InjectMocks
	private DepartmentServiceImpl departmentServiceImpl;
	
	@Mock
	private DepartmentValidation departmentValidation;
	
	DepartmentDTO departmentDTO;
	DepartmentDTO departmentDTO2;
	List<DepartmentDTO> departmentDTOs;
	
	@BeforeEach
	void setUp() throws Exception {
		departmentDTO = new DepartmentDTO();
		departmentDTO.setDepartmentId("1");
		departmentDTO.setDepartmentName("FS");
		
		departmentDTO2 = new DepartmentDTO();
		departmentDTO2.setDepartmentId("2");
		departmentDTO2.setDepartmentName("NON-FS");
		
		departmentDTOs = new ArrayList<>();
		departmentDTOs.add(departmentDTO2);
		departmentDTOs.add(departmentDTO);
	}

	@Test
	void testCreateDepartment() {
		Mockito.doNothing().when(departmentValidation).validateAddDepartment(departmentDTO);
		DepartmentDTO actualResponse = departmentServiceImpl.createDepartment(departmentDTO);
		assertEquals(String.valueOf(DepartmentService.DEPARTMENTS.size()), actualResponse.getDepartmentId());
	}

	@Test
	void testGetDepartments() {
		DepartmentService.DEPARTMENTS.addAll(departmentDTOs);
		assertEquals(DepartmentService.DEPARTMENTS.size(), departmentServiceImpl.getDepartments().size());
	}

	@Test
	void testGetDepartmentsById() {
		DepartmentService.DEPARTMENTS.addAll(departmentDTOs);
		DepartmentDTO actualResponse = departmentServiceImpl.getDepartmentsById("1");
		assertEquals("1", actualResponse.getDepartmentId());
	}
	
	@Test
	void testGetDepartmentsById_ResourceFoundException() {
		DepartmentService.DEPARTMENTS.addAll(departmentDTOs);
		Throwable throwable = assertThrows(ResourceNotFoundException.class, () -> departmentServiceImpl.getDepartmentsById("20"));
        assertEquals("departmentId not found with : '20'", throwable.getMessage());
	}

	@Test
	void testUpdateDepartment() {
		DepartmentService.DEPARTMENTS.addAll(departmentDTOs);
		DepartmentDTO actualResponse = departmentServiceImpl.updateDepartment("1", departmentDTO);
		assertEquals("1", actualResponse.getDepartmentId());
	}

	@Test
	void testDeleteDepartment() {
		DepartmentService.DEPARTMENTS.addAll(departmentDTOs);
		departmentServiceImpl.deleteDepartment("2");
		//Mockito.verify(DepartmentService.DEPARTMENTS.remove(departmentDTO), Mockito.times(1));
	}
	
	@Test
	void testDeleteDepartment_ResourceFoundException() {
		DepartmentService.DEPARTMENTS.addAll(departmentDTOs);
		Throwable throwable = assertThrows(ResourceNotFoundException.class, () -> departmentServiceImpl.deleteDepartment("20"));
        assertEquals("departmentId not found with : '20'", throwable.getMessage());
	}
}