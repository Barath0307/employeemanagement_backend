package com.miniproject1.EmployeeManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.miniproject1.EmployeeManagement.entity.EmployeeDetails;

@Repository
public interface EmployeeDetailsRepo extends JpaRepository<EmployeeDetails, Long>{
}
