package uy.edu.um.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
public class Calificacion {
    private String idUsuario;
    private String idPelicula;
    private Double calificacion;
    Timestamp tiempo;
}
