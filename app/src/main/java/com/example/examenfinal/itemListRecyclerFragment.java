package com.example.examenfinal;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.examenfinal.databinding.FragmentItemListRecyclerBinding;
import com.example.examenfinal.databinding.ViewholderItemListBinding;
import com.example.examenfinal.databinding.ViewholderMoveListBinding;
import com.example.examenfinal.models.ItemListItem;
import com.example.examenfinal.models.MoveListItem;

import java.util.ArrayList;
import java.util.List;


public class itemListRecyclerFragment extends Fragment {

    private FragmentItemListRecyclerBinding binding;
    private ItemViewModel itemViewModel;
    private NavController navController;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentItemListRecyclerBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        itemViewModel = new ViewModelProvider(this).get(ItemViewModel.class); // Cambiado requireActivity() a this para asegurarnos de que estamos usando el ViewModel del fragmento
        navController = Navigation.findNavController(view);

        ItemAdapter itemAdapter = new ItemAdapter();
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext())); // Asegurarnos de configurar el LayoutManager
        binding.recyclerView.setAdapter(itemAdapter);

        Log.d("ItemListRecyclerFragmen", "Setting up observer for item lis");
        // Observamos los cambios en el ViewModel
        itemViewModel.getAll().observe(getViewLifecycleOwner(), items -> {
            if (items != null) {
                Log.d("ItemListRecyclerFragmen", "Items received: " + items.size());
                itemAdapter.setList(items);
            } else {
                Log.d("ItemListRecyclerFragmen", "No items received");
            }
        });
    }

    class ItemAdapter extends RecyclerView.Adapter<itemListRecyclerFragment.ItemViewHolder> {
        private List<ItemListItem> itemList = new ArrayList<>();

        @NonNull
        @Override
        public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            Log.d("ItemAdapter", "onCreateViewHolder called");
            return new itemListRecyclerFragment.ItemViewHolder(ViewholderItemListBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
            ItemListItem item = itemList.get(position);
            Log.d("ItemAdapter", "onBindViewHolder called for position: " + position + ", item: " + item.getName());
            //cautela puede ser aqui
            holder.bind(item); // Asignar valores a las vistas del ViewHolder
        }

        @Override
        public int getItemCount() {

            return itemList != null ? itemList.size() : 0;
        }

        public void setList(List<ItemListItem> itemList) {
            this.itemList = itemList;
            Log.d("ItemAdapter", "setList called, new list size: " + itemList.size());
            notifyDataSetChanged();
        }
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        private final ViewholderItemListBinding binding;

        ItemViewHolder(ViewholderItemListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(ItemListItem item) {
            Log.d("ItemViewHolder", "bind called for item: " + item.getName());
            binding.setItem(item); // Asigna el item al binding
            binding.executePendingBindings(); // Ejecuta los bindings pendientes
        }

    }
}
