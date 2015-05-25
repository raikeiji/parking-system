/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.control.admin.editmember;


import com.connection.Koneksi;
import com.model.Kunjungan;
import com.model.Member;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Dion Wisnu
 */
public class EditMemberControl {
     private Connection conn;

    public EditMemberControl(Connection koneksi) {
        this.conn = koneksi;
    }

    public static EditMemberControl getKoneksiEditMember() throws SQLException {
        EditMemberControl kon = new EditMemberControl(Koneksi.getDBConnection());
        return kon;
    }
    
    public void pendaftaranMemberBaru(Member member)throws SQLException{
        PreparedStatement pstmt = null;
        try {
            String sql = "insert into member (id_member, nama_member, alamat, saldo) values (?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, member.getId_member());
            pstmt.setString(2, member.getNama_member());                     
            pstmt.setString(3, member.getAlamat());                     
            pstmt.setString(4, member.getSaldo());                     
            pstmt.executeUpdate();
            
        } catch (SQLException exception) {
            conn.rollback();
            throw exception;
        }
        conn.commit();
        conn.close();
    }
    
    public boolean cekDataMember(Member member) throws SQLException {
        Statement stmt = conn.createStatement();
        String id_member = member.getId_member();
        String sql = "select nama_member from member where id_member = '" + id_member + "'";
        boolean status = false;
        ResultSet rset = stmt.executeQuery(sql);
        while (rset.next()) {
            if (rset.getString("nama_member").equals("")) {
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
    
    public void updateDataMember(Member member)throws SQLException{
        PreparedStatement pstmt = null;
        try {
            String sql = "update member set nama_member=?, alamat=?, saldo=? where id_member=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, member.getNama_member());
            pstmt.setString(2, member.getAlamat());
            pstmt.setString(3, member.getSaldo());
            pstmt.setString(4, member.getId_member());
            pstmt.executeUpdate();
            conn.commit();
        } catch (SQLException exception) {
            conn.rollback();
            throw exception;
        }
        conn.close();
    }
    
    public void hapusDataMember(Member member)throws SQLException{
        PreparedStatement pstmt = null;
        try {
            String sql = "delete from member where id_member=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, member.getId_member());
            pstmt.executeUpdate();
            conn.commit();
        } catch (SQLException exception) {
            conn.rollback();
            throw exception;
        }
        conn.close();
    }
            
    public void tampilDataMemberMasuk(Member mem) throws SQLException {
        Statement stmt = conn.createStatement();
//        Member member = new Member();
//        String id_member = kunjungan.getId_member().getId_member();
        String id_member=mem.getId_member();
        String sql = "select * from MEMBER where ID_MEMBER='"+id_member+"'";
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
                mem.setNama_member(rset.getString(2));
                mem.setAlamat(rset.getString(3));
                mem.setSaldo(rset.getString(4));
            }
        } catch (SQLException x) {
            System.out.println("Error = " + x.getMessage());
        }
        conn.commit();
        conn.close();
    }

    public void tampilDataMemberMasuk(Kunjungan kunjungan) throws SQLException {
        Statement stmt = conn.createStatement();
        Member member = new Member();
        String id_member = kunjungan.getId_member().getId_member();
        String sql = "select m.id_member, m.nama_member, m.saldo, k.no_parkir, k.tanggal_parkir, k.jam_masuk, k.plat_nomor "
                + "from member m, kunjungan k "
                + "where k.tanggal_parkir= TO_CHAR(SYSDATE, 'fmDD MON YYYY') AND m.id_member = '" + id_member + "' AND k.id_member = '" + id_member + "'";
        try {
            ResultSet rset = stmt.executeQuery(sql);
            while (rset.next()) {
                member.setId_member(rset.getString("id_member"));
                member.setNama_member(rset.getString("nama_member"));
                member.setSaldo(rset.getString("saldo"));
                kunjungan.setId_member(member);
                kunjungan.setNo_parkir(rset.getString("no_parkir"));
                kunjungan.setTanggal_parkir(rset.getString("tanggal_parkir"));
                kunjungan.setJam_masuk(rset.getString("jam_masuk"));
                kunjungan.setPlat_nomor(rset.getString("plat_nomor"));
            }
        } catch (SQLException x) {
            System.out.println("Error = " + x.getMessage());
        }
        conn.commit();
        conn.close();
    }

    
}
