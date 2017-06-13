/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.ffaletar.helpers;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Filip
 */
public class Helper {
    
    public static String dohvatiTrenutniTimeStamp(boolean saMilisekundama, boolean specijalniFormat) {
        SimpleDateFormat sdfDate;
        if (specijalniFormat) {
            sdfDate = new SimpleDateFormat("dd.MM.yyyy hh.mm.ss.zzz");
        } else {
            if (saMilisekundama) {
                sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
            } else {
                sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            }
        }

        Date now = new Date();
        String strDate = sdfDate.format(now);

        return strDate;
    }
    
}
