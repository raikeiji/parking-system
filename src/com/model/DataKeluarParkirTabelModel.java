/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

import com.control.KeluarParkir.*;
import com.model.Kunjungan;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Dion Wisnu
 */
public class DataKeluarParkirTabelModel extends AbstractTableModel {

    private List<Kunjungan> dataKunjunganKeluar = new ArrayList<Kunjungan>();

    public DataKeluarParkirTabelModel(List<Kunjungan> dataKunjunganKeluar) {
        this.dataKunjunganKeluar = dataKunjunganKeluar;
    }

    public void deleteKategori(int row) {
        dataKunjunganKeluar.remove(row);
        fireTableRowsDeleted(row, row);
    }

    public void updateKategori(int row, Kunjungan kategori) {
        dataKunjunganKeluar.set(row, kategori);
        fireTableRowsUpdated(row, row);
    }

    public void addKategori(Kunjungan kategori) {
        dataKunjunganKeluar.add(kategori);
        fireTableRowsInserted(getRowCount() - 1, getColumnCount() - 1);
    }

    public int getRowCount() {
        return dataKunjunganKeluar.size();
    }

    public int getColumnCount() {
        return 7;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        Kunjungan b = dataKunjunganKeluar.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return b.getNo_parkir();
            case 1:
                return b.getPlat_nomor();
            case 2:
                return b.getId_petugas().getId_petugas();
            case 3:
                return b.getId_member().getId_member();
            case 4:
                return b.getJam_keluar();
            case 5:
                return b.getTanggal_parkir();
            case 6:
                return b.getStatus();
            default:
                return "";
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "no_parkir";
            case 1:
                return "plat_nomor";
            case 2:
                return "id_petugas";
            case 3:
                return "id_member";
            case 4:
                return "jam_keluar";
            case 5:
                return "tanggal_parkir";
            case 6:
                return "status";
            default:
                return "";
        }
    }
}
