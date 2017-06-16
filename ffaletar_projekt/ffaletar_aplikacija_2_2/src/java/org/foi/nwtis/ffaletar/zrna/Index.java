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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.servlet.http.HttpSession;
import org.foi.nwtis.ffaletar.helpers.Sesija;
import org.foi.nwtis.ffaletar.podaci.Korisnik;
import org.foi.nwtis.ffaletar.rest.klijenti.KorisniciREST;

/**
 *
 * @author Filip
 */
@Named(value = "index")
@SessionScoped
public class Index implements Serializable {

    private int id;
    private String korisnickoIme;
    private String ime;
    private String prezime;
    private String lozinka;
    private String mail;
    private String poruka;
    private List<Korisnik> korisnici;

    private HttpSession session;
    private FacesContext context;

    /**
     * Creates a new instance of Index
     */
    public Index() {

        context = FacesContext.getCurrentInstance();
        session = (HttpSession) context.getExternalContext().getSession(false);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPoruka() {
        return poruka;
    }

    public void setPoruka(String poruka) {
        this.poruka = poruka;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public List<Korisnik> getKorisnici() {
        
        KorisniciREST korisniciREST = new KorisniciREST();
        String korisnici = korisniciREST.getJson();
        
        JsonReader reader = Json.createReader(new StringReader(korisnici));
        JsonArray jsonArray = reader.readArray();
        

        List<Korisnik> korisniciLista = new ArrayList<Korisnik>();
        
        
        for (int i = 0; i < jsonArray.size(); i++) {
            Korisnik korisnik = new Korisnik();
            korisnik.setID(jsonArray.getJsonObject(i).getInt("id"));
            korisnik.setKorisnickoIme(jsonArray.getJsonObject(i).getJsonString("korisnickoIme").toString());
            korisnik.setIme(jsonArray.getJsonObject(i).getJsonString("ime").toString());
            korisnik.setPrezime(jsonArray.getJsonObject(i).getJsonString("prezime").toString());
            korisnik.setMail(jsonArray.getJsonObject(i).getJsonString("mail").toString());
            korisnik.setTipKorisnika(jsonArray.getJsonObject(i).getInt("tipKorisnika"));
            
            
            korisniciLista.add(korisnik);
        }
        
        return korisniciLista;
    }

    public void setKorisnici(List<Korisnik> korisnici) {
        this.korisnici = korisnici;
    }
    
    

    public void azurirajPodatke() {
        if (provjeriIspunjenostRegistracije()) {
            JsonObjectBuilder job = Json.createObjectBuilder();
            job.add("id", getId());
            job.add("korisnickoIme", getKorisnickoIme());
            job.add("ime", getIme());
            job.add("prezime", getPrezime());
            job.add("lozinka", getLozinka());
            job.add("mail", getMail());
            job.add("tipKorisnika", 2);

            KorisniciREST korisniciREST = new KorisniciREST();
            String korisnikAzuriran = korisniciREST.azurirajKorisnika(job.build().toString());

            if (korisnikAzuriran.equals("1")) {
                session = BeanHelper.azurirajPodatkeSesije(session, getKorisnickoIme(), getLozinka(), getId(), getIme(), getPrezime(), getMail());
            } else {
                poruka = "Korisnik nije uspješno ažuriran";
            }
        } else {
            poruka = "Greška prilikom registracije";
        }
    }

    public boolean provjeriIspunjenostRegistracije() {
        if (!getKorisnickoIme().equals("") && getKorisnickoIme() != null) {
            if (!getIme().equals("") && getIme() != null) {
                if (!getPrezime().equals("") && getPrezime() != null) {
                    if (!getLozinka().equals("") && getLozinka() != null) {
                        if (!getKorisnickoIme().equals("") && getKorisnickoIme() != null) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }


    public void postaviPodatke() {
        if (session != null) {
            if (session.getAttribute("idKorisnika") != null) {
                id = (int) session.getAttribute("idKorisnika");
            } else {
                BeanHelper.logout(session);
            }

            if (session != null) {
                if (session.getAttribute("korisnickoIme") != null) {
                    korisnickoIme = (String) session.getAttribute("korisnickoIme");
                }
            } else {
                BeanHelper.logout(session);
            }

            if (session != null) {
                if (session.getAttribute("ime") != null) {
                    ime = (String) session.getAttribute("ime");
                }
            } else {
                BeanHelper.logout(session);
            }

            if (session != null) {
                if (session.getAttribute("prezime") != null) {
                    prezime = (String) session.getAttribute("prezime");
                }
            } else {
                BeanHelper.logout(session);
            }

            if (session != null) {
                if (session.getAttribute("lozinka") != null) {
                    lozinka = (String) session.getAttribute("lozinka");
                }
            } else {
                BeanHelper.logout(session);
            }

            if (session != null) {
                if (session.getAttribute("mail") != null) {
                    mail = (String) session.getAttribute("mail");
                }
            } else {
                BeanHelper.logout(session);
            }
        } else {
            BeanHelper.logout(session);
        }
    }
    
    public void logout(){
        session = BeanHelper.logout(session);
    }
    

}
