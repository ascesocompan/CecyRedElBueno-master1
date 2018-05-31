package com.cecyred.ww.cecyred;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Carlos on 20/04/2018.
 */

public class requestregistro extends StringRequest {
    private static final String REGISTER_REQUEST_URL="http://cecyred.com.mx/registro.php";
    private Map<String,String> params;
    public requestregistro(String boleta, String usuario, String contrasena, Response.Listener<String> listener) {
        super(Method.POST,REGISTER_REQUEST_URL, listener, null);
        params=new HashMap<>();
        params.put("Boleta", boleta);
        params.put("NomUsuario", usuario);
        params.put("contrasena", contrasena);
    }

    /*public requestregistro(String usuario, String contrasena, String boleta, Listener<String> respolistener) {
        super();
    }*/

    @Override
    public Map<String, String> getParams() {
        return params;
    }



}
