package uy.edu.um.consultas;

import lombok.Data;
import uy.edu.um.entities.*;
import uy.edu.um.tad.hash.MyHashImpl;

@Data
public class GestionUM {
    private  MyHashImpl<String, Pelicula> peliculas= new MyHashImpl<>();
    private  MyHashImpl<String, Saga> sagas= new MyHashImpl<>();
    private MyHashImpl<String, Actor> actores= new MyHashImpl<>();
    private MyHashImpl<String, Director> directores= new MyHashImpl<>();
    private MyHashImpl<String, Usuario> usuarios= new MyHashImpl<>();

}