/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.ffaletar.slusaci;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.foi.nwtis.ffaletar.baza.MeteoBaza;
import org.foi.nwtis.ffaletar.dretve.PreuzmiMeteoPodatke;
import org.foi.nwtis.ffaletar.dretve.Server;
import org.foi.nwtis.ffaletar.dretve.SlanjeZahtjeva;
import org.foi.nwtis.ffaletar.konfiguracije.Konfiguracija;
import org.foi.nwtis.ffaletar.konfiguracije.KonfiguracijaApstraktna;
import org.foi.nwtis.ffaletar.konfiguracije.NeispravnaKonfiguracija;
import org.foi.nwtis.ffaletar.konfiguracije.NemaKonfiguracije;
import org.foi.nwtis.ffaletar.konfiguracije.bp.BP_Konfiguracija;
import org.foi.nwtis.ffaletar.helpers.KonfiguracijaHelper;
import org.foi.nwtis.ffaletar.podaci.MeteoPodaci;

/**
 * Web application lifecycle listener.
 *
 * @author Filip
 */
public class SlusacAplikacije implements ServletContextListener {

    public static BP_Konfiguracija bp_konf;
    ServletContext sc = null;
    public static Konfiguracija konfiguracija;

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        sc = sce.getServletContext();
        String datoteka = sce.getServletContext().getRealPath("/WEB-INF")
                + File.separator + sce.getServletContext().getInitParameter("konfiguracija");

        try {
            konfiguracija = KonfiguracijaApstraktna.preuzmiKonfiguraciju(datoteka);
            KonfiguracijaHelper konfiguracijaHelper = new KonfiguracijaHelper(konfiguracija);
        } catch (NemaKonfiguracije | NeispravnaKonfiguracija ex) {
            Logger.getLogger(SlusacAplikacije.class.getName()).log(Level.SEVERE, null, ex);
        }

        PreuzmiMeteoPodatke preuzmiMeteoPodatke = new PreuzmiMeteoPodatke();
        preuzmiMeteoPodatke.start();
        Server server = new Server();
        server.start();

        SlanjeZahtjeva slanjeZahtjeva = new SlanjeZahtjeva();
        slanjeZahtjeva.start();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
