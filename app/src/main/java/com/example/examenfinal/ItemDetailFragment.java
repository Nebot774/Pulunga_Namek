package com.example.examenfinal;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


// ItemDetailFragment.java
public class ItemDetailFragment extends Fragment {
    private ItemsViewModel itemViewModel;
    private TextView itemName;
    private TextView itemCategory;
    private TextView itemCost;
    private TextView itemEffect;
    private ImageView itemImage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("ItemDetailFragment", "onCreateView called");
        View view = inflater.inflate(R.layout.fragment_item_detail, container, false);
        itemName = view.findViewById(R.id.itemName);
        itemCategory = view.findViewById(R.id.itemCategory);
        itemCost = view.findViewById(R.id.itemCost);
        itemEffect = view.findViewById(R.id.itemEffect);
        // itemImage = view.findViewById(R.id.itemImage);

        itemViewModel = new ViewModelProvider(requireActivity()).get(ItemsViewModel.class); // Cambiado para usar la misma instancia

        Log.d("ItemDetailFragment", "ViewModel initialized: " + (itemViewModel != null));
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("ItemDetailFragment", "onViewCreated called");

        itemViewModel.getSelectedItem().observe(getViewLifecycleOwner(), item -> {
            if (item != null) {
                Log.d("ItemDetailFragment", "Received item details: " + item.toString());
                itemName.setText(item.getName());
                itemCategory.setText(item.getCategory());
                itemCost.setText(String.valueOf(item.getCost()));
                itemEffect.setText(item.getEffect());
                // Picasso.get().load(item.getUrl()).into(itemImage);
            } else {
                Log.d("ItemDetailFragment", "Item details are null");
            }
        });
    }


}
