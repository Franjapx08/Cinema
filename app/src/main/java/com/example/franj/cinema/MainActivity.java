package com.example.franj.cinema;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.franj.cinema.fragment.Location;
import com.example.franj.cinema.fragment.Peliculas;
import com.example.franj.cinema.fragment.Perfil;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    selectFragment(Peliculas.newInstance());
                    setTitle("Estas en Cartelera");
                  //  item.setTitle("Peliculas");
                    return true;
                case R.id.navigation_dashboard:
                    selectFragment(Location.newInstance());
                    setTitle("Ubicación");
                 //   item.setTitle("Ubicación");
                    return true;
                case R.id.navigation_notifications:
                    selectFragment(Perfil.newInstance());
                    setTitle("Perfil");
                 //   item.setTitle("Perfil");
                    return true;
            }
            return false;
        }
    };



    private void selectFragment(Fragment blankFragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.cont_fragment, blankFragment);
        fragmentTransaction.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Estas en Cartelera");
        //carga el fragment principal al abrir el activit   y
        selectFragment(Peliculas.newInstance());
        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
