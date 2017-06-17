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
@Named(value = "kontrolaServera")
@SessionScoped
public class KontrolaServera implements Serializable {

    private String porukaOServeru;
    private String porukaOIoTMaster;
    private static Socket s;

    private HttpSession session;
    private FacesContext context;

    /**
     * Creates a new instance of KontrolaServera
     */
    public KontrolaServera() {
        context = FacesContext.getCurrentInstance();
        session = (HttpSession) context.getExternalContext().getSession(false);
    }

    public String getPorukaOServeru() {
        return porukaOServeru;
    }

    public void setPorukaOServeru(String porukaOServeru) {
        this.porukaOServeru = porukaOServeru;
    }

    public String getPorukaOIoTMaster() {
        return porukaOIoTMaster;
    }

    public void setPorukaOIoTMaster(String porukaOIoTMaster) {
        this.porukaOIoTMaster = porukaOIoTMaster;
    }

    public void pokreniServer() {
        try {
            s = new Socket(KonfiguracijaHelper.getHost(), KonfiguracijaHelper.getPort());
            InputStream is = s.getInputStream();
            OutputStream os = s.getOutputStream();
            String zahtjev = "USER " + session.getAttribute("korisnickoIme") + "; PASSWD " + session.getAttribute("lozinka") + "; START;";

            os.write(zahtjev.getBytes());
            os.flush();
            s.shutdownOutput();

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

            porukaOServeru = sb.toString();
        } catch (IOException ex) {
            Logger.getLogger(KontrolaServera.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void zaustaviServer() {
        try {
            s = new Socket(KonfiguracijaHelper.getHost(), KonfiguracijaHelper.getPort());
            InputStream is = s.getInputStream();
            OutputStream os = s.getOutputStream();
            String zahtjev = "USER " + session.getAttribute("korisnickoIme") + "; PASSWD " + session.getAttribute("lozinka") + "; STOP;";

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

            porukaOServeru = sb.toString();
        } catch (IOException ex) {
            Logger.getLogger(KontrolaServera.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void pauzirajServer() {
        try {
            s = new Socket(KonfiguracijaHelper.getHost(), KonfiguracijaHelper.getPort());
            InputStream is = s.getInputStream();
            OutputStream os = s.getOutputStream();
            String zahtjev = "USER " + session.getAttribute("korisnickoIme") + "; PASSWD " + session.getAttribute("lozinka") + "; PAUSE;";

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

            porukaOServeru = sb.toString();
        } catch (IOException ex) {
            Logger.getLogger(KontrolaServera.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void dajStatusServera() {
        try {
            s = new Socket(KonfiguracijaHelper.getHost(), KonfiguracijaHelper.getPort());
            InputStream is = s.getInputStream();
            OutputStream os = s.getOutputStream();
            String zahtjev = "USER " + session.getAttribute("korisnickoIme") + "; PASSWD " + session.getAttribute("lozinka") + "; STATUS;";

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

            porukaOServeru = sb.toString();
        } catch (IOException ex) {
            Logger.getLogger(KontrolaServera.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void registrirajGrupu() {
        try {
            s = new Socket(KonfiguracijaHelper.getHost(), KonfiguracijaHelper.getPort());
            InputStream is = s.getInputStream();
            OutputStream os = s.getOutputStream();
            String zahtjev = "USER " + session.getAttribute("korisnickoIme") + "; PASSWD " + session.getAttribute("lozinka") + "; IoT_Master START;";

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

            porukaOIoTMaster = sb.toString();
        } catch (IOException ex) {
            Logger.getLogger(KontrolaServera.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void odjaviGrupu() {
        try {
            s = new Socket(KonfiguracijaHelper.getHost(), KonfiguracijaHelper.getPort());
            InputStream is = s.getInputStream();
            OutputStream os = s.getOutputStream();
            String zahtjev = "USER " + session.getAttribute("korisnickoIme") + "; PASSWD " + session.getAttribute("lozinka") + "; IoT_Master STOP;";

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

            porukaOIoTMaster = sb.toString();
        } catch (IOException ex) {
            Logger.getLogger(KontrolaServera.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void aktivirajGrupu() {
        try {
            s = new Socket(KonfiguracijaHelper.getHost(), KonfiguracijaHelper.getPort());
            InputStream is = s.getInputStream();
            OutputStream os = s.getOutputStream();
            String zahtjev = "USER " + session.getAttribute("korisnickoIme") + "; PASSWD " + session.getAttribute("lozinka") + "; IoT_Master WORK;";

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

            porukaOIoTMaster = sb.toString();
        } catch (IOException ex) {
            Logger.getLogger(KontrolaServera.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void blokirajGrupu() {
        try {
            s = new Socket(KonfiguracijaHelper.getHost(), KonfiguracijaHelper.getPort());
            InputStream is = s.getInputStream();
            OutputStream os = s.getOutputStream();
            String zahtjev = "USER " + session.getAttribute("korisnickoIme") + "; PASSWD " + session.getAttribute("lozinka") + "; IoT_Master WAIT;";

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

            porukaOIoTMaster = sb.toString();
        } catch (IOException ex) {
            Logger.getLogger(KontrolaServera.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void dohvatiStatusGrupe() {
        try {
            s = new Socket(KonfiguracijaHelper.getHost(), KonfiguracijaHelper.getPort());
            InputStream is = s.getInputStream();
            OutputStream os = s.getOutputStream();
            String zahtjev = "USER " + session.getAttribute("korisnickoIme") + "; PASSWD " + session.getAttribute("lozinka") + "; IoT_Master STATUS;";

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

            porukaOIoTMaster = sb.toString();
        } catch (IOException ex) {
            Logger.getLogger(KontrolaServera.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
