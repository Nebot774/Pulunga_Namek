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
import com.example.examenfinal.ItemsViewModel;

import java.util.ArrayList;

public class ItemListRecyclerFragment extends Fragment {
    private ItemsViewModel itemViewModel;
    private RecyclerView recyclerView;
    private ItemAdapter itemAdapter;
    private NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("ItemListRecyclerFragmen", "onCreateView called");
        View view = inflater.inflate(R.layout.fragment_item_list_recycler, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        navController = Navigation.findNavController(requireActivity(), R.id.fragment);
        Log.d("ItemListRecyclerFragmet", "NavController initialized");

        itemViewModel = new ViewModelProvider(requireActivity()).get(ItemsViewModel.class); // Cambiado para usar la misma instancia

        itemAdapter = new ItemAdapter(new ArrayList<>(), itemViewModel);
        recyclerView.setAdapter(itemAdapter);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("ItemListRecyclerFragmen", "onViewCreated called");

        itemViewModel.getItemList().observe(getViewLifecycleOwner(), itemList -> {
            itemAdapter.updateData(itemList);
            Log.d("ItemListRecyclerFragmen", "Updated item list with " + itemList.size() + " items");
        });

        itemAdapter.setOnItemClickListener(itemName -> {
            Log.d("ItemListRecyclerFragmen", "Item clicked: " + itemName);
            itemViewModel.fetchItemDetails(itemName);
        });

        itemViewModel.getSelectedItem().observe(getViewLifecycleOwner(), item -> {
            if (item != null) {
                Log.d("ItemListRecyclerFragmen", "Received item details: " + item.toString());
                navController.navigate(R.id.action_itemListRecyclerFragment_to_itemDetailFragment);
                Log.d("ItemListRecyclerFragmen", "Navigating to ItemDetailFragment");
            } else {
                Log.d("ItemListRecyclerFragmen", "Item details are null");
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("ItemListRecyclerFragmen", "onResume called");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("ItemListRecyclerFragmen", "onPause called");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("ItemListRecyclerFragmen", "onDestroyView called");
    }
}

