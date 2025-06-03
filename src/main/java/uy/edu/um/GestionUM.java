package uy.edu.um;

import lombok.Data;
import uy.edu.um.tad.hash.MyHashImpl;
@Data
public class GestionUM {
    private  MyHashImpl<Integer,Pelicula> peliculas= new MyHashImpl<>();
}
