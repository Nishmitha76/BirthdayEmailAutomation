package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.EmailTemplate;
import com.example.repository.EmailTemplateRepository;

@Service
public class EmailTemplateServiceImpl implements EmailTemplateService{
	
	@Autowired
	private EmailTemplateRepository emailTemRepo;

	@Override
	public EmailTemplate saveTemplate(EmailTemplate emailtemplate) {
		return emailTemRepo.save(emailtemplate);
	}

	@Override
	public Optional<EmailTemplate> getTemplateById(int id) {
		return emailTemRepo.findById(id);
	}

	@Override
	public Optional<EmailTemplate> getTemplateByName(String name) {
		return Optional.ofNullable(emailTemRepo.findByTemplateName(name));
	}

	@Override
	public void deleteTemplate(int id) {
		emailTemRepo.deleteById(id);	
	}

	@Override
	public List<EmailTemplate> getAllTemplates() {
		return emailTemRepo.findAll();
	}
	
	

}
