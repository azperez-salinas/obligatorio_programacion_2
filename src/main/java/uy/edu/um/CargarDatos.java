package uy.edu.um;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.opencsv.CSVReader;
import lombok.Data;
import uy.edu.um.tad.linkedlist.MyLinkedListImpl;

@Data
public class CargarDatos {
    GestionUM gestion = new GestionUM();

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
