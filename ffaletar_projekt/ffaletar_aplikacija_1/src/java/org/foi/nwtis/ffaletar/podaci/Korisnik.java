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
    private String ime;
    private String prezime;
    private String lozinka;
    private String mail;
    private int tipKorisnika;

    public Korisnik(int ID, String korisnickoIme, String ime, String prezime, String lozinka, String mail, int tipKorisnika) {
        this.ID = ID;
        this.korisnickoIme = korisnickoIme;
        this.ime = ime;
        this.prezime = prezime;
        this.lozinka = lozinka;
        this.mail = mail;
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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
    
}
