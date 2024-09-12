package com.example.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
	
	List<Employee> findByDateOfBirth(Date dateOfBirth);
	
	@Query("SELECT e FROM Employee e WHERE FUNCTION('MONTH', e.dateOfBirth) = :month AND FUNCTION('DAY', e.dateOfBirth) = :day")
	List<Employee> findByDateOfBirth(@Param("month") int month, @Param("day") int day);

}


