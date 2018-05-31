package com.cecyred.ww.cecyred;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Carlos on 22/04/2018.
 */

public class requestvalidacion extends StringRequest {
    private static final String REGISTER_REQUEST_URL="http://cecyred.com.mx/comprobacion.php";
    private Map<String,String> params;
    public requestvalidacion( String usuario, Response.Listener<String> listener) {
        super(Method.POST,REGISTER_REQUEST_URL, listener, null);
        params=new HashMap<>();
        params.put("NomUsuario", usuario);
    }


    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
