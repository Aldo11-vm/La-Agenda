package com.avm.application6;

import android.app.Application;

import java.util.ArrayList;

public class ContactApp extends Application {
    public ArrayList<Contact> contacts = new ArrayList<>();
    public  void addContact(Contact c){
        contacts.add(c);
    }
    public ArrayList<Contact> getContacts(){
        return contacts;
    }
}

