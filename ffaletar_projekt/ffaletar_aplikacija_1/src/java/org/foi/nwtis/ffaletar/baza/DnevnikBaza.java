/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.ffaletar.baza;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.foi.nwtis.ffaletar.helpers.Helper;

/**
 *
 * @author Filip
 */
public class DnevnikBaza {
    
    public static boolean upisiUDnevnik(String korisnickoIme, String url, String ipAdresa, int trajanje){
        
        String vrijeme = Helper.dohvatiTrenutniTimeStamp(false, false);
        
        String upit = "INSERT into dnevnik values (default, '"+korisnickoIme+"','"+ url + "', '"+ipAdresa+"','"+vrijeme+"',"+trajanje+", 1)";
        
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
    
}
