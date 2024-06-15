package com.example.examenfinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.examenfinal.databinding.ActivityMainBinding;
import com.example.examenfinal.models.MoveListItem;
import com.example.examenfinal.pokeapi.PokeAPI;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    private ItemsViewModel itemsViewModel;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


     // Inicializa el ViewModel para usarlo en caso de item aleatorio
        itemsViewModel = new ViewModelProvider(this).get(ItemsViewModel.class);

        // Configura el NavController
        NavController navController = ((NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragment)).getNavController();

        // Configura el NavigationView para que use el NavController
        NavigationUI.setupWithNavController(binding.navView, navController);

        // Configura un oyente para los elementos del menú
        binding.navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                // Cierra el cajón después de seleccionar un elemento
                binding.drawerLayout.closeDrawer(GravityCompat.START);

                // Verifica el ID del elemento del menú seleccionado y navega al fragmento correspondiente
                switch (item.getItemId()) {
                    case R.id.moveListRecyclerFragment:
                        navController.navigate(R.id.moveListRecyclerFragment);
                        return true;
                    case R.id.itemListRecyclerFragment:
                        navController.navigate(R.id.itemListRecyclerFragment);
                        return true;
                    case R.id.itemRandomFragment:
                        itemsViewModel.selectRandom();//llamamos al metodo para seleccioanr un item random
                        navController.navigate(R.id.ItemDetailFragment);
                        return true;
                    default:
                        return false;
                }
            }
        });


    }
}
