/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.ffaletar.soap.klijenti;

/**
 *
 * @author Filip
 */
public class MeteoSOAP {

    public static String adresaUredajaPremaGeolokaciji(int ioTUredjaj) {
        org.foi.nwtis.ffaletar.soap.klijenti.MeteoWS_Service service = new org.foi.nwtis.ffaletar.soap.klijenti.MeteoWS_Service();
        org.foi.nwtis.ffaletar.soap.klijenti.MeteoWS port = service.getMeteoWSPort();
        return port.adresaUredajaPremaGeolokaciji(ioTUredjaj);
    }

    public static java.util.List<org.foi.nwtis.ffaletar.soap.klijenti.MeteoPodaci> meteoPodaciUVremenskomRazdoblju(int ioTUredjaj, long pocetak, long kraj) {
        org.foi.nwtis.ffaletar.soap.klijenti.MeteoWS_Service service = new org.foi.nwtis.ffaletar.soap.klijenti.MeteoWS_Service();
        org.foi.nwtis.ffaletar.soap.klijenti.MeteoWS port = service.getMeteoWSPort();
        return port.meteoPodaciUVremenskomRazdoblju(ioTUredjaj, pocetak, kraj);
    }

    public static MeteoPodaci vazeciMeteoPodaciZaUredaj(int ioTUredjaj) {
        org.foi.nwtis.ffaletar.soap.klijenti.MeteoWS_Service service = new org.foi.nwtis.ffaletar.soap.klijenti.MeteoWS_Service();
        org.foi.nwtis.ffaletar.soap.klijenti.MeteoWS port = service.getMeteoWSPort();
        return port.vazeciMeteoPodaciZaUredaj(ioTUredjaj);
    }

    public static MeteoPodaci zadnjiMeteoPodaciZaUredjaj(int ioTUredjaj) {
        org.foi.nwtis.ffaletar.soap.klijenti.MeteoWS_Service service = new org.foi.nwtis.ffaletar.soap.klijenti.MeteoWS_Service();
        org.foi.nwtis.ffaletar.soap.klijenti.MeteoWS port = service.getMeteoWSPort();
        return port.zadnjiMeteoPodaciZaUredjaj(ioTUredjaj);
    }

    public static java.util.List<org.foi.nwtis.ffaletar.soap.klijenti.MeteoPodaci> zadnjihNMeteoPodatakaZaUredjaj(int ioTUredjaj, int brojMeteoPodataka) {
        org.foi.nwtis.ffaletar.soap.klijenti.MeteoWS_Service service = new org.foi.nwtis.ffaletar.soap.klijenti.MeteoWS_Service();
        org.foi.nwtis.ffaletar.soap.klijenti.MeteoWS port = service.getMeteoWSPort();
        return port.zadnjihNMeteoPodatakaZaUredjaj(ioTUredjaj, brojMeteoPodataka);
    }
    
    
    
}
