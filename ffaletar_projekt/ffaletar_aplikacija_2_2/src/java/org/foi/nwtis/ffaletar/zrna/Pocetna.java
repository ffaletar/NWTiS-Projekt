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

    private String korisnickoImeRegistracija;
    private String imeRegistracija;
    private String prezimeRegistracija;
    private String lozinkaRegistracija;
    private String potvrdaLozinkeRegistracija;
    private String emailRegistracija;
    private String porukaRegistracija;

    private String korisnickoImePrijava;
    private String lozinkaPrijava;
    private String porukaPrijava;

    private HttpSession session;
    private FacesContext context;

    public Pocetna() {

        context = FacesContext.getCurrentInstance();
        session = (HttpSession) context.getExternalContext().getSession(true);
    }

    public String getKorisnickoImeRegistracija() {
        return korisnickoImeRegistracija;
    }

    public void setKorisnickoImeRegistracija(String korisnickoImeRegistracija) {
        this.korisnickoImeRegistracija = korisnickoImeRegistracija;
    }

    public String getImeRegistracija() {
        return imeRegistracija;
    }

    public void setImeRegistracija(String imeRegistracija) {
        this.imeRegistracija = imeRegistracija;
    }

    public String getPrezimeRegistracija() {
        return prezimeRegistracija;
    }

    public void setPrezimeRegistracija(String prezimeRegistracija) {
        this.prezimeRegistracija = prezimeRegistracija;
    }

    public String getLozinkaRegistracija() {
        return lozinkaRegistracija;
    }

    public void setLozinkaRegistracija(String lozinkaRegistracija) {
        this.lozinkaRegistracija = lozinkaRegistracija;
    }

    public String getPotvrdaLozinkeRegistracija() {
        return potvrdaLozinkeRegistracija;
    }

    public void setPotvrdaLozinkeRegistracija(String potvrdaLozinkeRegistracija) {
        this.potvrdaLozinkeRegistracija = potvrdaLozinkeRegistracija;
    }

    public String getEmailRegistracija() {
        return emailRegistracija;
    }

    public void setEmailRegistracija(String emailRegistracija) {
        this.emailRegistracija = emailRegistracija;
    }

    public String getPorukaRegistracija() {
        return porukaRegistracija;
    }

    public void setPorukaRegistracija(String porukaRegistracija) {
        this.porukaRegistracija = porukaRegistracija;
    }

    public String getKorisnickoImePrijava() {
        return korisnickoImePrijava;
    }

    public void setKorisnickoImePrijava(String korisnickoImePrijava) {
        this.korisnickoImePrijava = korisnickoImePrijava;
    }

    public String getLozinkaPrijava() {
        return lozinkaPrijava;
    }

    public void setLozinkaPrijava(String lozinkaPrijava) {
        this.lozinkaPrijava = lozinkaPrijava;
    }

    public String getPorukaPrijava() {
        return porukaPrijava;
    }

    public void setPorukaPrijava(String porukaPrijava) {
        this.porukaPrijava = porukaPrijava;
    }

    public void registrirajKorisnika() {

        if (provjeriIspunjenostRegistracije()) {
            JsonObjectBuilder job = Json.createObjectBuilder();
            job.add("korisnickoIme", getKorisnickoImeRegistracija());
            job.add("ime", getImeRegistracija());
            job.add("prezime", getPrezimeRegistracija());
            job.add("lozinka", getLozinkaRegistracija());
            job.add("mail", getEmailRegistracija());

            KorisniciREST korisniciREST = new KorisniciREST();
            String korisnikDodan = korisniciREST.dodajKorisnika(job.build().toString());

            if (korisnikDodan.equals("1")) {
                setKorisnickoImeRegistracija("");
                setLozinkaRegistracija("");
                setImeRegistracija("");
                setPrezimeRegistracija("");
                setPorukaRegistracija("");
                setPotvrdaLozinkeRegistracija("");
                setEmailRegistracija("");
            } else {

            }
        } else {
            porukaRegistracija = "Greška prilikom registracije";
        }

    }

    public void prijaviSe() {

        KorisniciREST korisniciREST = new KorisniciREST();
        String content = korisniciREST.dajKorisnika(getKorisnickoImePrijava());

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

            if (korisnickoIme.equals(getKorisnickoImePrijava()) && lozinka.equals(getLozinkaPrijava())) {

                session = BeanHelper.pokreniSesiju(session, korisnickoIme, lozinka, id, ime, prezime, mail);

                resetObrazaca();
                
            } else {
                porukaPrijava = "Kriva lozinka";
            }
        } else {
            porukaPrijava = "Ne postoji korisnik s unesenim korisničkim imenom";
        }

    }

    public boolean provjeriIspunjenostRegistracije() {
        if (!getKorisnickoImeRegistracija().equals("") && getKorisnickoImeRegistracija() != null) {
            if (!getImeRegistracija().equals("") && getImeRegistracija() != null) {
                if (!getPrezimeRegistracija().equals("") && getPrezimeRegistracija() != null) {
                    if (!getLozinkaRegistracija().equals("") && getLozinkaRegistracija() != null) {
                        if (!getPotvrdaLozinkeRegistracija().equals("") && getPotvrdaLozinkeRegistracija() != null) {
                            if (!getEmailRegistracija().equals("") && getEmailRegistracija() != null) {
                                if (getLozinkaRegistracija().equals(getPotvrdaLozinkeRegistracija())) {
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

    public void preusmjeriAkoJeAktivnaSesija() {
        if (session.getAttribute("korisnickoIme") != null) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("/ffaletar_aplikacija_2_2/index.jsf");
            } catch (IOException ex) {
                Logger.getLogger(Pocetna.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    public void resetObrazaca() {
        setKorisnickoImePrijava("");
        setLozinkaPrijava("");
        setPorukaPrijava("");

        setKorisnickoImeRegistracija("");
        setEmailRegistracija("");
        setLozinkaRegistracija("");
        setImeRegistracija("");
        setPrezimeRegistracija("");
        setPotvrdaLozinkeRegistracija("");
        setPorukaRegistracija("");
    }

}
