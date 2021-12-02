package com.example.android3hw2.ui;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android3hw2.databinding.ItemFormBinding;
import com.example.android3hw2.network.MockerModel;

import java.util.ArrayList;
import java.util.List;



@SuppressLint("NotifyDataSetChanged")

public class FormAdapter extends RecyclerView.Adapter<FormAdapter.ViewHolder> {

    private List<MockerModel> modelList = new ArrayList<>();
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemFormBinding binding = ItemFormBinding.inflate(LayoutInflater.from(parent.getContext()));
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(modelList.get(position));
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }
    public MockerModel getItem(int position){
        return modelList.get(position);
    }
    public void remove(int position)
    {
        this.modelList.remove(position);
        notifyItemRemoved(position);
    }

    public void addItemsModelList(List<MockerModel> modelList) {
        this.modelList = modelList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemFormBinding binding;

        public ViewHolder(@NonNull ItemFormBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }


        public void onBind(MockerModel model) {
            binding.tvTitle.setText(model.getTitle());
            binding.tvContent.setText(model.getContent());
            binding.tvUser.setText(String.valueOf(model.getUser()));
            itemView.setOnClickListener(v -> onItemClickListener.clickItem(getAdapterPosition()));
            itemView.setOnLongClickListener(v -> {
                onItemClickListener.clickLongItem(getAdapterPosition());
                return true;
            });

        }
    }

    public interface OnItemClickListener {
        void clickItem(int position);
        void clickLongItem(int position);
    }
}
