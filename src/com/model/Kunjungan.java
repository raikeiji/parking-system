/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.model;

/**
 *
 * @author Dion Wisnu
 */
public class Kunjungan {
    private String no_parkir;
    private String plat_nomor;
    private String tanggal_parkir;
    private String jam_masuk;
    private String jam_keluar;
    private Petugas id_petugas;
    private Member id_member;
    private String Status;

    public String getNo_parkir() {
        return no_parkir;
    }

    public void setNo_parkir(String no_parkir) {
        this.no_parkir = no_parkir;
    }

    public String getPlat_nomor() {
        return plat_nomor;
    }

    public void setPlat_nomor(String plat_nomor) {
        this.plat_nomor = plat_nomor;
    }

    public String getTanggal_parkir() {
        return tanggal_parkir;
    }

    public void setTanggal_parkir(String tanggal_parkir) {
        this.tanggal_parkir = tanggal_parkir;
    }

    public String getJam_masuk() {
        return jam_masuk;
    }

    public void setJam_masuk(String jam_masuk) {
        this.jam_masuk = jam_masuk;
    }

    public String getJam_keluar() {
        return jam_keluar;
    }

    public void setJam_keluar(String jam_keluar) {
        this.jam_keluar = jam_keluar;
    }

    public Petugas getId_petugas() {
        return id_petugas;
    }

    public void setId_petugas(Petugas id_petugas) {
        this.id_petugas = id_petugas;
    }

    public Member getId_member() {
        return id_member;
    }

    public void setId_member(Member id_member) {
        this.id_member = id_member;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }
    
    
}
