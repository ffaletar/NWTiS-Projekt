/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.ffaletar.zrna;

import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.servlet.http.HttpSession;
import org.foi.nwtis.ffaletar.helpers.Sesija;
import org.foi.nwtis.ffaletar.rest.klijenti.KorisniciREST;

/**
 *
 * @author Filip
 */
@Named(value = "pocetna")
@SessionScoped
public class Pocetna implements Serializable {

    private String korisnickoIme;
    private String ime;
    private String prezime;
    private String lozinka;
    private String potvrdaLozinke;
    private String email;
    private String poruka;

    public Pocetna() {
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getPoruka() {
        return poruka;
    }

    public void setPoruka(String poruka) {
        this.poruka = poruka;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public String getPotvrdaLozinke() {
        return potvrdaLozinke;
    }

    public void setPotvrdaLozinke(String potvrdaLozinke) {
        this.potvrdaLozinke = potvrdaLozinke;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void registrirajKorisnika() {

        if (provjeriIspunjenostRegistracije()) {
            JsonObjectBuilder job = Json.createObjectBuilder();
            job.add("korisnickoIme", getKorisnickoIme());
            job.add("ime", getIme());
            job.add("prezime", getPrezime());
            job.add("lozinka", getLozinka());
            job.add("mail", getEmail());

            KorisniciREST korisniciREST = new KorisniciREST();
            String korisnikDodan = korisniciREST.dodajKorisnika(job.build().toString());

            if (korisnikDodan.equals("1")) {
                setKorisnickoIme("");
                setLozinka("");
                setIme("");
                setPrezime("");
                setPoruka("");
                setPotvrdaLozinke("");
                setEmail("");
            } else {

            }
        } else {
            poruka = "Greška prilikom registracije";
        }

    }

    public void prijaviSe() {

        KorisniciREST korisniciREST = new KorisniciREST();
        String content = korisniciREST.dajKorisnika(getKorisnickoIme());

        if (!content.equals("")) {
            JsonReader reader = Json.createReader(new StringReader(content));
            JsonObject jo = reader.readObject();
            int id = jo.getInt("id");
            String korisnickoIme = jo.getString("korisnickoIme");
            String ime = jo.getString("ime");
            String prezime = jo.getString("prezime");
            String lozinka = jo.getString("lozinka");
            String mail = jo.getString("mail");
            int tipKorisnika = jo.getInt("tipKorisnika");

            if (korisnickoIme.equals(getKorisnickoIme()) && lozinka.equals(getLozinka())) {
                boolean sesijaPokrenuta = pokreniSesiju(korisnickoIme, lozinka, id);

                if (sesijaPokrenuta) {
                    try {
                        FacesContext.getCurrentInstance().getExternalContext().redirect("/ffaletar_aplikacija_2_2/index.xhtml");
                    } catch (IOException ex) {
                        Logger.getLogger(Pocetna.class.getName()).log(Level.SEVERE, null, ex);
                        poruka = "Prijavljeni ste! Greška prilikom redirekcije";
                    }
                } else {
                    poruka = "Greška prilikom pokretanja sesije";
                }
            } else {
                poruka = "Greška prilikom prijave";
            }
        }else{
            poruka = "Ne postoji korisnik s unesenim korisničkim imenom";
        }

    }

    public boolean pokreniSesiju(String korisnickoIme, String lozinka, int id) {
        HttpSession session = Sesija.getSession();
        session.setAttribute("korisnickoIme", this.korisnickoIme);
        session.setAttribute("lozinka", this.lozinka);
        session.setAttribute("idKorisnika", id);

        if (session != null) {
            return true;
        } else {
            return false;
        }
    }

    public boolean provjeriIspunjenostRegistracije() {
        boolean korisnickoImePopunjeno;
        boolean imePopunjeno;
        boolean prezimePopunjeno;
        boolean lozinkaPopunjena;
        boolean provjeraLozinkePopunjena;
        boolean mailPopunjen;
        boolean lozinkaIPotvrdaLozinkeJednake;
        boolean registracijaPopunjena;

        if (!getKorisnickoIme().equals("") && getKorisnickoIme() != null) {
            korisnickoImePopunjeno = true;
            if (!getIme().equals("") && getIme() != null) {
                imePopunjeno = true;
                if (!getPrezime().equals("") && getPrezime() != null) {
                    prezimePopunjeno = true;
                    if (!getLozinka().equals("") && getLozinka() != null) {
                        lozinkaPopunjena = true;
                        if (!getPotvrdaLozinke().equals("") && getPotvrdaLozinke() != null) {
                            provjeraLozinkePopunjena = true;
                            if (!getKorisnickoIme().equals("") && getKorisnickoIme() != null) {
                                mailPopunjen = true;
                                if (getLozinka().equals(getPotvrdaLozinke())) {
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
}
