package com.avm.application6;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private ContactAdapter adapter;
    private  ContactApp contactApp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ListView listView = findViewById(R.id.contactList);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        contactApp = ((ContactApp)getApplication());
        adapter = new ContactAdapter(this,contactApp.getContacts());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(( (parent, view, position, id) ->{
            Contact c = contactApp.getContacts().get(position);
            Intent i = new Intent( MainActivity.this, Activity_contact_details.class);
            i.putExtra("phone", c.getPhone());
            i.putExtra("name", c.getName());
            i.putExtra("email", c.getEmail());
            String photo = (c.getPhotoUri() != null) ? c.getPhotoUri().toString():null;
            i.putExtra("photoUri", photo);
            startActivity(i);
        }));
    }
    @Override
    protected  void onResume(){
        super.onResume();
        adapter.notifyDataSetChanged();
    }
    public void addContacto(View v){
        Intent nuevocontacto =
                new Intent(this,
                        Activity_NuevoContacto.class);
        startActivity(nuevocontacto);
    }
}