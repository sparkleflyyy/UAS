/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itenas.is.crudproject.model;

/**
 *
 * @author riyann
 */
public class Peminjam {
    private  int id_Peminjaman;
    private  String nama_Penyewa;
    private  String no_Telp;
    private  String alamat;
    private  String ps;
    private  int durasi_Hari;
    private  String tgl_Pengambilan;
    private  String tgl_Tenggat;
    private  double harga;
    private  double total_Biaya;
    private  String tgl_pengembalian;
    private  double total_denda;

    public Peminjam() {
    }

    public Peminjam(int id_Peminjaman, String nama_Penyewa, String no_Telp, String alamat, String ps, int durasi_Hari, String tgl_Pengambilan, String tgl_Tenggat, double harga, double total_Biaya, String tgl_pengembalian, double total_denda) {
        this.id_Peminjaman = id_Peminjaman;
        this.nama_Penyewa = nama_Penyewa;
        this.no_Telp = no_Telp;
        this.alamat = alamat;
        this.ps = ps;
        this.durasi_Hari = durasi_Hari;
        this.tgl_Pengambilan = tgl_Pengambilan;
        this.tgl_Tenggat = tgl_Tenggat;
        this.harga = harga;
        this.total_Biaya = total_Biaya;
        this.tgl_pengembalian = tgl_pengembalian;
        this.total_denda = total_denda;
    }

    public int getId_Peminjaman() {
        return id_Peminjaman;
    }

    public void setId_Peminjaman(int id_Peminjaman) {
        this.id_Peminjaman = id_Peminjaman;
    }

    public String getNama_Penyewa() {
        return nama_Penyewa;
    }

    public void setNama_Penyewa(String nama_Penyewa) {
        this.nama_Penyewa = nama_Penyewa;
    }

    public String getNo_Telp() {
        return no_Telp;
    }

    public void setNo_Telp(String no_Telp) {
        this.no_Telp = no_Telp;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getPs() {
        return ps;
    }

    public void setPs(String ps) {
        this.ps = ps;
    }

    public int getDurasi_Hari() {
        return durasi_Hari;
    }

    public void setDurasi_Hari(int durasi_Hari) {
        this.durasi_Hari = durasi_Hari;
    }

    public String getTgl_Pengambilan() {
        return tgl_Pengambilan;
    }

    public void setTgl_Pengambilan(String tgl_Pengambilan) {
        this.tgl_Pengambilan = tgl_Pengambilan;
    }

    public String getTgl_Tenggat() {
        return tgl_Tenggat;
    }

    public void setTgl_Tenggat(String tgl_Tenggat) {
        this.tgl_Tenggat = tgl_Tenggat;
    }

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }

    public double getTotal_Biaya() {
        return total_Biaya;
    }

    public void setTotal_Biaya(double total_Biaya) {
        this.total_Biaya = total_Biaya;
    }

    public String getTgl_pengembalian() {
        return tgl_pengembalian;
    }

    public void setTgl_pengembalian(String tgl_pengembalian) {
        this.tgl_pengembalian = tgl_pengembalian;
    }

    public double getTotal_denda() {
        return total_denda;
    }

    public void setTotal_denda(double total_denda) {
        this.total_denda = total_denda;
    }
       
}
