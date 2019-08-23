package com.example.franj.cinema;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

//import static com.example.franj.cinema.Valores.JSON_SONG;

import static com.example.franj.cinema.Valores.*;


public class Adapter extends RecyclerView.Adapter<Adapter.PersonViewHolder> {

    private Context mContext;
    private List<InfoCard> persons;
    private OnItemClickListener mListener;


    public interface OnItemClickListener{
        void onItemClick(int position);
    }


    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }


    public Adapter(Context context, List<InfoCard> lista){
        mContext = context;
        persons = lista;
    }

    @NonNull
    @Override
    public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_card, viewGroup, false);
        return new PersonViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final PersonViewHolder personViewHolder, final int i) {
        InfoCard currentItem = persons.get(i);
        personViewHolder.song = currentItem;
        String name = currentItem.getName();
        String review = currentItem.getReview();
        String codigo = currentItem.getCodigo();
        String fecha = currentItem.getFecha();
        String imG = currentItem.getPhotoId();
        String id = currentItem.getId();

        personViewHolder.nombre.setText(name);
        personViewHolder.review.setText(review);
        personViewHolder.codigo.setText(codigo);
        personViewHolder.fecha.setText(fecha);

        //personViewHolder.personPhoto.setImageResource(imG);
        Picasso.with(mContext).load(imG).fit().centerInside().into(personViewHolder.personPhoto);
        personViewHolder.id.setText(id);

        personViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    int position = i;
                    if (position != RecyclerView.NO_POSITION){
                        mListener.onItemClick(position);

                        Intent detail = new Intent(v.getContext(), Information.class);
                        detail.putExtra(JSON_SONG, gson.toJson(personViewHolder.song));
                        v.getContext().startActivity(detail);//Inicia la actividad

                    }
                }
              //  Toast.makeText(v.getContext(),"no2",Toast.LENGTH_SHORT).show();
            }

        });
    }

    @Override
    public int getItemCount() {
        return persons.size();
    }

    public class PersonViewHolder extends RecyclerView.ViewHolder {
        public InfoCard song;
        Context contex;
        CardView cv;
        TextView nombre;
        TextView ubicacion;
        TextView codigo;
        TextView fecha, review;
        ImageView personPhoto;
        TextView id;
        Button a;

        public PersonViewHolder(View itemView) {
            super(itemView);
            //contex = itemView.getContext();
            cv = (CardView) itemView.findViewById(R.id.cv);
            nombre = (TextView) itemView.findViewById(R.id.nombre);
            codigo = (TextView) itemView.findViewById(R.id.codigo);
            fecha = (TextView) itemView.findViewById(R.id.fecha);
            personPhoto = (ImageView) itemView.findViewById(R.id.person_photo);
            id = (TextView) itemView.findViewById(R.id.id);
            review = (TextView) itemView.findViewById(R.id.review);

        }

    }
}

