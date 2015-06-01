/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.control.TambahSaldo;

import com.connection.Koneksi;
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
public class TambahSaldoControl {

    private Connection conn;

    public TambahSaldoControl(Connection koneksi) {
        this.conn = koneksi;
    }

    public static TambahSaldoControl getKoneksiTambahSaldo() throws SQLException {
        TambahSaldoControl kon = new TambahSaldoControl(Koneksi.getDBConnection());
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

    public String getLastSaldo(Member member) throws SQLException {
        Statement stmt = conn.createStatement();
        String lastSaldo = null;
        String sql = "select saldo from member "
                + "where id_member='" + member.getId_member() + "'";
        try {
            ResultSet rset = stmt.executeQuery(sql);
            while (rset.next()) {
                lastSaldo = rset.getString("saldo");
            }
        } catch (SQLException x) {
            System.out.println("Error = " + x.getMessage());
        }
        conn.commit();
        conn.close();
        return lastSaldo;
    }

    public void tambahSaldo(Member member, int saldoBaru) throws SQLException {
        PreparedStatement pstmt = null;

        try {
//            member = new Member();
            String lastSaldo = getKoneksiTambahSaldo().getLastSaldo(member);
            int saldoAkhir = Integer.parseInt(lastSaldo);
            String currentSaldo = member.getSaldo();
//            int saldoSekarang = Integer.parseInt(currentSaldo);
            int totalSaldo = saldoAkhir + saldoBaru;
            String newSaldo = String.valueOf(totalSaldo);
            System.out.println(lastSaldo + " " + saldoBaru + " " + newSaldo);
            
            String sql = "update member set saldo = ? where id_member = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, newSaldo);
            pstmt.setString(2, member.getId_member());
            pstmt.executeUpdate();

        } catch (SQLException exception) {
            conn.rollback();
            throw exception;
        }
        conn.commit();
        conn.close();
    }
}
