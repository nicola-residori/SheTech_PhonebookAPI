package com.shetech.phonebookapi.exception;

public class ContactNotFoundException extends Exception {

    public ContactNotFoundException(String phone) {
        super("Contact with phone=" + phone + ", not found");
    }
}
