package com.example.intermedio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.OnLifecycleEvent;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.intermedio.models.CallResult;
import com.example.intermedio.utils.Globals;
import com.example.intermedio.utils.ReporteService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReportActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private ReporteService service;
    //Variable guardadas en sharedPreferences
    String mNombre, mEmail, mTelefono, mReporte;

    //Variables de los campos de texto
    EditText txtNombre, txtEmail, txtTelefono, txtReporte, txtGeo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        sharedPreferences = getSharedPreferences(
                "MisDatos",
                Context.MODE_PRIVATE);
        mNombre = sharedPreferences.getString("nombre","");
        mEmail = sharedPreferences.getString("email","");
        mTelefono = sharedPreferences.getString("telefono","");

        txtNombre = findViewById(R.id.rnombre);
        txtEmail = findViewById(R.id.remail);
        txtTelefono = findViewById(R.id.rtelefono);
        txtReporte = findViewById(R.id.mreporte);
        txtGeo = findViewById(R.id.rptgeo);

        txtNombre.setText(mNombre);
        txtEmail.setText(mEmail);
        txtTelefono.setText(mTelefono);

        service = Globals.getApi().create(ReporteService.class);

        Button btnSend = findViewById(R.id.reporbutton);
        btnSend.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                guardarDatos();
            }
        });
        getPermisos();
    }
    private void guardarDatos(){
        Call<CallResult> llamadaGuardar = service.agregarReporte(
                txtNombre.getText().toString(),
                txtEmail.getText().toString(),
                txtTelefono.getText().toString(),
                txtReporte.getText().toString());

        llamadaGuardar.enqueue(new Callback<CallResult>() {
            @Override
            public void onResponse(Call<CallResult> call, Response<CallResult> response) {
                if (response.isSuccessful()){
                    CallResult resultado = response.body();
                    if(!resultado.isError()){

                        Toast.makeText(getApplicationContext(),
                                "Se agrego el reporte No: "+resultado.getId(),
                                Toast.LENGTH_LONG)
                                .show();
                        finish();
                    }
                }
            }

            @Override
            public void onFailure(Call<CallResult> call, Throwable t) {

            }
        });
    }

    private final int PERMISO_USUARIO_LOCALIZACION = 1;
    private void getPermisos(){
        if(checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED){

            String[] permisos = {Manifest.permission.ACCESS_COARSE_LOCATION};

            //Pedir permisos
            ActivityCompat.requestPermissions(this,
                    permisos,
                    PERMISO_USUARIO_LOCALIZACION);
        }else{
            txtGeo.setText("YA TIENE PERMISO");
        }
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults){
        switch (requestCode){
            case PERMISO_USUARIO_LOCALIZACION:{
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    txtGeo.setText("SI DIO PERMISO");
                }else{
                    txtGeo.setText("NO DIO PERMISO");
                }
            }
        }
    }
}
