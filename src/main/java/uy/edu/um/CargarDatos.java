package uy.edu.um;

import uy.edu.um.tad.linkedlist.MyLinkedListImpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CargarDatos {
    GestionUM gestion= new GestionUM();
    private void cargarDatosPelis(){

        try {
            File file= new File("movies_metadatacsv.csv");
            Scanner reader= new Scanner(file);
            reader.nextLine();
            while(reader.hasNextLine()){
                String line = reader.nextLine();
                String[] splitedLine= line.split(",");
                if(splitedLine.length!=19){
                    continue;
                }

                try{
                    int idPeli = Integer.parseInt(splitedLine[6]);
                    String titulo = splitedLine[9];
                    String idiomaOriginal = splitedLine[8];

                    MyLinkedListImpl<String> generos = new MyLinkedListImpl<>();
                    String generosString = splitedLine[4];

                    // Eliminamos los corchetes exteriores
                    generosString = generosString.substring(1, generosString.length() - 1);

                    // Sacamos el último '}' (siempre está al final)
                    if (generosString.endsWith("}")) {
                        generosString = generosString.substring(0, generosString.length() - 1);
                    }

                    // Ahora hacemos el split solo por "},"
                    String[] generosArray = generosString.split("\\},");

                    for (String generoItem : generosArray) {
                        // Limpiamos espacios y el { que queda al principio
                        generoItem = generoItem.trim();
                        if (generoItem.startsWith("{")) {
                            generoItem = generoItem.substring(1); // eliminamos el primer caracter '{'
                        }

                        String[] partes = generoItem.split(",");
                        for (String parte : partes) {
                            parte = parte.trim(); //Limpiamos espacios
                            if (parte.startsWith("'name':")) {
                                String nombreGenero = parte.split(":")[1].trim();
                                nombreGenero = nombreGenero.replace("'", "").trim();
                                generos.add(nombreGenero);
                            }
                        }
                    }

                    gestion.getPeliculas().put(idPeli,(new Pelicula(idPeli, titulo, idiomaOriginal, generos)));

                } catch (NumberFormatException e) {
                    System.out.println("Error de parceo");
                    continue;
                }

            }


        }catch (FileNotFoundException ex){
            System.out.println("Error de parceo");

        }


    }
}
