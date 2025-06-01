package uy.edu.um;

import java.util.Scanner;

public class MenuConsultas {
    Scanner scanner= new Scanner(System.in);
    public void mostrarMenu() {
        int opcion;

        do {
            System.out.println(
                    "\n╔══════════════════════════════════════════════════════════════════════╗" +
                            "\n║                 🎬🌟 MENÚ DE CONSULTAS 🌟🎬                              " +
                            "\n╠══════════════════════════════════════════════════════════════════════╣" +
                            "\n║  1️⃣  Top 5 de las películas que más calificaciones por idioma        " +
                            "\n║  2️⃣  Top 10 de las películas con mejor calificación media            " +
                            "\n║  3️⃣  Top 5 de las colecciones que más ingresos generaron             " +
                            "\n║  4️⃣  Top 10 de los directores con mejor calificación                 " +
                            "\n║  5️⃣  Actor con más calificaciones recibidas cada mes                  " +
                            "\n║  6️⃣  Usuarios con más calificaciones por género                      " +
                            "\n║  7️⃣  Salir                                                          " +
                            "\n╚══════════════════════════════════════════════════════════════════════╝"
            );
            System.out.print("👉 Elige una opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    top5PeliculasPorIdioma();
                    break;
                case 2:
                    top10PeliculasPorCalificacion( );
                    break;
                case 3:
                    top5ColeccionesMasIngresos( );
                    break;
                case 4:
                    top10Directores( );
                    break;
                case 5:
                    actorPorCadaAño( );
                    break;
                case 6:
                    usuariosMasCalificadores( );
                    break;
                case 7:
                    System.out.println("👉Saliendo...");
                    break;
                default:
                    System.out.println("👉Opción inválida, por favor intenta de nuevo.");
            }
        } while (opcion != 7);

    }
    private void top5PeliculasPorIdioma( ){
        System.out.println("evaluando");

    }
    private void top10PeliculasPorCalificacion( ){
        System.out.println("evaluando");

    }
    private void top5ColeccionesMasIngresos( ){
        System.out.println("evaluando");

    }
    private void top10Directores( ){
        System.out.println("evaluando");

    }
    private void actorPorCadaAño( ){
        System.out.println("evaluando");

    }
    private void usuariosMasCalificadores( ){
        System.out.println("evaluando");

    }

}
