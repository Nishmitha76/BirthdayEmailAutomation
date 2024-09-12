package com.example.service;

import java.util.List;

import com.example.entity.Employee;


public interface BirthdayEmailService {
	
	public void sendBirthdayEmails();
	
	public Employee addEmployee(Employee emp);
	
	public List<Employee> getAllEmployee();
	
	public List<Employee> getEmployeesWithBirthdayToday();

}
