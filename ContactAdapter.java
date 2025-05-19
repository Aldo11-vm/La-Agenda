package com.avm.application6;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class ContactAdapter extends ArrayAdapter<Contact> {

    public ContactAdapter(@NonNull Context context, List<Contact> contacts) {
        super(context,0,contacts);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        if(convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.contact_item,parent,false);
        Contact contact = getItem(position);
        ImageView imageview = convertView.findViewById(R.id.contactPhoto);
        TextView nameText = convertView.findViewById(R.id.contactName);
        TextView phoneText = convertView.findViewById(R.id.contactPhone);
        if(contact.getPhotoUri() != null)
            imageview.setImageURI(contact.getPhotoUri());
        else
            imageview.setImageResource(R.drawable.ic_person);
        nameText.setText(contact.getName());
        phoneText.setText(contact.getPhone());
        return convertView;
    }//getView
}
