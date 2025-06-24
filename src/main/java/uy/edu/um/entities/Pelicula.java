package uy.edu.um.entities;
import lombok.AllArgsConstructor;
import lombok.Data;
import uy.edu.um.tad.linkedlist.MyLinkedListImpl;
import uy.edu.um.tad.linkedlist.MyList;

@Data
@AllArgsConstructor
public class Pelicula implements Comparable<Pelicula> {
    private String id;
    private String titulo;
    private String idiomaOriginal;
    private MyLinkedListImpl<String> generos;
    private  MyLinkedListImpl<Calificacion> calificaciones= new MyLinkedListImpl<>();
    private long recaudacion;
    private MyList<String> idActores = new MyLinkedListImpl<>();
    public static boolean compararPorCantidad = false;
    private boolean necesitaCalcularCalificacionMedia=true;
    private double calMedia;


    public Pelicula(String id, String titulo, String idiomaOriginal, MyLinkedListImpl<String> generos, long recaudacion) {
        this.id = id;
        this.titulo = titulo;
        this.idiomaOriginal = idiomaOriginal;
        this.generos = generos;
        this.recaudacion= recaudacion;
    }

    @Override
    public int compareTo(Pelicula o) {
        if (o == null) return 1;

        if (compararPorCantidad) {
            int cmp = Integer.compare(this.calificaciones.size(), o.calificaciones.size());
            if (cmp != 0) return cmp;
        } else {
            int cmp = Double.compare(this.calificacionMedia(), o.calificacionMedia());
            if (cmp != 0) return cmp;
        }

        return this.titulo.compareToIgnoreCase(o.titulo);
    }

    public double   calificacionMedia(){
        if (!necesitaCalcularCalificacionMedia) return calMedia;
        if (calificaciones.isEmpty() || calificaciones.size()<100) {
            calMedia = 0;
        }else {
            double calificacion=0;
            for(int i =0; i< calificaciones.size(); i++) {
                calificacion+=calificaciones.get(i).getCalificacion();
            }
            calMedia = calificacion/calificaciones.size();
        }

        necesitaCalcularCalificacionMedia=false;
        return calMedia;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Pelicula{id=").append(id).append(" Titulo= ").append(titulo).append(" Cal Med= ").append(calificacionMedia());

        sb.append("]}");
        return sb.toString();
    }
}

