package uy.edu.um;

import uy.edu.um.consultas.MenuConsultas;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MenuConsultas menuConsultas= new MenuConsultas();
        Scanner scanner= new Scanner(System.in);
        short opcion;

        do{

            System.out.println(
                    "\n╔═══════════════════════╗" +
                            "\n║ 🌟 MENÚ PRINCIPAL 🌟  " +
                            "\n╠═══════════════════════╣" +

                            "\n║  1️⃣  Cargar Datos     " +
                            "\n║  2️⃣  Menú Consultas      " +
                            "\n║  3️⃣  Salir                  " +
                            "\n╚═══════════════════════╝"
            );
            opcion = scanner.nextShort();

            switch (opcion){
                case 1:
                    menuConsultas.cargarDatos();;
                    break;
                case 2:
                    menuConsultas.mostrarMenu();
                    break;
                case 3:
                    System.out.println("👉Cerrando programa...");
                    break;
                default:
                    System.out.println("👉Opcion invalida no reconocida");
                    break;
            }

        }while(opcion!=3);



    }
}