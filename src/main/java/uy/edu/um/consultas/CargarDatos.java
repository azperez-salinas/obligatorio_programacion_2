package uy.edu.um.consultas;

import java.io.*;
import java.sql.Timestamp;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONObject;
import com.opencsv.CSVReader;
import lombok.Data;
import uy.edu.um.entities.*;
import uy.edu.um.tad.linkedlist.MyLinkedListImpl;

@Data
public class CargarDatos {
    GestionUM gestion = new GestionUM();


    public void cargarDatosCreditos() {
        try {
            File file = new File("credits.csv"); //
            Scanner reader = new Scanner(file);
            reader.nextLine(); //

            while (reader.hasNextLine()) {
                String linea = reader.nextLine();

                try {
                    int castStart = linea.indexOf("[");
                    int crewStart = linea.indexOf("[", castStart + 1);
                    int crewEnd = linea.lastIndexOf("]");

                    String jsonCast = linea.substring(castStart, crewStart).trim();
                    String jsonCrew = linea.substring(crewStart, crewEnd + 1).trim();
                    String movieId = linea.substring(crewEnd + 1).replaceAll("[^0-9]", "");

                    JSONArray castArray = new JSONArray(jsonCast);
                    JSONArray crewArray = new JSONArray(jsonCrew);

                    for (int i = 0; i < castArray.length(); i++) {
                        JSONObject actorObj = castArray.getJSONObject(i);
                        String id = actorObj.get("id").toString();
                        String nombre = actorObj.getString("name");
                        Actor actor = new Actor(id, nombre);
                        if(!gestion.getActores().contains(id)){
                            gestion.getActores().put(id,actor);
                        }
                        gestion.getPeliculas().get(movieId).getIdActores().add(id);
                    }
                    for (int i = 0; i < crewArray.length(); i++) {
                        JSONObject crewObj = crewArray.getJSONObject(i);
                        if ("Director".equalsIgnoreCase(crewObj.optString("job"))) {
                            String id = crewObj.get("id").toString();
                            String nombre = crewObj.getString("name");
                            if(!gestion.getDirectores().contains(id)){
                                Director director = new Director(id, nombre);
                                director.addPelicula(gestion.getPeliculas().get(movieId));
                                gestion.getDirectores().put(id,director);
                            }else{gestion.getDirectores().get(id).addPelicula(gestion.getPeliculas().get(movieId)); }
                        }
                    }
                } catch (Exception e) {
//                    System.out.println("Error procesando línea: " + e.getMessage());
                }
            }
            System.out.println("Se cargo creditos");
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void cargarDatosCalificaciones() {
        try {

            File file = new File("ratings_1mm.csv");
            Scanner reader = new Scanner(file);
            reader.nextLine();

            while (reader.hasNextLine()) {

                String linea = reader.nextLine();
                String[] partes = linea.split(",");

                if (partes.length != 4) {
                    System.out.println("Línea inválida");
                    continue;
                }

                try {
                    String userId = (partes[0].trim());
                    String movieId = (partes[1].trim());
                    double rating = Double.parseDouble(partes[2].trim());
                    long timestampSeconds = Long.parseLong(partes[3].trim());
                    Timestamp timestamp = new Timestamp(timestampSeconds * 1000);


                    if (!gestion.getPeliculas().contains(movieId)) {
                        continue;
                    }
                    Usuario usuario= new Usuario(userId);
                    Calificacion calificacion = new Calificacion(userId, movieId, rating, timestamp);
                    gestion.getPeliculas().get(movieId).getCalificaciones().add(calificacion);
                    Pelicula pelicula =gestion.getPeliculas().get(movieId);


                    if(!gestion.getUsuarios().contains(userId)){
                        gestion.getUsuarios().put(userId,usuario);
                    }
                    //gestion.getUsuarios().add(usuario);

                } catch (NumberFormatException e) {
//                    System.out.println("Error de parseo en la línea: " + linea);
                }
            }
            System.out.println("Se cargo calificaciones");
            reader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void cargarDatosPelis() {
        try (CSVReader reader = new CSVReader(new FileReader("movies_metadata.csv"))) {
            String[] linea;
            reader.readNext();

            while ((linea = reader.readNext()) != null) {
                if(linea.length<19){
                    continue;
                }

                String generosRaw = linea[3];
                MyLinkedListImpl generos = extraerNombresGeneros(generosRaw);
                String idString = linea[5];
                String titulo= linea[18];
                String idioma= linea[7];
                String sagaRaw= linea[1];
                String recaudacionString= linea[13];
                long recaudacion=0;

                try {
                    recaudacion = Long.parseLong(recaudacionString);
                } catch (NumberFormatException e) {
                    //System.out.println("No se pudo parsear la recaudación: " + recaudacionString);

                }
                titulo = titulo.replaceAll("^[\"']|[\"']$", "");

                Pelicula pelicula= new Pelicula(idString,titulo,idioma,generos,recaudacion);
                gestion.getPeliculas().put(idString,pelicula);

                if (sagaRaw != null && !sagaRaw.trim().isEmpty() && !sagaRaw.trim().equals("null")) {
                    Pattern idPattern = Pattern.compile("'id'\\s*:\\s*(\\d+)");
                    Pattern namePattern = Pattern.compile("'name'\\s*:\\s*'(.*?)'");
                    Matcher idMatcher = idPattern.matcher(sagaRaw);
                    Matcher nameMatcher = namePattern.matcher(sagaRaw);

                    if(idMatcher.find() && nameMatcher.find()){
                        String idSaga= idMatcher.group(1);
                        String nombreSaga= nameMatcher.group(1);

                        if(!gestion.getSagas().contains(idSaga)){
                            Saga nuevaSaga= new Saga(idSaga,nombreSaga);
                            nuevaSaga.getPelicuas().add(pelicula);
                            gestion.getSagas().put(idSaga,nuevaSaga);
                        } else {
                            Saga sagaYaExiste = gestion.getSagas().get(idSaga);
                            sagaYaExiste.getPelicuas().add(pelicula);

                        }
                    }
                }else {
                    String idPeliculSinSaga= "sin_saga_"+idString;

                    if(gestion.getSagas().contains(idPeliculSinSaga)) continue;
                    Saga sagaUnaSolaPelicula = new Saga(idPeliculSinSaga, titulo);
                    sagaUnaSolaPelicula.getPelicuas().add(pelicula);
                    gestion.getSagas().put(idPeliculSinSaga, sagaUnaSolaPelicula);
                }

            }
            System.out.println("Se cargo sagas");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static MyLinkedListImpl<String> extraerNombresGeneros(String data) {

        Pattern pattern = Pattern.compile("'name':\\s*'([^']*)'");
        Matcher matcher = pattern.matcher(data);

        MyLinkedListImpl<String> nombres = new MyLinkedListImpl<>();

        while (matcher.find()) {
            nombres.add(matcher.group(1));
        }

        return nombres;
    }
}
