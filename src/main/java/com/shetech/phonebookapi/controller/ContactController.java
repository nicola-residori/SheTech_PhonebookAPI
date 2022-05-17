package com.shetech.phonebookapi.controller;

import com.shetech.phonebookapi.exception.ContactNotFoundException;
import com.shetech.phonebookapi.exception.DuplicateContactException;
import com.shetech.phonebookapi.model.Contact;
import com.shetech.phonebookapi.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("contact")
public class ContactController {

    /* - service - */
    @Autowired
    private ContactService service;

    @GetMapping
    public ResponseEntity<List<Contact>> retrieve() {
        return new ResponseEntity<>(service.retrieveContactsList(), HttpStatus.OK);
    }

    @GetMapping("/{phone}")
    public ResponseEntity<Contact> retrieve(@PathVariable("phone") String phone) throws ContactNotFoundException {
        return new ResponseEntity<>(service.retrieveContact(phone), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Contact> create(@RequestBody Contact contact) throws DuplicateContactException {
        return new ResponseEntity<>(service.createContact(contact), HttpStatus.OK);
    }

    @PutMapping("/{phone}")
    public ResponseEntity<Contact> update(@PathVariable("phone") String phone, @RequestBody Contact contact) throws ContactNotFoundException {
        return new ResponseEntity<>(service.updateContact(phone, contact), HttpStatus.OK);
    }

    @DeleteMapping("/{phone}")
    public ResponseEntity<Contact> delete(@PathVariable("phone") String phone) throws ContactNotFoundException {
        return new ResponseEntity<>(service.deleteContact(phone), HttpStatus.OK);
    }
}
