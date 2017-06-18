/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.ffaletar.baza;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.foi.nwtis.ffaletar.helpers.Helper;
import org.foi.nwtis.ffaletar.podaci.Dnevnik;
import org.foi.nwtis.ffaletar.podaci.Korisnik;

/**
 *
 * @author Filip
 */
public class DnevnikBaza {

    public static boolean upisiUDnevnik(String korisnickoIme, String url, String ipAdresa, int trajanje, int status) {

        String vrijeme = Helper.dohvatiTrenutniTimeStamp(false, false);

        String upit = "INSERT into dnevnik values (default, '" + korisnickoIme + "','" + url + "', '" + ipAdresa + "','" + vrijeme + "'," + trajanje + ", "+status+")";

        Statement s = null;
        int rs = 0;
        Baza baza = new Baza();

        try (Connection c = baza.dohvatiKonekciju();) {
            s = (Statement) c.createStatement();
            rs = s.executeUpdate(upit);

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Baza.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            try {
                if (s != null) {
                    s.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(Baza.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public List<Dnevnik> dajDnevnik(int status, int pocetak, int kraj) {
        
        int limit = kraj - pocetak;
        
        String query = "SELECT * FROM dnevnik WHERE status = " + status + " ORDER BY vrijeme DESC LIMIT " + limit + " OFFSET " + pocetak;
        Statement s = null;
        ResultSet rs = null;
        List<Dnevnik> dnevnikLista = new ArrayList<>();
        Baza baza = new Baza();

        try (Connection c = baza.dohvatiKonekciju();) {
            s = (Statement) c.createStatement();
            rs = s.executeQuery(query);

            while (rs.next()) {
                Dnevnik dnevnik = new Dnevnik(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getTimestamp(5), rs.getInt(6), rs.getInt(7));

                dnevnikLista.add(dnevnik);
            }

            return dnevnikLista;
        } catch (SQLException ex) {
            Logger.getLogger(Baza.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            try {
                if (s != null) {
                    s.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(Baza.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
