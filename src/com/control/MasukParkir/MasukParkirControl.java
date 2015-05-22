/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.control.MasukParkir;

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
public class MasukParkirControl {

    private Connection conn;

    public MasukParkirControl(Connection koneksi) {
        this.conn = koneksi;
    }

    public static MasukParkirControl getKoneksiMasukParkir() throws SQLException {
        MasukParkirControl kon = new MasukParkirControl(Koneksi.getDBConnection());
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

    public boolean cekStatusKunjunganMember(Member member) throws SQLException {
        Statement stmt = conn.createStatement();
        String id_member = member.getId_member();
        boolean status = false;
        String sql = "select status from kunjungan where tanggal_parkir= TO_CHAR(SYSDATE, 'fmDD MON YYYY') AND id_member='" + id_member + "'";
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

    public boolean getLastNoParkir() throws SQLException {
        Statement stmt = conn.createStatement();
        boolean status = false;
        String sql = "select substr(max(no_parkir),8,10) as last_num "
                + "from kunjungan "
                + "where tanggal_parkir = TO_CHAR(SYSDATE, 'fmDD MON YYYY')";
        try {
            ResultSet rset = stmt.executeQuery(sql);
            while (rset.next()) {
                if (rset.getString("last_num").equals("")) {
                    status = false;
                } else {
                    status = true;
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

    public String getNewNoParkir() throws SQLException {
        Statement stmt = conn.createStatement();
        String no_baru = "";
        String sql = "select concat(TO_CHAR(SYSDATE,'DDMMYY'),(to_char(001,'009'))) as no_parkir from dual";
        try {
            ResultSet rset = stmt.executeQuery(sql);
            while (rset.next()) {
                no_baru = rset.getString("no_parkir");
            }
        } catch (SQLException x) {
            System.out.println("Error = " + x.getMessage());
        }
        conn.commit();
        conn.close();
        return no_baru;
    }

    public String getNextNoParkir() throws SQLException {
        Statement stmt = conn.createStatement();
        String no_baru = "";
        String sql = "select concat(TO_CHAR(SYSDATE,'DDMMYY'),(to_char(substr(max(no_parkir),8,10)+1,'009'))) as no_parkir from kunjungan";
        try {
            ResultSet rset = stmt.executeQuery(sql);
            while (rset.next()) {
                no_baru = rset.getString("no_parkir");
            }
        } catch (SQLException x) {
            System.out.println("Error = " + x.getMessage());
        }
        conn.commit();
        conn.close();
        return no_baru;
    }

    public void tambahDataKunjunganMasuk(Kunjungan kunjungan) throws SQLException {
        PreparedStatement pstmt = null;
        String no_parkir = null;
//        if (MasukParkirControl.getKoneksiMasukParkir().getLastNoParkir()) {
//            no_parkir = getKoneksiMasukParkir().getNextNoParkir();
//        } else if (!MasukParkirControl.getKoneksiMasukParkir().getLastNoParkir()) {
//           no_parkir = getKoneksiMasukParkir().getNewNoParkir();
//        }
        try{
            MasukParkirControl.getKoneksiMasukParkir().getLastNoParkir();
            no_parkir = getKoneksiMasukParkir().getNextNoParkir();
        }catch(NullPointerException ne){
            no_parkir = MasukParkirControl.getKoneksiMasukParkir().getNewNoParkir();
        }
        try {
            String sql = "insert into kunjungan (no_parkir, plat_nomor, tanggal_parkir, jam_masuk, id_petugas, id_member, status)"
                    + "VALUES (?, ?, TO_CHAR(SYSDATE, 'fmDD MON YYYY'), TO_CHAR(SYSDATE, 'fmHH24:MI:SS'), ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, no_parkir);
            pstmt.setString(2, kunjungan.getPlat_nomor());
            pstmt.setString(3, kunjungan.getId_petugas().getId_petugas());
            pstmt.setString(4, kunjungan.getId_member().getId_member());
            pstmt.setString(5, "masuk");
            pstmt.executeUpdate();
            conn.commit();
        } catch (SQLException exception) {
            conn.rollback();
            throw exception;
        }
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
