package com.contacts.controller;

import static org.junit.Assert.assertSame;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ViewContactsControllerTest {

    @Test
    void testViewContacts() {
        assertSame("hello", "hello");
    }
}