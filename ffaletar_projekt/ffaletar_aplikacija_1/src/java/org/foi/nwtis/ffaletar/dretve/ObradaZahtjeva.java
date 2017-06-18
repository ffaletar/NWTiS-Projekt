/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.ffaletar.dretve;

import com.mysql.jdbc.StringUtils;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SocketHandler;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.jms.Session;
import org.foi.nwtis.ffaletar.baza.KorisnikBaza;
import org.foi.nwtis.ffaletar.helpers.KonfiguracijaHelper;
import org.foi.nwtis.ffaletar.helpers.Obrada;
import org.foi.nwtis.ffaletar.helpers.Regex;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import org.foi.nwtis.ffaletar.baza.DnevnikBaza;
import org.foi.nwtis.ffaletar.helpers.Helper;
import org.foi.nwtis.ffaletar.konfiguracije.Konfiguracija;

/**
 *
 * @author Filip
 */
public class ObradaZahtjeva extends Thread {

    private static Socket ss;

    public ObradaZahtjeva(Socket ss) {
        this.ss = ss;
    }

    @Override
    public void interrupt() {
        super.interrupt(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void run() {
        super.run(); //To change body of generated methods, choose Tools | Templates.
        String poruka = "";
        String current = System.getProperty("user.dir");
        Date pocetakObrade = new Date();
        System.out.println("Current working directory in Java : " + current);
        try {
            System.err.println("Moram pročitati komandu");
            InputStream is = ss.getInputStream();
            OutputStream os = ss.getOutputStream();

            StringBuffer sb = new StringBuffer();

            String ipAdresaKlijenta = ss.getRemoteSocketAddress().toString();
            
            System.out.println(">> Ip adresa klijenta " + ipAdresaKlijenta);
            
            while (true) {
                int znak = is.read();
                if (znak == -1) {
                    break;
                }
                sb.append((char) znak);
            }

            System.out.println("Primljena komanda: " + sb);
            
            
            
            
            String naredba = sb.toString();

            Regex regex = new Regex(sb.toString());
            
            System.out.println("Id uredaja" + regex.getIdUredaja());
            System.out.println("Naziv uredaja" + regex.getNazivUredaja());
            System.out.println("Adresa uredaja" + regex.getAdresaUredaja());
            boolean korisnikPostoji = KorisnikBaza.provjeriKorisnika(regex.getKorisnickoIme(), regex.getLozinka());
            if (regex.isServer()) {
                System.out.println("Usao u server regex");
                if (korisnikPostoji) {
                    Obrada obrada = new Obrada();
                    if (naredba.contains("PAUSE")) {
                        poruka = obrada.serverPause();
                    } else if (naredba.contains("START")) {
                        System.out.println("Usao sam u regex start");
                        poruka = obrada.serverStart();
                    } else if (naredba.contains("STOP")) {
                        poruka = obrada.serverStop();
                    } else if (naredba.contains("STATUS")) {
                        poruka = obrada.serverStatus();
                    }
                } else {
                    poruka = Obrada.getERR10();
                }
                if (poruka.contains("OK")) {
                    System.out.println("Ušao u if za slanje maila");
//                    posaljiMail(naredba + "\n" + Helper.dohvatiTrenutniTimeStamp(false, true));

                } else {
                    System.out.println("Nisam ušao u if za slanje maila");
                }
            } else if (regex.isIoTMaster()) {
                System.out.println("Usao u IoTMaster regex");
                if (korisnikPostoji) {
                    Obrada obrada = new Obrada();
                    if (naredba.contains("START")) {
                        poruka = obrada.IoTMasterStart(KonfiguracijaHelper.getIoTMasterKorisnik(), KonfiguracijaHelper.getIoTMasterLozinka());
                    } else if (naredba.contains("STOP")) {
                        System.out.println("Usao sam u regex start");
                        poruka = obrada.IoTMasterStop(KonfiguracijaHelper.getIoTMasterKorisnik(), KonfiguracijaHelper.getIoTMasterLozinka());
                    } else if (naredba.contains("WORK")) {
                        poruka = obrada.IoTMasterWork(KonfiguracijaHelper.getIoTMasterKorisnik(), KonfiguracijaHelper.getIoTMasterLozinka());
                    } else if (naredba.contains("WAIT")) {
                        poruka = obrada.IoTMasterWait(KonfiguracijaHelper.getIoTMasterKorisnik(), KonfiguracijaHelper.getIoTMasterLozinka());
                    } else if (naredba.contains("LOAD")) {
                        poruka = obrada.IoTMasterLoad(KonfiguracijaHelper.getIoTMasterKorisnik(), KonfiguracijaHelper.getIoTMasterLozinka());
                    } else if (naredba.contains("CLEAR")) {
                        poruka = obrada.IoTMasterClear(KonfiguracijaHelper.getIoTMasterKorisnik(), KonfiguracijaHelper.getIoTMasterLozinka());
                    } else if (naredba.contains("STATUS")) {
                        poruka = obrada.IoTMasterStatus(KonfiguracijaHelper.getIoTMasterKorisnik(), KonfiguracijaHelper.getIoTMasterLozinka());
                    } else if (naredba.contains("LIST")) {
                        poruka = obrada.IoTMasterList(KonfiguracijaHelper.getIoTMasterKorisnik(), KonfiguracijaHelper.getIoTMasterLozinka());
                    }
                } else {
                    poruka = Obrada.getERR10();
                }

            } else if (regex.isIoT()) {
                System.out.println("Usao u IoT regex");
                if (korisnikPostoji) {
                    Obrada obrada = new Obrada();
                    if (naredba.contains("ADD")) {
                        
                        poruka = obrada.IoTAdd(KonfiguracijaHelper.getIoTMasterKorisnik(), KonfiguracijaHelper.getIoTMasterLozinka(), Integer.parseInt(regex.getIdUredaja()), regex.getNazivUredaja(), regex.getAdresaUredaja());
                    } else if(naredba.contains("WORK")){
                        poruka = obrada.IoTWork(KonfiguracijaHelper.getIoTMasterKorisnik(), KonfiguracijaHelper.getIoTMasterLozinka(), Integer.parseInt(regex.getIdUredaja()));
                    }else if(naredba.contains("WAIT")){
                        poruka = obrada.IoTWait(KonfiguracijaHelper.getIoTMasterKorisnik(), KonfiguracijaHelper.getIoTMasterLozinka(), Integer.parseInt(regex.getIdUredaja()));
                    }else if(naredba.contains("REMOVE")){
                        poruka = obrada.IoTRemove(KonfiguracijaHelper.getIoTMasterKorisnik(), KonfiguracijaHelper.getIoTMasterLozinka(), Integer.parseInt(regex.getIdUredaja()));
                    }else if(naredba.contains("STATUS")){
                        poruka = obrada.IoTStatus(KonfiguracijaHelper.getIoTMasterKorisnik(), KonfiguracijaHelper.getIoTMasterLozinka(), Integer.parseInt(regex.getIdUredaja()));
                    }
                } else {
                    poruka = Obrada.getERR10();
                }

            } else {
                System.out.println("Nisam uspio ući u if regex");
            }

            Date krajObrade = new Date();
            
            int trajanjeObrade = (int) (krajObrade.getTime() - pocetakObrade.getTime());

            DnevnikBaza.upisiUDnevnik(regex.getKorisnickoIme(), "/ObradaZahtjeva", ipAdresaKlijenta, trajanjeObrade);
            
            os.write(poruka.getBytes());
            os.flush();
            ss.shutdownOutput();
            
            

            System.out.println(">>" + poruka);

            
        } catch (IOException ex) {
            Logger.getLogger(ObradaZahtjeva.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public synchronized void start() {
        super.start(); //To change body of generated methods, choose Tools | Templates.
    }

    public static void posaljiMail(String poruka) {

        System.out.println("Zapocinjem slanje maila");
        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", KonfiguracijaHelper.getHost());

        javax.mail.Session session = javax.mail.Session.getInstance(properties);

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(KonfiguracijaHelper.getMailAdresaPosiljatelja()));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(KonfiguracijaHelper.getMailAdresaPrimatelja()));
            message.setSubject(KonfiguracijaHelper.getMailPredmet());
            message.setText(poruka);

            Transport.send(message);

            System.out.println("Mail poslan");
        } catch (MessagingException mex) {
            System.out.println("Greška kod slanja mail");
            mex.printStackTrace();
        }
    }

}
