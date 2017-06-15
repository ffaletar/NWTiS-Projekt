/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.foi.nwtis.ffaletar.podaci;

import java.util.Date;

/**
 *
 * @author dkermek
 */
public class Uredjaj {
    private int id;
    private String naziv;
    private Lokacija geoloc;
    private int status;
    private String vrijemePromjene;
    private String vrijemeKreiranja;

    public Uredjaj() {
    }

    public Uredjaj(int id, String naziv, Lokacija geoloc) {
        this.id = id;
        this.naziv = naziv;
        this.geoloc = geoloc;
    }

    public Uredjaj(int id, String naziv, Lokacija geoloc, int status, String vrijemePromjene, String vrijemeKreiranja) {
        this.id = id;
        this.naziv = naziv;
        this.geoloc = geoloc;
        this.status = status;
        this.vrijemePromjene = vrijemePromjene;
        this.vrijemeKreiranja = vrijemeKreiranja;
    }
    
    

    public Lokacija getGeoloc() {
        return geoloc;
    }

    public void setGeoloc(Lokacija geoloc) {
        this.geoloc = geoloc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }        

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getVrijemePromjene() {
        return vrijemePromjene;
    }

    public void setVrijemePromjene(String vrijemePromjene) {
        this.vrijemePromjene = vrijemePromjene;
    }

    public String getVrijemeKreiranja() {
        return vrijemeKreiranja;
    }

    public void setVrijemeKreiranja(String vrijemeKreiranja) {
        this.vrijemeKreiranja = vrijemeKreiranja;
    }
}
