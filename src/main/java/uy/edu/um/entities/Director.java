package uy.edu.um.entities;

import lombok.Data;
import uy.edu.um.tad.binarytree.MySearchBinaryTreeImpl;
import uy.edu.um.tad.linkedlist.MyList;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Data

public class Director implements Comparable<Director>{
    private String id;
    private String nombre;

    private MySearchBinaryTreeImpl<Double, Calificacion> calificaciones = new MySearchBinaryTreeImpl();

    private boolean necesitaCalcularMedianaCalificacion=true;

    private int cantidadPeliculas = 0;
    private int cantidadCalificaciones = 0;
    private double medianaCalculadaCalificacion;


    public Director(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;

    }
    public void addPelicula(Pelicula p) {

        cantidadPeliculas++;

        for(int i=0 ; i< p.getCalificaciones().size();i++){
            Calificacion c= p.getCalificaciones().get(i);
            Double calificacion = c.getCalificacion();
            double incremento = 1e-12;
            Random random = new Random();
            int numeroAleatorio = random.nextInt(1000000);

            calificaciones.add(calificacion+(incremento*numeroAleatorio),c);
            cantidadCalificaciones++;
        }
        necesitaCalcularMedianaCalificacion = true;

    }
    public double calcularMedianaCalificaciones(){
        if(!necesitaCalcularMedianaCalificacion){
            return medianaCalculadaCalificacion;
        }


        if (cantidadPeliculas <= 1 || cantidadCalificaciones <= 100) {
            medianaCalculadaCalificacion = 0;
            necesitaCalcularMedianaCalificacion = false;
            return medianaCalculadaCalificacion;
        }

        MyList<Double> calificacionesOrdenadas = calificaciones.inOrder();


        int medianaIndice = cantidadCalificaciones / 2;
        medianaCalculadaCalificacion = calificacionesOrdenadas.get(medianaIndice);

        String texto = String.valueOf(medianaCalculadaCalificacion);

        Pattern pattern = Pattern.compile("^([0-9]+\\.[0-9])");
        Matcher matcher = pattern.matcher(texto);
        double resultado;

        if (matcher.find()) {
            String reducido = matcher.group(1); // Ej: "1.2"
            resultado = Double.parseDouble(reducido); // ahora es double → 1.2

        } else {
            // Si no coincide (muy raro, pero puede pasar con valores enteros tipo "5.0")
            resultado = 0.0; // o alguna acción alternativa

        }
        medianaCalculadaCalificacion=resultado;
        necesitaCalcularMedianaCalificacion = false;
        return  medianaCalculadaCalificacion;
    }




    @Override
    public int compareTo(Director o) {
        if (o == null) return 1;

        double thisMed = this.calcularMedianaCalificaciones();
        double otherMed = o.calcularMedianaCalificaciones();
        int cmp = Double.compare(thisMed, otherMed);
        if (cmp != 0) return cmp;




        return this.nombre.compareToIgnoreCase(o.nombre);
    }

}
