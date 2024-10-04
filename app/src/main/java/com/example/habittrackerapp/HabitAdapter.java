package com.example.habittrackerapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import android.util.Log;


public class HabitAdapter extends RecyclerView.Adapter<HabitAdapter.HabitViewHolder> {

    private ArrayList<String> habitList;
    private Context context;
    private OnHabitClickListener listener;

    public HabitAdapter(ArrayList<String> habitList, Context context, OnHabitClickListener listener) {
        this.habitList = habitList;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public HabitViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.habit_item, parent, false);
        return new HabitViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HabitViewHolder holder, int position) {
        holder.tvHabitName.setText(habitList.get(position));

        holder.itemLayout.setOnClickListener(v -> {
            Intent intent = new Intent(context, StatisticsActivity.class);
            intent.putExtra("habit_detail", habitList.get(position));
            String[] parts = habitList.get(position).split("\\|");
            String reward = parts[2].split(":")[1].trim();
            intent.putExtra("habit_reward", reward);
            context.startActivity(intent);
        });

        holder.btnDelete.setOnClickListener(v -> listener.onDeleteClick(position));
        holder.btnEdit.setOnClickListener(v -> listener.onEditClick(position));
    }

    @Override
    public int getItemCount() {
        return habitList.size();
    }

    public static class HabitViewHolder extends RecyclerView.ViewHolder {
        TextView tvHabitName;
        Button btnDelete, btnEdit;
        LinearLayout itemLayout;

        public HabitViewHolder(@NonNull View itemView) {
            super(itemView);
            tvHabitName = itemView.findViewById(R.id.tvHabitName);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            itemLayout = itemView.findViewById(R.id.itemLayout); // ผูก LinearLayout
        }
    }

    public interface OnHabitClickListener {
        void onDeleteClick(int position);
        void onEditClick(int position);
    }
}
