/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblio.Files;

import biblio.Models.Estudiante;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 *
 * @author Gamcas
 */
public class ObjectWrite {

    public static boolean saveEstudent(Estudiante estudiante) {

        File file = new File("DB/estudiantes/" + estudiante.getNombre() + ".est");

        try (FileOutputStream fileOutputStream = new FileOutputStream(file);
                ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);) {
                outputStream.writeObject(estudiante);
            } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error de conexion con el archivo");
        }
        return true;
    }
}
