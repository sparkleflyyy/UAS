/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itenas.is.crudproject.model;

/**
 *
 * @author riyann
 */
public class Pemilik_ps {
    private int id_ps;
    private String nama_ps;
    private String newNama_ps;
    private double harga;
    
    public Pemilik_ps() {
    }

    public Pemilik_ps(int id_ps, String nama_ps, double harga) {
        this.id_ps = id_ps;
        this.nama_ps = nama_ps;
        this.harga = harga;
    }

    public int getId_ps() {
        return id_ps;
    }

    public void setId_ps(int id_ps) {
        this.id_ps = id_ps;
    }

    public String getNama_ps() {
        return nama_ps;
    }

    public void setNama_ps(String nama_ps) {
        this.nama_ps = nama_ps;
    }

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }

    
    
        
    
}
