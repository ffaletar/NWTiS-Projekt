/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.ffaletar.zrna;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.foi.nwtis.ffaletar.helpers.KonfiguracijaHelper;

/**
 *
 * @author Filip
 */
@Named(value = "kontrolaUredaja")
@SessionScoped
public class KontrolaUredaja implements Serializable {

    private HttpSession session;
    private FacesContext context;
    private String porukaOPosluzitelju;
    private String porukaOKontroliUredaja;
    private String porukaOGrupi;
    private static Socket s;
    
    private String IoTUredajiUGrupi;
    private String idUredaja;
    private String nazivUredaja;
    private String adresaUredaja;
    private String idIoTUredaja;
    private String porukaOIoTUredaju;
    

    
    
    /**
     * Creates a new instance of KontrolaUredaja
     */
    public KontrolaUredaja() {
        context = FacesContext.getCurrentInstance();
        session = (HttpSession) context.getExternalContext().getSession(false);
    }

    public String getIdUredaja() {
        return idUredaja;
    }

    public void setIdUredaja(String idUredaja) {
        this.idUredaja = idUredaja;
    }

    public String getPorukaOIoTUredaju() {
        return porukaOIoTUredaju;
    }

    public void setPorukaOIoTUredaju(String porukaOIoTUredaju) {
        this.porukaOIoTUredaju = porukaOIoTUredaju;
    }
    
    

    public String getIdIoTUredaja() {
        return idIoTUredaja;
    }

    public void setIdIoTUredaja(String idIoTUredaja) {
        this.idIoTUredaja = idIoTUredaja;
    }
    
    

    public String getNazivUredaja() {
        return nazivUredaja;
    }

    public void setNazivUredaja(String nazivUredaja) {
        this.nazivUredaja = nazivUredaja;
    }

    public String getAdresaUredaja() {
        return adresaUredaja;
    }

    public void setAdresaUredaja(String adresaUredaja) {
        this.adresaUredaja = adresaUredaja;
    }

    

    

    public String getPorukaOPosluzitelju() {
        return porukaOPosluzitelju;
    }

    public void setPorukaOPosluzitelju(String porukaOPosluzitelju) {
        this.porukaOPosluzitelju = porukaOPosluzitelju;
    }

    public String getPorukaOKontroliUredaja() {
        return porukaOKontroliUredaja;
    }

    public void setPorukaOKontroliUredaja(String porukaOKontroliUredaja) {
        this.porukaOKontroliUredaja = porukaOKontroliUredaja;
    }
    
    

    public String getPorukaOGrupi() {
        return porukaOGrupi;
    }

    public String getIoTUredajiUGrupi() {
        return IoTUredajiUGrupi;
    }

    public void setIoTUredajiUGrupi(String IoTUredajiUGrupi) {
        this.IoTUredajiUGrupi = IoTUredajiUGrupi;
    }
    
    

    /**
     * Creates a new instance of KontrolaServera
     */
    public void setPorukaOGrupi(String porukaOGrupi) {
        this.porukaOGrupi = porukaOGrupi;
    }

