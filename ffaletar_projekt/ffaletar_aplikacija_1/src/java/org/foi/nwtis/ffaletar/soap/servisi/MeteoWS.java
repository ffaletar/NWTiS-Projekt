/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.ffaletar.soap.servisi;

import java.util.Date;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.ejb.Stateless;
import org.foi.nwtis.ffaletar.baza.DnevnikBaza;
import org.foi.nwtis.ffaletar.baza.MeteoBaza;
import org.foi.nwtis.ffaletar.baza.UredajiBaza;
import org.foi.nwtis.ffaletar.helpers.KonfiguracijaHelper;
import org.foi.nwtis.ffaletar.podaci.Lokacija;
import org.foi.nwtis.ffaletar.podaci.MeteoPodaci;
import org.foi.nwtis.ffaletar.podaci.Uredjaj;
import org.foi.nwtis.ffaletar.rest.klijenti.GMKlijent;
import org.foi.nwtis.ffaletar.rest.klijenti.OWMKlijent;

/**
 *
 * @author Filip
 */
@WebService(serviceName = "MeteoWS")
@Stateless()
public class MeteoWS {

    private Date pocetakObrade;
    private Date krajObrade;
    private int trajanjeObrade;
    
    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }
    
    @WebMethod(operationName = "zadnjiMeteoPodaciZaUredjaj")
    public MeteoPodaci zadnjiMeteoPodaciZaUredjaj(@WebParam(name = "IoTUredjaj") int IoTUredjaj) {
        setPocetakObrade();
        
        MeteoBaza meteoBaza = new MeteoBaza();
        MeteoPodaci meteoPodaci = meteoBaza.dohvatiZadnjeMeteoPodatkeZaUredjaj(IoTUredjaj);
        
        setKrajObrade();
        
        DnevnikBaza.upisiUDnevnik(KonfiguracijaHelper.getIoTMasterKorisnik(), "/MeteoWS/zadnjiMeteoPodaciZaUredjaj", KonfiguracijaHelper.getHost(), getTrajanjeObrade(), 0);
        
        return meteoPodaci;
    }
    
    @WebMethod(operationName = "zadnjihNMeteoPodatakaZaUredjaj")
    public List<MeteoPodaci> zadnjihNMeteoPodatakaZaUredjaj(@WebParam(name = "IoTUredjaj") int IoTUredjaj, @WebParam(name = "brojMeteoPodataka") int broj) {
        setPocetakObrade();
        MeteoBaza meteoBaza = new MeteoBaza();
        List<MeteoPodaci> meteoPodaci = meteoBaza.dohvatiZadnjihNMeteoPodatakaZaUredjaj(broj, IoTUredjaj);
        
        setKrajObrade();
        
        DnevnikBaza.upisiUDnevnik(KonfiguracijaHelper.getIoTMasterKorisnik(), "/MeteoWS/zadnjihNMeteoPodatakaZaUredjaj", KonfiguracijaHelper.getHost(), getTrajanjeObrade(),0);
        
        return meteoPodaci;
    }
    
    @WebMethod(operationName = "meteoPodaciUVremenskomRazdoblju")
    public List<MeteoPodaci> meteoPodaciUVremenskomRazdoblju(@WebParam(name = "IoTUredjaj") int IoTUredjaj, @WebParam(name = "pocetak") long pocetak, @WebParam(name = "kraj") long kraj) {
        setPocetakObrade();
        
        MeteoBaza meteoBaza = new MeteoBaza();
        List<MeteoPodaci> meteoPodaci = meteoBaza.dohvatiZadnjeMeteoPodatakaZaUredjajUVremenskomRazdoblju(IoTUredjaj,pocetak, kraj);
        
        setKrajObrade();
        DnevnikBaza.upisiUDnevnik(KonfiguracijaHelper.getIoTMasterKorisnik(), "/MeteoWS/zadnjihNMeteoPodatakaZaUredjaj", KonfiguracijaHelper.getHost(), getTrajanjeObrade(),0);
        
        return meteoPodaci;
    }

    @WebMethod(operationName = "vazeciMeteoPodaciZaUredaj")
    public MeteoPodaci vazeciMeteoPodaciZaUredaj(@WebParam(name = "IoTUredjaj") int IoTUredjaj) {
        setPocetakObrade();
        OWMKlijent oWMKlijent = new OWMKlijent();
        
        MeteoPodaci meteoPodaci = oWMKlijent.getRealTimeWeatherById(IoTUredjaj);
        
        setKrajObrade();
        DnevnikBaza.upisiUDnevnik(KonfiguracijaHelper.getIoTMasterKorisnik(), "/MeteoWS/zadnjihNMeteoPodatakaZaUredjaj", KonfiguracijaHelper.getHost(), getTrajanjeObrade(),0);
        
        return meteoPodaci;
    }
    
    @WebMethod(operationName = "adresaUredajaPremaGeolokaciji")
    public String adresaUredajaPremaGeolokaciji(@WebParam(name = "IoTUredjaj") int IoTUredjaj) {
        setPocetakObrade();
        
        GMKlijent gmk = new GMKlijent();
        
        UredajiBaza ub = new UredajiBaza();
        Uredjaj uredjaj = ub.dohvatiJedanUredjaj(IoTUredjaj);
        
        Lokacija lokacija = new Lokacija(uredjaj.getGeoloc().getLatitude(), uredjaj.getGeoloc().getLongitude());
        
        String adresa = gmk.getAddress(lokacija);
        
        setKrajObrade();
        DnevnikBaza.upisiUDnevnik(KonfiguracijaHelper.getIoTMasterKorisnik(), "/MeteoWS/zadnjihNMeteoPodatakaZaUredjaj", KonfiguracijaHelper.getHost(), getTrajanjeObrade(),0);
        
        return adresa;
    }

    public Date getPocetakObrade() {
        return pocetakObrade;
    }

    public void setPocetakObrade() {
        this.pocetakObrade = new Date();
    }

    public Date getKrajObrade() {
        return krajObrade;
    }

    public void setKrajObrade() {
        this.krajObrade = new Date();
    }

    public int getTrajanjeObrade() {
        trajanjeObrade = (int)(getKrajObrade().getTime() - getPocetakObrade().getTime());
        return trajanjeObrade;
    }

    public void setTrajanjeObrade(int trajanjeObrade) {
        this.trajanjeObrade = trajanjeObrade;
    }
    
    
    
    
}
