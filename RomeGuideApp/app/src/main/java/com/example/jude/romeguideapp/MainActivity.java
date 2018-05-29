package com.example.jude.romeguideapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);

        final FrameLayout frameLayout = findViewById(R.id.content_frame);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // set item as selected to persist highlight
                item.setChecked(true);
                // close drawer when item is tapped
                drawerLayout.closeDrawers();

                // Add code here to update the UI based on the item selected
                // For example, swap UI fragments here

                android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();

                if (item.getItemId() == R.id.home) {

                    startActivity(new Intent(getBaseContext(), MainActivity.class));
                }

                if (item.getItemId() == R.id.famous_places) {
                    frameLayout.removeAllViews();
                    FamousPlacesFragment famousPlacesFragment = new FamousPlacesFragment();
                    fragmentManager.beginTransaction().replace(R.id.content_frame, famousPlacesFragment).addToBackStack(null).commit();
                } else if (item.getItemId() == R.id.museums) {
                    frameLayout.removeAllViews();
                    MuseumsFragment museumsFragment = new MuseumsFragment();
                    fragmentManager.beginTransaction().replace(R.id.content_frame, museumsFragment).addToBackStack(null).commit();
                } else if (item.getItemId() == R.id.hotels) {
                    frameLayout.removeAllViews();
                    HotelsFragment hotelsFragment = new HotelsFragment();
                    fragmentManager.beginTransaction().replace(R.id.content_frame, hotelsFragment).addToBackStack(null).commit();
                }
                else if(item.getItemId() == R.id.hospitals) {
                    frameLayout.removeAllViews();
                    HospitalsFragment hospitalsFragment = new HospitalsFragment();
                    fragmentManager.beginTransaction().replace(R.id.content_frame, hospitalsFragment).addToBackStack(null).commit();
                }

                return true;
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
