/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itenas.is.crudproject.servicesdbimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.itenas.is.crudproject.dbconfig.ConnectionManager;
import org.itenas.is.crudproject.model.Peminjam;
import org.itenas.is.crudproject.services.CrudService;

/**
 *
 * @author riyann
 */
public class ControllerPengembalian implements CrudService<Peminjam>{
    private final ConnectionManager connMan;

    public ControllerPengembalian(ConnectionManager connMan) {
        this.connMan = connMan;
    }


        @Override
     public Peminjam findOne(int id_Peminjaman) {
         Peminjam peminjam = null;
         String query = """
             SELECT id_peminjam, nama_penyewa, no_telp, alamat, ps, durasi_hari, tgl_pengambilan, tgl_tenggat, harga, total_biaya, tgl_pengembalian, total_denda
             FROM peminjam
             WHERE id_peminjam = ?""";

         try (Connection connection = connMan.connectDb();
              PreparedStatement statement = connection.prepareStatement(query)) {

             statement.setInt(1, id_Peminjaman);

             try (ResultSet resultSet = statement.executeQuery()) {
                 if (resultSet.next()) {
                     peminjam = new Peminjam(
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
                         resultSet.getString("tgl_pengembalian"),
                         resultSet.getDouble("total_denda")
                     );
                 }
             }
         } catch (SQLException e) {
             e.printStackTrace();
         }
         return peminjam;
     }

