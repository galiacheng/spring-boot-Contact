import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import com.contacts.repository.ContactRepository;
import com.contacts.repository.ContactPhoneRepository;
import com.contacts.controller.ViewContactsController;
import com.contacts.entity.Contact;
import com.contacts.entity.ContactPhone;
import com.contacts.util.ContactWrapper;
import org.junit.jupiter.api.BeforeEach;

class ViewContactsControllerTest {

    @Mock
    private ContactRepository contactRepository;

    @Mock
    private ContactPhoneRepository contactPhoneRepository;

    @Mock
    private HttpSession httpSession;

    @InjectMocks
    private ViewContactsController viewContactsController;

    @Mock
    private Model model;

    @Mock
    private HttpServletRequest request;

    @Test
    void testViewContacts() {
        // Arrange
        Long userId = 1L;
        List<Contact> contactList = new ArrayList<>();
        List<ContactPhone> phList = new ArrayList<>();
        Map<Long, ContactWrapper> contacts = new HashMap<>();

        Contact contact1 = new Contact();
        contact1.setContactId(1L);
        contact1.setEmailId("test1@example.com");
        contact1.setName("Test1");
        contactList.add(contact1);

        ContactPhone contactPhone1 = new ContactPhone();
        contactPhone1.setPhoneNo("1234567890");
        contactPhone1.setContact(contact1);
        phList.add(contactPhone1);

        when(httpSession.getAttribute("uid")).thenReturn(userId);
        when(contactRepository.findByUserid(userId)).thenReturn(contactList);
        when(contactPhoneRepository.findByUserid(userId)).thenReturn(phList);

        // Act
        String result = viewContactsController.viewContacts(model, request);

        // Assert
        assertEquals("view_contact", result);
        verify(model).addAttribute("clist", contacts);
        verify(contactRepository).findByUserid(userId);
        verify(contactPhoneRepository).findByUserid(userId);
    }    
}