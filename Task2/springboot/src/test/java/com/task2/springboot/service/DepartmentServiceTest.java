package com.task2.springboot.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.task2.springboot.entity.Department;
import com.task2.springboot.repository.DepartmentRepository;
import com.task2.springboot.repository.service.DepartmentService;

@SpringBootTest
public class DepartmentServiceTest {

	@Autowired
	private DepartmentService departmentService;
	
	@MockBean
	private DepartmentRepository departmentRepository;
	
	@BeforeEach
	void setUp() {
		Department department = Department.builder()
				.departmentName("cse")
				.departmentAddress("chennai")
				.departmentCode("53")
				.departmentId(1L)
				.build();
		
		Mockito.when(departmentRepository.findBydepartmentNameIgnoreCase("cse")).thenReturn(department);
		
	}
	
	@Test
	@DisplayName("Get Data based on Valid Department Name")
	public void whenVaildDepartmentName_thenDepartmentShouldFound() {
		String departmentName = "cse";
		Department found = departmentService.fetchDepartmentByName(departmentName); 
		
		assertEquals(departmentName, found.getDepartmentName());
	}
	
}
