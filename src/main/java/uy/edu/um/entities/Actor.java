package uy.edu.um.entities;
import lombok.Data;

@Data

public class Actor {
    private String id;
    private String nombre;

    public Actor(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Actor{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}

