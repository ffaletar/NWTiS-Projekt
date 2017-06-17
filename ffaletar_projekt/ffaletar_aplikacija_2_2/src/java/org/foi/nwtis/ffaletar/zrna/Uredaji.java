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
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import org.foi.nwtis.ffaletar.podaci.Lokacija;
import org.foi.nwtis.ffaletar.podaci.MeteoPodaci;
import org.foi.nwtis.ffaletar.podaci.Uredjaj;
import org.foi.nwtis.ffaletar.rest.klijenti.UredajiREST;
import org.foi.nwtis.ffaletar.soap.klijenti.MeteoSOAP;

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
    private MeteoPodaci vazeciMeteoPodaci;
    private int brojMeteo;
    private List<org.foi.nwtis.ffaletar.soap.klijenti.MeteoPodaci> meteoPodaciUVremenskomIntervalu;
    private List<org.foi.nwtis.ffaletar.soap.klijenti.MeteoPodaci> zadnjihNMeteoPodataka;
    private String adresaUredaja;

    private String vrijemePocetak;
    private String vrijemeKraj;
    private String azuriranjeNaziv;
    private String azuriranjeLatitude;
    private String azuriranjeLongitude;
    private int idUredaja;

    private boolean azuriranje = false;
    private boolean vazeciMeteoPodaciPanel = false;
    private boolean meteoPodaciUVremenskomIntervaluPanel = false;
    private boolean zadnjihNMeteoPodatakaPanel = false;
    private boolean adresaUredajaPanel = false;

    private String poruka;

    private boolean prviPut = true;
    private static String idZaAzuriranje;

    private String meteoTemp;
    private String meteoMaxTemp;
    private String meteoMinTemp;
    private String meteoVlaga;
    private String meteoTlak;
    private String meteoNaziv;

    /**
     * Creates a new instance of Uredaji
     */
    public Uredaji() {
    }

    public int getBrojMeteo() {
        return brojMeteo;
    }

    public void setBrojMeteo(int brojMeteo) {
        this.brojMeteo = brojMeteo;
    }

    public String getAdresaUredaja() {
        return adresaUredaja;
    }

    public void setAdresaUredaja(String adresaUredaja) {
        this.adresaUredaja = adresaUredaja;
    }

    public boolean isAdresaUredajaPanel() {
        return adresaUredajaPanel;
    }

    public void setAdresaUredajaPanel(boolean adresaUredajaPanel) {
        this.adresaUredajaPanel = adresaUredajaPanel;
    }

    public String getPoruka() {
        return poruka;
    }

    public void setPoruka(String poruka) {
        this.poruka = poruka;
    }

    public String getVrijemePocetak() {
        return vrijemePocetak;
    }

    public void setVrijemePocetak(String vrijemePocetak) {
        this.vrijemePocetak = vrijemePocetak;
    }

    public String getVrijemeKraj() {
        return vrijemeKraj;
    }

    public void setVrijemeKraj(String vrijemeKraj) {
        this.vrijemeKraj = vrijemeKraj;
    }

    public int getIdUredaja() {
        return idUredaja;
    }

    public void setIdUredaja(int idUredaja) {
        this.idUredaja = idUredaja;
    }

    public MeteoPodaci getVazeciMeteoPodaci() {

        return vazeciMeteoPodaci;
    }

    public String getMeteoNaziv() {
        return meteoNaziv;
    }

    public void setMeteoNaziv(String meteoNaziv) {
        this.meteoNaziv = meteoNaziv;
    }

    public List<org.foi.nwtis.ffaletar.soap.klijenti.MeteoPodaci> getMeteoPodaciUVremenskomIntervalu() {
        return meteoPodaciUVremenskomIntervalu;
    }

    public void setMeteoPodaciUVremenskomIntervalu(List<org.foi.nwtis.ffaletar.soap.klijenti.MeteoPodaci> meteoPodaciUVremenskomIntervalu) {
        this.meteoPodaciUVremenskomIntervalu = meteoPodaciUVremenskomIntervalu;
    }

    public boolean isMeteoPodaciUVremenskomIntervaluPanel() {
        return meteoPodaciUVremenskomIntervaluPanel;
    }

    public void setMeteoPodaciUVremenskomIntervaluPanel(boolean meteoPodaciUVremenskomIntervaluPanel) {
        this.meteoPodaciUVremenskomIntervaluPanel = meteoPodaciUVremenskomIntervaluPanel;
    }

    public boolean isZadnjihNMeteoPodatakaPanel() {
        return zadnjihNMeteoPodatakaPanel;
    }

    public void setZadnjihNMeteoPodatakaPanel(boolean zadnjihNMeteoPodatakaPanel) {
        this.zadnjihNMeteoPodatakaPanel = zadnjihNMeteoPodatakaPanel;
    }

    public String getMeteoTemp() {
        return meteoTemp;
    }

    public void setMeteoTemp(String meteoTemp) {
        this.meteoTemp = meteoTemp;
    }

    public String getMeteoMaxTemp() {
        return meteoMaxTemp;
    }

    public void setMeteoMaxTemp(String meteoMaxTemp) {
        this.meteoMaxTemp = meteoMaxTemp;
    }

    public String getMeteoMinTemp() {
        return meteoMinTemp;
    }

    public void setMeteoMinTemp(String meteoMinTemp) {
        this.meteoMinTemp = meteoMinTemp;
    }

    public String getMeteoVlaga() {
        return meteoVlaga;
    }

    public void setMeteoVlaga(String meteoVlaga) {
        this.meteoVlaga = meteoVlaga;
    }

    public String getMeteoTlak() {
        return meteoTlak;
    }

    public void setMeteoTlak(String meteoTlak) {
        this.meteoTlak = meteoTlak;
    }

    public void setVazeciMeteoPodaci(MeteoPodaci vazeciMeteoPodaci) {
        this.vazeciMeteoPodaci = vazeciMeteoPodaci;
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

    public boolean isVazeciMeteoPodaciPanel() {
        return vazeciMeteoPodaciPanel;
    }

    public void setVazeciMeteoPodaciPanel(boolean vazeciMeteoPodaciPanel) {
        this.vazeciMeteoPodaciPanel = vazeciMeteoPodaciPanel;
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

    public List<org.foi.nwtis.ffaletar.soap.klijenti.MeteoPodaci> getZadnjihNMeteoPodataka() {
        return zadnjihNMeteoPodataka;
    }

    public void setZadnjihNMeteoPodataka(List<org.foi.nwtis.ffaletar.soap.klijenti.MeteoPodaci> zadnjihNMeteoPodataka) {
        this.zadnjihNMeteoPodataka = zadnjihNMeteoPodataka;
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
        } else {
            poruka = "Niste odabrali uređaj";
        }

    }

    public void dohvatiVazeceMeteoPodatke() {

        org.foi.nwtis.ffaletar.soap.klijenti.MeteoPodaci mp = new org.foi.nwtis.ffaletar.soap.klijenti.MeteoPodaci();
        if (popisRaspoloziviIoT.size() == 1) {
            idZaAzuriranje = popisRaspoloziviIoT.get(0);

            for (Iterator<Map.Entry<String, Uredjaj>> iterator = raspoloziviIoT.entrySet().iterator(); iterator.hasNext();) {
                Map.Entry<String, Uredjaj> uredaj = iterator.next();

                if (uredaj.getValue().toString().compareTo(idZaAzuriranje) == 0) {
                    setMeteoNaziv(uredaj.getValue().getNaziv());

                    mp = MeteoSOAP.vazeciMeteoPodaciZaUredaj(uredaj.getValue().getId());
                    break;
                }
            }
        } else {
            poruka = "Niste odabrali uređaj";
        }
        if (mp != null) {
            setMeteoTemp(mp.getTemperatureValue().toString());
            setMeteoMaxTemp(mp.getTemperatureMax().toString());
            setMeteoMinTemp(mp.getTemperatureMin().toString());
            setMeteoVlaga(mp.getHumidityValue().toString());
            setMeteoTlak(mp.getPressureValue().toString());
        }

        vazeciMeteoPodaciPanel = true;

    }

    public Map<String, Uredjaj> dohvatiRaspoloziveIoT() {
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

    public void azurirajIoT() {
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

    public void dohvatiSveMeteoPodatkeUVremenskomIntervalu() {

        meteoPodaciUVremenskomIntervalu = new ArrayList<>();
        if (popisRaspoloziviIoT.size() == 1) {
            idZaAzuriranje = popisRaspoloziviIoT.get(0);

            for (Iterator<Map.Entry<String, Uredjaj>> iterator = raspoloziviIoT.entrySet().iterator(); iterator.hasNext();) {
                Map.Entry<String, Uredjaj> uredaj = iterator.next();

                if (uredaj.getValue().toString().compareTo(idZaAzuriranje) == 0) {
                    setMeteoNaziv(uredaj.getValue().getNaziv());

                    meteoPodaciUVremenskomIntervalu = MeteoSOAP.meteoPodaciUVremenskomRazdoblju(uredaj.getValue().getId(), 1497638261000L, 1497639263000L);
                    break;
                }
            }
        } else {
            poruka = "Niste odabrali uređaj";
        }

        meteoPodaciUVremenskomIntervaluPanel = true;
    }

    public void dohvatiZadnjihNMeteoPodataka() {

        zadnjihNMeteoPodataka = new ArrayList<>();
        if (popisRaspoloziviIoT.size() == 1) {
            idZaAzuriranje = popisRaspoloziviIoT.get(0);

            for (Iterator<Map.Entry<String, Uredjaj>> iterator = raspoloziviIoT.entrySet().iterator(); iterator.hasNext();) {
                Map.Entry<String, Uredjaj> uredaj = iterator.next();

                if (uredaj.getValue().toString().compareTo(idZaAzuriranje) == 0) {
                    setMeteoNaziv(uredaj.getValue().getNaziv());

                    zadnjihNMeteoPodataka = MeteoSOAP.zadnjihNMeteoPodatakaZaUredjaj(uredaj.getValue().getId(), brojMeteo);
                    break;
                }
            }
        } else {
            poruka = "Niste odabrali uređaj";
        }

        zadnjihNMeteoPodatakaPanel = true;
    }

    public void dohvatiAdresuUredaja() {

        if (popisRaspoloziviIoT.size() == 1) {
            idZaAzuriranje = popisRaspoloziviIoT.get(0);

            for (Iterator<Map.Entry<String, Uredjaj>> iterator = raspoloziviIoT.entrySet().iterator(); iterator.hasNext();) {
                Map.Entry<String, Uredjaj> uredaj = iterator.next();

                if (uredaj.getValue().toString().compareTo(idZaAzuriranje) == 0) {
                    setMeteoNaziv(uredaj.getValue().getNaziv());

                    adresaUredaja = MeteoSOAP.adresaUredajaPremaGeolokaciji(uredaj.getValue().getId());
                    break;
                }
            }
        } else {
            poruka = "Niste odabrali uređaj";
        }

        adresaUredajaPanel = true;

    }

}
