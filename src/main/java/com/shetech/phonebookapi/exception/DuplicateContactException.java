package com.shetech.phonebookapi.exception;

public class DuplicateContactException extends Exception {

    public DuplicateContactException(String phone) {
        super("Contact with phone=" + phone + ", already exists");
    }
}
