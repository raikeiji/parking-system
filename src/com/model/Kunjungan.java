/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;
import java.util.Date;
/**
 *
 * @author Arakhel
 */
public class Kunjungan {
    private Date tanggal_parkir;
    private String plat_nomor, no_parkir;

    public Date getTanggal_parkir() {
        return tanggal_parkir;
    }

    public void setTanggal_parkir(Date tanggal_parkir) {
        this.tanggal_parkir = tanggal_parkir;
    }

    public String getPlat_nomor() {
        return plat_nomor;
    }

    public void setPlat_nomor(String plat_nomor) {
        this.plat_nomor = plat_nomor;
    }

    public String getNo_parkir() {
        return no_parkir;
    }

    public void setNo_parkir(String no_parkir) {
        this.no_parkir = no_parkir;
    }
    
}
