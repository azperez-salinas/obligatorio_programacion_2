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
    private  MyLinkedListImpl<Calificacion> calificaciones= new MyLinkedListImpl<>();

    public Pelicula(int id, String titulo, String idiomaOriginal, MyLinkedListImpl<String> generos) {
        this.id = id;
        this.titulo = titulo;
        this.idiomaOriginal = idiomaOriginal;
        this.generos = generos;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Pelicula{id=").append(id).append(", calificaciones=[");

        if (calificaciones != null && calificaciones.size() > 0) {
            for (int i = 0; i < calificaciones.size(); i++) {
                sb.append(calificaciones.get(i).toString());
                if (i != calificaciones.size() - 1) {
                    sb.append(", ");
                }
            }
        } else {
            sb.append("Sin calificaciones");
        }

        sb.append("]}");
        return sb.toString();
    }
}
