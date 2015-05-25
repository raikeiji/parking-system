/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.control.admin.editmember;

import com.control.admin.editmember.EditMemberControl;
import com.model.Member;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dion Wisnu
 */
public class TestEditMember {

    public static void main(String[] args) {
        Member mb = new Member();
        mb.setId_member("125314061");
        mb.setNama_member("Theo Mahardian Bangkit Sugiri");
        mb.setAlamat("Paingan");
        mb.setSaldo("15000");
        try {
            if (EditMemberControl.getKoneksiEditMember().cekDataMember(mb)) {
//                System.out.println("Pendaftaran gagal, member sudah terdaftar");
//                EditMemberControl.getKoneksiEditMember().updateDataMember(mb);
//                System.out.println("Update Data Berhasil");
                EditMemberControl.getKoneksiEditMember().hapusDataMember(mb);
                System.out.println("Hapus Data Member Berhasil");
            } else {
//                EditMemberControl.getKoneksiEditMember().pendaftaranMemberBaru(mb);
//                System.out.println("Pendaftaran Berhasil");
//                System.out.println("Update Data Gagal, member belum terdaftar");
                System.out.println("Hapus Data Member Gagal, data tidak ditemukan");
            }

        } catch (SQLException ex) {
            System.out.println("Kueri gagal : " + ex.getMessage());
        }
    }
}
