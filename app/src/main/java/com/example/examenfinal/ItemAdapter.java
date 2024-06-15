package com.example.examenfinal;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;

import androidx.recyclerview.widget.RecyclerView;

import com.example.examenfinal.models.Item;
import com.example.examenfinal.models.ItemListDetails;

import java.util.List;

// ItemAdapter.java
public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {
    private List<ItemListDetails> itemList;
    private OnItemClickListener listener;
    private ItemsViewModel itemsViewModel;

    public interface OnItemClickListener {
        void onItemClick(String itemName);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public ItemAdapter(List<ItemListDetails> itemList, ItemsViewModel itemsViewModel) {
        this.itemList = itemList;
        this.itemsViewModel = itemsViewModel;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_item_list, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        ItemListDetails item = itemList.get(position);
        String itemName = item.getName();
        holder.bind(itemName);
        Glide.with(holder.itemView)
                .load(item.getUrl())
                .into(holder.itemImage);
        holder.itemView.setOnClickListener(v -> {
            Log.d("ItemAdapter", "Item clicked: " + itemName);
            itemsViewModel.select(item);
            if (listener != null) {
                listener.onItemClick(itemName);
            }
        });
    }

    @Override
    public int getItemCount() {
        // Devuelve el número de elementos en la lista, o 0 si la lista es nula
        return itemList != null ? itemList.size() : 0;
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView itemName;
        private ImageView itemImage; // Añade esta línea

        public ItemViewHolder(View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.itemName);
            itemImage = itemView.findViewById(R.id.itemImage); // Añade esta línea
        }

        public void bind(String name) {
            itemName.setText(name);
        }
    }

    public void updateData(List<ItemListDetails> itemList) {
        this.itemList = itemList;
        notifyDataSetChanged();
    }
}
