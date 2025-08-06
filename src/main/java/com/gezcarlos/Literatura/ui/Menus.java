package com.gezcarlos.Literatura.ui;

import java.util.Scanner;

public class Menus {
    private Scanner teclado = new Scanner(System.in);

    public String obtenDatosTeclado() {
        return teclado.nextLine();
    }


    public int menuPrincipal() {
        int opcion = -1;
        while (opcion == -1) {

            System.out.println("Menú Principal");

            var menu = """
                    *********************************
                    *     LITERATURA GUNTENDEX      *
                    *********************************
                    
                    En Gutendex:
                    1 - Buscar libro por título
                    
                    En la Base de Datos:
                    2 - Listar libros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos en un determinado año
                    5 - Listar libros por idioma
                    6 - Listar libros por idioma Paginado
                    
                    0 - Salir
                    
                    Ingresa Una Opcion:""";
            System.out.println(menu);
            opcion = validaEsEntero( obtenDatosTeclado() );
            if (opcion < 0 || opcion > 6) {
                opcion = -1;
                mensajeCorto("  Opción inválida");
            }
        }
        return opcion;
    }

    public Integer validaEsEntero(String valor) {
        Integer opcion;
        try {
            opcion = Integer.valueOf( valor );
        } catch (Exception e) {
            opcion = -1;
            mensajeCorto("  Opción inválida");
        }

        return opcion;
    }

    public void despedida() {
        System.out.println("""
        *********************************
        *       FIN DEL PROGRAMA        *
        *********************************""");
    }

    public void mensajeCorto(String mensaje) {
        System.out.println(mensaje);
    }
}
