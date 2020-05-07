package com.example.androidphp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FetchDataAdapter extends RecyclerView.Adapter<FetchDataAdapter.MyViewHolder> {
    public FetchDataAdapter(Context mcontext, List<Responsclass> listdata) {
        this.mcontext = mcontext;
        this.listdata = listdata;
    }

    Context mcontext;
    List<Responsclass> listdata;

    @NonNull
    @Override
    public FetchDataAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mcontext).inflate(R.layout.adapterformate,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FetchDataAdapter.MyViewHolder holder, int position) {
        Responsclass responsdata = listdata.get(position);
       holder.name.setText(responsdata.getName());
       holder.email.setText(responsdata.getEmail());
    }

    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name,email;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.usernametext);
            email = itemView.findViewById(R.id.useremailtext);
        }
    }
}
