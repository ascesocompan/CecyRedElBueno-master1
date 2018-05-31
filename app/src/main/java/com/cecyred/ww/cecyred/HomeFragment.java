package com.cecyred.ww.cecyred;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;

public class HomeFragment extends Fragment {

    private static final String URL_DATA="http://www.cecyred.com.mx/";

    private RecyclerView.Adapter adapter;
    private List<listitem> listItems;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        final RecyclerView r1=(RecyclerView) rootView.findViewById(R.id.recyclerviewo);
        r1.setHasFixedSize(true);
        LinearLayoutManager llm1= new LinearLayoutManager(getActivity());
        llm1.setOrientation(LinearLayoutManager.VERTICAL);
        r1.setLayoutManager(llm1);

        listItems=new ArrayList<>();

        load_data_from_server(0);

        adapter=new MyAdapter(listItems,this.getActivity());
        r1.setAdapter(adapter);

         return rootView;
}

private void load_data_from_server(final int id)
{
    AsyncTask<Integer,Void,Void> task =new AsyncTask<Integer, Void, Void>() {

        @Override
        protected Void doInBackground(Integer... integers) {
            OkHttpClient okHttpClient= new OkHttpClient();
            okhttp3.Request request= new okhttp3.Request.Builder().url("http://cecyred.com.mx/")
                    .build();
            try {
                okhttp3.Response response=okHttpClient.newCall(request).execute();
                JSONArray array=new JSONArray(response.body().string());
                for(int i=0; i<array.length(); i++)
                {
                    JSONObject object=array.getJSONObject(i);

                    listitem items=new listitem(object.getString("title"),object.getString("content"));
                    listItems.add(items);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    };
    task.execute(id);
}

    private void loadRecyclerViewData(){
        ProgressDialog progressDialog= new ProgressDialog(this.getActivity());
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        StringRequest stringRequest=new StringRequest(Request.Method.GET,
                URL_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        try {
                            JSONObject jsonObject= new JSONObject(s);
                            JSONArray array=jsonObject.getJSONArray("");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue requestQueue= Volley.newRequestQueue(this.getActivity());
        requestQueue.add(stringRequest);
    }
}
