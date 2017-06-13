/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.ffaletar.dretve;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.foi.nwtis.ffaletar.baza.Baza;
import org.foi.nwtis.ffaletar.baza.MeteoBaza;
import org.foi.nwtis.ffaletar.baza.UredajiBaza;
import org.foi.nwtis.ffaletar.helpers.KonfiguracijaHelper;
import org.foi.nwtis.ffaletar.podaci.Lokacija;
import org.foi.nwtis.ffaletar.podaci.MeteoPodaci;
import org.foi.nwtis.ffaletar.podaci.Uredjaj;
import org.foi.nwtis.ffaletar.rest.klijenti.OWMKlijent;

/**
 *
 * @author Filip
 */
public class PreuzmiMeteoPodatke extends Thread {

    private static boolean pauzirana;
    private static boolean zaustavljena;

    public PreuzmiMeteoPodatke() {
    }

    @Override
    public void interrupt() {
        super.interrupt(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void run() {

        UredajiBaza uredajiBaza = new UredajiBaza();
        MeteoBaza meteoBaza = new MeteoBaza();
        Map<Lokacija, MeteoPodaci> lokacijaMeteo = new HashMap<>();

        Map<Lokacija, Uredjaj> listaLokacija = null;
        while (true) {
            if (isZaustavljena()) {
                break;
            }

            if (isPauzirana()) {
                System.out.println("Dretva je pauzirana");
            } else {
                listaLokacija = uredajiBaza.dohvatiSveLokacije();

                for (Map.Entry<Lokacija, Uredjaj> uredjaj : listaLokacija.entrySet()) {
                    if (!lokacijaMeteo.isEmpty()) {
                        for (Map.Entry<Lokacija, MeteoPodaci> lokacijaMeteoPodatak : lokacijaMeteo.entrySet()) {
                            if (lokacijaMeteoPodatak.getKey().getLatitude().equals(uredjaj.getKey().getLatitude()) && lokacijaMeteoPodatak.getKey().getLongitude().equals(uredjaj.getKey().getLongitude())) {
                                meteoBaza.dodajMeteo(lokacijaMeteoPodatak.getValue(), uredjaj.getValue());
                            } else {
                                OWMKlijent client = new OWMKlijent();
                                MeteoPodaci meteo = client.getRealTimeWeather(uredjaj.getKey().getLatitude(), uredjaj.getKey().getLongitude());

                                lokacijaMeteo.put(uredjaj.getKey(), meteo);

                                meteoBaza.dodajMeteo(meteo, uredjaj.getValue());
                            }
                        }
                    } else {
                        OWMKlijent client = new OWMKlijent();
                        MeteoPodaci meteo = client.getRealTimeWeather(uredjaj.getKey().getLatitude(), uredjaj.getKey().getLongitude());

                        lokacijaMeteo.put(uredjaj.getKey(), meteo);

                        meteoBaza.dodajMeteo(meteo, uredjaj.getValue());
                    }

                }

                try {
                    Thread.sleep(KonfiguracijaHelper.getIntervalDretveZaMeteoPodatke() * 1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(PreuzmiMeteoPodatke.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public synchronized void start() {
        super.start(); //To change body of generated methods, choose Tools | Templates.
    }

    public static boolean isPauzirana() {
        return pauzirana;
    }

    public static void setPauzirana(boolean pauzirana) {
        PreuzmiMeteoPodatke.pauzirana = pauzirana;
    }

    public static boolean isZaustavljena() {
        return zaustavljena;
    }

    public static void setZaustavljena(boolean zaustavljena) {
        PreuzmiMeteoPodatke.zaustavljena = zaustavljena;
    }

    public static int dohvatiStatusDretve(){
        if(isZaustavljena()){
            return 15;
        }else{
            if(isPauzirana()){
                return 13;
            }else{
                return 14;
            }
        }
    }
    
}
