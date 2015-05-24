/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.model;

import com.model.Petugas;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Dion Wisnu
 */
public class DataPetugasTableModel extends AbstractTableModel {
    private List<Petugas> dataPetugas = new ArrayList<Petugas>();

    public DataPetugasTableModel(List<Petugas> dataPetugasMasuk) {
        this.dataPetugas = dataPetugasMasuk;
    }

    public void deleteKategori(int row) {
        dataPetugas.remove(row);
        fireTableRowsDeleted(row, row);
    }

    public void updateKategori(int row, Petugas kategori) {
        dataPetugas.set(row, kategori);
        fireTableRowsUpdated(row, row);
    }

    public void addKategori(Petugas kategori) {
        dataPetugas.add(kategori);
        fireTableRowsInserted(getRowCount() - 1, getColumnCount() - 1);
    }

    public int getRowCount() {
        return dataPetugas.size();
    }

    public int getColumnCount() {
        return 3;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        Petugas b = dataPetugas.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return b.getId_petugas();
            case 1:
                return b.getNama_petugas();
            case 2:
                return b.getPassword();
            default:
                return "";
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "id_petugas";
            case 1:
                return "nama_petugas";
            case 2:
                return "password";
            default:
                return "";
        }
    }
}
