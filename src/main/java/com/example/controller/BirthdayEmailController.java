package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Employee;
import com.example.service.BirthdayEmailServiceImpl;

@RestController
@RequestMapping("/api/birthday-email")
public class BirthdayEmailController {
	
	@Autowired
	private BirthdayEmailServiceImpl service;
	
	@GetMapping("/send")
	public String sendBirthdayEmails() {
		service.sendBirthdayEmails();
		return "Birthday emails sent successfully!";
	}
	
	@PostMapping("/add")
	public ResponseEntity<Employee> addEmployee(@RequestBody Employee emp){
		Employee savedEmployee = service.addEmployee(emp);
		return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Employee>> getAllEmployee(){
		List<Employee> templates = service.getAllEmployee();
		return new ResponseEntity<>(templates, HttpStatus.OK);
	}
	
	@GetMapping("/emp/birthday-today")
	public List<Employee> getEmployeesWithBirthdayToday(){
		return service.getEmployeesWithBirthdayToday();
	}
	
}