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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.foi.nwtis.ffaletar.podaci.Korisnik;

/**
 *
 * @author Filip
 */
public class KorisnikBaza {
    
    public static boolean provjeriKorisnika(String korisnickoIme, String lozinka){
        
        //TODO ako je obicni korisnik vrati 1, ako je admin vrati 2, ako ne postoji korisnik vrati 0
        
        
        
        return true;
    }
    
    
    public boolean dodajKorisnika(String korisnickoIme,String ime, String prezime, String lozinka, String mail){
        
        String upit = "INSERT into korisnik values (default, '"+korisnickoIme+"', '"+ime+"', '"+prezime+"','"+lozinka+"','"+mail+"', 2)";
        
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
    
    public boolean azurirajKorisnika(int id, String korisnickoIme,String ime, String prezime, String lozinka, String mail, int tipKorisnika){

        String query = "update korisnik set korisnickoIme='" + korisnickoIme + "',ime='" + ime + "',prezime='" + prezime + "',lozinka='" + lozinka + "',mail='" + mail + "',tipKorisnika=" + tipKorisnika + " where id=" + id;
        Statement s = null;
        ResultSet rs = null;
        List<Korisnik> korisnici = new ArrayList<>();
        Baza baza = new Baza();
        int korisnikAzuriran = 0;
        

        try (Connection c = baza.dohvatiKonekciju();) {
            s = (Statement) c.createStatement();
            korisnikAzuriran = s.executeUpdate(query);
            
            if(korisnikAzuriran == 1){
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
    
    public List<Korisnik> dajSveKorisnike(){
        String query = "SELECT * FROM korisnik";
        Statement s = null;
        ResultSet rs = null;
        List<Korisnik> korisnici = new ArrayList<>();
        Baza baza = new Baza();
        

        try (Connection c = baza.dohvatiKonekciju();) {
            s = (Statement) c.createStatement();
            rs = s.executeQuery(query);

            while (rs.next()) {
                Korisnik korisnik = new Korisnik(rs.getInt(1), rs.getString(2), rs.getString(3),rs.getString(4), rs.getString(5), rs.getString(6),rs.getInt(7));
                
                korisnici.add(korisnik);
            }

            return korisnici;
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
    
    public Korisnik dajJednogKorisnika(String korisnickoIme){
        String query = "SELECT * FROM korisnik WHERE korisnickoIme = '" + korisnickoIme + "'";
        Statement s = null;
        ResultSet rs = null;
        List<Korisnik> korisnici = new ArrayList<>();
        Baza baza = new Baza();
        

        try (Connection c = baza.dohvatiKonekciju();) {
            s = (Statement) c.createStatement();
            rs = s.executeQuery(query);
            Korisnik korisnik = null;
            if (rs.next()) {
                korisnik = new Korisnik(rs.getInt(1), rs.getString(2), rs.getString(3),rs.getString(4), rs.getString(5), rs.getString(6),rs.getInt(7));
            }
            return korisnik;
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