    @Override
public List<Peminjam> findAll() {
    String query = """
        SELECT id_peminjam, nama_penyewa, no_telp, alamat, ps, durasi_hari, tgl_pengambilan, tgl_tenggat, harga, total_biaya, tgl_pengembalian, total_denda
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
                resultSet.getInt("durasi_hari"),
                resultSet.getString("tgl_pengambilan"),
                resultSet.getString("tgl_tenggat"),
                resultSet.getDouble("harga"),
                resultSet.getDouble("total_biaya"),
                resultSet.getString("tgl_pengembalian"),
                resultSet.getDouble("total_denda")
            );
            peminjamList.add(peminjam);
        }
    } catch (SQLException e) {
        System.err.println("Error saat mengambil data: " + e.getMessage());
    }

    return peminjamList;
}


       @Override
public void update(int id_peminjaman, Peminjam peminjam) {
try (Connection connection = connMan.connectDb()) {
    connection.setAutoCommit(false);

    String query = """
    UPDATE peminjam 
    SET nama_penyewa = ?, no_telp = ?, alamat = ?, ps = ?, durasi_hari = ?, tgl_pengambilan = ?, tgl_tenggat = ?, harga = ?, 
    total_biaya = ?, tgl_pengembalian = ?, total_denda = ? 
    WHERE id_peminjam = ?""";

    PreparedStatement statement = connection.prepareStatement(query);
    
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
        connection.commit();
        System.out.println("Data berhasil diperbarui.");
    } else {
        System.err.println("Tidak ada baris yang diperbarui. Periksa ID atau data yang diberikan.");
    }
} catch (SQLException e) {
    System.err.println("Error: " + e.getMessage());
}


//    String query = """
//    UPDATE peminjam 
//    SET nama_penyewa = ?, no_telp = ?, alamat = ?, ps = ?, durasi_hari = ?, 
//        tgl_pengambilan = ?, tgl_tenggat = ?, harga = ?, total_biaya = ?, 
//        tgl_pengembalian = ?, total_denda = ? 
//    WHERE id_peminjam = ?""";
//    try (Connection connection = connMan.connectDb()) {
//    connection.setAutoCommit(false);  // Auto-commit dinonaktifkan
//
//    String query = """
//    UPDATE peminjam 
//    SET nama_penyewa = ?, no_telp = ?, alamat = ?, ps = ?, durasi_hari = ?, 
//        tgl_pengambilan = ?, tgl_tenggat = ?, harga = ?, total_biaya = ?, 
//        tgl_pengembalian = ?, total_denda = ? 
//    WHERE id_peminjam = ?""";
//
//    PreparedStatement statement = connection.prepareStatement(query);
//    statement.setString(1, peminjam.getNama_Penyewa());
//    statement.setString(2, peminjam.getNo_Telp());
//    statement.setString(3, peminjam.getAlamat());
//    statement.setString(4, peminjam.getPs());
//    statement.setInt(5, peminjam.getDurasi_Hari());
//    statement.setString(6, peminjam.getTgl_Pengambilan());
//    statement.setString(7, peminjam.getTgl_Tenggat());
//    statement.setDouble(8, peminjam.getHarga());
//    statement.setDouble(9, peminjam.getTotal_Biaya());
//    statement.setDate(10, java.sql.Date.valueOf(peminjam.getTgl_pengembalian()));
//    statement.setDouble(11, peminjam.getTotal_denda());
//    statement.setInt(12, id_peminjaman);
//    
//
//    // Validasi tanggal pengembalian
//    try {
//        if (peminjam.getTgl_pengembalian() != null && !peminjam.getTgl_pengembalian().trim().isEmpty()) {
//            statement.setDate(10, java.sql.Date.valueOf(peminjam.getTgl_pengembalian()));
//        } else {
//            statement.setNull(10, java.sql.Types.DATE);
//        }
//    } catch (Exception e) {
//        System.err.println("Error saat parsing tanggal: " + e.getMessage());
//    }
//
//
//    statement.setDouble(11, peminjam.getTotal_denda());
//    statement.setInt(12, id_peminjaman);
//
//    int rowsUpdated = statement.executeUpdate();
//    if (rowsUpdated > 0) {
//        connection.commit();  // Commit jika berhasil
//        System.out.println("Data berhasil diperbarui.");
//    } else {
//        System.err.println("ID peminjam tidak ditemukan.");
//    }
//} catch (SQLException e) {
//    System.err.println("Error saat memperbarui data: " + e.getMessage());
//}
//    try (Connection connection = connMan.connectDb();
//     PreparedStatement statement = connection.prepareStatement(query)) {
//
//    statement.setString(1, peminjam.getNama_Penyewa());
//    statement.setString(2, peminjam.getNo_Telp());
//    statement.setString(3, peminjam.getAlamat());
//    statement.setString(4, peminjam.getPs());
//    statement.setInt(5, peminjam.getDurasi_Hari());
//    statement.setDate(6, java.sql.Date.valueOf(peminjam.getTgl_Pengambilan()));
//    statement.setDate(7, java.sql.Date.valueOf(peminjam.getTgl_Tenggat()));
//    statement.setDouble(8, peminjam.getHarga());
//    statement.setDouble(9, peminjam.getTotal_Biaya());
//    
//    if (peminjam.getTgl_pengembalian() != null) {
//        statement.setDate(10, java.sql.Date.valueOf(peminjam.getTgl_pengembalian()));
//    } else {
//        statement.setNull(10, java.sql.Types.DATE);
//    }
//
//    statement.setDouble(11, peminjam.getTotal_denda());
//    statement.setInt(12, id_peminjaman);
//
//    int rowsUpdated = statement.executeUpdate();
//    if (rowsUpdated > 0) {
//        System.out.println("Data berhasil diperbarui.");
//        connection.commit(); // Jika menggunakan transaksi manual
//    } else {
//        System.out.println("Data dengan ID tersebut tidak ditemukan.");
//    }
//} catch (SQLException e) {
//    System.err.println("Error saat memperbarui data: " + e.getMessage());
//} catch (Exception e) {
//    System.err.println("Error umum: " + e.getMessage());
//}

//    try (Connection connection = connMan.connectDb();
//         PreparedStatement statement = connection.prepareStatement(query)) {
//
//        statement.setString(1, peminjam.getNama_Penyewa());
//        statement.setString(2, peminjam.getNo_Telp());
//        statement.setString(3, peminjam.getAlamat());
//        statement.setString(4, peminjam.getPs());
//        statement.setInt(5, peminjam.getDurasi_Hari());
//        statement.setDate(6, java.sql.Date.valueOf(peminjam.getTgl_Pengambilan()));
//        statement.setDate(7, java.sql.Date.valueOf(peminjam.getTgl_Tenggat()));
//        statement.setDouble(8, peminjam.getHarga());
//        statement.setDouble(9, peminjam.getTotal_Biaya());
//        statement.setDate(10, java.sql.Date.valueOf(peminjam.getTgl_pengembalian()));
//        statement.setDouble(11, peminjam.getTotal_denda());
//        statement.setInt(12, id_peminjaman);
//
//        // Log query dan parameter untuk debugging
////        System.out.println("Query SQL: " + query);
////        System.out.println("Parameter: "
////            + peminjam.getNama_Penyewa() + ", "
////            + peminjam.getNo_Telp() + ", "
////            + peminjam.getAlamat() + ", "
////            + peminjam.getPs() + ", "
////            + peminjam.getDurasi_Hari() + ", "
////            + peminjam.getTgl_Pengambilan() + ", "
////            + peminjam.getTgl_Tenggat() + ", "
////            + peminjam.getHarga() + ", "
////            + peminjam.getTotal_Biaya() + ", "
////            + peminjam.getTgl_pengembalian() + ", "
////            + peminjam.getTotal_denda() + ", "
////            + id_peminjaman);
//
//        int rowsUpdated = statement.executeUpdate();
//        if (rowsUpdated > 0) {
//            System.out.println("Data berhasil diperbarui.");
//        } else {
//            System.out.println("Data dengan ID tersebut tidak ditemukan.");
//        }
//
//    } catch (SQLException e) {
//        System.err.println("Error saat memperbarui data: " + e.getMessage());
//    } catch (Exception e) {
//        System.err.println("Error umum: " + e.getMessage());
//    }
}




    @Override
    public boolean delete(int id_Peminjaman) {
        String query = "DELETE FROM peminjam WHERE id_peminjam = ?";
        try (Connection connection = connMan.connectDb();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id_Peminjaman);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error saat menghapus data: " + e.getMessage());
            return false;
        }
    }

    @Override
    public void updatePs(int id_Peminjaman, Peminjam object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void create(Peminjam object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
