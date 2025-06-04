package uy.edu.um;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.opencsv.CSVReader;
import lombok.Data;
import uy.edu.um.tad.linkedlist.MyLinkedListImpl;

@Data
public class CargarDatos {
    GestionUM gestion = new GestionUM();
    public void cargarDatosCalificaciones() {
        try {
            File file = new File("ratings_1mm.csv");
            Scanner scanner = new Scanner(file);
            scanner.nextLine();

            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                String[] partes = linea.split(",");

                if (partes.length != 4) {
                    System.out.println("Línea inválida");
                    continue;
                }

                try {
                    int userId = Integer.parseInt(partes[0].trim());
                    int movieId = Integer.parseInt(partes[1].trim());
                    double rating = Double.parseDouble(partes[2].trim());
                    long timestampSeconds = Long.parseLong(partes[3].trim());
                    Timestamp timestamp = new Timestamp(timestampSeconds * 1000);


                    if (!gestion.getPeliculas().contains(movieId)) {
                        continue;
                    }

                    Calificacion calificacion = new Calificacion(userId, movieId, rating, timestamp);
                    gestion.getPeliculas().get(movieId).getCalificaciones().add(calificacion);

                } catch (NumberFormatException e) {
                    System.out.println("Error de parseo en la línea: " + linea);
                }
            }

            scanner.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void cargarDatosPelis() {
        try (CSVReader reader = new CSVReader(new FileReader("movies_metadata.csv"))) {
            String[] linea;
            reader.readNext(); // saltar cabecera
            while ((linea = reader.readNext()) != null) {
                String generosRaw = linea[3];
                List<String> generos = ParserGeneros.extraerNombresGeneros(generosRaw);
                String idString = linea[5];
                int id=0;
                try {
                    id = Integer.parseInt(idString);
                } catch (NumberFormatException e) {
                    System.out.println("ID inválido: " + idString);
                    continue;  // Salteo la película si el id no es válido
                }
                String titulo= linea[8];
                titulo = titulo.replaceAll("^[\"']|[\"']$", "");
                String idioma= linea[7];
                MyLinkedListImpl<String> generosPelicula= new MyLinkedListImpl<>();
                for(String genero: generos){
                    generosPelicula.add(genero);
                }
                Pelicula pelicula= new Pelicula(id,titulo,idioma,generosPelicula);
                gestion.getPeliculas().put(id,pelicula);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static class ParserGeneros {

        public static List<String> extraerNombresGeneros(String data) {
            // Este es el regex corregido: busca 'name': 'valor'
            Pattern pattern = Pattern.compile("'name':\\s*'([^']*)'");
            Matcher matcher = pattern.matcher(data);

            List<String> nombres = new ArrayList<>();

            while (matcher.find()) {
                nombres.add(matcher.group(1));
            }

            return nombres;
        }
    }
}
