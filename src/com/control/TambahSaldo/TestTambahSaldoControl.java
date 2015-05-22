/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.control.TambahSaldo;


import com.model.Member;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dion Wisnu
 */
public class TestTambahSaldoControl {

    public static void main(String[] args) {
        Member mb = new Member();
        mb.setSaldo("25000");
        mb.setId_member("125314001");
        System.out.println(mb.getSaldo());
        try {
            if (TambahSaldoControl.getKoneksiTambahSaldo().cekDataMember(mb)) {
                TambahSaldoControl.getKoneksiTambahSaldo().tambahSaldo(mb);
                System.out.println("Tambah saldo berhasil");
            } else {
                System.out.println("Tambah saldo gagal, member tidak terdaftar");
            }
        } catch (SQLException ex) {
            System.out.println("Tambah saldo gagal = " + ex.getMessage());
        }
    }
}
