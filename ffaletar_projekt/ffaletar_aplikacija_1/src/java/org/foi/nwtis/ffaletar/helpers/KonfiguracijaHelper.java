/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.ffaletar.helpers;

import org.foi.nwtis.ffaletar.konfiguracije.Konfiguracija;

/**
 *
 * @author Filip
 */
public class KonfiguracijaHelper {
    private static String apiKeyOWM;
    private static int intervalDretveZaMeteoPodatke;
    private static String dbKorisnickoIme;
    private static String dbLozinka;
    private static String dbServer;
    private static String dbDriver;
    private static int port;
    private static String host;
    private static String mailAdresaPosiljatelja;
    private static String mailAdresaPrimatelja;
    private static String mailPredmet;
    private static String IoTMasterKorisnik;
    private static String IoTMasterLozinka;
    private static int brojPrikaza;
    
    private static Konfiguracija konfiguracija;

    public KonfiguracijaHelper(Konfiguracija konfiguracija) {
        this.konfiguracija = konfiguracija;
    }
    
    public static String getApiKeyOWM() {
        return konfiguracija.dajPostavku("apiKeyOWM");
    }
    public static int getIntervalDretveZaMeteoPodatke() {
        return Integer.parseInt(konfiguracija.dajPostavku("intervalDretveZaMeteoPodatke"));
    }

    public static String getDbKorisnickoIme() {
        return konfiguracija.dajPostavku("dbKorisnickoIme");
    }

    public static String getDbLozinka() {
        return konfiguracija.dajPostavku("dbLozinka");
    }
    public static String getDbServer() {
        return konfiguracija.dajPostavku("dbServer");
    }
    public static String getDbDriver() {
        return konfiguracija.dajPostavku("dbDriver");
    }

    public static int getPort() {
        return Integer.parseInt(konfiguracija.dajPostavku("port"));
    }

    public static String getHost() {
        return konfiguracija.dajPostavku("host");
    }


    public static String getMailAdresaPosiljatelja() {
        return konfiguracija.dajPostavku("mailAdresaPosiljatelja");
    }

    public static String getMailAdresaPrimatelja() {
        return konfiguracija.dajPostavku("mailAdresaPrimatelja");
    }

    public static String getMailPredmet() {
        return konfiguracija.dajPostavku("mailPredmet");
    }

    public static String getIoTMasterKorisnik() {
        return konfiguracija.dajPostavku("IoTMasterKorisnik");
    }

    public static String getIoTMasterLozinka() {
        return konfiguracija.dajPostavku("IoTMasterLozinka");
    }

    public static int getBrojPrikaza() {
        return Integer.parseInt(konfiguracija.dajPostavku("brojPrikaza"));
    }
    
    
    
}
