package com.task2.springboot.repository.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task2.springboot.entity.Department;
import com.task2.springboot.error.DepartmentNotFoundException;
import com.task2.springboot.repository.DepartmentRepository;

@Service
public class DepartmentServiceImpl implements DepartmentService {
	
	@Autowired
	private DepartmentRepository departmentRepository;

	@Override
	public Department saveDepartment(Department department) {
		return departmentRepository.save(department);
	}

	@Override
	public List<Department> fetchDepartmentList() {
		return departmentRepository.findAll();
	}

	@Override
	public Department fetchDepartmentById(Long departmentId) throws DepartmentNotFoundException {
		Optional<Department> department = departmentRepository.findById(departmentId);
		
		if(!department.isPresent()) {
			throw new DepartmentNotFoundException("Department Not Available");
		}
		return department.get();
	}

	@Override
	public void deleteDepartmentById(Long departmentId) {
		departmentRepository.deleteById(departmentId);
		
	}

	@Override
	public Department updateDepartment(Long departmentId, Department department) {
		Department depDb = departmentRepository.findById(departmentId).get();
		
		if(Objects.nonNull(department.getDepartmentName()) &&
				!"".equalsIgnoreCase(department.getDepartmentName())) {
			depDb.setDepartmentName(department.getDepartmentName());
		}
		
		if(Objects.nonNull(department.getDepartmentAddress()) &&
				!"".equalsIgnoreCase(department.getDepartmentAddress())) {
			depDb.setDepartmentAddress(department.getDepartmentAddress());
		}
		
		if(Objects.nonNull(department.getDepartmentCode()) &&
				!"".equalsIgnoreCase(department.getDepartmentCode())) {
			depDb.setDepartmentCode(department.getDepartmentCode());
		}
		
		return departmentRepository.save(depDb);
		
	}

	@Override
	public Department fetchDepartmentByName(String departmentName) {
		return departmentRepository.findBydepartmentNameIgnoreCase(departmentName);
	}

}
