package com.example.franj.cinema;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ViewBoletos extends AppCompatActivity implements Adapter.OnItemClickListener{

    private RecyclerView mylist;
    private Adapter mAdapter;
    private List<InfoCard> persons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_boletos);


        //recicler
        mylist = (RecyclerView) findViewById(R.id.milista1282);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        mylist.setLayoutManager(llm);
        mylist.setHasFixedSize(true);
        initializeData();

    }


    private void initializeData(){
        persons = new ArrayList<>();
        persons.add(new InfoCard("a", "b", "x" , "x","2","a","b"));
        persons.add(new InfoCard("a", "b", "x" , "x","2","a", "b"));
        /*
        persons.add(new InfoCard("Captiana Marvel","Cinépolis La Paz", "Código de conmfirmación: 252525", "Sabado 16 de marzo de 2019" , R.drawable.cap_marvel, "1"));
        persons.add(new InfoCard("Battle Angel: La última guerrera","Cinépolis La Paz", "Código de conmfirmación: 692505", "Domingo 17 de marzo de 2019" , R.drawable.battle, "2"));
        persons.add(new InfoCard("Captiana Marvel","Cinépolis La Paz", "Código de conmfirmación: 252525", "Sabado 16 de marzo de 2019" , R.drawable.cap_marvel, "1"));
        persons.add(new InfoCard("Captiana Marvel","Cinépolis La Paz", "Código de conmfirmación: 252525", "Sabado 16 de marzo de 2019" , R.drawable.cap_marvel, "1"));
        persons.add(new InfoCard("Captiana Marvel","Cinépolis La Paz", "Código de conmfirmación: 252525", "Sabado 16 de marzo de 2019" , R.drawable.cap_marvel, "1"));
        persons.add(new InfoCard("Captiana Marvel","Cinépolis La Paz", "Código de conmfirmación: 252525", "Sabado 16 de marzo de 2019" , R.drawable.cap_marvel, "1"));
        persons.add(new InfoCard("Captiana Marvel","Cinépolis La Paz", "Código de conmfirmación: 252525", "Sabado 16 de marzo de 2019" , R.drawable.cap_marvel, "1"));
        */
        Adapter adapter = new Adapter(ViewBoletos.this, persons);
        mylist.setAdapter(adapter);
        adapter.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(int position) {

    }
}
