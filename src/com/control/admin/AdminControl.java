/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.control.admin;

import com.connection.Koneksi;
import com.model.Kunjungan;
import com.model.Member;
import com.model.Petugas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dion Wisnu
 */
public class AdminControl {
     private Connection conn;

    public AdminControl(Connection koneksi) {
        this.conn = koneksi;
    }

    public static AdminControl getKoneksiAdminControl() throws SQLException {
        AdminControl kon = new AdminControl(Koneksi.getDBConnection());
        return kon;
    }
    
    public List<Member> tampilDataMember() throws SQLException {
        PreparedStatement statement = null;
        ResultSet result = null;
        
        try {
//            conn.setAutoCommit(false);
            statement = conn.prepareStatement("select * from member order by id_member");
            result = statement.executeQuery();
            List<Member> kategoris = new ArrayList<Member>();
            while (result.next()) {
                Member mb = new Member();
                mb.setId_member(result.getString("id_member"));
                mb.setNama_member(result.getString("nama_member"));
                mb.setAlamat(result.getString("alamat"));
                mb.setSaldo(result.getString("saldo"));
                kategoris.add(mb);
            }
            conn.commit();
            return kategoris;
        } catch (SQLException exception) {
            throw exception;
        } finally {
            try {
                conn.setAutoCommit(true);
                if (result != null) {
                    result.close();
                }
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException exception) {
                throw exception;
            }
        }
    }
    
    public List<Petugas> tampilDataPetugas() throws SQLException {
        PreparedStatement statement = null;
        ResultSet result = null;
        try {
//            conn.setAutoCommit(false);
            statement = conn.prepareStatement("select * from petugas order by id_petugas");
            result = statement.executeQuery();
            List<Petugas> kategoris = new ArrayList<Petugas>();
            while (result.next()) {
                Petugas pt = new Petugas();
                pt.setId_petugas(result.getString("id_petugas"));
                pt.setNama_petugas(result.getString("nama_petugas"));
                pt.setPassword(result.getString("password"));
                kategoris.add(pt);
            }
            conn.commit();
            return kategoris;
        } catch (SQLException exception) {
            throw exception;
        } finally {
            try {
                conn.setAutoCommit(true);
                if (result != null) {
                    result.close();
                }
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException exception) {
                throw exception;
            }
        }
    }
    
}
