package com.example.tugasmobile7;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class AdapterPerson extends RecyclerView.Adapter<AdapterPerson.ViewHolder> {
    Context context;
    private List<UserResponse> dataPerson;

    public AdapterPerson(Context context, List<UserResponse> dataPerson) {
        this.context = context;
        this.dataPerson = dataPerson;
    }

    @NonNull
    @Override
    public AdapterPerson.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_person, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        UserResponse userResponse = dataPerson.get(position);
        String name = userResponse.getFirstName() + " " + userResponse.getLastName();
        holder.Name.setText(name);
        holder.Email.setText(userResponse.getEmail());
        Glide.with(holder.itemView.getContext()) .load(userResponse.getAvatarUrl()) .into(holder.Profile);

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(holder.itemView.getContext(), DetailProfile.class);
            intent.putExtra("id", userResponse.getId());
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return dataPerson.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView Name, Email;
        ImageView Profile;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Name = itemView.findViewById(R.id.name);
            Email = itemView.findViewById(R.id.email);
            Profile = itemView.findViewById(R.id.Profile);
        }
    }
}