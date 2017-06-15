/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.ffaletar.baza;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.foi.nwtis.ffaletar.baza.Baza;
import org.foi.nwtis.ffaletar.helpers.Helper;
import org.foi.nwtis.ffaletar.helpers.KonfiguracijaHelper;
import org.foi.nwtis.ffaletar.konfiguracije.Konfiguracija;
import org.foi.nwtis.ffaletar.podaci.Korisnik;
import org.foi.nwtis.ffaletar.podaci.Lokacija;
import org.foi.nwtis.ffaletar.podaci.Uredjaj;

/**
 *
 * @author Filip
 */
public class UredajiBaza {

    public List<Uredjaj> dohvatiSveUredjaje() {

        Baza baza = new Baza();
        Connection connection = baza.dohvatiKonekciju();
        String upit = "select * from uredaji";

        try {
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(upit);

            List<Uredjaj> listaUredjaja = new ArrayList<>();

            while (resultSet.next()) {
                listaUredjaja.add(new Uredjaj(resultSet.getInt(1), resultSet.getString(2), new Lokacija(resultSet.getString(3), resultSet.getString(4))));
            }

            baza.zatvoriKonekciju();
            return listaUredjaja;
        } catch (SQLException ex) {
            baza.zatvoriKonekciju();
            return null;
        }
    }
    
    public Uredjaj dohvatiJedanUredjaj(int id) {

        Baza baza = new Baza();
        Connection connection = baza.dohvatiKonekciju();
        String upit = "select * from uredaji WHERE id = " + id;

        try {
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(upit);

            Uredjaj uredjaj = null;

            if (resultSet.next()) {
                uredjaj = new Uredjaj(resultSet.getInt(1), resultSet.getString(2), new Lokacija(resultSet.getString(3), resultSet.getString(4)), resultSet.getInt(5), resultSet.getString(6), resultSet.getString(7));
            }

            baza.zatvoriKonekciju();
            return uredjaj;
        } catch (SQLException ex) {
            baza.zatvoriKonekciju();
            return null;
        }
    }
    
    public boolean dodajUredaj(String naziv, String latitude, String longitude) {
        String vrijeme = Helper.dohvatiTrenutniTimeStamp(false, false);
        
        String query = "INSERT INTO uredaji(naziv, latitude, longitude, status, vrijeme_promjene, vrijeme_kreiranja) VALUES ('" + naziv + "'," + latitude + "," + longitude + "," + 0 + ", '"+vrijeme+"','" + vrijeme + "')";
        Statement s = null;
        int rs = 0;
        Baza baza = new Baza();
        
        try (Connection c = baza.dohvatiKonekciju();) {
            s = (Statement) c.createStatement();
            rs = s.executeUpdate(query);

            if(rs == 1){
                return true;
            }else{
                return false;
            }
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
    
    public boolean azurirajKorisnika(int id, String naziv, String latitude, String longitude){

        String vrijeme = Helper.dohvatiTrenutniTimeStamp(false, false);
        
        String query = "UPDATE uredaji SET naziv='" + naziv + "',latitude=" + latitude + ",longitude=" + longitude + ",status=0,vrijeme_promjene='" + vrijeme+ "' where id=" + id;
        Statement s = null;
        ResultSet rs = null;
        Baza baza = new Baza();
        int uredajAzuriran = 0;
        

        try (Connection c = baza.dohvatiKonekciju();) {
            s = (Statement) c.createStatement();
            uredajAzuriran = s.executeUpdate(query);
            
            if(uredajAzuriran == 1){
                return true;
            }else{
                return false;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Baza.class.getName()).log(Level.SEVERE, null, ex);
            return false;
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

    public Map<Lokacija, Uredjaj> dohvatiSveLokacije() {

        Baza baza = new Baza();
        Connection connection = baza.dohvatiKonekciju();
        String upit = "select * from uredaji";

        try {
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(upit);

            Map<Lokacija, Uredjaj> map = new HashMap<>();

            while (resultSet.next()) {
                Uredjaj uredjaj = new Uredjaj(resultSet.getInt(1), resultSet.getString(2), new Lokacija(resultSet.getString(3), resultSet.getString(4)));
                map.put(new Lokacija(resultSet.getString(3), resultSet.getString(4)), uredjaj);
            }

            baza.zatvoriKonekciju();
            return map;
        } catch (SQLException ex) {
            baza.zatvoriKonekciju();
            return null;
        }
    }

    public Lokacija dohvatiLatitudeLongitude(int id) {

        Baza baza = new Baza();
        String upit = "Select latitude, longitude from uredaji WHERE id = " + id;
        Statement statement = null;
        ResultSet rs = null;
        Lokacija lokacija = new Lokacija();

        try (Connection c = baza.dohvatiKonekciju();) {
            statement = (Statement) c.createStatement();
            rs = statement.executeQuery(upit);
            if (rs.next()) {
                lokacija.setLatitude(String.valueOf(rs.getDouble(1)));
                lokacija.setLongitude(String.valueOf(rs.getDouble(2)));
            }
            return lokacija;
        } catch (SQLException ex) {
            Logger.getLogger(Baza.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            try {
                if (statement != null) {
                    statement.close();
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
