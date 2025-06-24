package uy.edu.um.consultas;

import uy.edu.um.entities.*;
import uy.edu.um.tad.hash.MyHash;
import uy.edu.um.tad.hash.MyHashImpl;
import uy.edu.um.tad.heap.MyHeap;
import uy.edu.um.tad.heap.MyHeapImpl;
import uy.edu.um.tad.linkedlist.MyLinkedListImpl;
import uy.edu.um.tad.linkedlist.MyList;
import java.util.Scanner;

public class MenuConsultas {
    Scanner scanner= new Scanner(System.in);
    CargarDatos cargarDatos=new CargarDatos();
    private boolean datosCargados=false;
    public void cargarDatos(){
        if(!datosCargados){
            cargarDatos.cargarDatosPelis();
            cargarDatos.cargarDatosCalificaciones();
            cargarDatos.cargarDatosCreditos();


            MyList<Saga> sagas = cargarDatos.getGestion().getSagas().values();
            MyList<Pelicula> peliculas = cargarDatos.getGestion().getPeliculas().values();
            System.out.println("cantidad sagas ," +sagas.size()); //BUENA CANTIDAD DE SAGAS
            System.out.println("cantidad peliculas ," +peliculas.size()); //BUENA CANTIDAD DE PELIUCLAS
            //System.out.println(cargarDatos.gestion.getSagas().get("1241").toString()); //BIEN CARGADAS LAS SAGAS
//        for(int i=0; i< peliculas.size();i++)
//            if(peliculas.get(i).calificacionMedia()!=0)
//                System.out.println(peliculas.get(i).toString());
            System.out.println("cantidad usuarios ,"+ cargarDatos.gestion.getUsuarios().size());
            System.out.println("cantidad directores ,"+ cargarDatos.gestion.getDirectores().size());
            System.out.println("cantidad actores ,"+ cargarDatos.gestion.getActores().size());
            datosCargados=true;
            //MyLinkedListImpl<String> generosPeliculaUno = cargarDatos.gestion.getPeliculas().get("862").getGeneros();
//                    for(int i=0; i< generosPeliculaUno.size(); i++){
//                        System.out.println(generosPeliculaUno.get(i));
//                    } // BIEN CARGADOS LOS GENEROS
        }else {
            System.out.println("Ya se cargaron los datos");
        }

    }

