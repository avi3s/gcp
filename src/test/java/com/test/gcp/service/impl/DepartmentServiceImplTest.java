package com.test.gcp.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.test.gcp.payload.DepartmentDTO;
import com.test.gcp.service.DepartmentService;
import com.test.gcp.util.DepartmentValidation;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceImplTest {

	@InjectMocks
	private DepartmentServiceImpl departmentService;
	
//	@InjectMocks
//	private DepartmentServiceImpl departmentServiceImpl;
	
	@Mock
	private DepartmentValidation departmentValidation;
	
//	@Mock
	DepartmentDTO departmentDTO;
//	@Mock
	DepartmentDTO departmentDTO2;
//	@Mock
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
//		DepartmentService.DEPARTMENTS.addAll(departmentDTOs);
	}

	@Test
	void testCreateDepartment() {
		//ReflectionTestUtils.setField(departmentService, "validateAddDepartment", departmentValidation);
		//Mockito.spy(departmentServiceImpl);
		Mockito.doNothing().when(departmentValidation).validateAddDepartment(departmentDTO);
		DepartmentDTO actualResponse = departmentService.createDepartment(departmentDTO);
		assertEquals(DepartmentService.DEPARTMENTS.size(), actualResponse.getDepartmentId());
	}

	@Test
	void testGetDepartments() {
		DepartmentService.DEPARTMENTS.addAll(departmentDTOs);
//		Mockito.spy(DepartmentService.class);
		//Mockito.when(DepartmentService.DEPARTMENTS).thenReturn(departmentDTOs);
		//Mockito.when(departmentService.getDepartments()).thenReturn(departmentDTOs);
		assertEquals(departmentDTOs, departmentService.getDepartments());
	}

	@Test
	void testGetDepartmentsById() {
		DepartmentService.DEPARTMENTS.addAll(departmentDTOs);
		//Mockito.when(departmentService.getDepartmentsById("1")).thenReturn(departmentDTO);
		DepartmentDTO actualResponse = departmentService.getDepartmentsById("1");
		assertEquals("1", actualResponse.getDepartmentId());
	}

	@Test
	void testUpdateDepartment() {
		DepartmentService.DEPARTMENTS.addAll(departmentDTOs);
		//Mockito.doCallRealMethod().when(departmentValidation).validateUpdateDepartment(departmentDTO, "1");
		//Mockito.when(departmentService.updateDepartment("1", departmentDTO)).thenReturn(departmentDTO);
		DepartmentDTO actualResponse = departmentService.updateDepartment("1", departmentDTO);
		assertEquals("1", actualResponse.getDepartmentId());
	}

	@Test
	void testDeleteDepartment() {
		DepartmentService.DEPARTMENTS.addAll(departmentDTOs);
////		Mockito.doCallRealMethod().when(departmentService).deleteDepartment(Mockito.anyString());
//		// Making Original Method Call
//		departmentService.deleteDepartment("1");
//		// Checking Result
//		Mockito.mock(DepartmentService.DEPARTMENTS.getClass());
		departmentService.deleteDepartment("2");
		//Mockito.verify(DepartmentService.DEPARTMENTS.remove(departmentDTO), Mockito.times(1));
	}
}