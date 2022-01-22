package com.uaspbo2.uas_pbo2_19710116;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Koneksi2 {
    public Connection getKoneksi(){
        Connection conn = null;
        try {
            String DB ="jdbc:mysql://localhost/uas_pbo2_19710116";
            String user="root";
            String pass="";
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            conn = DriverManager.getConnection(DB,user,pass);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Tidak ada koneksi","Error",
            JOptionPane.INFORMATION_MESSAGE);
            System.err.println(e.getMessage());
            System.exit(0);
        }
        return conn;
         
    }
}

