package com.miniproject1.EmployeeManagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miniproject1.EmployeeManagement.entity.EmployeeDetails;
import com.miniproject1.EmployeeManagement.repository.EmployeeDetailsRepo;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeService {
	
	@Autowired
	private EmployeeDetailsRepo emprepo;
	
	public EmployeeDetails postEmployee(EmployeeDetails emp) {
		return emprepo.save(emp);
	}
	
	public List<EmployeeDetails> getAllEmployees() {
		return emprepo.findAll();
	}
	
	public void deleteEmployee(Long id) {
		if (!emprepo.existsById(id)) {
			throw new EntityNotFoundException("Employee ID - " + id + " not found.");
		}
		
		emprepo.deleteById(id);
	}
	
	public EmployeeDetails getEmployeeByID(Long id) {
		return emprepo.findById(id).orElse(null);
	}
	
	public EmployeeDetails updateEmployee(Long id, EmployeeDetails emp) {
		Optional<EmployeeDetails> optionalEmp = emprepo.findById(id);
		
		if (optionalEmp.isPresent()) {
			EmployeeDetails existingemp = optionalEmp.get();
			
			existingemp.setEmail(emp.getEmail());
			existingemp.setName(emp.getName());
			existingemp.setPhone(emp.getPhone());
			existingemp.setDepartment(emp.getDepartment());
			
			return emprepo.save(existingemp);
		}
		
		return null;
	}
}
