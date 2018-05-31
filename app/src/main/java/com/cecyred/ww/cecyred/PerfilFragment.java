package com.cecyred.ww.cecyred;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cecyred.ww.cecyred.R;

/**
 * Created by User on 18/04/2018.
 */

public class PerfilFragment extends Fragment {
     private TextView txt_nom, txt_bol;
    private View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_perfil,container,false);

    }
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        txt_nom=(TextView)view.findViewById(R.id.textvinom);
        txt_bol=(TextView)view.findViewById(R.id.textvibol);

        txt_nom.setText("Usuario: "+LogginActivity.nombredeusuario);
        txt_bol.setText("Boleta:  "+LogginActivity.numeroboleta);
    //txt_nom=(TextView)view.findViewById(R.id.textv_nom);
    //txt3=(TextView)view.findViewById(R.id.textView3);
    }

}
