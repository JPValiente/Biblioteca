/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblio.Files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gamcas
 */
public class TextRead extends Thread{

    private static long tiempoReferencia;
    private static long tiempoReferenciaOriginal;
    private String absolutePath;
    private String[] commands;

    @Override
    public void run() {
        readText(absolutePath);
    }

    public TextRead(String absolutePath) {
        this.absolutePath = absolutePath;
        tiempoReferencia = System.currentTimeMillis();
        tiempoReferenciaOriginal = System.currentTimeMillis();
    }

    private static void esperar(int segundos) {
        try {
            Thread.sleep(segundos * 500);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    public void readText(String path) {

        try {
            FileReader lector = null;
            BufferedReader buffer = null;

            lector = new FileReader(new File(path));

            buffer = new BufferedReader(lector);
            String data = buffer.readLine();
            while (data != null) {
                identificarComando(data);
                esperar(0);
                
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TextRead.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TextRead.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void identificarComando(String linea){
        commands=linea.split(":");
        
        if (linea.contains("ESTUDIANTE")) {
            System.out.println(commands[0]);
        }
    }

}
