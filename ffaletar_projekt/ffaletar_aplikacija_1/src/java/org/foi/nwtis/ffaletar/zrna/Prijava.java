/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.ffaletar.zrna;

import java.io.IOException;
import javax.inject.Named;
//import javax.enterprise.context.SessionScoped;
import javax.faces.bean.SessionScoped;
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
import org.foi.nwtis.ffaletar.baza.Baza;
import org.foi.nwtis.ffaletar.baza.KorisnikBaza;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Filip
 */
@Named(value = "prijava")
@SessionScoped
public class Prijava implements Serializable {

    private String korisnickoImePrijava;
    private String lozinkaPrijava;
    private String porukaPrijava;

    private HttpSession session;
    private FacesContext context;

    /**
     * Creates a new instance of Prijava
     */
    public Prijava() {
        context = FacesContext.getCurrentInstance();
        session = (HttpSession) context.getExternalContext().getSession(true);
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

    public void prijaviSe() {

        boolean korisnikPrijavljen = KorisnikBaza.provjeriKorisnika(getKorisnickoImePrijava(), getLozinkaPrijava());

        if (korisnikPrijavljen) {

            session = BeanHelper.pokreniSesiju(session, getKorisnickoImePrijava(), getLozinkaPrijava());

        } else {
            porukaPrijava = "Ne postoji korisnik s unesenim korisničkim podacima";
        }

    }

    public void preusmjeriAkoJeAktivnaSesija() {
        if (session.getAttribute("korisnickoIme") != null) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("/ffaletar_aplikacija_1/index.jsf");
            } catch (IOException ex) {
                Logger.getLogger(Prijava.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
