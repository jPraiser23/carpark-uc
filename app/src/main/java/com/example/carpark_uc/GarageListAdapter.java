package com.example.carpark_uc;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class GarageListAdapter extends RecyclerView.Adapter<GarageVH>{
    List<String> garageNames;

    public GarageListAdapter(List<String> garageNames) {
        this.garageNames = garageNames;
    }

    @NonNull
    @Override
    public GarageVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent,false);

        return new GarageVH(view).linkAdapter(this);
    }

    @Override
    public void onBindViewHolder(@NonNull GarageVH holder, int position) {
        holder.textView.setText(garageNames.get(position));
    }

    @Override
    public int getItemCount() {
        return garageNames.size();
    }
}

class GarageVH extends RecyclerView.ViewHolder{
    TextView textView;
    private GarageListAdapter adapter;
    public GarageVH(@NonNull View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.text);

        itemView.findViewById(R.id.select).setOnClickListener(view ->{
            //Link to next page-->
            Intent intent = new Intent(itemView.getContext(), FirstGarage.class);
            itemView.getContext().startActivity(intent);
        });
    }
    public GarageVH linkAdapter(GarageListAdapter adapter){
        this.adapter = adapter;
        return this;
    }
}