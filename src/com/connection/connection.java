/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.connection;

import java.sql.Connection;
import javax.swing.JOptionPane;
import oracle.jdbc.pool.OracleDataSource;

/**
 *
 * @author admiin
 */
public class connection {

    public static Connection getConnectionUsingParameter(String userid, String userpassword) {
        Connection conn = null;
        try {
            OracleDataSource ds;
            ds = new OracleDataSource();
            ds.setURL("jdbc:Oracle:thin:@localhost:1521:xe");
            conn = ds.getConnection(userid, userpassword);
            System.out.println("koneksi berhasil");
        } catch (Exception e) {
            System.out.println("error pada " + e.getMessage());
        }
        return conn;
    }
    
     public static Connection getConnection() {
        String url = "jdbc:Oracle:thin:@localhost:1521:xe";
        String userid = "engel";
        String userpassword = "engel";
        Connection conn = null;
        try {
            OracleDataSource ds;
            ds = new OracleDataSource();
            ds.setURL(url);
            conn = ds.getConnection(userid, userpassword);
            System.out.println("koneksi berhasil");
        } catch (Exception e) {
            System.out.println("error pada " + e.getMessage());
        }
        return conn;
    }
    public static void main(String[] args) {
        getConnection();
    }
}
