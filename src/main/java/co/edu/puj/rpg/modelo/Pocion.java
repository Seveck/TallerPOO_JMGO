package co.edu.puj.rpg.modelo;

public class Pocion {

    private String nombre;
    private int puntosCuracion;

    public Pocion(String nombre, int puntosCuracion) {
        this.nombre = nombre;
        this.puntosCuracion = puntosCuracion;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPuntosCuracion() {
        return this.puntosCuracion;
    }

    public void setPuntosCuracion(int puntosCuracion) {
        this.puntosCuracion = puntosCuracion;
    }
}
