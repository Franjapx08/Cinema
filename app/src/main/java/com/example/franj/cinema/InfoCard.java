package com.example.franj.cinema;

public class InfoCard {
    private String name;
    private String ubicacion;
    private String codigo;
    private String fecha;
    private String photoId;
    String id;
    private String review;
    private String poster;


    public InfoCard(){

    }


    public InfoCard(String name, String codigo, String fecha, String photoId, String id, String review, String poster) {
        this.name = name;
        this.ubicacion = ubicacion;
       this.codigo = codigo;
        this.fecha = fecha;
        this.photoId = photoId;
        this.id = id;
        this.review = review;
        this.poster = poster;
    }

    public String getName(){
        return name;
    }

    public String getReview(){
        return review;
    }

    public String getCodigo(){
        return codigo;
    }

    public String getFecha(){
        return fecha;
    }

    public String getPhotoId() {
        return photoId;
    }

    public String getId() {
        return id;
    }

    public String getPoster(){
        return poster;
    }



}