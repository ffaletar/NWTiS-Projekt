/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.ffaletar.helpers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Filip
 */
public class Regex {
    
    private final String sintaksaServer = "^USER ([^\\\\s]+); PASSWD ([^\\\\s]+); (PAUSE|START|STOP|STATUS);$";
    private final String sintaksaIoTMaster = "^USER ([^\\\\s]+); PASSWD ([^\\\\s]+); IoT_Master (START|STOP|WORK|WAIT|LOAD|CLEAR|STATUS|LIST);$";
    private final String sintaksaIoT = "^USER ([^\\\\s]+); PASSWD ([^\\\\s]+); IoT (ADD '([^\\\\s]+)' '([^\\\\s]+)'|WORK|WAIT|REMOVE|STATUS);$";

    private boolean server;
    private boolean IoT;
    private boolean IoTMaster;
    
    private static String naredba;
    
    private String korisnickoIme;
    private String lozinka;
    
    public Regex(String naredba) {
        this.naredba = naredba;
        Pattern patternServer = Pattern.compile(sintaksaServer);
        Pattern patternIoTMaster = Pattern.compile(sintaksaIoTMaster);
        Pattern patternIoT = Pattern.compile(sintaksaIoT);
        Matcher matcherServer = patternServer.matcher(naredba);
        Matcher matcherIoTMaster = patternIoTMaster.matcher(naredba);
        Matcher matcherIoT = patternIoT.matcher(naredba);
        boolean statusServer = matcherServer.matches();
        boolean statusIoTMaster = matcherIoTMaster.matches();
        boolean statusIoT = matcherIoT.matches();
        
        if(statusServer){
            korisnickoIme = matcherServer.group(1);
            lozinka = matcherServer.group(2);
            server = true;
            IoT = false;
            IoTMaster = false;
        }else if(statusIoTMaster){
            korisnickoIme = matcherIoTMaster.group(1);
            lozinka = matcherIoTMaster.group(2);
            server = false;
            IoT = false;
            IoTMaster = true;
        }else if(statusIoT){
            korisnickoIme = matcherIoT.group(1);
            lozinka = matcherIoT.group(2);
            server = false;
            IoT = true;
            IoTMaster = false;
        }
        
        System.out.println("Regex >> server " + server + " IoTMaster " + IoTMaster + " IoT " + IoT);
    }

    public boolean isServer() {
        return server;
    }

    public void setServer(boolean server) {
        this.server = server;
    }

    public boolean isIoT() {
        return IoT;
    }

    public void setIoT(boolean IoT) {
        this.IoT = IoT;
    }

    public boolean isIoTMaster() {
        return IoTMaster;
    }

    public void setIoTMaster(boolean IoTMaster) {
        this.IoTMaster = IoTMaster;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public String getLozinka() {
        return lozinka;
    }
    
}
