package com.example.examenfinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.MutableLiveData;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.examenfinal.databinding.ActivityMainBinding;
import com.example.examenfinal.models.MoveListItem;
import com.example.examenfinal.pokeapi.PokeAPI;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Configura el NavController
        //NavController navController = Navigation.findNavController(this, R.id.fragment);
        NavController navController = ((NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragment)).getNavController();


        // Configura el NavigationView para que use el NavController
        NavigationUI.setupWithNavController(binding.navView, navController);

        // Configura un oyente para los elementos del menú
        binding.navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                // Navega al destino correspondiente
                NavigationUI.onNavDestinationSelected(item, navController);
                // Cierra el cajón después de seleccionar un elemento
                binding.drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        MutableLiveData<List<MoveListItem>> moveList = new MutableLiveData<>();
        PokeAPI.getMoveList(moveList);
    }
}