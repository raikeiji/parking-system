/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.control;

import com.model.LoginModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author rai
 */
public class LoginControl {

    private final Connection conn;

    public LoginControl(Connection conn) {
        this.conn = conn;
    }

    public static LoginControl getKoneksiLogin() throws SQLException {
        LoginControl kon = new LoginControl(com.connection.Koneksi.getDBConnection());
        return kon;
    }

    public String cekDataLogin(com.model.LoginModel lg) throws SQLException {
        PreparedStatement prepare = null;
        ResultSet result = null;
        
        String id_number=lg.getId_petugas(); 
        String password=lg.getPassword();
        String status="";
        
        try {
            conn.setAutoCommit(true);
            String sql = "SELECT id_petugas, nama_petugas, password, status FROM PETUGAS WHERE ID_PETUGAS=? AND PASSWORD=?";
            prepare = conn.prepareStatement(sql);
            prepare.setString(1, id_number);
            prepare.setString(2, password);
            result = prepare.executeQuery();
            if (result.next()) {
                status = result.getString("status");
            }
            conn.commit();
        } catch (SQLException e) {
            conn.rollback();
        }
        return status;        
    }
    
//    public String carikategoribarang(kategoribarang kategori) throws Exception {
//        PreparedStatement prepare = null;
//        ResultSet result = null;
//        String namaBarang = "";
//        try {
//            conn.setAutoCommit(true);
//            String sql = "select namakategori from kategori where kodekategori="+kategori+"";
//            prepare = conn.prepareStatement(sql);
//            prepare.setString(1, kategori.getKode_kategori());
//            result = prepare.executeQuery();
//            if (result.next()) {
//                namaBarang = result.getString("namakategori");
//            }
//            conn.commit();
//        } catch (Exception e) {
//            conn.rollback();
//        }
//        return namaBarang;
//    }

    public static void main(String[] args) throws SQLException {
        LoginControl l=new LoginControl(com.connection.Koneksi.getDBConnection());
        com.model.LoginModel lf=new LoginModel();
        lf.setId_petugas("K.1234");
        lf.setPassword("dion");
        l.cekDataLogin(lf);
    }
}
