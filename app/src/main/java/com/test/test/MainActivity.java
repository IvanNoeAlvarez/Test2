package com.test.test;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.navigation.NavigationView;
import com.test.test.databinding.ActivityMainBinding;

/**
 * Esta es la clase principal de la app, la primera que se carga.
 * Implementa un FrameLayout para sustituir por distintos fragments.
 */
public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    /**
     * Se puede usar esta forma o {@link MainActivity#findViewById)} para acceder a las views
     */
    ActivityMainBinding binding;
    Fragment mContent = null;

    /**
     * Metodo ejecutado al iniciarse la pantalla. Es el metodo tipico donde cargar las views y
     * desde el cual lanzar toda la logica relacionada con la carga de la pantalla.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setSupportActionBar(binding.barMain.toolbar);
        getSupportActionBar();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, binding.drawerLayout, binding.barMain.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        binding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        binding.navView.setNavigationItemSelectedListener(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame, new MainFragment()).commitAllowingStateLoss();
    }

    /**
     * Metodo para manejar el comportamiento del boton "atras"
     */
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (!(mContent instanceof MainFragment)) {
            mContent = new MainFragment();
            getSupportActionBar().setTitle(R.string.app_name);
            getSupportFragmentManager().beginTransaction().replace(R.id.frame, mContent).commitAllowingStateLoss();
        } else {
            super.onBackPressed();
        }
    }

    /**
     * Metodo para crear los menus tipicos
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /**
     * Metodo para saber qué opcion del menu ha sido seleccionada
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Toast.makeText(this, "Has clicado en settings", Toast.LENGTH_SHORT).show();

            startActivityForResult(new Intent(MainActivity.this, SettingsActivity.class), 1000);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {
            case 1000:
                if (resultCode == RESULT_OK) {
                    if (mContent instanceof RecyclerFragment) {
                        String text = data.getStringExtra("nombre");
                        if (text == null)
                            text = "";
                        ((RecyclerFragment) mContent).binding.mockText.setText(text);
                    }
                }
                break;
            default:
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            mContent = new MainFragment();
            getSupportActionBar().setTitle(R.string.app_name);
        } else if (id == R.id.nav_recycler) {
            mContent = new RecyclerFragment();
            getSupportActionBar().setTitle(R.string.menu_recycler);
        } else if (id == R.id.nav_multi_layout) {
            mContent = new MultiLayoutFragment();
            getSupportActionBar().setTitle(R.string.menu_multi_layout);
        } else if (id == R.id.nav_form) {
            mContent = new FormFragment();
            getSupportActionBar().setTitle(R.string.menu_forms);
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }
        if (mContent == null)
            mContent = new MainFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.frame, mContent).commitAllowingStateLoss();

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void mambo(View view) {
        Toast.makeText(view.getContext(), "Has hecho otro clic", Toast.LENGTH_SHORT).show();
    }
}
