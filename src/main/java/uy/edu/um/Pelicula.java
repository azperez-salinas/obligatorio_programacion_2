package uy.edu.um;
import lombok.AllArgsConstructor;
import lombok.Data;
import uy.edu.um.tad.linkedlist.MyLinkedListImpl;

@Data
@AllArgsConstructor
public class Pelicula {
    private int id;
    private String titulo;
    private String idiomaOriginal;
    private MyLinkedListImpl<String> generos;
    private static MyLinkedListImpl<Calificacion> calificaciones;

}
