/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.ffaletar.dretve;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.foi.nwtis.ffaletar.helpers.KonfiguracijaHelper;
import org.foi.nwtis.ffaletar.slusaci.SlusacAplikacije;

/**
 *
 * @author Filip
 */
public class SlanjeZahtjeva extends Thread {

    @Override
    public void interrupt() {
        super.interrupt(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void run() {
        super.run(); //To change body of generated methods, choose Tools | Templates.

        try {
            System.err.println("Ušao sam u dretvu ta slanje zahtjeva");

            Thread.sleep(10000);
//            while (true) {
            Socket socket = null;

            socket = new Socket(KonfiguracijaHelper.getHost(), KonfiguracijaHelper.getPort());

            String myString = "USER ffaletar; PASSWD ffaletar; START;";
            OutputStream out = socket.getOutputStream();
            InputStream in = socket.getInputStream();

            out.write(myString.getBytes());
            out.flush();
            socket.shutdownOutput();

            System.out.println("Naredba poslana");

            StringBuffer sb = new StringBuffer();

            if (sb.length() != 0) {
                while (true) {
                    int znak = in.read();
                    if (znak == -1) {
                        break;
                    }
                    sb.append((char) znak);
                }
            }

            socket.close();
            System.out.println("Primljeni  odgovor: " + sb);

        } catch (InterruptedException | IOException ex) {
            Logger.getLogger(SlanjeZahtjeva.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public synchronized void start() {
        super.start(); //To change body of generated methods, choose Tools | Templates.
    }

}
