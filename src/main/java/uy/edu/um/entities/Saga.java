package uy.edu.um.entities;
import lombok.Data;
import uy.edu.um.tad.linkedlist.MyLinkedListImpl;

@Data

public class Saga  implements Comparable<Saga>{
    private String id;
    private String nombre;
    private MyLinkedListImpl<Pelicula> pelicuas = new MyLinkedListImpl<>();

    public Saga(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
    public long recaudacion(){
        int recaudacionTotal=0;
        for(int i=0; i<pelicuas.size();i++){
            recaudacionTotal+=pelicuas.get(i).getRecaudacion();
        }
        return recaudacionTotal;
    }
    @Override
    public int compareTo(Saga o) {
        return Long.compare(this.recaudacion(), o.recaudacion());
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Saga{");
        sb.append("id='").append(id).append('\'');
        sb.append(", nombre='").append(nombre).append('\'');
        sb.append(", peliculas=[");

        for (int i = 0; i < pelicuas.size(); i++) {
            sb.append(pelicuas.get(i).getTitulo()); // O lo que quieras mostrar de la película
            if (i < pelicuas.size() - 1) {
                sb.append(", ");
            }
        }

        sb.append("]}");
        return sb.toString();
    }
}