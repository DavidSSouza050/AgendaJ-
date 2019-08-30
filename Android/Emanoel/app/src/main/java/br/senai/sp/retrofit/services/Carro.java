package br.senai.sp.retrofit.services;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"id"})
public class Carro  {

    //public Long id;
    public String tipo;
    public String nome;
    public String desc;
    @JsonAlias({"url_foto"})
    public String urlFoto;
    @JsonAlias({"url_info"})
    public String urlInfo;
    @JsonAlias({"url_video"})
    public String urlVideo;
    public String latitude;
    public String longitude;


}
