package uy.edu.um;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Calificacion {
    private int idUsuario;
    private int idPelicula;
    private Double calificacion;
}
