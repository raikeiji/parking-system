/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.control.MasukParkir;


import com.model.Kunjungan;
import com.model.Member;
import com.model.Petugas;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dion Wisnu
 */
public class TestMasukParkirControl {

    public static void main(String[] args) {

        Member mb = new Member();
        Kunjungan kj = new Kunjungan();
        Petugas pt = new Petugas();
        kj.setPlat_nomor("AB4369JK");
        pt.setId_petugas("K.1024");
        kj.setId_petugas(pt);
        mb.setId_member("125314001");
        kj.setId_member(mb);

        try {
            if (MasukParkirControl.getKoneksiMasukParkir().cekDataMember(mb)) {
                if (!MasukParkirControl.getKoneksiMasukParkir().cekStatusKunjunganMember(mb)) {
                    MasukParkirControl.getKoneksiMasukParkir().tambahDataKunjunganMasuk(kj);
                    MasukParkirControl.getKoneksiMasukParkir().tampilDataMemberMasuk(kj);
                    System.out.println("ID = " + kj.getId_member().getId_member());
                    System.out.println("Nama = " + kj.getId_member().getNama_member());
                    System.out.println("Saldo = " + kj.getId_member().getSaldo());
                    System.out.println("No Parkir = " + kj.getNo_parkir());
                    System.out.println("Tanggal Parkir = " + kj.getTanggal_parkir());
                    System.out.println("Jam Masuk = " + kj.getJam_masuk());
                    System.out.println("Plat Nomor = " + kj.getPlat_nomor());
                    System.out.println("Data Parkir bertambah");
                } else {
                    System.out.println("Gagal, status member sedang parkir");
                }
            } else {
                System.out.println("Data Parkir gagal bertambah");
            }
//            if(MasukParkirControl.getKoneksiMasukParkir().getLastNoParkir()){
//                System.out.println("nomor parkir ada");
//            }else{
//                System.out.println("nomor parkir tidak ada");
//            }
//            MasukParkirControl.getKoneksiMasukParkir().getLastNoParkir();
        } catch (SQLException ex) {
            System.out.println("Gagal = " + ex);
        } catch (NullPointerException ex) {
//            System.out.println("Tanggal tidak ada");
//            System.out.println("Tanggal parkir baru : ");
//            try {
//                System.out.print(MasukParkirControl.getKoneksiMasukParkir().getNewNoParkir());
//            } catch (SQLException ex1) {
//                Logger.getLogger(TestMasukParkirControl.class.getName()).log(Level.SEVERE, null, ex1);
//            }
        }
    }
}
