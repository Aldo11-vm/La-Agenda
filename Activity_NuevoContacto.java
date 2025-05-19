package com.avm.application6;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.File;
import java.io.FileOutputStream;

public class Activity_NuevoContacto extends AppCompatActivity {
    EditText txtNombre,txtPhone,txtMail;
    Button btnSeleccionImagen,btnSave;
    ImageView ivPreview;
    static  final int PICK_IMAGE_REQUEST =1;
    Uri selectedImage = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_nuevocontacto);

        //mapeamos los controles
        txtNombre = findViewById(R.id.txtName);
        txtPhone = findViewById(R.id.txtPhone);
        txtMail = findViewById(R.id.txtMail);
        ivPreview = findViewById(R.id.ivPreview);
        btnSave = findViewById(R.id.btnSave);
        btnSeleccionImagen = findViewById(R.id.btnSeleccionImagen);

        //codigo para click del boton
        btnSeleccionImagen.setOnClickListener( v -> abrirGaleria());
        btnSave.setOnClickListener(v -> {
            String name = txtNombre.getText().toString();
            String phone = txtPhone.getText().toString();
            String email = txtMail.getText().toString();
            if(!name.isEmpty() && !phone.isEmpty())
            {
                try{
                    Contact c = new Contact(name, phone, email, selectedImage);
                    ((ContactApp)getApplication()).addContact(c);
        Toast.makeText(this, "Contacto agregado", Toast.LENGTH_SHORT).show();
        finish();
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            }


        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        }); // view compat

    }//oncreat
    public  void abrirGaleria(){
        Intent galeria = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galeria,PICK_IMAGE_REQUEST);
    }
    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode,
                                    Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode == PICK_IMAGE_REQUEST &&
                resultCode == RESULT_OK &&
                data != null && data.getData() != null){
            selectedImage = data.getData();
            //ivPreview.setImageURI(selectedImage);
            try{
                Bitmap b = MediaStore.Images.Media.getBitmap(getContentResolver(),selectedImage);
                selectedImage = saveImage(b);
                if(b==null)
                {
                    Toast.makeText(this,
                            "Error al Guardar la imagen", Toast.LENGTH_SHORT).show();
                    return;
                }
                ivPreview.setImageURI(selectedImage);
            }
            catch (Exception ex){
                ex.printStackTrace();
                Toast.makeText(this,
                        "Error al cargar la imagen",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }
    private Uri saveImage(Bitmap b){

        String fileName =txtPhone.getText().toString()+".jpg";
        try{
            FileOutputStream out = openFileOutput(fileName, Context.MODE_PRIVATE);
            b.compress(Bitmap.CompressFormat.JPEG,100,out);
            out.close();
            File f = new File(getFilesDir(),fileName);
            return  Uri.fromFile(f);
        }
        catch (Exception e){
            Toast.makeText(this,
                    "Error al Guardar la imagen",
                    Toast.LENGTH_SHORT).show();
            return  null;
        }
    }
}