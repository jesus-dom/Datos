package com.example.intermedio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MisDatos extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    //Variable guardadas en sharedPreferences
    String mNombre, mEmail, mTelefono;

    //Variables de los campos de texto
    EditText txtNombre, txtEmail, txtTelefono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_datos);

        sharedPreferences = getSharedPreferences(
                "MisDatos",
                Context.MODE_PRIVATE);
        mNombre = sharedPreferences.getString("nombre","");
        mEmail = sharedPreferences.getString("email","");
        mTelefono = sharedPreferences.getString("telefono","");

        txtNombre = findViewById(R.id.nombre_edit);
        txtEmail = findViewById(R.id.correo_edit);
        txtTelefono = findViewById(R.id.telefono_edit);

        txtNombre.setText(mNombre);
        txtEmail.setText(mEmail);
        txtTelefono.setText(mTelefono);

    }

    public void guardarDatos(View view){
        if (view.getId() == R.id.button_save){
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("nombre",txtNombre.getText().toString());
            editor.commit();
            Toast.makeText(this,"Se guardaron los datos", Toast.LENGTH_SHORT).show();
            editor.putString("email",txtEmail.getText().toString());
            editor.commit();
            editor.putString("telefono",txtTelefono.getText().toString());
            editor.commit();
        }


    }

}
