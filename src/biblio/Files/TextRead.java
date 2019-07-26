/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblio.Files;

import biblio.Models.Estudiante;
import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
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
    private List<String> commandList = new ArrayList<>();
    private List<Object> attribs;

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
            String complete = data;
            while (data != null) {
                data = buffer.readLine();
                complete += "\n"+data;
                esperar(0);
            }
            identificarComando(complete);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TextRead.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TextRead.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void identificarComando(String linea){
        commands=linea.split("\n");
        for (String command : commands) {
            commandList.add(command);
        }
        for (String command : commandList) {
            if(command.equalsIgnoreCase("estudiante")) {
                Estudiante estudiante = estudiante();
                System.out.println("");
                if(estudiante != null) {
                    //Agregar a un arreglo de estudiantes
                }
            }
        }
    }
    
    /*
    * @javadoc de ahuevo :v
    *
    * Analizador Sintactico Descendente.
    *
    * Cada metodo es una regla de la gramática.
    *
    * Puto el que lo lea, ya deberias saber esto.
    *
    * Siempre obtiene lo que esta al principio del arraylist de Strings,
    * si es valido lo que se obtiene, entonces elimina el index 0 del arraylist,
    * y continua con el siguiente estado (En este caso, metodo)
    *
    * PD: Esto es atributos heredados y los retorna en forma de metodo juas juas juas
    *
    * Va recolectando las cosas en un arreglo, cuando llega a la ultima regla, que
    * es la de la carrera, se crea el objeto 'Estudiante' y lo retorna hasta el metodo
    * que implemento el metodo estudiante().
    *
    * PD2: Al final se me ocurrio que pude haber hecho atributos sintetizados, pero ya
    * esta hecho asi que me pela jsjsjs
    *
    * PD3: HIJO DE PUTA COPIASTE TODA LA CLASE MALDITO, OJALA TE PONGAN 0 MIERDA
    *
    * 
    */
    
    // Produccion inicial
    
    public Estudiante estudiante() {
        attribs = new ArrayList<>();
        commandList.remove(0);
        return carnetEstudiante();
    }
    
    public Estudiante carnetEstudiante() {
        String command = commandList.get(0);
        String[] line = command.split(":");
        if(line[0].equals("CARNET")) {
            try {
                commandList.remove(0);
                int carnet = Integer.parseInt(line[1]);
                attribs.add(carnet);
                return nombreEstudiante();
            } catch (Exception ex){
                System.err.println(ex.getMessage());
                return null;
            }
        } 
        return null;
    }
    
    public Estudiante nombreEstudiante() {
        String command = commandList.get(0);
        String[] line = command.split(":");
        if(line[0].equalsIgnoreCase("nombre")) {
            try {
                commandList.remove(0);
                attribs.add(line[1]);
                return carreraEstudiante();
            } catch(Exception ex) {
                System.err.println(ex.getMessage());
                return null;
            }
        }
        return null;
    }
    
    public Estudiante carreraEstudiante() {
        String command = commandList.get(0);
        String[] line = command.split(":");
        if(line[0].equalsIgnoreCase("carrera")) {
            try {
                commandList.remove(0);
                int carrera = Integer.parseInt(line[1]);
                attribs.add(carrera);
                return crearEstudiante();
            } catch(Exception ex) {
                System.err.println(ex.getMessage());
                return null;
            }
        }
        return null;
    }
    
    public Estudiante crearEstudiante() {
        Estudiante estudiante = new Estudiante((int)attribs.get(0),(int)attribs.get(2),(String)attribs.get(1));
        commandList.remove(0);
        commandList.remove(0);
        return estudiante;
    }

}
