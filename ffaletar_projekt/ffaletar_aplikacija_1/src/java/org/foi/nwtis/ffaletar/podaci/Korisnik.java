/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.ffaletar.podaci;

/**
 *
 * @author Filip
 */
public class Korisnik {
    
    private int ID;
    private String korisnickoIme;
    private String lozinka;
    private int tipKorisnika;

    public Korisnik(int ID, String korisnickoIme, String lozinka, int tipKorisnika) {
        this.ID = ID;
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
        this.tipKorisnika = tipKorisnika;
    }

    public Korisnik() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public int getTipKorisnika() {
        return tipKorisnika;
    }

    public void setTipKorisnika(int tipKorisnika) {
        this.tipKorisnika = tipKorisnika;
    }
}