    public void dohvatiUredajeGrupe() {
        try {
            s = new Socket(KonfiguracijaHelper.getHost(), KonfiguracijaHelper.getPort());
            InputStream is = s.getInputStream();
            OutputStream os = s.getOutputStream();
            String zahtjev = "USER " + session.getAttribute("korisnickoIme") + "; PASSWD " + session.getAttribute("lozinka") + "; IoT_Master LIST;";

            os.write(zahtjev.getBytes());
            os.flush();
            s.shutdownOutput();

            System.out.println("Poruka poslana");

            StringBuffer sb = new StringBuffer();
            while (true) {
                int znak = is.read();
                if (znak == -1) {
                    break;
                }
                sb.append((char) znak);
            }
            s.close();
            System.out.println("Primljeni  odgovor: " + sb.toString());

            porukaOPosluzitelju = sb.toString();
        } catch (IOException ex) {
            Logger.getLogger(KontrolaServera.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void ucitajPredefiniraneUredaje() {
        try {
            s = new Socket(KonfiguracijaHelper.getHost(), KonfiguracijaHelper.getPort());
            InputStream is = s.getInputStream();
            OutputStream os = s.getOutputStream();
            String zahtjev = "USER " + session.getAttribute("korisnickoIme") + "; PASSWD " + session.getAttribute("lozinka") + "; IoT_Master LOAD;";

            os.write(zahtjev.getBytes());
            os.flush();
            s.shutdownOutput();

            System.out.println("Poruka poslana");

            StringBuffer sb = new StringBuffer();
            while (true) {
                int znak = is.read();
                if (znak == -1) {
                    break;
                }
                sb.append((char) znak);
            }
            s.close();
            System.out.println("Primljeni  odgovor: " + sb.toString());

            porukaOPosluzitelju = sb.toString();
        } catch (IOException ex) {
            Logger.getLogger(KontrolaServera.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void brisiSveUredajeGrupe() {
        try {
            s = new Socket(KonfiguracijaHelper.getHost(), KonfiguracijaHelper.getPort());
            InputStream is = s.getInputStream();
            OutputStream os = s.getOutputStream();
            String zahtjev = "USER " + session.getAttribute("korisnickoIme") + "; PASSWD " + session.getAttribute("lozinka") + "; IoT_Master CLEAR;";

            os.write(zahtjev.getBytes());
            os.flush();
            s.shutdownOutput();

            System.out.println("Poruka poslana");

            StringBuffer sb = new StringBuffer();
            while (true) {
                int znak = is.read();
                if (znak == -1) {
                    break;
                }
                sb.append((char) znak);
            }
            s.close();
            System.out.println("Primljeni  odgovor: " + sb.toString());

            porukaOPosluzitelju = sb.toString();
        } catch (IOException ex) {
            Logger.getLogger(KontrolaServera.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void dodajNoviIoTUredajUGrupu(){
        try {
            s = new Socket(KonfiguracijaHelper.getHost(), KonfiguracijaHelper.getPort());
            InputStream is = s.getInputStream();
            OutputStream os = s.getOutputStream();
            String zahtjev = "USER " + session.getAttribute("korisnickoIme") + "; PASSWD " + session.getAttribute("lozinka") + "; IoT "+getIdUredaja()+" ADD '"+getNazivUredaja()+"' '"+getAdresaUredaja()+"';";

            os.write(zahtjev.getBytes());
            os.flush();
            s.shutdownOutput();

            System.out.println("Poruka poslana");

            StringBuffer sb = new StringBuffer();
            while (true) {
                int znak = is.read();
                if (znak == -1) {
                    break;
                }
                sb.append((char) znak);
            }
            s.close();
            System.out.println("Primljeni  odgovor: " + sb.toString());

            porukaOKontroliUredaja = sb.toString();
        } catch (IOException ex) {
            Logger.getLogger(KontrolaServera.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void aktivirajIoTUredaj(){
        try {
            s = new Socket(KonfiguracijaHelper.getHost(), KonfiguracijaHelper.getPort());
            InputStream is = s.getInputStream();
            OutputStream os = s.getOutputStream();
            String zahtjev = "USER " + session.getAttribute("korisnickoIme") + "; PASSWD " + session.getAttribute("lozinka") + "; IoT "+getIdUredaja()+" WORK;";

            os.write(zahtjev.getBytes());
            os.flush();
            s.shutdownOutput();

            System.out.println("Poruka poslana");

            StringBuffer sb = new StringBuffer();
            while (true) {
                int znak = is.read();
                if (znak == -1) {
                    break;
                }
                sb.append((char) znak);
            }
            s.close();
            System.out.println("Primljeni  odgovor: " + sb.toString());

            porukaOKontroliUredaja = sb.toString();
        } catch (IOException ex) {
            Logger.getLogger(KontrolaServera.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void blokirajIoTUredaj(){
        try {
            s = new Socket(KonfiguracijaHelper.getHost(), KonfiguracijaHelper.getPort());
            InputStream is = s.getInputStream();
            OutputStream os = s.getOutputStream();
            String zahtjev = "USER " + session.getAttribute("korisnickoIme") + "; PASSWD " + session.getAttribute("lozinka") + "; IoT "+getIdUredaja()+" WAIT;";

            os.write(zahtjev.getBytes());
            os.flush();
            s.shutdownOutput();

            System.out.println("Poruka poslana");

            StringBuffer sb = new StringBuffer();
            while (true) {
                int znak = is.read();
                if (znak == -1) {
                    break;
                }
                sb.append((char) znak);
            }
            s.close();
            System.out.println("Primljeni  odgovor: " + sb.toString());

            porukaOKontroliUredaja = sb.toString();
        } catch (IOException ex) {
            Logger.getLogger(KontrolaServera.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void brisiIoTUredaj(){
        try {
            s = new Socket(KonfiguracijaHelper.getHost(), KonfiguracijaHelper.getPort());
            InputStream is = s.getInputStream();
            OutputStream os = s.getOutputStream();
            String zahtjev = "USER " + session.getAttribute("korisnickoIme") + "; PASSWD " + session.getAttribute("lozinka") + "; IoT "+getIdUredaja()+" REMOVE;";

            os.write(zahtjev.getBytes());
            os.flush();
            s.shutdownOutput();

            System.out.println("Poruka poslana");

            StringBuffer sb = new StringBuffer();
            while (true) {
                int znak = is.read();
                if (znak == -1) {
                    break;
                }
                sb.append((char) znak);
            }
            s.close();
            System.out.println("Primljeni  odgovor: " + sb.toString());

            porukaOKontroliUredaja = sb.toString();
        } catch (IOException ex) {
            Logger.getLogger(KontrolaServera.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void dohvatiStatusIoT(){
        try {
            s = new Socket(KonfiguracijaHelper.getHost(), KonfiguracijaHelper.getPort());
            InputStream is = s.getInputStream();
            OutputStream os = s.getOutputStream();
            String zahtjev = "USER " + session.getAttribute("korisnickoIme") + "; PASSWD " + session.getAttribute("lozinka") + "; IoT "+getIdUredaja()+" STATUS;";

            os.write(zahtjev.getBytes());
            os.flush();
            s.shutdownOutput();

            System.out.println("Poruka poslana");

            StringBuffer sb = new StringBuffer();
            while (true) {
                int znak = is.read();
                if (znak == -1) {
                    break;
                }
                sb.append((char) znak);
            }
            s.close();
            System.out.println("Primljeni  odgovor: " + sb.toString());

            porukaOKontroliUredaja = sb.toString();
        } catch (IOException ex) {
            Logger.getLogger(KontrolaServera.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    

}
