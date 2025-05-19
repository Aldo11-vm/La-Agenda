package com.avm.application6;

import android.net.Uri;

public class Contact {
    private String name;
    private String phone;
    private String email;
    private Uri photoUri;

    public Contact(String name,
                  String phone,
                  String email,
                  Uri photoUri) {
        
        this.phone = phone;
        this.name = name;
        this.photoUri = photoUri;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public Uri getPhotoUri() {
        return photoUri;
    }
}


