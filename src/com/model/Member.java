package com.model;

/**
 *
 * @author Dion Wisnu
 */
public class Member {
    private String id_member;
    private String nama_member;
    private String alamat;
    private String saldo;

    public String getId_member() {
        return id_member;
    }

    public void setId_member(String no_member) {
        this.id_member = no_member;
    }

    public String getNama_member() {
        return nama_member;
    }

    public void setNama_member(String nama_member) {
        this.nama_member = nama_member;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getSaldo() {
        return saldo;
    }

    public void setSaldo(String saldo) {
        this.saldo = saldo;
    }
    
    
}
