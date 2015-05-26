/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.control.KeluarParkir;

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
import java.util.List;

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
        String sql = "select status from kunjungan where plat_nomor='" + plat_no + "' AND tanggal_parkir= TO_CHAR(SYSDATE, 'fmDD MON YYYY') AND id_member='" + id_member + "'";
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

    public void kurangSaldoMember(Member member) throws SQLException {
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
                kunjungan.setJam_keluar(rset.getString("jam_keluar"));
                kunjungan.setPlat_nomor(rset.getString("plat_nomor"));
            }
        } catch (SQLException x) {
            System.out.println("Error = " + x.getMessage());
        }
        conn.commit();
        conn.close();
    }

    public List<Kunjungan> tampilDataParkirKeluar() throws SQLException {
        PreparedStatement statement = null;
        ResultSet result = null;
        Member mb = new Member();
        Petugas pt = new Petugas();
        try {
//            conn.setAutoCommit(false);
//            statement = conn.prepareStatement("select no_parkir, plat_nomor, id_petugas, id_member, jam_keluar, tanggal_parkir, status"
//                    + "from kunjungan order by tanggal_parkir");

//            statement = conn.prepareStatement("select no_parkir, plat_nomor, id_petugas, id_member, jam_keluar, tanggal_parkir, status"
//                    + " from kunjungan where tanggal_parkir = TO_CHAR(SYSDATE, 'fmDD MON YYYY') AND status = 'keluar' order by tanggal_parkir");
            
//            statement=conn.prepareStatement("select * from kunjungan where tanggal_parkir = TO_CHAR(SYSDATE, 'fmDD MON YYYY') AND status = 'keluar' order by tanggal_parkir");
            statement=conn.prepareStatement("select k.NO_PARKIR, k.PLAT_NOMOR, k.ID_PETUGAS, m.ID_MEMBER, k.JAM_KELUAR, k.TANGGAL_PARKIR, k.STATUS from kunjungan k, member m where tanggal_parkir = TO_CHAR(SYSDATE, 'fmDD MON YYYY') AND status = 'keluar' AND k.ID_MEMBER=m.ID_MEMBER order by k.tanggal_parkir");
//            statement=conn.prepareStatement("select * from kunjungan where status = 'keluar' order by tanggal_parkir");
            
            result = statement.executeQuery();
            List<Kunjungan> kategoris = new ArrayList<Kunjungan>();
            while (result.next()) {
                Kunjungan dataKunjunganKeluar = new Kunjungan();
                mb=new Member();
                pt=new Petugas();
                dataKunjunganKeluar.setNo_parkir(result.getString(1));
                dataKunjunganKeluar.setPlat_nomor(result.getString(2));
                
                pt.setId_petugas(result.getString(3));
                
                dataKunjunganKeluar.setId_petugas(pt);
                
                mb.setId_member(result.getString(4));
                dataKunjunganKeluar.setId_member(mb);
//                dataKunjunganKeluar.setJam_masuk(result.getString("jam_masuk"));
                dataKunjunganKeluar.setJam_keluar(result.getString(5));
                dataKunjunganKeluar.setTanggal_parkir(result.getString(6));
                dataKunjunganKeluar.setStatus(result.getString(7));
                kategoris.add(dataKunjunganKeluar);
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
    
    public List<Kunjungan> caritampilDataParkirKeluar(String key) throws SQLException {
        PreparedStatement statement = null;
        ResultSet result = null;
        Member mb = new Member();
        Petugas pt = new Petugas();
        try {
//            conn.setAutoCommit(false);
//            statement = conn.prepareStatement("select no_parkir, plat_nomor, id_petugas, id_member, jam_keluar, tanggal_parkir, status"
//                    + "from kunjungan order by tanggal_parkir");

//            statement = conn.prepareStatement("select no_parkir, plat_nomor, id_petugas, id_member, jam_keluar, tanggal_parkir, status"
//                    + " from kunjungan where tanggal_parkir = TO_CHAR(SYSDATE, 'fmDD MON YYYY') AND status = 'keluar' order by tanggal_parkir");
            
            statement=conn.prepareStatement("select * from kunjungan where tanggal_parkir = TO_CHAR(SYSDATE, 'fmDD MON YYYY') AND status = 'keluar' and id_member = '"+key+"' order by tanggal_parkir");
//            statement=conn.prepareStatement("select * from kunjungan where status = 'keluar' order by tanggal_parkir");
            
            result = statement.executeQuery();
            List<Kunjungan> kategoris = new ArrayList<Kunjungan>();
            while (result.next()) {
                Kunjungan dataKunjunganKeluar = new Kunjungan();
                dataKunjunganKeluar.setNo_parkir(result.getString("no_parkir"));
                dataKunjunganKeluar.setPlat_nomor(result.getString("plat_nomor"));
                pt.setId_petugas(result.getString("id_petugas"));
                dataKunjunganKeluar.setId_petugas(pt);
                mb.setId_member(result.getString("id_member"));
                dataKunjunganKeluar.setId_member(mb);
//                dataKunjunganKeluar.setJam_masuk(result.getString("jam_masuk"));
                dataKunjunganKeluar.setJam_keluar(result.getString("jam_keluar"));
                dataKunjunganKeluar.setTanggal_parkir(result.getString("tanggal_parkir"));
                dataKunjunganKeluar.setStatus(result.getString("status"));
                kategoris.add(dataKunjunganKeluar);
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
