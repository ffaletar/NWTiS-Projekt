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
import org.foi.nwtis.ffaletar.soap.klijenti.StatusKorisnika;
import org.foi.nwtis.ffaletar.soap.klijenti.StatusUredjaja;

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
            PreuzmiMeteoPodatke.setPauzirana(false);
            return getOK10();
        } else {
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
        boolean grupaRegistrirana = IoT_Master.registrirajGrupuIoT(korisnickoIme, korisnickaLozinka);

        if (grupaRegistrirana) {
            return getOK10();
        } else {
            return getERR20();
        }
    }

    public String IoTMasterStop(String korisnickoIme, String korisnickaLozinka) {

        boolean grupaDeregistrirana = IoT_Master.deregistrirajGrupuIoT(korisnickoIme, korisnickaLozinka);
        if (grupaDeregistrirana) {
            return getOK10();
        } else {
            return getERR21();
        }
    }

    public String IoTMasterWork(String korisnickoIme, String korisnickaLozinka) {
        boolean grupaPokrenuta = IoT_Master.aktivirajGrupuIoT(korisnickoIme, korisnickaLozinka);
        if (grupaPokrenuta) {
            return getOK10();
        } else {
            return getERR22();
        }
    }

    public String IoTMasterWait(String korisnickoIme, String korisnickaLozinka) {
        boolean grupaBlokirana = IoT_Master.blokirajGrupuIoT(korisnickoIme, korisnickaLozinka);
        if (grupaBlokirana) {
            return getOK10();
        } else {
            return getERR23();
        }
    }

    public String IoTMasterLoad(String korisnickoIme, String korisnickaLozinka) {
        IoT_Master.ucitajSveUredjajeGrupe(korisnickoIme, korisnickaLozinka);
        return getOK10();
    }

    public String IoTMasterClear(String korisnickoIme, String korisnickaLozinka) {
        IoT_Master.obrisiSveUredjajeGrupe(korisnickoIme, korisnickaLozinka);
        return getOK10();
    }

    public String IoTMasterStatus(String korisnickoIme, String korisnickaLozinka) {
        StatusKorisnika status = IoT_Master.dajStatusGrupeIoT(korisnickoIme, korisnickaLozinka);
        if (status.AKTIVAN != null) {
            return getOK24();
        } else{
            return getOK25();
        }
    }

    public String IoTMasterList(String korisnickoIme, String korisnickaLozinka) {
        java.util.List<org.foi.nwtis.ffaletar.soap.klijenti.Uredjaj> uredaji = IoT_Master.dajSveUredjajeGrupe(korisnickoIme, korisnickaLozinka);
        return getOK10(uredaji);
    }
    
    
    public String IoTAdd(String korisnickoIme, String korisnickaLozinka, int uredaj, String naziv, String adresa) {
        boolean uredajDodan = IoT_Master.dodajNoviUredjajGrupi(korisnickoIme, korisnickaLozinka, uredaj, naziv, adresa);
        if (uredajDodan == true) {
            return getOK10();
        } else{
            return getERR30();
        }
    }
    
    public String IoTWork(String korisnickoIme, String korisnickaLozinka, int uredaj) {
        boolean uredajAktiviran = IoT_Master.aktivirajUredjajGrupe(korisnickoIme, korisnickaLozinka, uredaj);
        if (uredajAktiviran == true) {
            return getOK10();
        } else{
            return getERR31();
        }
    }
    
    
    public String IoTWait(String korisnickoIme, String korisnickaLozinka, int uredaj) {
        boolean uredajBlokiran = IoT_Master.blokirajUredjajGrupe(korisnickoIme, korisnickaLozinka, uredaj);
        if (uredajBlokiran == true) {
            return getOK10();
        } else{
            return getERR32();
        }
    }
    
    public String IoTRemove(String korisnickoIme, String korisnickaLozinka, int uredaj) {
        boolean uredajObrisan = IoT_Master.obrisiUredjajGrupe(korisnickoIme, korisnickaLozinka, uredaj);
        if (uredajObrisan == true) {
            return getOK10();
        } else{
            return getERR33();
        }
    }
    
    public String IoTStatus(String korisnickoIme, String korisnickaLozinka, int uredaj) {
        StatusUredjaja status = IoT_Master.dajStatusUredjajaGrupe(korisnickoIme, korisnickaLozinka, uredaj);
        if (status.AKTIVAN != null) {
            return getOK35();
        } else{
            return getOK34();
        }
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

    public static String getOK10(java.util.List<org.foi.nwtis.ffaletar.soap.klijenti.Uredjaj> IoTUredjaji) {
        String poruka = "";
        String ukupnaPoruka = "";
        String porukaDodatak = OK10;
        for (org.foi.nwtis.ffaletar.soap.klijenti.Uredjaj IoT : IoTUredjaji) {
            poruka = " {IoT " + IoT.getId() + " " + IoT.getNaziv() + "}";
            ukupnaPoruka =  ukupnaPoruka + poruka;
        }
        
        return porukaDodatak + " " + ukupnaPoruka;
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
