package com.contacts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import com.contacts.entity.Contact;
import com.contacts.entity.ContactPhone;
import com.contacts.entity.User;
import com.contacts.repository.ContactPhoneRepository;
import com.contacts.repository.ContactRepository;
import com.contacts.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class ContactsManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContactsManagementApplication.class, args);
		log.info("Application started ");
	}	
}
