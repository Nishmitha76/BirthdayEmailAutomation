package com.example.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.entity.EmailTemplate;
import com.example.entity.Employee;
import com.example.repository.EmailTemplateRepository;
import com.example.repository.EmployeeRepository;

import jakarta.transaction.Transactional;

@Service
public class BirthdayEmailServiceImpl implements BirthdayEmailService {

	@Autowired
	private EmployeeRepository empRepo;

	@Autowired
	private EmailTemplateRepository emailRepo;

	@Autowired
	private JavaMailSender mailSender;

	@Transactional
	@Override
	public void sendBirthdayEmails() {

		System.out.println("Inside sendBirthdayEmails method");
//		List<Employee> employees = empRepo.findByDateOfBirth(today);

		List<Employee> employees = getEmployeesWithBirthdayToday();

		if (!employees.isEmpty()) {
			System.out.println("Inside !employees.isEmpty()");
			EmailTemplate birthdayTemplate = emailRepo.findByTemplateName("Birthday");

			for (Employee employee : employees) {

				String personalizedBody = replacePlaceholders(birthdayTemplate.getBody(), employee);
				String personalizedSubject = replacePlaceholders(birthdayTemplate.getSubject(), employee);
				System.out.println("Sending email to : " + employee.getEmail());

				sendEmail(employee, personalizedSubject, personalizedBody);

			}
		}

	}

	private String replacePlaceholders(String body, Employee employee) {
		return body.replace("{name}", employee.getName()).replace("{email}", employee.getEmail());
	}

	private void sendEmail(Employee employee, String subject, String body) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(employee.getEmail());
		message.setSubject(subject);
		message.setText(body);
		mailSender.send(message);
		System.out.println("Sent email successfully");

	}

	@Override
	public Employee addEmployee(Employee emp) {
		Employee save = empRepo.save(emp);
		return save;
	}

	@Override
	public List<Employee> getAllEmployee() {
		return empRepo.findAll();
	}

	@Override
	public List<Employee> getEmployeesWithBirthdayToday() {
		LocalDate today = LocalDate.now();
		int month = today.getMonthValue();
		int day = today.getDayOfMonth();
		return empRepo.findByDateOfBirth(month, day);
	}

}