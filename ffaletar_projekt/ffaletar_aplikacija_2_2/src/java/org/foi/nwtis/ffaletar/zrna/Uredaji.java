/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.ffaletar.zrna;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import org.foi.nwtis.ffaletar.podaci.Korisnik;
import org.foi.nwtis.ffaletar.podaci.Lokacija;
import org.foi.nwtis.ffaletar.podaci.Uredjaj;
import org.foi.nwtis.ffaletar.rest.klijenti.KorisniciREST;
import org.foi.nwtis.ffaletar.rest.klijenti.UredajiREST;

/**
 *
 * @author Filip
 */
@Named(value = "uredaji")
@SessionScoped
public class Uredaji implements Serializable {

//    private List<Uredjaj> raspoloziviIoT;
//    private List<Uredjaj> popisRaspoloziviIoT;
    private Map<String, Uredjaj> raspoloziviIoT;
    private List<String> popisRaspoloziviIoT;
    private String odabraniUredjaj;
    
    private String azuriranjeNaziv;
    private String azuriranjeLatitude;
    private String azuriranjeLongitude;
    private int idUredaja;
    
    private boolean azuriranje = false;
    
    private String poruka;
    
    private boolean prviPut = true;
    private static String idZaAzuriranje;

    /**
     * Creates a new instance of Uredaji
     */
    public Uredaji() {
    }

    public String getPoruka() {
        return poruka;
    }

    public void setPoruka(String poruka) {
        this.poruka = poruka;
    }

    public int getIdUredaja() {
        return idUredaja;
    }

    public void setIdUredaja(int idUredaja) {
        this.idUredaja = idUredaja;
    }
    
    

    public static String getIdZaAzuriranje() {
        return idZaAzuriranje;
    }

    public static void setIdZaAzuriranje(String idZaAzuriranje) {
        Uredaji.idZaAzuriranje = idZaAzuriranje;
    }
    
    
    
    public String getAzuriranjeNaziv() {
        return azuriranjeNaziv;
    }

    public void setAzuriranjeNaziv(String azuriranjeNaziv) {
        this.azuriranjeNaziv = azuriranjeNaziv;
    }

    public String getAzuriranjeLatitude() {
        return azuriranjeLatitude;
    }

    public void setAzuriranjeLatitude(String azuriranjeLatitude) {
        this.azuriranjeLatitude = azuriranjeLatitude;
    }

    public String getAzuriranjeLongitude() {
        return azuriranjeLongitude;
    }

    public void setAzuriranjeLongitude(String azuriranjeLongitude) {
        this.azuriranjeLongitude = azuriranjeLongitude;
    }

    public boolean isAzuriranje() {
        return azuriranje;
    }

    public void setAzuriranje(boolean azuriranje) {
        this.azuriranje = azuriranje;
    }
    
    
    

    public Map<String, Uredjaj> getRaspoloziviIoT() {
        if (prviPut) {
            dohvatiRaspoloziveIoT();
            prviPut = false;
        }
        return raspoloziviIoT;
    }

    public void setRaspoloziviIoT(Map<String, Uredjaj> raspoloziviIoT) {
        this.raspoloziviIoT = raspoloziviIoT;
    }

    public List<String> getPopisRaspoloziviIoT() {
        return popisRaspoloziviIoT;
    }

    public void setPopisRaspoloziviIoT(List<String> popisRaspoloziviIoT) {
        this.popisRaspoloziviIoT = popisRaspoloziviIoT;
    }

    public String getOdabraniUredjaj() {
        return odabraniUredjaj;
    }

    public void setOdabraniUredjaj(String odabraniUredjaj) {
        this.odabraniUredjaj = odabraniUredjaj;
    }

    public void prikaziOdabraniIoT() {
        
        if (popisRaspoloziviIoT.size() == 1) {
            azuriranje = true;
            idZaAzuriranje = popisRaspoloziviIoT.get(0);
            
            for (Iterator<Map.Entry<String, Uredjaj>> iterator = raspoloziviIoT.entrySet().iterator(); iterator.hasNext();) {
                Map.Entry<String, Uredjaj> uredaj = iterator.next();

                if (uredaj.getValue().toString().compareTo(idZaAzuriranje) == 0) {
                    idUredaja = uredaj.getValue().getId();
                    azuriranjeNaziv = uredaj.getValue().getNaziv().substring(uredaj.getValue().getNaziv().indexOf('"') + 1, uredaj.getValue().getNaziv().length() - 1);
                    Lokacija lokacija = new Lokacija(uredaj.getValue().getGeoloc().getLatitude(), uredaj.getValue().getGeoloc().getLongitude());

                    azuriranjeLatitude = lokacija.getLatitude().substring(lokacija.getLatitude().indexOf('"') + 1, lokacija.getLatitude().length() - 1);
                    azuriranjeLongitude = lokacija.getLongitude().substring(lokacija.getLongitude().indexOf('"') + 1, lokacija.getLongitude().length() - 1);
                }
            }
        }else{
            poruka = "Niste odabrali uređaj";
        }
        
    }
    
    
    public Map<String, Uredjaj> dohvatiRaspoloziveIoT(){
        raspoloziviIoT = new LinkedHashMap<>();
        UredajiREST uredajiREST = new UredajiREST();
        String uredaji = uredajiREST.getJson();
        JsonReader reader = Json.createReader(new StringReader(uredaji));
        JsonArray jsonArray = reader.readArray();
        List<Uredjaj> uredajiLista = new ArrayList<Uredjaj>();

        for (int i = 0; i < jsonArray.size(); i++) {
            Uredjaj uredaj = new Uredjaj();
            Lokacija lokacija = new Lokacija();
            uredaj.setId(jsonArray.getJsonObject(i).getInt("id"));
            uredaj.setNaziv(jsonArray.getJsonObject(i).getJsonString("naziv").toString());
            lokacija.setLatitude(jsonArray.getJsonObject(i).getJsonString("latitude").toString());
            lokacija.setLongitude(jsonArray.getJsonObject(i).getJsonString("longitude").toString());
            uredaj.setGeoloc(lokacija);
            uredajiLista.add(uredaj);
        }

        for (Uredjaj u : uredajiLista) {
            
            raspoloziviIoT.put(u.getNaziv(), u);
        }

        return raspoloziviIoT;
    }
    
    public void azurirajIoT(){
        if (azuriranjeNaziv.equals("") || azuriranjeNaziv == null || azuriranjeLatitude.equals("") || azuriranjeLatitude == null || azuriranjeLongitude.equals("") || azuriranjeLongitude == null) {
            poruka = "Uneseni podaci nisu ispravni";
        } else {
            JsonObjectBuilder job = Json.createObjectBuilder();
            job.add("id", getIdUredaja());
            job.add("naziv", getAzuriranjeNaziv());
            job.add("latitude", getAzuriranjeLatitude());
            job.add("longitude", getAzuriranjeLongitude());

            String a = job.build().toString();
            
            UredajiREST uredajiREST = new UredajiREST();
            uredajiREST.azurirajUredaj(a);
            
            
            azuriranje = false;

            

            dohvatiRaspoloziveIoT();
        }
    }
}
