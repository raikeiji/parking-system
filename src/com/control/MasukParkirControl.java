///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.control;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
///**
// *
// * @author rai
// */
//public class MasukParkirControl {
//
//    Connection conn;
//
//    public MasukParkirControl(Connection kon) {
//        this.conn = kon;
//    }
//
//    public static MasukParkirControl getKoneksiMasukParkir() {
//        MasukParkirControl kon = new MasukParkirControl(com.connection.Koneksi.getDBConnection());
//        return kon;
//    }
//
//    public String cariIDMember(com.model.MemberModel mem) throws SQLException {
//        PreparedStatement prepare = null;
//        ResultSet result = null;
//        String idMember = mem.getIdMember();
//        String namaMember = mem.getNamaMember();
//        int saldo = mem.getSaldo();
//
//        try {
//            conn.setAutoCommit(true);
//            String sql = "SELECT ID_MEMBER,NAMA_MEMBER,SALDO FROM MEMBER WHERE ID_MEMBER=?";
//            prepare = conn.prepareStatement(sql);
//            prepare.setString(1, idMember);
//            result = prepare.executeQuery();
//            if (result.next()) {
//                idMember = result.getString("ID_MEMBER");
//                namaMember = result.getString("NAMA_MEMBER");
//                saldo = result.getInt("SALDO");
//            }
//            conn.commit();
//        } catch (Exception e) {
//            conn.rollback();
//        }
//        return idMember;
//    }
//
//    public String cariNamaMember(com.model.MemberModel mem) throws SQLException {
//        PreparedStatement prepare = null;
//        ResultSet result = null;
//        String idMember = mem.getIdMember();
//        String namaMember = mem.getNamaMember();
//        int saldo = mem.getSaldo();
//
//        try {
//            conn.setAutoCommit(true);
//            String sql = "SELECT ID_MEMBER,NAMA_MEMBER,SALDO FROM MEMBER WHERE ID_MEMBER=?";
//            prepare = conn.prepareStatement(sql);
//            prepare.setString(1, namaMember);
//            result = prepare.executeQuery();
//            if (result.next()) {
//                idMember = result.getString("ID_MEMBER");
//                namaMember = result.getString("NAMA_MEMBER");
//                saldo = result.getInt("SALDO");
//            }
//            conn.commit();
//        } catch (Exception e) {
//            conn.rollback();
//        }
//        return namaMember;
//    }
//    
//    public int cariSaldoMember(com.model.MemberModel mem) throws SQLException {
//        PreparedStatement prepare = null;
//        ResultSet result = null;
//        String idMember = mem.getIdMember();
//        String namaMember = mem.getNamaMember();
//        int saldo = mem.getSaldo();
//
//        try {
//            conn.setAutoCommit(true);
//            String sql = "SELECT ID_MEMBER,NAMA_MEMBER,SALDO FROM MEMBER WHERE ID_MEMBER=?";
//            prepare = conn.prepareStatement(sql);
////            prepare.setString(1, saldo);
//            prepare.setInt(1, saldo);
//            result = prepare.executeQuery();
//            if (result.next()) {
//                idMember = result.getString("ID_MEMBER");
//                namaMember = result.getString("NAMA_MEMBER");
//                saldo = result.getInt("SALDO");
//            }
//            conn.commit();
//        } catch (Exception e) {
//            conn.rollback();
//        }
//        return saldo;
//    }
//}
