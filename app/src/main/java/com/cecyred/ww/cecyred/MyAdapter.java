package com.cecyred.ww.cecyred;

import android.app.LauncherActivity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 09/05/2018.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {


    private ArrayList<listitem> listItems;


    public MyAdapter(ArrayList<listitem> listItems) {
        this.listItems = listItems;

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
      holder.textViewHead.setText(listItems.get(position).getHead());
        holder.textViewDesc.setText(listItems.get(position).getDesc());
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView textViewHead;
        private TextView textViewDesc;
        public ViewHolder(View itemView) {
            super(itemView);

            textViewHead=itemView.findViewById(R.id.textviewHead);
            textViewDesc=itemView.findViewById(R.id.textviewDescripcion);
        }
    }
}