    public void mostrarMenu() {
        int opcion;

        do {
            System.out.println(
                    "\n╔══════════════════════════════════════════════════════════════════════╗" +
                            "\n║                 🎬🌟 MENÚ DE CONSULTAS 🌟🎬                              " +
                            "\n╠══════════════════════════════════════════════════════════════════════╣" +
                            "\n║  1️⃣  Top 5 de las películas que más calificaron por idioma           " +
                            "\n║  2️⃣  Top 10 de las películas con mejor calificación media            " +
                            "\n║  3️⃣  Top 5 de las colecciones que más ingresos generaron             " +
                            "\n║  4️⃣  Top 10 de los directores con mejor calificación                 " +
                            "\n║  5️⃣  Actor con más calificaciones recibidas cada mes                  " +
                            "\n║  6️⃣  Usuarios con más calificaciones por género                      " +
                            "\n║  7️⃣  Salir                                                          " +
                            "\n╚══════════════════════════════════════════════════════════════════════╝"
            );
            System.out.print("👉 Elige una opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    top5PeliculasPorIdioma();
                    break;
                case 2:
                    top10PeliculasPorCalificacion( );
                    break;
                case 3:
                    top5ColeccionesMasIngresos( );
                    break;
                case 4:
                    top10Directores( );
                    break;
                case 5:
                    actorPorCadaAño( );
                    break;
                case 6:
                    usuariosMasCalificadores( );
                    break;
                case 7:
                    System.out.println("👉Saliendo...");
                    break;
                default:
                    System.out.println("👉Opción inválida, por favor intenta de nuevo.");
            }
        } while (opcion != 7);

    }
    private void top5PeliculasPorIdioma( ){
        System.out.println("evaluando");
        Pelicula.compararPorCantidad=true;
        MyHeap<Pelicula> topPeliculasIngles = new MyHeapImpl<>(true);
        MyHeap<Pelicula> topPeliculasFrances = new MyHeapImpl<>(true);
        MyHeap<Pelicula> topPeliculasItaliano = new MyHeapImpl<>(true);
        MyHeap<Pelicula> topPeliculasEspaniol = new MyHeapImpl<>(true);
        MyHeap<Pelicula> topPeliculasPortugues = new MyHeapImpl<>(true);

        MyList<Pelicula> listaPeliculas = cargarDatos.gestion.getPeliculas().values();

        for (int i = 0; i < listaPeliculas.size(); i++) {
            Pelicula peli= listaPeliculas.get(i);
            if(peli.getIdiomaOriginal().toLowerCase().equalsIgnoreCase("es")){
                if(topPeliculasEspaniol.size()<10){
                    topPeliculasEspaniol.insert(peli);
                }else if(peli.getCalificaciones().size()>topPeliculasEspaniol.get().getCalificaciones().size()){
                    topPeliculasEspaniol.delete();
                    topPeliculasEspaniol.insert(peli);
                }
            }
            if(peli.getIdiomaOriginal().toLowerCase().equalsIgnoreCase("en")){
                if(topPeliculasIngles.size()<10){
                    topPeliculasIngles.insert(peli);
                }else if(peli.getCalificaciones().size()>topPeliculasIngles.get().getCalificaciones().size()){
                    topPeliculasIngles.delete();
                    topPeliculasIngles.insert(peli);
                }
            }
            if(peli.getIdiomaOriginal().toLowerCase().equalsIgnoreCase("fr")){
                if(topPeliculasFrances.size()<10){
                    topPeliculasFrances.insert(peli);
                }else if(peli.getCalificaciones().size()>topPeliculasFrances.get().getCalificaciones().size()){
                    topPeliculasFrances.delete();
                    topPeliculasFrances.insert(peli);
                }
            }
            if(peli.getIdiomaOriginal().toLowerCase().equalsIgnoreCase("it")){
                if(topPeliculasItaliano.size()<10){
                    topPeliculasItaliano.insert(peli);
                }else if(peli.getCalificaciones().size()>topPeliculasItaliano.get().getCalificaciones().size()){
                    topPeliculasItaliano.delete();
                    topPeliculasItaliano.insert(peli);
                }
            }
            if(peli.getIdiomaOriginal().toLowerCase().equalsIgnoreCase("pt")){
                if(topPeliculasPortugues.size()<10){
                    topPeliculasPortugues.insert(peli);
                }else if(peli.getCalificaciones().size()>topPeliculasPortugues.get().getCalificaciones().size()){
                    topPeliculasPortugues.delete();
                    topPeliculasPortugues.insert(peli);
                }
            }
        }


        while(topPeliculasEspaniol.size()>0) {
            Pelicula peli = topPeliculasEspaniol.delete();
            System.out.println("Idioma: español, "+"Cantidad de calificaciones: "+peli.getCalificaciones().size()+" "+peli.toString());
        }
        System.out.println();
        while(topPeliculasIngles.size()>0) {
            Pelicula peli = topPeliculasIngles.delete();
            System.out.println("Idioma: ingles, "+"Cantidad de calificaciones: "+peli.getCalificaciones().size()+" "+peli.toString());
        }
        System.out.println();
        while(topPeliculasItaliano.size()>0) {
            Pelicula peli = topPeliculasItaliano.delete();
            System.out.println("Idioma: italiano, "+"Cantidad de calificaciones: "+peli.getCalificaciones().size()+" "+peli.toString());
        }
        System.out.println();
        while(topPeliculasFrances.size()>0) {
            Pelicula peli = topPeliculasFrances.delete();
            System.out.println("Idioma: frances, "+"Cantidad de calificaciones: "+peli.getCalificaciones().size()+" "+peli.toString());
        }
        System.out.println();
        while(topPeliculasPortugues.size()>0) {
            Pelicula peli = topPeliculasPortugues.delete();
            System.out.println("Idioma: portuges, "+"Cantidad de calificaciones: "+peli.getCalificaciones().size()+" "+peli.toString());
        }

    }

    private void top10PeliculasPorCalificacion( ){
        System.out.println("Evaluando top 10...");
        MyHeap<Pelicula> topPeliculas = new MyHeapImpl<>(true);
        Pelicula.compararPorCantidad=false;
        MyList<Pelicula> listaPeliculas = cargarDatos.gestion.getPeliculas().values();
        for (int i = 0; i < listaPeliculas.size(); i++) {
            Pelicula peli = listaPeliculas.get(i);
            if (topPeliculas.size() < 10) {
                topPeliculas.insert(peli);
            } else if (peli.calificacionMedia() > topPeliculas.get().calificacionMedia() ) {
                topPeliculas.delete();
                topPeliculas.insert(peli);
            }
        }
        for(int i=0; i<10; i++)
            System.out.println(10-i +": "+topPeliculas.delete());
    }

    private void top5ColeccionesMasIngresos( ){
        System.out.println("evaluando");
        MyHeap<Saga> sagasTop= new MyHeapImpl<>(true);
        MyList<Saga> sagas = cargarDatos.gestion.getSagas().values();
        for(int i=0; i< sagas.size();i++){
            if(sagasTop.size()<5){
                sagasTop.insert(sagas.get(i));
            }else if(sagas.get(i).recaudacion() > sagasTop.get().recaudacion()){
                sagasTop.delete();
                sagasTop.insert(sagas.get(i));
            }
        }
        while(sagasTop.size()>0) {
            Saga saga = sagasTop.delete();
            System.out.println("Recaudación: USD"+saga.recaudacion()+", nombre:"+saga.getNombre());
        }
    }

