package com.task2.springboot.controller;

import java.util.List;

import org.slf4j.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.task2.springboot.entity.Department;
import com.task2.springboot.error.DepartmentNotFoundException;
import com.task2.springboot.repository.service.DepartmentService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PutMapping;


@RestController
public class DepartmentController {
	
	@Autowired
	private DepartmentService departmentService;
	

	private final Logger LOGGER= LoggerFactory.getLogger( DepartmentController.class);
	
	@PostMapping("/departments")
	public Department saveDepartment(@Valid @RequestBody Department department){
		LOGGER.info("Inside saveDevelopment of DevelopmentController");
		return departmentService.saveDepartment(department);
	}
	
	@GetMapping("/departments")
	public List<Department> fetchDepartmentList(){
		LOGGER.info("Inside fetchDevelopment of DevelopmentController");
		return departmentService.fetchDepartmentList();
	}
	
	@GetMapping("/departments/{id}")
	public Department fetchDepartmentById(@PathVariable("id") Long departmentId) throws DepartmentNotFoundException {
		return departmentService.fetchDepartmentById(departmentId);
	}
		
	@DeleteMapping("/departments/{id}")
	public String deleteDepartmentById(@PathVariable("id") Long departmentId) {
		departmentService.deleteDepartmentById(departmentId);
		return "Deleted";
	}
	
	@PutMapping("departments/{id}")
	public Department updateDepartment(@PathVariable("id") Long departmentId, @RequestBody Department department) {
		return departmentService.updateDepartment(departmentId,department);
	}
	
	@GetMapping("/departments/name/{name}")
	public Department fetchDepartmentByName(@PathVariable("name") String departmentName) {
		return departmentService.fetchDepartmentByName(departmentName);
	}
	
}
