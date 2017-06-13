/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.ffaletar.baza;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.foi.nwtis.ffaletar.helpers.Helper;
import org.foi.nwtis.ffaletar.podaci.Lokacija;
import org.foi.nwtis.ffaletar.podaci.MeteoPodaci;
import org.foi.nwtis.ffaletar.podaci.Uredjaj;

/**
 *
 * @author Filip
 */
public class MeteoBaza {
    
    public synchronized boolean dodajMeteo(MeteoPodaci data, Uredjaj uredjaj) {
        String vrijeme = Helper.dohvatiTrenutniTimeStamp(false, false);
        float minimalnaTemperatura = data.getTemperatureMin();
        float maksimalnaTemperatura = data.getTemperatureMax();
        float temperatura = data.getTemperatureValue();
        float tlak = data.getPressureValue();
        float vlaga = data.getHumidityValue();
        float vjetar = data.getWindSpeedValue();
        float vjetarSmjer = data.getWindDirectionValue();
        float vrijemee = data.getWeatherNumber();
        String vrijemeOpis = data.getWeatherValue();

        String query = "INSERT INTO meteo(id, adresaStanice, latitude, longitude, vrijeme, vrijemeOpis,temp, tempMin, tempMax, vlaga, tlak, vjetar, vjetarSmjer, preuzeto) VALUES (" + uredjaj.getId() + ", '" + uredjaj.getNaziv() + "', " + uredjaj.getGeoloc().getLatitude() + ", " + uredjaj.getGeoloc().getLongitude() + ", '"+vrijemee+"', '"+vrijemeOpis+"'," + temperatura + ", " + minimalnaTemperatura + ", " + maksimalnaTemperatura + ", " + vlaga + ", " + tlak + ", " + vjetar + ", " + vjetarSmjer + ", '" + vrijeme + "')";

        int rs = 0;
        
        Baza baza = new Baza();
        Connection connection = baza.dohvatiKonekciju();
        
        Statement stmt = null;

        try {
            stmt = (Statement) connection.createStatement();
            rs = stmt.executeUpdate(query);

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Baza.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            baza.zatvoriKonekciju();
        }
    }
    
    public MeteoPodaci dohvatiZadnjeMeteoPodatkeZaUredjaj(int id){
        String query = "SELECT * FROM meteo WHERE meteo.id = " + id +" ORDER BY preuzeto DESC LIMIT 1";
        ResultSet rs = null;
        PreparedStatement preparedStatement = null;
        MeteoPodaci meteoPodaci = new MeteoPodaci();
        Baza baza = new Baza();
        Statement statement = null;
        
        try (Connection connection = baza.dohvatiKonekciju();) {
            
            statement = (Statement) connection.createStatement();
            rs = statement.executeQuery(query);
             
            while (rs.next()) {
                meteoPodaci = new MeteoPodaci();

                meteoPodaci.setTemperatureValue(Float.parseFloat(rs.getString(8)));
                meteoPodaci.setTemperatureMin(Float.parseFloat(rs.getString(9)));
                meteoPodaci.setTemperatureMax(Float.parseFloat(rs.getString(10)));
                meteoPodaci.setHumidityValue(rs.getFloat(11));
                meteoPodaci.setPressureValue((float) rs.getDouble(12));
                meteoPodaci.setWindSpeedValue((float) rs.getDouble(13));
                meteoPodaci.setWindDirectionValue((float) rs.getDouble(14));
                meteoPodaci.setLastUpdate(rs.getDate(15));

            }
            return meteoPodaci;
        } catch (SQLException ex) {
            Logger.getLogger(Baza.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(Baza.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    public List<MeteoPodaci> dohvatiZadnjihNMeteoPodatakaZaUredjaj(int n, int id){
        String query = "SELECT * FROM meteo WHERE meteo.id = " + id +" ORDER BY preuzeto DESC LIMIT " + n;
        ResultSet rs = null;
        PreparedStatement preparedStatement = null;
        List<MeteoPodaci> meteoPodaciLista = new ArrayList<>();
        MeteoPodaci meteoPodaci = new MeteoPodaci();
        Baza baza = new Baza();
        Statement statement = null;
        
        try (Connection connection = baza.dohvatiKonekciju();) {
            
            statement = (Statement) connection.createStatement();
            rs = statement.executeQuery(query);
             
            while (rs.next()) {
               
                meteoPodaci = new MeteoPodaci();

                meteoPodaci.setTemperatureValue(Float.parseFloat(rs.getString(8)));
                meteoPodaci.setTemperatureMin(Float.parseFloat(rs.getString(9)));
                meteoPodaci.setTemperatureMax(Float.parseFloat(rs.getString(10)));
                meteoPodaci.setHumidityValue(rs.getFloat(11));
                meteoPodaci.setPressureValue((float) rs.getDouble(12));
                meteoPodaci.setWindSpeedValue((float) rs.getDouble(13));
                meteoPodaci.setWindDirectionValue((float) rs.getDouble(14));
                meteoPodaci.setLastUpdate(rs.getDate(15));

                meteoPodaciLista.add(meteoPodaci);
            }
            return meteoPodaciLista;
        } catch (SQLException ex) {
            Logger.getLogger(Baza.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(Baza.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    public List<MeteoPodaci> dohvatiZadnjeMeteoPodatakaZaUredjajUVremenskomRazdoblju(int id, long pocetak, long kraj){
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date od = new Date(pocetak);
        Date doo = new Date(kraj);
        String pocetakString = sdfDate.format(od);
        String krajString = sdfDate.format(doo);
        
        String query = "SELECT * FROM meteo WHERE meteo.id = " + id +" AND preuzeto >= '" + pocetakString + "' AND preuzeto < '" + krajString + "' ORDER BY preuzeto DESC";
        
        
        ResultSet rs = null;
        PreparedStatement preparedStatement = null;
        List<MeteoPodaci> meteoPodaciLista = new ArrayList<>();
        MeteoPodaci meteoPodaci = new MeteoPodaci();
        Baza baza = new Baza();
        Statement statement = null;
        
        try (Connection connection = baza.dohvatiKonekciju();) {
            
            statement = (Statement) connection.createStatement();
            rs = statement.executeQuery(query);
             
            while (rs.next()) {
               
                meteoPodaci = new MeteoPodaci();

                meteoPodaci.setTemperatureValue(Float.parseFloat(rs.getString(8)));
                meteoPodaci.setTemperatureMin(Float.parseFloat(rs.getString(9)));
                meteoPodaci.setTemperatureMax(Float.parseFloat(rs.getString(10)));
                meteoPodaci.setHumidityValue(rs.getFloat(11));
                meteoPodaci.setPressureValue((float) rs.getDouble(12));
                meteoPodaci.setWindSpeedValue((float) rs.getDouble(13));
                meteoPodaci.setWindDirectionValue((float) rs.getDouble(14));
                meteoPodaci.setLastUpdate(rs.getDate(15));

                meteoPodaciLista.add(meteoPodaci);
            }
            return meteoPodaciLista;
        } catch (SQLException ex) {
            Logger.getLogger(Baza.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
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
