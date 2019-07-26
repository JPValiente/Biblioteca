/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblio;

import biblio.Files.TextRead;
import java.io.File;

/**
 *
 * @author Gamcas
 */
public class Biblio {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        initComponents();
        new TextRead("F:\\Gamcas\\Documents\\NetBeansProjects\\Biblio\\j.txt").run();
    }
    
    
    public static void initComponents() {

        File estudiante = new File("DB\\estudiantes");
        File prestamo = new File("DB\\prestamos");
        File libro = new File("DB\\libros");

        if (!estudiante.exists()) {
            estudiante.mkdir();
         
        } else {
  
        }
        if (!prestamo.exists()) {
            prestamo.mkdir();
        }
        if (!libro.exists()) {
            libro.mkdir();
        }
     
       
    }
    
}
