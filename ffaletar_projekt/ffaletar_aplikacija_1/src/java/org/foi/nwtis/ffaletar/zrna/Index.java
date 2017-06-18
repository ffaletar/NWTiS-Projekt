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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.foi.nwtis.ffaletar.baza.KorisnikBaza;
import org.foi.nwtis.ffaletar.podaci.Korisnik;

/**
 *
 * @author Filip
 */
@Named(value = "index")
@SessionScoped
public class Index implements Serializable {

    private String poruka;
    private List<Korisnik> listaKorisnika;
    private HttpSession session;
    private FacesContext context;
    
    /**
     * Creates a new instance of Index
     */
    public Index() {
        context = FacesContext.getCurrentInstance();
        session = (HttpSession) context.getExternalContext().getSession(true);
    }
    
    public void preusmjeriAkoJeAktivnaSesija() {
        if (session.getAttribute("korisnickoIme") == null) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("/ffaletar_aplikacija_1/prijava.jsf");
            } catch (IOException ex) {
                Logger.getLogger(Prijava.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public String getPoruka() {
        return poruka;
    }

    public void setPoruka(String poruka) {
        this.poruka = poruka;
    }

    public List<Korisnik> getListaKorisnika() {
        KorisnikBaza kb = new KorisnikBaza();
        
        List<Korisnik> korisnici = kb.dajSveKorisnike();
        
        listaKorisnika = korisnici;
        
        return listaKorisnika;
    }

    public void setListaKorisnika(List<Korisnik> listaKorisnika) {
        this.listaKorisnika = listaKorisnika;
    }
    
    public void logout(){
        session = BeanHelper.logout(session);
    }
    
    
}
