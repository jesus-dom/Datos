package com.example.intermedio.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.intermedio.R;
import com.example.intermedio.models.Empleado;
import com.google.gson.Gson;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    private Gson gson;

    private TextView txtRes;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });


        gson = new Gson();
        final Empleado empleadoObjeto = new Empleado(1,"Juanito Perez","Patito");
        final String jsonObejeto = "{id: 4, nombreCompleto: \"Jaimito Hernandez\", empresa: \"ACME\" }";

        txtRes = root.findViewById(R.id.txtresult);

        Button toJsonBtn = root.findViewById(R.id.btn_clasgson);

        toJsonBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                claseAJson(empleadoObjeto);
            }
        });

        Button fromJsonBtn = root.findViewById(R.id.btn_gsonclas);

        fromJsonBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jsonAClase(jsonObejeto);
            }
        });

        return root;
    }

    private void claseAJson(Empleado empleado){
        String resultado = gson.toJson(empleado);
        txtRes.setText(resultado);
    }

    private void jsonAClase(String json){
        Empleado empResult = gson.fromJson(json,Empleado.class);
        String resultado = "id: "+empResult.getId();
        resultado += "\nnombre: "+empResult.getNombre();
        resultado += "\nempresa: "+empResult.getEmpresa();

        txtRes.setText(resultado);

    }

}