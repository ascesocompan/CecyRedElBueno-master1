package com.cecyred.ww.cecyred;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Carlos on 23/04/2018.
 */

public class requestlogin extends StringRequest {

    private static final String LOGIN_REQUEST_URL="http://cecyred.com.mx/Login_Cecyred.php";
    private Map<String,String> params;
    public requestlogin(String usuario, String contrasena, Response.Listener<String> respolistener) {
        super(Method.POST,LOGIN_REQUEST_URL,respolistener,null);
        params=new HashMap<>();
        params.put("NomUsuario", usuario);
        params.put("contrasena", contrasena);
    }
    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
