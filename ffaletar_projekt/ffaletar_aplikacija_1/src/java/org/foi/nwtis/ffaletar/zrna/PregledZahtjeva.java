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
import org.foi.nwtis.ffaletar.baza.DnevnikBaza;
import org.foi.nwtis.ffaletar.helpers.KonfiguracijaHelper;
import org.foi.nwtis.ffaletar.podaci.Dnevnik;

/**
 *
 * @author Filip
 */
@Named(value = "pregledZahtjeva")
@SessionScoped
public class PregledZahtjeva implements Serializable {

    private static int brojPrikazaPoStranici;
    private static int trenutnaStranica;
    private List<Dnevnik> dnevnikWS;
    private HttpSession session;
    private FacesContext context;
    private String poruka;
    private boolean kraj = false;

    
    /**
     * Creates a new instance of PregledZahtjeva
     */
    public PregledZahtjeva() {
        context = FacesContext.getCurrentInstance();
        session = (HttpSession) context.getExternalContext().getSession(true);
        brojPrikazaPoStranici = KonfiguracijaHelper.getBrojPrikaza();
        trenutnaStranica = 1;
        preuzmiDnevnik();
    }
    
    
    public String getPoruka() {
        return poruka;
    }

    public void setPoruka(String poruka) {
        this.poruka = poruka;
    }

    public List<Dnevnik> getDnevnikWS() {

        return dnevnikWS;
    }

    public void setDnevnikWS(List<Dnevnik> dnevnikWS) {
        this.dnevnikWS = dnevnikWS;
    }

    public void postaviPodatke() {

    }

    public void prethodnaStranica() {

        if (trenutnaStranica == 1) {
            return;
        } else {
            trenutnaStranica = trenutnaStranica - 1;
            preuzmiDnevnik();
        }
        kraj = false;

    }

    public void sljedecaStranica() {

        if (!kraj) {
            trenutnaStranica = trenutnaStranica + 1;
            preuzmiDnevnik();
        }

    }

    public void preuzmiDnevnik() {
        DnevnikBaza db = new DnevnikBaza();

        int pocetak = trenutnaStranica * brojPrikazaPoStranici - brojPrikazaPoStranici + 1;
        int krajPregleda = trenutnaStranica * brojPrikazaPoStranici;

        dnevnikWS = db.dajDnevnik(1, pocetak, krajPregleda);
        int brojPrikazaPostavke = KonfiguracijaHelper.getBrojPrikaza() - 1;
        if (dnevnikWS.size() != brojPrikazaPostavke) {
            kraj = true;
        }

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
    
}
