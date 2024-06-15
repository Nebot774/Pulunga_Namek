package com.example.examenfinal;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.examenfinal.pokeapi.PokeAPI;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.examenfinal.models.Item;
import com.example.examenfinal.models.ItemList;
import com.example.examenfinal.models.ItemListDetails;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


// ItemViewModel.java
public class ItemsViewModel extends ViewModel {
    private MutableLiveData<List<ItemListDetails>> itemList;
    private MutableLiveData<Item> selectedItem;
    private ItemListDetails selected;

    public ItemsViewModel() {
        itemList = new MutableLiveData<>();
        selectedItem = new MutableLiveData<>();
        fetchItems();
    }

    public LiveData<List<ItemListDetails>> getItemList() {
        return itemList;
    }

    public LiveData<Item> getSelectedItem() {
        if (selected != null) {
            PokeAPI.getItem(selected.getName(), selectedItem);
        } else {
            Log.e("ItemsViewModel", "Selected item is null");
        }
        return selectedItem;
    }

    public void fetchItems() {
        PokeAPI.getItemList(itemList);
    }

    public void fetchItemDetails(String itemName) {
        Log.d("ItemsViewModel", "Fetching item details for item: " + itemName);
        PokeAPI.getItem(itemName, selectedItem);
    }

    public void select(ItemListDetails itemListDetails) {
        selected = itemListDetails;
        Log.d("ItemsViewModel", "Item selected: " + itemListDetails.getName());
    }

    //METODO PARA SELECCIONAR item aleatorio
    public void selectRandom() {
        List<ItemListDetails> list = itemList.getValue();
        if (list != null && !list.isEmpty()) {
            int randomIndex = (int) (Math.random() * list.size());
            select(list.get(randomIndex));
            Log.d("ItemsViewModel", "Random item selected: " + selected.getName());
        }
    }
}


