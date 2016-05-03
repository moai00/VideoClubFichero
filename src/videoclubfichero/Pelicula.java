/*
 * Clase para guardar los datos de una pelicula
 */
package videoclubfichero;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author miquel port
 */
public class Pelicula implements Serializable {

    private String titulo;
    private String codigo;
    private int duracion;
    private boolean visto;
    private int valoracion;
    private String genero;

    public Pelicula() {

    }

    public Pelicula(String titulo, String codigo, int duracion, boolean visto, int valoracion, String genero) {
        this.titulo = titulo;
        this.codigo = codigo;
        this.duracion = duracion;
        this.visto = visto;
        this.valoracion = valoracion;
        this.genero = genero;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + Objects.hashCode(this.codigo);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pelicula other = (Pelicula) obj;
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        return true;
    }
    
    

    @Override
    public String toString() {

        String vista;
        if (visto) { //equivale a visto = true || !visto seria visto = false
            vista = "SI";
        } else {
            vista = "NO";
        }

        return "codigo=" + codigo + ", titulo=" + titulo + ", duracion=" + duracion + ", visto=" + vista + ", valoracion=" + valoracion + ", genero=" + genero + '}';
    }

    public int getValoracion() {
        return valoracion;
    }

    public void setValoracion(int valoracion) {
        this.valoracion = valoracion;
    }

    

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public boolean isVisto() {
        return visto;
    }

    public void setVisto(boolean visto) {
        this.visto = visto;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getCodigo() {
        return codigo;
    }

}
