package clases;

import java.io.Serializable;
import java.util.ArrayList;

public class Serie implements Serializable {
    private String nombre;
    private int anio;
    private int temporadas;

    public Serie(String nombre, int anio, int temporadas) {
        this.nombre = nombre;
        this.anio = anio;
        this.temporadas = temporadas;
    }

    public Serie(){


    }


    public String getNombre() {
        return nombre;
    }

    public int getAnio() {
        return anio;
    }

    @Override
    public String toString() {
        return "nombre: '" + nombre + '\'' +
                ", año: " + anio +
                ", temporadas: " + temporadas;
    }


    public static void buscarSerie(String titulo, ArrayList<Serie> lista){
        for (Serie serie: lista) {
            if (titulo.equals(serie.getNombre())){
                System.out.println(serie);
            }
        }
        System.out.println("La serie buscada no existe");
    }
}
