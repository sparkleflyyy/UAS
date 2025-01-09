/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itenas.is.crudproject.servicesdbimpl;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.itenas.is.crudproject.dbconfig.ConnectionManager;
import org.itenas.is.crudproject.model.Peminjam;
import org.itenas.is.crudproject.model.Pemilik_ps;
import org.itenas.is.crudproject.services.CrudService;

/**
 *
 * @author Kurnia R. Putra
 */
public class ControllerPeminjam implements CrudService<Peminjam> {
    
    private final ConnectionManager connMan;

    public ControllerPeminjam(ConnectionManager connMan) {
        this.connMan = connMan;
    }

    @Override
    public void create(Peminjam peminjam) {
        String query = "INSERT INTO peminjam (nama_penyewa, no_telp, alamat, ps, durasi_hari, tgl_pengambilan, "
             + "tgl_tenggat, harga, total_biaya, tgl_pengembalian, total_denda) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, NULL, 0)";

        try (Connection connection = connMan.connectDb();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, peminjam.getNama_Penyewa());
            statement.setString(2, peminjam.getNo_Telp());
            statement.setString(3, peminjam.getAlamat());
            statement.setString(4, peminjam.getPs());
            statement.setInt(5, peminjam.getDurasi_Hari());
            statement.setString(6, peminjam.getTgl_Pengambilan());
            statement.setString(7, peminjam.getTgl_Tenggat());
            statement.setDouble(8, peminjam.getHarga());
            statement.setDouble(9, peminjam.getTotal_Biaya());
//            statement.setString(10, peminjam.getTgl_pengembalian());
//            statement.setDouble(11, peminjam.getTotal_denda());
            statement.executeUpdate();
            System.out.println("Data berhasil ditambahkan.");
        } catch (SQLException e) {
            System.err.println("Error saat menambahkan data: " + e.getMessage());
        }
    }

    @Override
    public Peminjam findOne(int id_Peminjaman) {
        Peminjam peminjam = null;
        String query = """
        SELECT id_peminjam, nama_penyewa, no_telp, alamat, ps, durasi_hari, tgl_pengambilan, tgl_tenggat, harga, total_biaya
        FROM peminjam
        WHERE id_peminjam = ?""";

        try (Connection connection = connMan.connectDb();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, id_Peminjaman);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Peminjam(
                        resultSet.getInt("id_peminjam"),
                        resultSet.getString("nama_penyewa"),
                        resultSet.getString("no_telp"),
                        resultSet.getString("alamat"),
                        resultSet.getString("ps"),
                        resultSet.getInt("durasi_hari"),
                        resultSet.getString("tgl_pengambilan"),
                        resultSet.getString("tgl_tenggat"),
                        resultSet.getDouble("harga"),
                        resultSet.getDouble("total_biaya"),
                        null,
                        0.0 // Total denda default jika tidak diambil dari database
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return peminjam; // Mengembalikan null jika data tidak ditemukan
    }

    @Override
    public List<Peminjam> findAll() {
        String query = """
        SELECT * 
        FROM peminjam""";
        List<Peminjam> peminjamList = new ArrayList<>();

        try (Connection connection = connMan.connectDb();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                // Membuat objek Peminjam berdasarkan hasil query
                Peminjam peminjam = new Peminjam(
                    resultSet.getInt("id_peminjam"),
                    resultSet.getString("nama_penyewa"),
                    resultSet.getString("no_telp"),
                    resultSet.getString("alamat"),
                    resultSet.getString("ps"),
                    resultSet.getInt("durasi_hari"), // Sesuai dengan nama kolom di database
                    resultSet.getString("tgl_pengambilan"),
                    resultSet.getString("tgl_tenggat"),
                    resultSet.getDouble("harga"),
                    resultSet.getDouble("total_biaya"),
                    resultSet.getString("tgl_pengembalian"),
                    resultSet.getDouble("total_denda") // Default untuk total_denda, karena tidak diambil dari query
                );
                peminjamList.add(peminjam);
            }
        } catch (SQLException e) {
            System.err.println("Error saat mengambil data: " + e.getMessage());
        }

        return peminjamList;
    }
//                   ( nama_penyewa, no_telp, alamat, id_ps, tgl_pengambilan, tgl_tenggat)
    @Override
    public void update(int id_peminjaman, Peminjam peminjam) {
    String query = """
    UPDATE peminjam 
    SET nama_penyewa = ?, no_telp = ?, alamat = ?, ps = ?, durasi_hari = ?, tgl_pengambilan = ?, tgl_tenggat = ?, harga = ?, 
    total_biaya = ?, tgl_pengembalian = ?, total_denda = ? 
    WHERE id_peminjam = ?""";

    try (Connection connection = connMan.connectDb();
         PreparedStatement statement = connection.prepareStatement(query)) {

        statement.setString(1, peminjam.getNama_Penyewa());
    statement.setString(2, peminjam.getNo_Telp());
    statement.setString(3, peminjam.getAlamat());
    statement.setString(4, peminjam.getPs());
    statement.setInt(5, peminjam.getDurasi_Hari());
    statement.setString(6, peminjam.getTgl_Pengambilan());
    statement.setString(7, peminjam.getTgl_Tenggat());
    statement.setDouble(8, peminjam.getHarga());
    statement.setDouble(9, peminjam.getTotal_Biaya());
    statement.setString(10, peminjam.getTgl_pengembalian());
    statement.setDouble(11, peminjam.getTotal_denda());
    statement.setInt(12, id_peminjaman);

        int rowsUpdated = statement.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("Data berhasil diperbarui.");
        } else {
            System.out.println("Data dengan ID tersebut tidak ditemukan.");
        }

    } catch (SQLException e) {
        System.err.println("Error saat memperbarui data: " + e.getMessage());
    }
}


    @Override
    public boolean delete(int id_peminjaman) {
        String query = "DELETE FROM peminjam WHERE id_peminjam = ?";
        try (Connection connection = connMan.connectDb();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id_peminjaman);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error saat menghapus data: " + e.getMessage());
            return false;
        }
    }
    
    @Override
    public void updatePs(int id_peminjaman, Peminjam peminjam) {
    String query = """
        UPDATE peminjam 
        SET tgl_pengembalian = ?, total_denda = ? 
        WHERE id_peminjam = ?""";

    try (Connection connection = connMan.connectDb();
         PreparedStatement statement = connection.prepareStatement(query)) {

        // Set parameter untuk query
        statement.setString(1, peminjam.getTgl_pengembalian());
        statement.setDouble(2, peminjam.getTotal_denda());
        statement.setInt(3, id_peminjaman);

        // Eksekusi update
        int rowsUpdated = statement.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("Data berhasil diperbarui untuk ID: " + id_peminjaman);
        } else {
            System.out.println("Data dengan ID tersebut tidak ditemukan.");
        }

    } catch (SQLException e) {
        System.err.println("Error saat memperbarui data: " + e.getMessage());
    }
}
    
    
}
