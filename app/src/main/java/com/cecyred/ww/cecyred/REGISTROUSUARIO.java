package com.cecyred.ww.cecyred;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class REGISTROUSUARIO extends AppCompatActivity {
EditText Et_nom,Et_pass,Et_bol;
    private ProgressBar progresbar2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrousuario);
       Et_nom=(EditText)findViewById(R.id.TXT_NOMBRE);
       Et_pass=(EditText)findViewById(R.id.TXT_CONTRA);
       Et_bol=(EditText)findViewById(R.id.TXT_BOLETA);
        progresbar2=(ProgressBar)findViewById(R.id.progressBar2);
    }
    public void registro(View v) {
        String usuario = Et_nom.getText().toString();
        String contrasena = Et_pass.getText().toString();
        String boleta = Et_bol.getText().toString();
        if (usuario.isEmpty() || contrasena.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Ingresa el usuario y la contraseña", Toast.LENGTH_SHORT).show();
        }else {
            progresbar2.setVisibility(View.VISIBLE);
            Response.Listener<String> respolistener = new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {

                    // jsonResponse= null;
                  //  comprobarusuario();
                    try {
                        JSONObject jsonResponse = new JSONObject(response);
                        boolean succes = jsonResponse.getBoolean("success");
                        if (succes) {
                            progresbar2.setVisibility(View.INVISIBLE);
                            Toast.makeText(getApplicationContext(), "Registrado con exito", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(REGISTROUSUARIO.this, LogginActivity.class);
                            REGISTROUSUARIO.this.startActivity(intent);
                        } else {
                            progresbar2.setVisibility(View.INVISIBLE);
                            Toast.makeText(getApplicationContext(), "Ocurrio un error en el registro", Toast.LENGTH_LONG).show();
                        }
                    } catch (JSONException e) {
                        //e.printStackTrace();
                        Toast.makeText(getApplicationContext(), "" + e.toString(), Toast.LENGTH_LONG).show();
                    }
                }
            };
            requestregistro requestreg = new requestregistro(boleta, usuario, contrasena, respolistener);
            RequestQueue queue = Volley.newRequestQueue(REGISTROUSUARIO.this);
            queue.add(requestreg);
        }
    }
   /* public void comprobarusuario(){
        String usuario = Et_nom.getText().toString();
        Response.Listener<String> respolistener1 = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // jsonResponse= null;
                try {
                    JSONObject jsonResponse1 = new JSONObject(response);
                    boolean succes1 = jsonResponse1.getBoolean("success");
                    if (succes1) {
                        Toast.makeText(getApplicationContext(), "Usuario ya registrado", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Ocurrio un error", Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    //e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "" + e.toString(), Toast.LENGTH_LONG).show();
                }
            }
        };
        requestvalidacion requestval = new requestvalidacion( usuario,  respolistener1);
        RequestQueue queue = Volley.newRequestQueue(REGISTROUSUARIO.this);
        queue.add(requestval);
    }*/

}
