/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itenas.is.crudproject.servicesdbimpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.itenas.is.crudproject.model.Pemilik_ps;
import org.itenas.is.crudproject.dbconfig.ConnectionManager;
/**
 *
 * @author Riyann
 */
public class ControllerPs {
     Scanner sc = new Scanner(System.in);
     ConnectionManager conMan = new ConnectionManager();
     Connection con = conMan.logOn();
     
     public boolean insertPs(String nama, double harga) {
    String query = "INSERT INTO pemilik_ps (nama_ps, harga) VALUES ('" + nama + "', " + harga + ")";
    try {
        Statement stm = con.createStatement();
        stm.executeUpdate(query);
        return true;
    } catch (SQLException ex) {
        System.err.println("Error saat melakukan insert: " + ex.getMessage());
        return false;
    }
}
     
     public List<Pemilik_ps> showPs(){
         List<Pemilik_ps> listPs = new ArrayList<Pemilik_ps>();
         try {
             Statement stm = con.createStatement();
             ResultSet rs = stm.executeQuery("SELECT * FROM pemilik_ps");
             while (rs.next()) {
                 Pemilik_ps ps = new Pemilik_ps();
                 ps.setId_ps(rs.getInt("id_ps"));
                 ps.setNama_ps(rs.getString("nama_ps"));
                 ps.setHarga(rs.getInt("harga"));
                 listPs.add(ps);
             }
         } catch (SQLException ex) {
             System.out.println(ex.toString());
         }
         return listPs;
     }
     
     public boolean updatePs (String newNama_ps, String nama_ps, double harga, int id_ps){
         String query = "UPDATE pemilik_ps SET harga = "
                 + harga + ", nama_ps = '" + newNama_ps + "' WHERE id_ps ="
                 + id_ps;
         try {
             Statement stm = con.createStatement();
             stm.executeUpdate(query);
             return true;
         } catch (SQLException ex){
             System.out.println(ex.toString());
             return false; 
         }
     }
     
     public boolean deletePs(int id_ps){
         String query = "DELETE FROM pemilik_ps WHERE id_ps ="
                 + id_ps;
         try {
             Statement stm = con.createStatement();
             stm.executeUpdate(query);
             return true;
         } catch (SQLException ex){
             System.out.println(ex.toString());
             return false; 
         }
    }
     
     public Pemilik_ps searchById(int id_ps) {
        Pemilik_ps ps = new Pemilik_ps();
        try {
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM mobil WHERE id_mobil = " + id_ps);
            while (rs.next()) {
                ps.setId_ps(rs.getInt("id_ps"));
                ps.setNama_ps(rs.getString("nama_ps"));
                ps.setHarga(rs.getDouble("harga"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
         return ps;
     }
}
