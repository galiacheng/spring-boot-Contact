package com.contacts.controller;

import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.ui.Model;
import org.springframework.validation.support.BindingAwareModelMap;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.contacts.repository.ContactRepository; // Ensure this import is correct
import com.contacts.entity.Contact;
import com.contacts.entity.ContactPhone;
import com.contacts.repository.ContactPhoneRepository; // Ensure this import is correct

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(ViewContactsController.class)
class ViewContactsControllerTest {

    @MockBean
    private ContactRepository contactRepository;

    @MockBean
    private ContactPhoneRepository contactPhoneRepository;

    @Mock
    HttpSession httpSession;

    @Autowired
    private ViewContactsController viewContactsController;

    private Model model;
    private MockHttpServletRequest request;

    @BeforeEach
    void setUp() {
        model = new BindingAwareModelMap();
        request = new MockHttpServletRequest();
        ServletRequestAttributes attrs = new ServletRequestAttributes(request);
        RequestContextHolder.setRequestAttributes(attrs);
    }

    @Test
    void testViewContacts() {
        // Prepare data
        Long userId = 1L;
        when(httpSession.getAttribute("uid")).thenReturn(userId);
        // Mock repository methods
        request.setSession(httpSession);
        // Add your mock data setup here
        Contact contact1 = new Contact();
        contact1.setContactId(1L);
        contact1.setEmailId("hello@126.com");
        contact1.setName("John Doe");
        ContactPhone contactPhone1 = new ContactPhone();
        contactPhone1.setPhoneNo("1234567890");
        contactPhone1.setContact(contact1);
        List<ContactPhone> contactPhoneList = new ArrayList<>();
        contactPhoneList.add(contactPhone1);
        when(contactPhoneRepository.findByUseridandContactId(userId, contact1.getContactId())).thenReturn(contactPhoneList);
        when(contactPhoneRepository.findByUserid(userId)).thenReturn(contactPhoneList);
        List<Contact> contactList = new ArrayList<>();
        contactList.add(contact1);
        when(contactRepository.findByUserid(userId)).thenReturn(contactList);
        // Call the method
        String viewName = viewContactsController.viewContacts(model, request);

        // Assertions
        assertEquals("view_contact", viewName);
        // Add more assertions to verify model attributes

        // Verify interactions
        verify(contactRepository).findByUserid(userId);
        verify(contactPhoneRepository).findByUserid(userId);
    }

    @AfterEach
    public void tearDown() {
        RequestContextHolder.resetRequestAttributes();
    }
}