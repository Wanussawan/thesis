package com.rana.adarsh.imagetotext.Model;

public class Provinsi {
    String Id, Nama, Build;

    public Provinsi(String id, String nama, String build) {
        Id = id;
        Nama = nama;
        Build = build;
    }

    public String getId() {
        return Id;
    }

    public String getNama() {
        return Nama;
    }

    public String getBuild() {
        return Build;
    }

}