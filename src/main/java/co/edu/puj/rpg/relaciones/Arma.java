package co.edu.puj.rpg.relaciones;

public class Arma {

    private String nombre;
    private int danioBase;

    public Arma(String nombre, int danioBase) {
        this.nombre = nombre;
        this.danioBase = danioBase;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDanioBase() {
        return this.danioBase;
    }

    public void setDanioBase(int danioBase) {
        this.danioBase = danioBase;
    }

    @Override
    public String toString() {
        return "Arma: " + this.nombre + " (Daño: " + this.danioBase + ")";
    }
}
