/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videoclubfichero;

import utilidades.EntradaDatos;
import utilidades.Fichero;

/**
 *
 * @author usu21
 */
public class Menu {

    private ListaPeliculas misPeliculas;

    private Fichero miFichero;

    public Menu() {

        //creamos el fichero
        miFichero = new Fichero("videoclub.xml");

        //cargo las peliculas del fichero
        misPeliculas = (ListaPeliculas) miFichero.leer();
        if (misPeliculas == null) {
            misPeliculas = new ListaPeliculas();
        }
        int opcion;
        do {

            mostrarMenu();
            opcion = EntradaDatos.pedirEntero("Escoge una opcion");
            switch (opcion) {
                case 1:
                    altaPelicula();
                    break;
                case 2:
                    listadoPeliculas();
                    break;
                case 3:
                    listadoPeliculasxGenero();
                    break;
                case 4:
                    peliculaFavorita();
                    break;
                case 5:
                    totales();
                    break;
                case 6:
                    modificarPelicula();
                    break;
                case 7:
                    break;

                case 0:
                    System.out.println("Hasta la proxima");
                    break;
                default:
                    System.out.println("Opción Incorrecta");
            }

        } while (opcion != 0);
    }

    private void totales() {
        //peliculas que tiene por ver el ususario

    }

    private void peliculaFavorita() {

        Pelicula favorita = misPeliculas.favorita();
        if (favorita == null){
            System.out.println("No hay peliculas");
            
        }else{
            System.out.println("La pelicula mejor valorada es:");
            System.out.println(favorita);
        }

    }

    private void modificarPelicula() {
        //pedir codigo de una pelicula
        //el usuario puede cambiar todo menos el codigo
        //set variables y volver a grabar el fichero

        String codigo = EntradaDatos.pedirCadena("Introduce el codigo de la peli a modificar");
        Pelicula p = misPeliculas.peliculaPorCodigo(codigo);
        if (p == null) {
            System.out.println("No hay ninguna pelicula con ese codigo");
        } else {
            System.out.println("Datos de la pelicula con codigo " + p.getCodigo());
            System.out.println("1. Titulo: " + p.getTitulo());
            System.out.println("2. Duración: " + p.getDuracion());
            System.out.println("3. Género: " + p.getGenero());
            System.out.println("4. Valoración: " + p.getValoracion());
            System.out.println("5. Visto / no visto: ");
            if (p.isVisto()) {
                System.out.println("Pelicula vista");
            } else {
                System.out.println("Pelicula por ver");
            }

            int dato = EntradaDatos.pedirEntero("Que dato quieres cambiar?");
            switch (dato) {
                case 1:
                    String titulo = EntradaDatos.pedirCadena("Nuevo titulo");
                    p.setTitulo(titulo);
                    System.out.println("Titulo modificado");
                    break;
                case 2:
                    int duracion;
                    do {
                        duracion = EntradaDatos.pedirEntero("Nueva duracion");
                        if (duracion < 1) {
                            System.out.println("La duracion no puede ser menor de 1");

                        } else {
                            p.setDuracion(duracion);
                            System.out.println("Duracion modificada");
                        }

                    } while (duracion < 1);
                    break;
                case 3:
                    String genero = EntradaDatos.pedirCadena("Nuevo género");
                    p.setGenero(genero);
                    System.out.println("Genero Modificado");
                    break;
                case 4:
                    int valoracion;
                    do {
                        valoracion = EntradaDatos.pedirEntero("Nueva valoracion");
                        if (valoracion < 0 || valoracion > 10) {
                            System.out.println("Incorrecta. Debe estar entre 0 y 10");
                        } else {
                            p.setValoracion(valoracion);
                            System.out.println("Valoracion modificada");
                        }

                    } while (valoracion < 0 || valoracion > 10);
                    break;
                case 5:

                    String pregunta;
                    if (p.isVisto()) {
                        System.out.println("Habias indicado que habias visto la peli");
                        pregunta = "¿Quieres indicar que no la has visto (S/N)?";
                    } else {
                        pregunta = "Has visto la peli S/N";
                    }
                    String respuesta;
                    do {
                        respuesta = EntradaDatos.pedirCadena(pregunta);
                        if (respuesta.equalsIgnoreCase("S")) {
                            p.setVisto(!p.isVisto());
                            System.out.println("Dato modificado");

                        } else if (respuesta.equalsIgnoreCase("N")) {
                            System.out.println("Entonces no modificamos el dato");
                        } else {
                            System.out.println("Respuesta incorrecta debes indicar S o N");
                        }
                    } while (!respuesta.equalsIgnoreCase("s") && !respuesta.equalsIgnoreCase("n"));

                    break;
                case 7:
                    borrarPelicula();
                    break;
                default:
                    System.out.println("Introduce un numero del 1 al 5");
            }
            miFichero.grabar(misPeliculas);
        }
    }

