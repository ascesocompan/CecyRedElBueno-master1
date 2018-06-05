package com.cecyred.ww.cecyred;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.JsonReader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment {

    private static final String URL_DATA="http://www.cecyred.com.mx/";

    private RecyclerView.Adapter adapter;
    private ArrayList<listitem> data;
    private RecyclerView r1;
    private MyAdapter adapter1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        r1=(RecyclerView) rootView.findViewById(R.id.recyclerviewo);
        r1.setHasFixedSize(true);
        LinearLayoutManager llm1= new LinearLayoutManager(getActivity());
        llm1.setOrientation(LinearLayoutManager.VERTICAL);
        r1.setLayoutManager(llm1);

        loadJSON();

         return rootView;
}

    private void loadJSON(){
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.readTimeout(60, TimeUnit.SECONDS);
        client.connectTimeout(60,TimeUnit.SECONDS);
         Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://cecyred.com.mx/")
                .client(client.build())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        RequestInterface request = retrofit.create(RequestInterface.class);
        Call<JSONResponse> call = request.getJSON();
        call.enqueue(new Callback<JSONResponse>() {
            @Override
            public void onResponse(Call<JSONResponse> call, retrofit2.Response<JSONResponse> response) {

                JSONResponse jsonResponse = response.body();

                data = new ArrayList<>(Arrays.asList(jsonResponse.getListItem()));
                adapter1 = new MyAdapter(data);
                r1.setAdapter(adapter1);
            }

            @Override
            public void onFailure(Call<JSONResponse> call, Throwable t) {
                Log.d("Error",t.getMessage());
            }
        });
    }
private void load_data_from_server(final int id)
{
    @SuppressLint("StaticFieldLeak") AsyncTask<Integer,Void,Void> task =new AsyncTask<Integer, Void, Void>() {

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
