package com.example.franj.cinema;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.franj.cinema.fragment.Horarios;
import com.example.franj.cinema.fragment.Peliculas;
import com.example.franj.cinema.fragment.Resumen;
import com.squareup.picasso.Picasso;

import static com.example.franj.cinema.Valores.*;

public class Information extends AppCompatActivity {

    private TextView mTextMessage;
    TextView info;
    InfoCard songs;
    ImageView fondo;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_1:
                    selectFragment(Horarios.newInstance());
                    return true;
                case R.id.navigation_2:
                    selectFragment(Resumen.newInstance());
                    return true;

            }
            return false;
        }
    };

    public Information() {
    }

    private void selectFragment(Fragment blankFragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.cont_fragment2, blankFragment);
        fragmentTransaction.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        fondo = (ImageView) findViewById(R.id.backRevie);
        String song = getIntent().getStringExtra(JSON_SONG);
        songs = gson.fromJson(song, InfoCard.class);
        String imG = songs.getPoster();
        Picasso.with(getApplicationContext())
                .load(imG)
                .fit().centerCrop()
                .into(fondo);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation2);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        selectFragment(Horarios.newInstance());
    }

}
