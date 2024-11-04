package com.miniproject1.EmployeeManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.miniproject1.EmployeeManagement.entity.EmployeeDetails;
import com.miniproject1.EmployeeManagement.service.EmployeeService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/api")
public class EmployeeController {
	
	@Autowired
	private EmployeeService empservice;
	
	@PostMapping("/employee")
	public EmployeeDetails postEmployee(@RequestBody EmployeeDetails emp) {
		System.out.println(emp.getName());
		return empservice.postEmployee(emp);
	}
	
	@GetMapping("/employee")
	public List<EmployeeDetails> getAllEmployees() {
		return empservice.getAllEmployees();
	}
	
	@DeleteMapping("/employee/{id}")
	public 	ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
		try {
			empservice.deleteEmployee(id);
			return new ResponseEntity<>(" Employee with ID - " + id + " deleted successfully.", HttpStatus.OK);
		} catch(EntityNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/employee/{id}")
	public ResponseEntity<?> getEmployeeByID(@PathVariable Long id) {
		EmployeeDetails emp = empservice.getEmployeeByID(id);
		
		if (emp == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(emp);
	}
	
	@PutMapping("/employee/{id}")
	public ResponseEntity<?> updateEmployee(@PathVariable Long id, @RequestBody EmployeeDetails emp) {
		EmployeeDetails empd = empservice.updateEmployee(id, emp);
		
		if (empd == null) { return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); }
		
		return ResponseEntity.ok(empd);

	}
}
