/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.control.KeluarParkir;

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
public class KeluarParkirControl {

    private Connection conn;

    public KeluarParkirControl(Connection koneksi) {
        this.conn = koneksi;
    }

    public static KeluarParkirControl getKoneksiKeluarParkir() throws SQLException {
        KeluarParkirControl kon = new KeluarParkirControl(Koneksi.getDBConnection());
        return kon;
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

    public boolean cekStatusKunjunganMember(Kunjungan kunjungan) throws SQLException {
        Statement stmt = conn.createStatement();
        String id_member = kunjungan.getId_member().getId_member();
        String plat_no = kunjungan.getPlat_nomor();
        boolean status = false;
        String sql = "select status from kunjungan where plat_nomor='"+plat_no+"' AND tanggal_parkir= TO_CHAR(SYSDATE, 'fmDD MON YYYY') AND id_member='" + id_member + "'";
        try {
            ResultSet rset = stmt.executeQuery(sql);
            while (rset.next()) {
                if (rset.getString("status").equals("masuk")) {
                    status = true;
                } else {
                    status = false;
                }
            }
            System.out.println("status : " + status);
        } catch (SQLException x) {
            System.out.println("Error = " + x.getMessage());
        }
        conn.commit();
        conn.close();
        return status;
    }

    public void tambahDataKunjunganKeluar(Member member) throws SQLException {
        PreparedStatement pstmt = null;
        try {
            String sql = "update kunjungan SET jam_keluar = TO_CHAR(SYSDATE, 'fmHH24:MI:SS'), status = 'keluar' "
                    + "where tanggal_parkir= TO_CHAR(SYSDATE, 'fmDD MON YYYY') AND id_member=?";
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
    
    public void kurangSaldoMember(Member member)throws SQLException{
        PreparedStatement pstmt = null;
        try {
            String sql = "update member set saldo = (select (saldo-2000) from member where id_member=?) where id_member=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, member.getId_member());
            pstmt.setString(2, member.getId_member());
            pstmt.executeUpdate();
            conn.commit();
        } catch (SQLException exception) {
            conn.rollback();
            throw exception;
        }
        conn.close();
    }
    
    public void tampilDataMemberKeluar(Kunjungan kunjungan) throws SQLException {
        Statement stmt = conn.createStatement();
        Member member = new Member();
        String id_member = kunjungan.getId_member().getId_member();
        String sql = "select m.id_member, m.nama_member, m.saldo, k.no_parkir, k.tanggal_parkir, k.jam_masuk, k.jam_keluar,k.plat_nomor "
                + "from member m, kunjungan k "
                + "where k.tanggal_parkir= TO_CHAR(SYSDATE, 'fmDD MON YYYY') AND m.id_member = '"+id_member+"' AND k.id_member = '"+id_member+"'";
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
                kunjungan.setJam_keluar(rset.getString("jam_keluar"));
                kunjungan.setPlat_nomor(rset.getString("plat_nomor"));
            }
        } catch (SQLException x) {
            System.out.println("Error = " + x.getMessage());
        }
        conn.commit();
        conn.close(); 
    }
}
