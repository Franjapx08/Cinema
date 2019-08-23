package com.example.franj.cinema.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.franj.cinema.Adapter;
import android.content.Intent;
import com.example.franj.cinema.InfoCard;
import com.example.franj.cinema.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static android.content.Intent.getIntent;
import static com.example.franj.cinema.Valores.JSON_SONG;
import static com.example.franj.cinema.Valores.gson;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Resumen.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Resumen#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Resumen extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Resumen() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Resumen.
     */
    // TODO: Rename and change types and number of parameters
    public static Resumen newInstance(String param1, String param2) {
        Resumen fragment = new Resumen();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public static Resumen newInstance(){
        Resumen fragment = new Resumen();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private RequestQueue mq;
    private TextView resumen, review, actores, director, cat;
    private InfoCard songs;
    private LinearLayout header;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_resumen, container, false);
        resumen = (TextView) view.findViewById(R.id.resu);
        review = (TextView) view.findViewById(R.id.reviw);
        director = (TextView) view.findViewById(R.id.director);
        actores = (TextView) view.findViewById(R.id.actores);
        cat = (TextView) view.findViewById(R.id.cat);
        String song = getActivity().getIntent().getStringExtra(JSON_SONG);
        songs = gson.fromJson(song, InfoCard.class);
        mq = Volley.newRequestQueue(getActivity());
        parseJSON();
        return view;

    }

    private void parseJSON() {
        String idd = songs.getId();
        String nombre = songs.getName();
        String imG = songs.getPhotoId();
        String reviw = songs.getReview();
        resumen.setText(nombre);
        review.setText(reviw);

        String url = "https://api.themoviedb.org/3/movie/"+idd+"?api_key=007e11936f2a1af9806a20f10a297891";
       // Toast.makeText(getContext(),idd,Toast.LENGTH_SHORT).show();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {

                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("production_companies");
                            //Toast.makeText(getActivity(),"Hay respuesta",Toast.LENGTH_SHORT).show();
                            for (int i=0; i < jsonArray.length(); i++){
                                JSONObject hit = jsonArray.getJSONObject(i);
                                String nameCompany = hit.getString("name");
                                actores.setText(nameCompany);
                                director.setText(nameCompany);
                            }

                            JSONArray jsonArray2 = response.getJSONArray("genres");
                            //Toast.makeText(getActivity(),"Hay respuesta",Toast.LENGTH_SHORT).show();
                            for (int i=0; i < jsonArray2.length(); i++){
                                JSONObject hit = jsonArray2.getJSONObject(i);
                                String nameCate = hit.getString("name");
                                cat.setText(nameCate);
                            }
                            JSONArray jsonArray3 = response.getJSONArray("runtime");
                            /*
                            for (int i=0; i < jsonArray2.length(); i++){
                                JSONObject hit = jsonArray2.getJSONObject(i);
                                String nameCate = hit.getString("name");
                                cat.setText(hit.getString());
                            }
                            */
                        } catch (JSONException e) {
                         //   Toast.makeText(getActivity(),"No hay respuesta1",Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(),"No hay conexión a internet",Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        });
        mq.add(request);

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
