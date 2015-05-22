/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.control.KeluarParkir;


import com.model.Kunjungan;
import com.model.Member;
import java.sql.SQLException;

/**
 *
 * @author Dion Wisnu
 */
public class TestKeluarParkirControl {

    public static void main(String[] args) {
        Member mb = new Member();
        Kunjungan kj = new Kunjungan();
        mb.setId_member("125314001");
        kj.setPlat_nomor("AB4369JK");
        kj.setId_member(mb);

        try {
            if (KeluarParkirControl.getKoneksiKeluarParkir().cekDataMember(mb)) {
                if (KeluarParkirControl.getKoneksiKeluarParkir().cekStatusKunjunganMember(kj)){
                    KeluarParkirControl.getKoneksiKeluarParkir().tambahDataKunjunganKeluar(mb);
                    KeluarParkirControl.getKoneksiKeluarParkir().kurangSaldoMember(mb);
                    KeluarParkirControl.getKoneksiKeluarParkir().tampilDataMemberKeluar(kj);
                    System.out.println("ID = " + kj.getId_member().getId_member());
                    System.out.println("Nama = " + kj.getId_member().getNama_member());
                    System.out.println("Sisa Saldo = " + kj.getId_member().getSaldo());
                    System.out.println("No Parkir = " + kj.getNo_parkir());
                    System.out.println("Tanggal Parkir = " + kj.getTanggal_parkir());
                    System.out.println("Jam Masuk = " + kj.getJam_masuk());
                    System.out.println("Jam Keluar = " + kj.getJam_keluar());
                    System.out.println("Plat Nomor = " + kj.getPlat_nomor());
                    System.out.println("Data Parkir terupdate");
                } else {
                    System.out.println("Member belum masuk parkir");
                    
                }
            } else {
                System.out.println("Member tidak terdaftar");
            }

        } catch (SQLException ex) {
            System.out.println("Gagal = " + ex);
        }
    }
}
