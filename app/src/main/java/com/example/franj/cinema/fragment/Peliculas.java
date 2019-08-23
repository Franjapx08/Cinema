package com.example.franj.cinema.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.franj.cinema.Adapter;
import com.example.franj.cinema.InfoCard;
import com.example.franj.cinema.R;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import service.ApiCliente;
import service.NowPlayingResponse;
import retrofit2.Call;
import retrofit2.Callback;
import service.Result;


public class Peliculas extends Fragment implements Adapter.OnItemClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String r;

    private OnFragmentInteractionListener mListener;

    public Peliculas() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Peliculas newInstance(String param1, String param2) {
        Peliculas fragment = new Peliculas();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public static Peliculas newInstance(){
        Peliculas fragment = new Peliculas();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }
    private Spinner spiner;
    private RequestQueue mq;
    private int b = 1;
    private RecyclerView mylist;
    private Adapter mAdapter;
    private List<InfoCard> persons;
    private int a = 1;
    CarouselView carouselView;
    int[] sampleImages = {R.drawable.uno, R.drawable.dos, R.drawable.tres};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_peliculas, container, false);
        mylist = (RecyclerView) view.findViewById(R.id.milista128);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        mylist.setLayoutManager(llm);
        mylist.setHasFixedSize(true);
        carouselView = (CarouselView) view.findViewById(R.id.carouselView);
        carouselView.setPageCount(sampleImages.length);
        carouselView.setImageListener(imageListener);
        //requestQueue = Volley.newRequestQueue(getActivity());
        mq = Volley.newRequestQueue(getActivity());

        parseJSON();

        //initializeData();
       // callAPINowPlaying();
        return view;
    }
/*
    static String API_KEY = "007e11936f2a1af9806a20f10a297891";
    public List<Result> result;
    private void callAPINowPlaying() {
        retrofit2.Call<NowPlayingResponse> call = ApiCliente.getApiService().getNowPlaying(API_KEY);
        call.enqueue(new Callback<NowPlayingResponse>() {
            @Override
            public void onResponse(Call<NowPlayingResponse> call, retrofit2.Response<NowPlayingResponse> response) {
                if(response.isSuccessful() && !response.body().getResults().isEmpty()){
                    result = new ArrayList<service.Result>();
                    result.addAll(response.body().getResults());
                }
            }

            @Override
            public void onFailure(Call<NowPlayingResponse> call, Throwable t) {

            }
        });
    }
*/
    private void parseJSON() {
        // lista
        //String url = "https://api.themoviedb.org/4/list/350?page=1&api_key=007e11936f2a1af9806a20f10a297891";
        // pruena qr
        //http://frajavxi.x10host.com/ScannerQR/api/v1/eventos.php
        String url = "https://api.themoviedb.org/4/list/001?page=1&api_key=007e11936f2a1af9806a20f10a297891";
        //el de antes
        ///https://prueba2.fjavpra.now.sh
        //https://api.themoviedb.org/4/list/001?page=1&api_key=007e11936f2a1af9806a20f10a297891

        //String url2 = "https://console.firebase.google.com/project/visionlite-39d6e/database/visionlite-39d6e/data";
        // naruto
        // String url = "https://gist.githubusercontent.com/aws1994/f583d54e5af8e56173492d3f60dd5ebf/raw/c7796ba51d5a0d37fc756cf0fd14e54434c547bc/anime.json";
        //gatos
        //  String url = "https://pixabay.com/api/?key=5303976-fd6581ad4ac165d1b75cc15b3&q=kitten&image_type=photo&pretty=true";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            //   JSONObject jsonObject = null;
                            JSONArray jsonArray = response.getJSONArray("results");
                            Toast.makeText(getActivity(),"Hay respuesta",Toast.LENGTH_SHORT).show();
                            persons = new ArrayList<>();
                            for (int i=0; i < jsonArray.length(); i++){
                                JSONObject hit = jsonArray.getJSONObject(i);
                                String name = hit.getString("title");
                                String type = hit.getString("media_type");
                                String img = hit.getString("poster_path");
                                String id = hit.getString("title");
                                String time = "07:00 pm";
                                String review = hit.getString("overview");
                                String poster = hit.getString("backdrop_path")
                                        ;
                                persons.add(new InfoCard(name, type, time , "https://image.tmdb.org/t/p/w500"+img,id,review, "https://image.tmdb.org/t/p/w500"+poster));
                            }
                            mAdapter = new Adapter( getContext(), persons);
                            mylist.setAdapter(mAdapter);
                            mAdapter.setOnItemClickListener(Peliculas.this);
                        } catch (JSONException e) {
                           // Toast.makeText(getActivity(),"No hay respuesta1",Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
               // Toast.makeText(getActivity(),"No hay respuesta",Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        });
        mq.add(request);
    }

    @Override
    public void onItemClick(int position) {
       // Toast.makeText(getActivity(),"No hay respuesta",Toast.LENGTH_SHORT).show();

    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(sampleImages[position]);
        }
    };

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // TODO Add your menu entries here
        inflater.inflate(R.menu.op_view, menu);
        super.onCreateOptionsMenu(menu, inflater);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.op:
                if (a==1){
                    mylist.setLayoutManager(new GridLayoutManager(getActivity(), 2));
                    a++;

                    item.setIcon(R.drawable.ic_list);
                }else{
                    mylist.setLayoutManager(new GridLayoutManager(getActivity(), 1));
                    a--;
                    item.setIcon(R.drawable.ic_vertical_grid_layout);
                }
                return true;
        }
        return false;

    }

    private void initializeData(){
        persons = new ArrayList<>();
        persons.add(new InfoCard("Captiana Marvel","Cinépolis La Paz", "Código de conmfirmación: 252525", "https://image.tmdb.org/t/p/w500" ,"id","a", "1"));
        Adapter adapter = new Adapter( getActivity(), persons);
        mylist.setAdapter(adapter);

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
