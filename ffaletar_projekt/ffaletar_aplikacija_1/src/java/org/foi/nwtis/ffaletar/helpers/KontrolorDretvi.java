/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.ffaletar.helpers;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.foi.nwtis.ffaletar.slusaci.SlusacAplikacije;

/**
 *
 * @author Filip
 */
public class KontrolorDretvi {
    
    private static ExecutorService executor;
    private static KontrolorDretvi instance;
    
    private KontrolorDretvi() {
        executor = Executors.newFixedThreadPool(10);
    }
    
    public static KontrolorDretvi getInstance() {
        if (instance == null) 
        {
            instance = new KontrolorDretvi();
        }
        return instance;
    }
    
    public void submit(Thread thread) {
        executor.submit(thread);
    }
    
    public void shutDown() {
        try 
        {
            executor.shutdown();
            
            if (executor.awaitTermination(10, TimeUnit.SECONDS))
            {
                System.out.println("Tasks completed");
            }
            else
            {
                System.out.println("Forcing shutdown...");
                executor.shutdownNow();
            }
        } 
        catch (InterruptedException ex) {
            Logger.getLogger(SlusacAplikacije.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
