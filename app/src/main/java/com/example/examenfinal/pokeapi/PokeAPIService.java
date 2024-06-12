package com.example.examenfinal.pokeapi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import com.example.examenfinal.models.*;

public interface PokeAPIService {
    String BASE_URL = "https://pokeapi.co/api/v2/";

    @GET("move/{name}")
    Call<Move> getMoveById(@Path("name") String id);

    @GET("move")
    Call<MoveList> getMoveList(@Query("limit") int limit, @Query("offset") int offset);

    // Añadir método para obtener un ítem por ID
    //@GET("item/{id}")
    //Call<Item> getItemById(@Path("id") String id);

    // Añadir método para obtener la lista de ítems
    @GET("item")
    Call<ItemList> getItemList(@Query("limit") int limit, @Query("offset") int offset);
}
