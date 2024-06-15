package com.example.examenfinal.models;

// Clase que representa los detalles de un elemento de la lista
public class ItemListDetails {
    // Nombre del elemento
    private String name;

    // Método getter para obtener el nombre del elemento
    public String getName() {
        return name;
    }

    public String getUrl() {
        String index = this.name;
        return String.format("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/items/%s.png", index);


    }
}
