/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.control.MasukParkir;

import com.connection.Koneksi;
import com.model.Kunjungan;
import com.model.Member;
import com.model.Petugas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
        try {
            MasukParkirControl.getKoneksiMasukParkir().getLastNoParkir();
            no_parkir = getKoneksiMasukParkir().getNextNoParkir();
        } catch (NullPointerException ne) {
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
//        String sql = "select m.id_member, m.nama_member, m.saldo, k.no_parkir, k.tanggal_parkir, k.jam_masuk, k.plat_nomor "
//                + "from member m, kunjungan k "
//                + "where k.tanggal_parkir= TO_CHAR(SYSDATE, 'fmDD MON YYYY') AND m.id_member = '" + id_member + "' AND k.id_member = '" + id_member + "'";
        String sql = "select m.id_member, m.nama_member, m.saldo, k.no_parkir, k.tanggal_parkir, k.jam_masuk, k.plat_nomor "
                + "from member m, kunjungan k "
                + "where k.tanggal_parkir= TO_CHAR(SYSDATE, 'fmDD MON YYYY') AND m.id_member = k.id_member AND k.id_member = '" + id_member + "'";
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

    public List<Kunjungan> tampilDataParkirMasuk() throws SQLException {
        PreparedStatement statement = null;
        ResultSet result = null;
        Member mb = new Member();
        Petugas pt = new Petugas();
        List<Kunjungan> kategoris = new ArrayList<Kunjungan>();
        try {
//            conn.setAutoCommit(false);
            statement = conn.prepareStatement("select * from kunjungan where tanggal_parkir = TO_CHAR(SYSDATE, 'fmDD MON YYYY') AND status = 'masuk' order by tanggal_parkir");
            result = statement.executeQuery();
            
            while (result.next()) {
                Kunjungan dataKunjunganMasuk = new Kunjungan();
                mb = new Member();
                pt=new Petugas();
                dataKunjunganMasuk.setNo_parkir(result.getString("no_parkir"));
                dataKunjunganMasuk.setPlat_nomor(result.getString("plat_nomor"));
                pt.setId_petugas(result.getString("id_petugas"));
                dataKunjunganMasuk.setId_petugas(pt);
                mb.setId_member(result.getString("id_member"));

                dataKunjunganMasuk.setId_member(mb);
//                
                dataKunjunganMasuk.setJam_masuk(result.getString("jam_masuk"));
                dataKunjunganMasuk.setTanggal_parkir(result.getString("tanggal_parkir"));
                dataKunjunganMasuk.setStatus(result.getString("status"));

                // bug here -> setelah masuk list, dataKunjunganMasuk.getId_Member().getId_Member() berubah menjadi 002, padahal sebelum masuk list tidak ya
                kategoris.add(dataKunjunganMasuk);
            }
            conn.commit();
//            Iterator<Kunjungan> iterator = kategoris.iterator();
//            while (iterator.hasNext()) {
//                Kunjungan kunjungan = iterator.next();
//                System.out.println(" --- " + kunjungan.getId_member().getId_member());
//            }
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

    public List<Kunjungan> caritampilDataParkirMasuk(String key) throws SQLException {
        PreparedStatement statement = null;
        ResultSet result = null;
        Member mb = new Member();
        Petugas pt = new Petugas();
        try {
//            conn.setAutoCommit(false);
            statement = conn.prepareStatement("select * from kunjungan where tanggal_parkir = TO_CHAR(SYSDATE, 'fmDD MON YYYY') AND status = 'masuk' AND id_member= '" + key + "' order by tanggal_parkir");
            result = statement.executeQuery();
            List<Kunjungan> kategoris = new ArrayList<Kunjungan>();
            while (result.next()) {
                Kunjungan dataKunjunganMasuk = new Kunjungan();
                dataKunjunganMasuk.setNo_parkir(result.getString("no_parkir"));
                dataKunjunganMasuk.setPlat_nomor(result.getString("plat_nomor"));
                pt.setId_petugas(result.getString("id_petugas"));
                dataKunjunganMasuk.setId_petugas(pt);
                mb.setId_member(result.getString("id_member"));
                dataKunjunganMasuk.setId_member(mb);
                dataKunjunganMasuk.setJam_masuk(result.getString("jam_masuk"));
                dataKunjunganMasuk.setTanggal_parkir(result.getString("tanggal_parkir"));
                dataKunjunganMasuk.setStatus(result.getString("status"));
                kategoris.add(dataKunjunganMasuk);
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
