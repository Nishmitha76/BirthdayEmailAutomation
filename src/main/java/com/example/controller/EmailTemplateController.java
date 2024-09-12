package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.EmailTemplate;
import com.example.service.EmailTemplateServiceImpl;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/email-templates")
public class EmailTemplateController {
	
	@Autowired
	private EmailTemplateServiceImpl emailService;
	
	@PostMapping("/add")
	public ResponseEntity<EmailTemplate> addTemplate(@RequestBody EmailTemplate emailtemplate){
		EmailTemplate savedTemplate = emailService.saveTemplate(emailtemplate);
		return new ResponseEntity<EmailTemplate>(savedTemplate, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<EmailTemplate> getTemplateById(@PathVariable int id){
		Optional<EmailTemplate> template = emailService.getTemplateById(id);
		return template.map(ResponseEntity::ok)
				       .orElseGet(()-> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	@GetMapping("/name/{name}")
	public ResponseEntity<EmailTemplate> getTemplateByName(@PathVariable String name){
		Optional<EmailTemplate> template = emailService.getTemplateByName(name);
		return template.map(ResponseEntity::ok)
				       .orElseGet(()-> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteTemplate(@PathVariable int id){
		emailService.deleteTemplate(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);	
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<EmailTemplate>> getAllTemplates(){
		List<EmailTemplate> templates = emailService.getAllTemplates();
		return new ResponseEntity<>(templates, HttpStatus.OK);
	}
	

}
