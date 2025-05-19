package com.avm.application6;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Activity_contact_details extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_contact_details);
        ImageView photo = findViewById(R.id.detailPhoto);
        TextView name = findViewById(R.id.detailName);
        TextView phone = findViewById(R.id.detailPhone);
        TextView email = findViewById(R.id.detailEmail);
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            name.setText(extras.getString("name"));
            phone.setText(extras.getString("phone"));
            email.setText(extras.getString("email"));
            try{
                String photoUri= extras.getString("photoUri");
                if(photoUri != null)
                    photo.setImageURI(Uri.parse(photoUri));
                else
                    photo.setImageURI(Uri.parse(photoUri));

            } catch (Exception e) {
                photo.setImageResource(R.drawable.ic_person);
            }
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.maindetail), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}