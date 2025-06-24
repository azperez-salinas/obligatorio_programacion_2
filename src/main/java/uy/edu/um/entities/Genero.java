package uy.edu.um.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Genero implements Comparable<Genero>{
    private int calificaciones;
    private String genero;

    @Override
    public int compareTo(Genero o) {
        return Integer.compare(this.calificaciones, o.calificaciones);
    }
}
