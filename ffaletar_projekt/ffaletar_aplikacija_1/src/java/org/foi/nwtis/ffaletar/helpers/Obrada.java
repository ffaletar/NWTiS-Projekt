/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.ffaletar.helpers;

import java.util.List;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.foi.nwtis.ffaletar.dretve.PreuzmiMeteoPodatke;
import org.foi.nwtis.ffaletar.podaci.Uredjaj;
import org.foi.nwtis.ffaletar.soap.klijenti.IoT_Master;

/**
 *
 * @author Filip
 */
public class Obrada {

    private static String ERR10 = "ERR 10;";
    private static String ERR11 = "ERR 11;";
    private static String ERR12 = "ERR 12;";
    private static String ERR20 = "ERR 20;";
    private static String ERR21 = "ERR 21;";
    private static String ERR22 = "ERR 22;";
    private static String ERR23 = "ERR 23;";
    private static String ERR30 = "ERR 30;";
    private static String ERR31 = "ERR 31;";
    private static String ERR32 = "ERR 32;";
    private static String ERR33 = "ERR 33;";
    private static String OK10 = "OK 10;";
    private static String OK13 = "OK 13;";
    private static String OK14 = "OK 14;";
    private static String OK15 = "OK 15;";
    private static String OK24 = "OK 24;";
    private static String OK25 = "OK 25;";
    private static String OK34 = "OK 34;";
    private static String OK35 = "OK 35;";

    public Obrada() {
    }

    public String serverPause() {
        if (PreuzmiMeteoPodatke.isPauzirana()) {
            System.out.println("Server nije pauziran ERR10");
            return getERR10();
        } else {
            PreuzmiMeteoPodatke.setPauzirana(true);
            System.out.println("Server pauziran");
            return getOK10();
        }
    }

    public String serverStart() {
        if (PreuzmiMeteoPodatke.isPauzirana()) {
            System.out.println("Server je uspješno pokrenut");
            return getOK10();
        } else {
            PreuzmiMeteoPodatke.setPauzirana(false);
            System.out.println("Server nije uspješno pokrenut - ERR11");
            return getERR11();
        }
    }

    public String serverStop() {
        if (PreuzmiMeteoPodatke.isZaustavljena()) {
            System.out.println("Server nije moguće zaustaviti jer je već zaustavljen");
            return getERR12();
        } else {
            PreuzmiMeteoPodatke.setZaustavljena(true);
            System.out.println("Server zaustavljen");
            return getOK10();
        }
    }

    public String serverStatus() {
        int status = PreuzmiMeteoPodatke.dohvatiStatusDretve();
        System.out.println("Dohvaćamo status dretve");
        switch (status) {
            case 13:
                return getOK13();
            case 14:
                return getOK14();
            case 15:
                return getOK15();
            default:
                return getOK13();
        }
    }

    public String IoTMasterStart(String korisnickoIme, String korisnickaLozinka) {
        //registrirajGrupuIoT
        
        boolean grupaRegistrirana = IoT_Master.registrirajGrupuIoT(korisnickoIme, korisnickaLozinka);
        
        return null;
    }

    public String IoTMasterStop() {
        //deregistrirajGrupuIoT
        return null;
    }

    public String IoTMasterWork() {
        //aktivirajGrupuIoT
        return null;
    }

    public String IoTMasterWait() {
        //blokirajGrupuIoT
        return null;
    }

    public String IoTMasterLoad() {
        //ucitajSveUredjajeGrupe
        return null;
    }

    public String IoTMasterClear() {
        
        //obrisiSveUredjajeGrupe
        return null;
    }

    public String IoTMasterStatus() {
        //dajStatusGrupeIoT
        return null;
    }

    public String IoTMasterList() {
        
        //dajSveUredjajeGrupe
        return null;
    }

    public static String getERR10() {
        return ERR10;
    }

    public static void setERR10(String ERR10) {
        Obrada.ERR10 = ERR10;
    }

    public static String getERR11() {
        return ERR11;
    }

    public static void setERR11(String ERR11) {
        Obrada.ERR11 = ERR11;
    }

    public static String getERR12() {
        return ERR12;
    }

    public static void setERR12(String ERR12) {
        Obrada.ERR12 = ERR12;
    }

    public static String getERR20() {
        return ERR20;
    }

    public static void setERR20(String ERR20) {
        Obrada.ERR20 = ERR20;
    }

    public static String getERR21() {
        return ERR21;
    }

    public static void setERR21(String ERR21) {
        Obrada.ERR21 = ERR21;
    }

    public static String getERR22() {
        return ERR22;
    }

    public static void setERR22(String ERR22) {
        Obrada.ERR22 = ERR22;
    }

    public static String getERR23() {
        return ERR23;
    }

    public static void setERR23(String ERR23) {
        Obrada.ERR23 = ERR23;
    }

    public static String getERR30() {
        return ERR30;
    }

    public static void setERR30(String ERR30) {
        Obrada.ERR30 = ERR30;
    }

    public static String getERR31() {
        return ERR31;
    }

    public static void setERR31(String ERR31) {
        Obrada.ERR31 = ERR31;
    }

    public static String getERR32() {
        return ERR32;
    }

    public static void setERR32(String ERR32) {
        Obrada.ERR32 = ERR32;
    }

    public static String getERR33() {
        return ERR33;
    }

    public static void setERR33(String ERR33) {
        Obrada.ERR33 = ERR33;
    }

    public static String getOK10() {
        return OK10;
    }

    public static String getOK10(List<Uredjaj> IoTUredjaji) {
        String poruka = "";
        for (Uredjaj IoT : IoTUredjaji) {
            poruka = OK10 + " IoT " + IoT.getId() + " " + IoT.getNaziv();
        }
        return poruka;
    }

    public static void setOK10(String OK10) {
        Obrada.OK10 = OK10;
    }

    public static String getOK13() {
        return OK13;
    }

    public static void setOK13(String OK13) {
        Obrada.OK13 = OK13;
    }

    public static String getOK14() {
        return OK14;
    }

    public static void setOK14(String OK14) {
        Obrada.OK14 = OK14;
    }

    public static String getOK15() {
        return OK15;
    }

    public static void setOK15(String OK15) {
        Obrada.OK15 = OK15;
    }

    public static String getOK24() {
        return OK24;
    }

    public static void setOK24(String OK24) {
        Obrada.OK24 = OK24;
    }

    public static String getOK25() {
        return OK25;
    }

    public static void setOK25(String OK25) {
        Obrada.OK25 = OK25;
    }

    public static String getOK34() {
        return OK34;
    }

    public static void setOK34(String OK34) {
        Obrada.OK34 = OK34;
    }

    public static String getOK35() {
        return OK35;
    }

    public static void setOK35(String OK35) {
        Obrada.OK35 = OK35;
    }



}
