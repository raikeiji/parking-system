/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.model;

import com.model.Member;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Dion Wisnu
 */
public class DataMemberTableModel extends AbstractTableModel {
    private List<Member> dataMember = new ArrayList<Member>();

    public DataMemberTableModel(List<Member> dataMemberMasuk) {
        this.dataMember = dataMemberMasuk;
    }

    public void deleteKategori(int row) {
        dataMember.remove(row);
        fireTableRowsDeleted(row, row);
    }

    public void updateKategori(int row, Member kategori) {
        dataMember.set(row, kategori);
        fireTableRowsUpdated(row, row);
    }

    public void addKategori(Member kategori) {
        dataMember.add(kategori);
        fireTableRowsInserted(getRowCount() - 1, getColumnCount() - 1);
    }

    public int getRowCount() {
        return dataMember.size();
    }

    public int getColumnCount() {
        return 4;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        Member b = dataMember.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return b.getId_member();
            case 1:
                return b.getNama_member();
            case 2:
                return b.getAlamat();
            case 3:
                return b.getSaldo();
            default:
                return "";
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "id_member";
            case 1:
                return "nama_member";
            case 2:
                return "alamat";
            case 3:
                return "saldo";
            case 4:
                return "jam_masuk";
            default:
                return "";
        }
    }
}
