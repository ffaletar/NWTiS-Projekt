/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.ffaletar.baza;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;
import org.foi.nwtis.ffaletar.helpers.KonfiguracijaHelper;
import org.apache.tomcat.jdbc.pool.DataSource;

/**
 *
 * @author Filip
 */
public class Baza {

    private static Connection conn = null;

    public Baza() {
    }

    public Connection dohvatiKonekciju() {
        try {
            Class.forName(KonfiguracijaHelper.getDbDriver());
            
            conn = DriverManager.getConnection(KonfiguracijaHelper.getDbServer(),
                    KonfiguracijaHelper.getDbKorisnickoIme(),
                    KonfiguracijaHelper.getDbLozinka());
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Baza.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }

    public void zatvoriKonekciju() {
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Baza.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
