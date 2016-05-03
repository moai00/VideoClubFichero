/*
 * Javabean que implementa el ArrayList<Pelicula>
 */
package videoclubfichero;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author usu21
 */
public class ListaPeliculas implements Serializable {

    private ArrayList<Pelicula> lista;

    public ListaPeliculas() {
        lista = new ArrayList<>();
    }

    public void altaPelicula(Pelicula p) {
        lista.add(p);
    }

    public void borrarPelicula(Pelicula p) {
        lista.remove(p);
    }

    public int cantidad() {
        return lista.size();
    }

    public Pelicula peliculaPorCodigo(String codigo) {
        for (Pelicula p : lista) {
            if (p.getCodigo().equalsIgnoreCase(codigo)) {
                return p;
            }
        }
        return null;
    }

        //metodo que me devuelve la pelicula mejor valorada
    //devuelve null si no hay peliculas
    public Pelicula favorita() {
        if (lista.isEmpty()) {
            return null;

        } else {
            Pelicula fav = lista.get(0);
            for (Pelicula p : lista) {
                if (p.getValoracion() > fav.getValoracion()) {
                    fav = p;
                }
            }
            
            return fav;
        }

    }

    public Pelicula obtenerPelicula(int posicion) {
        return lista.get(posicion);
    }

        //funcion que comprueba si existe una pelicula con el mismo codigo
    //que p (Parametro) en el array List
    public boolean existe(Pelicula p) {
//            for (Pelicula aux : lista){
//                if (p.getCodigo().equalsIgnoreCase(aux.getCodigo())){
//                    return true;
//                }
//            }
//            return false;
        return lista.contains(p);

    }

    public ArrayList getLista() {
        return lista;
    }

    public void setLista(ArrayList lista) {
        this.lista = lista;
    }

}
