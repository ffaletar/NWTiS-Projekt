/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.ffaletar.dretve;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.foi.nwtis.ffaletar.helpers.KonfiguracijaHelper;

/**
 *
 * @author Filip
 */
public class Server extends Thread {

    private static ServerSocket ss;

    @Override
    public void interrupt() {
        super.interrupt(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void run() {
        super.run(); //To change body of generated methods, choose Tools | Templates.

        try {
            System.out.println("Server radi");
            ss = new ServerSocket(KonfiguracijaHelper.getPort());
            while(true){
                Socket socket = ss.accept();
                ObradaZahtjeva obradaZahtjeva = new ObradaZahtjeva(socket);
                obradaZahtjeva.start();
            }
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public synchronized void start() {
        super.start(); //To change body of generated methods, choose Tools | Templates.
    }
}
