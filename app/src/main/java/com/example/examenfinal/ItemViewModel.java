package com.example.examenfinal;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.examenfinal.models.ItemListItem;
import com.example.examenfinal.models.Move;
import com.example.examenfinal.models.MoveListItem;
import com.example.examenfinal.pokeapi.PokeAPI;

import java.util.List;

public class ItemViewModel extends AndroidViewModel {
    private MutableLiveData<List<ItemListItem>> listItemsMutableLiveData  = new MutableLiveData<>();

    public ItemViewModel(@NonNull Application application) {
        super(application);
        Log.d("ItemViewModel", "Loading items...");
        PokeAPI.getItemList(listItemsMutableLiveData);

    }

    public LiveData<List<ItemListItem>> getAll() {
        return listItemsMutableLiveData;
    }


}

