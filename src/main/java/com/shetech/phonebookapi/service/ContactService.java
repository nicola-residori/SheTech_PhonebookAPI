package com.shetech.phonebookapi.service;

import com.shetech.phonebookapi.exception.ContactNotFoundException;
import com.shetech.phonebookapi.exception.DuplicateContactException;
import com.shetech.phonebookapi.model.Contact;
import com.shetech.phonebookapi.repository.ContactRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactService {

    /* - repository - */
    @Autowired
    private ContactRepository repository;

    public List<Contact> retrieveContactsList() {
        return repository.findAll();
    }

    public Contact retrieveContact(String phone) throws ContactNotFoundException {
        Optional<Contact> opContact = repository.findById(phone);
        if (opContact.isPresent()) {
            return opContact.get();
        }
        throw new ContactNotFoundException(phone);
    }

    public Contact retrieveContactLambda(String phone) throws ContactNotFoundException {
        return repository.findById(phone).orElseThrow(() -> new ContactNotFoundException((phone)));
    }

    public Contact createContact(Contact contact) throws DuplicateContactException {

        /* - check already exists - */
        try {
            retrieveContact(contact.getPhone());
        } catch (ContactNotFoundException cnfe) {

            /* - contact is new - */
            repository.save(contact);
            return contact;
        }

        /* - contact already present - */
        throw new DuplicateContactException(contact.getPhone());
    }

    public Contact updateContact(String phone, Contact contact) throws ContactNotFoundException {

        /* - retrieve current contact - */
        Contact currentContact = repository.findById(phone).orElseThrow(() -> new ContactNotFoundException(phone));

        /* - load new properties - */
        if (StringUtils.isNotBlank(contact.getPhone())) {
            currentContact.setPhone(contact.getPhone());
        }

        if (StringUtils.isNotBlank(contact.getName())) {
            currentContact.setName(contact.getName());
        }

        if (StringUtils.isNotBlank(contact.getSurname())) {
            currentContact.setSurname(contact.getSurname());
        }

        if (contact.getAge() != null) {
            currentContact.setAge(contact.getAge());
        }

        if (contact.getFemale() != null) {
            currentContact.setFemale(contact.getFemale());
        }

        /* - save on db - */
        repository.save(currentContact);

        return currentContact;
    }

    public Contact deleteContact(String phone) throws ContactNotFoundException {

        /* - retrieve current contact - */
        Contact currentContact = repository.findById(phone).orElseThrow(() -> new ContactNotFoundException(phone));

        /* - delete - */
        repository.delete(currentContact);

        return currentContact;
    }
}