    private void top10Directores( ){
        System.out.println("evaluando");

        MyList<Director> directores = cargarDatos.gestion.getDirectores().values();


        System.out.println("evaluando por calificacion");


        MyHeap<Director> topDirectoresCalificacion = new MyHeapImpl<>(true);
        for (int i = 0; i < directores.size(); i++) {
            Director d = directores.get(i);

            double mediana = d.calcularMedianaCalificaciones();



            if (topDirectoresCalificacion.size() < 10) {
                topDirectoresCalificacion.insert(d);
            } else if (mediana > topDirectoresCalificacion.get().calcularMedianaCalificaciones()) {
                topDirectoresCalificacion.delete();
                topDirectoresCalificacion.insert(d);
            }
        }

        while (topDirectoresCalificacion.size() > 0) {
            Director d = topDirectoresCalificacion.delete();
            System.out.println("Mediana: " + d.calcularMedianaCalificaciones()
                    + ", nombre: " + d.getNombre()
                    + ", Cant. pelis: " + d.getCantidadPeliculas()+ ", Cant. calif: " + d.getCantidadCalificaciones());
        }
    }

    private void actorPorCadaAño( ) {
        MyHashImpl<String, int[]> conteo = new MyHashImpl<>();  // actorId → int[13]
        int[] maxCantMes = new int[13];                         // máximo por mes
        String[] mejorActor = new String[13];                   // actorId con más calificaciones
        MyList<String> todasLasPeliculas = cargarDatos.gestion.getPeliculas().keys(); // ← lista de IDs

        for (int i = 0; i < todasLasPeliculas.size(); i++) {
            String peliId = todasLasPeliculas.get(i);
            Pelicula peli = cargarDatos.gestion.getPeliculas().get(peliId);

            MyList<Calificacion> califs = peli.getCalificaciones();
            MyList<String> actorIds = peli.getIdActores();

            if (califs == null || actorIds == null || actorIds.isEmpty() || califs.isEmpty()) continue;

            for (int j = 0; j < califs.size(); j++) {
                Calificacion c = califs.get(j);
                int mes = c.getTiempo().toLocalDateTime().getMonthValue();  // 1..12

                for (int k = 0; k < actorIds.size(); k++) {
                    String actorId = actorIds.get(k);

                    int[] contador = conteo.get(actorId);
                    if (contador == null) {
                        contador = new int[13];
                        conteo.put(actorId, contador);
                    }

                    int total = ++contador[mes];

                    if (total > maxCantMes[mes]) {
                        maxCantMes[mes] = total;
                        mejorActor[mes] = actorId;
                    }
                }
            }
        }
        System.out.println("Actor con más calificaciones por mes:");
        for (int mes = 1; mes <= 12; mes++) {
            String id = mejorActor[mes];
            int cantidad = maxCantMes[mes];

            if (id != null && cargarDatos.gestion.getActores().contains(id)) {
                Actor a = cargarDatos.gestion.getActores().get(id);
                System.out.printf("Mes %2d → %-25s (%d calificaciones)%n",
                        mes, a.getNombre(), cantidad);
            } else {
                System.out.printf("Mes %2d → ningún actor calificado%n", mes);
            }
        }

    }

    private void usuariosMasCalificadores( ){
        System.out.println("evaluando");
        MyHash<String,Integer> generoMasCalificado= new MyHashImpl<>();

        for(int i=0; i< cargarDatos.gestion.getPeliculas().size();i++){
            Pelicula peli = cargarDatos.gestion.getPeliculas().values().get(i);
            int calificacionesActuales= peli.getCalificaciones().size();
            for(int j=0; j< peli.getGeneros().size();j++){
                if(generoMasCalificado.get(peli.getGeneros().get(j).toLowerCase())==null){
                    generoMasCalificado.put(peli.getGeneros().get(j).toLowerCase(),calificacionesActuales);
                }else {
                    int valorAvtual = generoMasCalificado.get(peli.getGeneros().get(j).toLowerCase());
                    generoMasCalificado.put(peli.getGeneros().get(j).toLowerCase(),calificacionesActuales+valorAvtual);
                }
            }
        }
        MyList<String>  generos = generoMasCalificado.keys();
        MyHeap<Genero> generosOrdenados = new MyHeapImpl<>(true);
        for (int i = 0; i < generos.size(); i++) {
            String genero = generos.get(i);
            int total = generoMasCalificado.get(genero);
            Genero g= new Genero( total,genero);
            if(generosOrdenados.size()<10){
                generosOrdenados.insert(g);
            }
            else{
                if(generosOrdenados.get().getCalificaciones()<g.getCalificaciones()){
                    generosOrdenados.delete();
                    generosOrdenados.insert(g);
                }
            }
        }
        MyList<Genero> generos2= new MyLinkedListImpl<>();
        System.out.println("Top 10 géneros con más calificaciones:");
        for(int i=0 ; i<10;i++){
            Genero g= generosOrdenados.delete();
            generos2.add(g);
            System.out.println("El genero :"+g.getGenero()+" tiene: "+g.getCalificaciones());
        }


    }

}