    private void borrarPelicula() {
        String codigo = EntradaDatos.pedirCadena("Codigo de la pelicula para borrar");
        Pelicula p = misPeliculas.peliculaPorCodigo(codigo);
        if (p == null) {
            System.out.println("No existe ninguna pelicula con ese codigo");

        } else {

            String confirmacion;
            do {
                System.out.println("Datos de la pelicula");
                System.out.println(p);
                confirmacion = EntradaDatos.pedirCadena("Estas seguro que quieres borrar la peli");
                if (confirmacion.equalsIgnoreCase("S")) {
                    misPeliculas.borrarPelicula(p);
                    miFichero.grabar(misPeliculas);
                    System.out.println("Pelicula borrada");

                } else if (confirmacion.equalsIgnoreCase("N")) {
                    System.out.println("Vale,pues no la borramos");
                } else {
                    System.out.println("Respuesta incorrecta debes poner N o S");
                }

            } while (!confirmacion.equalsIgnoreCase("s") && !confirmacion.equalsIgnoreCase("n"));

        }
    }

    private void listadoPeliculasxGenero() {

        int cantidad = misPeliculas.cantidad();
        if (cantidad == 0) {
            System.out.println("No hay peliculas");
        } else {
            String inGenero = EntradaDatos.pedirCadena("Introduce un genero");
            boolean encontrado = false;
            for (int i = 0; i < cantidad; i++) {
                Pelicula actual = misPeliculas.obtenerPelicula(i);
                if (inGenero.equalsIgnoreCase(actual.getGenero())) {
                    System.out.println(actual);
                    encontrado = true;
                }
            }
            if (!encontrado) {
                System.out.println("No tenemos peliculas del genero indicado");
            }
        }
    }

    private void listadoPeliculas() {
        //cantidad de peliculas que hay en misPeliculas
        int cantidad = misPeliculas.cantidad();
        if (cantidad == 0) {
            System.out.println("No hay peliculas");
        } else {
            System.out.println("***** LISTADO DE PELICULAS *****");
            for (int i = 0; i < cantidad; i++) {
                //obtenemos la pelicula que esta en la posicion i
                Pelicula actual = misPeliculas.obtenerPelicula(i);
                System.out.println(actual);
            }
        }
    }

    private void altaPelicula() {
        boolean existe = false;
        String codigo;
        do {

            codigo = EntradaDatos.pedirCadena("Codigo de la pelicula");
            Pelicula auxiliar = new Pelicula();
            auxiliar.setCodigo(codigo);
            existe = misPeliculas.existe(auxiliar);
            if (existe) {
                System.out.println("Ya existe una película con ese codigo");
            }

        } while (existe);

        String titulo = EntradaDatos.pedirCadena("Titulo");
        int duracion;
        do {
            duracion = EntradaDatos.pedirEntero("Duracion en minutos");
            if (duracion < 1) {
                System.out.println("La duracion no puede ser inferior a 1 min");
            }

        } while (duracion < 1);
        String genero = EntradaDatos.pedirCadena("Introduce el genero");
        int valoracion;
        do {

            valoracion = EntradaDatos.pedirEntero("Valoración");
            if (valoracion < 0 || valoracion > 10) {
                System.out.println("La valoracion tiene que estar entre 0 y 10");
            }

        } while (valoracion < 0 || valoracion > 10);

        boolean visto = false;
        String respuesta = " ";
        do {
            respuesta = EntradaDatos.pedirCadena("Has visto la peli (S/N)?");
            if (respuesta.equalsIgnoreCase("S")) {
                visto = true;
            } else if (respuesta.equalsIgnoreCase("N")) {
                visto = false;

            } else {
                System.out.println("Introduce S o N");
            }

        } while (!respuesta.equalsIgnoreCase("s") && !respuesta.equalsIgnoreCase("n"));

        Pelicula p = new Pelicula(titulo, codigo, duracion, visto, valoracion, genero);

        misPeliculas.altaPelicula(p);
//grabamos en fichero
        miFichero.grabar(misPeliculas);
    }

    private void mostrarMenu() {
        System.out.println("Gestión de peliculas");
        System.out.println("1. Nueva pelicula");
        System.out.println("2. Listado de todas las peliculas");
        System.out.println("3. Listado de peliculas de un genero");
        System.out.println("4. Pelicula favorita");
        System.out.println("5. Totales");
        System.out.println("6. Modificar Pelicula");
        System.out.println("7. Borrar Pelicula");
        System.out.println("0. Salir");
    }

}
