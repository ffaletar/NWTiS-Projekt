/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.ffaletar.zrna;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Locale;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import org.foi.nwtis.ffaletar.podaci.Izbornik;

/**
 *
 * @author 
 */
@Named(value = "lokalizacija")
@SessionScoped
public class Lokalizacija implements Serializable {

    private String odabirJezika;
    private final ArrayList<Izbornik> izbornik = new ArrayList<>();
    
    /**
     * Creates a new instance of Lokalizacija
     */
    public Lokalizacija() {
        izbornik.add(new Izbornik("Hrvatski", "hr"));
        izbornik.add(new Izbornik("English", "en"));
    }

    public ArrayList<Izbornik> getIzbornik() {
        return izbornik;
    }

    public String getOdabirJezika() {
        UIViewRoot UIVR = FacesContext.getCurrentInstance().getViewRoot();
        
        if(UIVR != null){
            Locale jezik = FacesContext.getCurrentInstance().getViewRoot().getLocale();
            odabirJezika = jezik.getLanguage();
        }
        return odabirJezika;
    }

    public void setOdabirJezika(String odabirJezika) {
        this.odabirJezika = odabirJezika;
        Locale jezik = new Locale(odabirJezika);
        FacesContext.getCurrentInstance().getViewRoot().setLocale(jezik);
    }
    
    public void izaberiJezik(){
        setOdabirJezika(odabirJezika);
    }
    
}
