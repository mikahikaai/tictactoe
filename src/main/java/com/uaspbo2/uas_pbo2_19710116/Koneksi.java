package com.uaspbo2.uas_pbo2_19710116;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Koneksi {
    
    private final String url = "jdbc:mysql://localhost/uas_pbo2_19710116";

    public Connection getKoneksi() {

        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, "root", "");
        } catch (SQLException ex) {
            Logger.getLogger(Koneksi.class.getName()).log(Level.SEVERE, null, ex);
        } return conn;
    }
    
    
}
