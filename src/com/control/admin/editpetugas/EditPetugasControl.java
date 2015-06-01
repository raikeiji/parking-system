/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.control.admin.editpetugas;


import com.control.admin.editmember.*;
import com.connection.Koneksi;
import com.model.Kunjungan;
import com.model.Member;
import com.model.Petugas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Dion Wisnu
 */
public class EditPetugasControl {
     private Connection conn;

    public EditPetugasControl(Connection koneksi) {
        this.conn = koneksi;
    }

    public static EditPetugasControl getKoneksiEditPetugas() throws SQLException {
        EditPetugasControl kon = new EditPetugasControl(Koneksi.getDBConnection());
        return kon;
    }
    
    public void pendaftaranPetugasBaru(Petugas petugas)throws SQLException{
        PreparedStatement pstmt = null;
        try {
            String sql = "insert into petugas (ID_PETUGAS, NAMA_PETUGAS, PASSWORD, STATUS) values (?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, petugas.getId_petugas());
            pstmt.setString(2, petugas.getNama_petugas());                     
            pstmt.setString(3, petugas.getPassword());                     
            pstmt.setString(4, petugas.getStatus());                     
            pstmt.executeUpdate();
            
        } catch (SQLException exception) {
            conn.rollback();
            throw exception;
        }
        conn.commit();
        conn.close();
    }
    
    public boolean cekDataPetugas(Petugas petugas) throws SQLException {
        Statement stmt = conn.createStatement();
        String id_petugas = petugas.getId_petugas();
        String sql = "select NAMA_PETUGAS from PETUGAS where ID_PETUGAS = '" + id_petugas + "'";
        boolean status = false;
        ResultSet rset = stmt.executeQuery(sql);
        while (rset.next()) {
            if (rset.getString("NAMA_PETUGAS").equals("")) {
                status = false;
            } else {
                status = true;
            }
        }
        conn.commit();
        conn.close();
        System.out.println("status : " + status);
        return status;
    }
    
    public void updateDataPetugas(Petugas petugas)throws SQLException{
        PreparedStatement pstmt = null;
        try {
            String sql = "update PETUGAS set NAMA_PETUGAS=?, PASSWORD=?, STATUS=? where ID_PETUGAS=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, petugas.getNama_petugas());
            pstmt.setString(2, petugas.getPassword());
            pstmt.setString(3, petugas.getStatus());
            pstmt.setString(4, petugas.getId_petugas());
            pstmt.executeUpdate();
            conn.commit();
        } catch (SQLException exception) {
            conn.rollback();
            throw exception;
        }
        conn.close();
    }
    
    public void hapusDataPetugas(Petugas petugas)throws SQLException{
        PreparedStatement pstmt = null;
        try {
            String sql = "delete from PETUGAS where ID_PETUGAS=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, petugas.getId_petugas());
            pstmt.executeUpdate();
            conn.commit();
        } catch (SQLException exception) {
            conn.rollback();
            throw exception;
        }
        conn.close();
    }
     
    public void hapusDataPetugasFromTableKunjungan(Kunjungan kun)throws SQLException{
        PreparedStatement pstmt = null;
        try {
            String sql = "delete from KUNJUNGAN where ID_PETUGAS=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, kun.getId_petugas().getId_petugas());
            pstmt.executeUpdate();
            conn.commit();
        } catch (SQLException exception) {
            conn.rollback();
            throw exception;
        }
        conn.close();
    }
    
    public void tampilDataPetugasMasuk(Petugas mem) throws SQLException {
        Statement stmt = conn.createStatement();
//        Member member = new Member();
//        String id_member = kunjungan.getId_member().getId_member();
        String id_member=mem.getId_petugas();
        String sql = "select * from PETUGAS where ID_PETUGAS='"+id_member+"'";
        try {
            ResultSet rset = stmt.executeQuery(sql);
            while (rset.next()) {
//                member.setId_member(rset.getString("id_member"));
//                member.setNama_member(rset.getString("nama_member"));
//                member.setSaldo(rset.getString("saldo"));
//                kunjungan.setId_member(member);
//                kunjungan.setNo_parkir(rset.getString("no_parkir"));
//                kunjungan.setTanggal_parkir(rset.getString("tanggal_parkir"));
//                kunjungan.setJam_masuk(rset.getString("jam_masuk"));
//                kunjungan.setPlat_nomor(rset.getString("plat_nomor"));
                mem.setNama_petugas(rset.getString(2));
                mem.setPassword(rset.getString(3));
                mem.setStatus(rset.getString(4));
            }
        } catch (SQLException x) {
            System.out.println("Error = " + x.getMessage());
        }
        conn.commit();
        conn.close();
    }

    
}
