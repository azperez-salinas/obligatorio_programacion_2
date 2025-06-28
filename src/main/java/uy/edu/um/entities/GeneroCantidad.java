package uy.edu.um.entities;

import lombok.Data;

@Data
public class GeneroCantidad implements Comparable<GeneroCantidad> {
    private String genero;
    private int cantidad;

    public GeneroCantidad(String genero, int cantidad) {
        this.genero = genero;
        this.cantidad = cantidad;
    }


    @Override
    public int compareTo(GeneroCantidad o) {
        return Integer.compare(this.cantidad, o.cantidad); // orden natural ascendente
    }
}
