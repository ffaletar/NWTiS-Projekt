/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.ffaletar.zrna;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Filip
 */
@Named(value = "beanHelper")
@Dependent
public class BeanHelper {

    /**
     * Creates a new instance of BeanHelper
     */
    public BeanHelper() {
    }
    
    public static HttpSession azurirajPodatkeSesije(HttpSession session, String korime, String loz, int idKorisnika, String imeKorisnika, String prezimeKorisnika, String mailKorisnika) {
        session.setAttribute("korisnickoIme", korime);
        session.setAttribute("lozinka", loz);
        session.setAttribute("idKorisnika", idKorisnika);
        session.setAttribute("ime", imeKorisnika);
        session.setAttribute("prezime", prezimeKorisnika);
        session.setAttribute("mail", mailKorisnika);
        
        return session;

    }
    
    public static HttpSession logout(HttpSession session) {

        if (session.getAttribute("korisnickoIme") != null) {
            session.removeAttribute("korisnickoIme");
        }
        if (session.getAttribute("idKorisnika") != null) {
            session.removeAttribute("idKorisnika");
        }
        if (session.getAttribute("ime") != null) {
            session.removeAttribute("ime");
        }
        if (session.getAttribute("prezime") != null) {
            session.removeAttribute("prezime");
        }
        if (session.getAttribute("mail") != null) {
            session.removeAttribute("mail");
        }
        if (session.getAttribute("lozinka") != null) {
            session.removeAttribute("lozinka");
        }

        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/ffaletar_aplikacija_2_2/pocetna.jsf");
        } catch (IOException ex) {
            Logger.getLogger(Pocetna.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return session;
    }
    
    public static HttpSession pokreniSesiju(HttpSession session, String korime, String loz, int idKorisnika, String imeKorisnika, String prezimeKorisnika, String mailKorisnika) {
        session.setAttribute("korisnickoIme", korime);
        session.setAttribute("lozinka", loz);
        session.setAttribute("idKorisnika", idKorisnika);
        session.setAttribute("ime", imeKorisnika);
        session.setAttribute("prezime", prezimeKorisnika);
        session.setAttribute("mail", mailKorisnika);

        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/ffaletar_aplikacija_2_2/index.jsf");
        } catch (IOException ex) {
            Logger.getLogger(Pocetna.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return session;
    }
    
}
