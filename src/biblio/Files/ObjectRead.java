/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblio.Files;

import biblio.Models.Estudiante;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 *
 * @author Gamcas
 */
public class ObjectRead {

    public static Estudiante readEstudiante(String estudiante) throws IOException {

        File file = new File("DB/estudiantes/" + estudiante + ".est");
        try (FileInputStream fileInputStream = new FileInputStream(file);
                ObjectInputStream inputStream = new ObjectInputStream(fileInputStream);) {
            return (Estudiante) inputStream.readObject();
        } catch (IOException e) {
            throw new IOException();
        } catch (ClassNotFoundException e) {
            System.out.println("El objeto no tiene la forma de un estudiante");
        }
        return null;
    }

}
