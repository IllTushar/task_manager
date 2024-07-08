package com.example.test.main_screen;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.test.Fragments.Home.Home;
import com.example.test.R;
import com.example.test.assets.toolbar_class;
import com.example.test.main_screen.bottom_nav.bottom_nav_bar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findId();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.nav_host_fragment, new Home())
                    .commit();
        }
        bottom_nav_bar.nav_bar(MainActivity.this, bottomNavigationView);
    }

    public void findId() {
        bottomNavigationView = findViewById(R.id.bottom_navigation);

    }

}
