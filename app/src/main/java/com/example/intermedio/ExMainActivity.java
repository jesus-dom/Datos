package com.example.intermedio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ExMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exactivity_main);
    }

    public void OnClick(View view){
        Intent intent = new Intent(ExMainActivity.this,MisDatos.class);
        startActivity(intent);

    }
}
