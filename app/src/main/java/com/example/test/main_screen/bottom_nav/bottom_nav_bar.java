package com.example.test.main_screen.bottom_nav;

import static com.example.test.R.id.nav_dashboard;

import android.annotation.SuppressLint;

import android.content.Context;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.example.test.Fragments.DashBoard.Dashboard;
import com.example.test.Fragments.Home.Home;
import com.example.test.Fragments.Settings.Settings;
import com.example.test.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class bottom_nav_bar {

    public static void nav_bar(Context context,BottomNavigationView bottomNavigationView){
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment selected_fragment = null;
                int id = menuItem.getItemId();
                switch (id) {
                    case R.id.nav_home:
                        selected_fragment = new Home();
                        break;
                    case nav_dashboard:
                        selected_fragment = new Dashboard();
                        break;
                    case R.id.nav_setting:
                        selected_fragment = new Settings();
                        break;
                }
                if (selected_fragment != null && context instanceof FragmentActivity) {
                    ((FragmentActivity) context).getSupportFragmentManager().beginTransaction()
                            .replace(R.id.nav_host_fragment, selected_fragment)
                            .commit();
                }
                return true;
            }
        });

    }
}
