package com.example.service;

import java.util.List;
import java.util.Optional;

import com.example.entity.EmailTemplate;

public interface EmailTemplateService {
	
	public EmailTemplate saveTemplate(EmailTemplate emailtemplate);
	
	public Optional<EmailTemplate> getTemplateById(int id);
	
	public Optional<EmailTemplate> getTemplateByName(String name);
	
	public void deleteTemplate(int id);
	
	public List<EmailTemplate> getAllTemplates();

}
