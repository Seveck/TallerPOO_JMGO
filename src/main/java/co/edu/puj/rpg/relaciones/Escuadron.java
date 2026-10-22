package co.edu.puj.rpg.relaciones;

import java.util.ArrayList;
import java.util.List;

public class Escuadron {

    private String nombre;
    private List<Personaje> miembros; // Agregación (0..*): Relación débil

    public Escuadron(String nombre) {
        this.nombre = nombre;
        this.miembros = new ArrayList<>(); // Inicialización de la colección
    }

    // --- MÉTODOS DE GESTIÓN DE AGREGACIÓN ---

    public void reclutarPersonaje(Personaje p) {
        // Validamos que 'p' no sea null y que no esté ya dentro de la lista de miembros
        if (p != null && this.miembros.contains(p) == false) {
            // Agregamos 'p' a la lista sin modificar su creación original
            this.miembros.add(p);
            System.out.println("El personaje " + p.getNombre() + " fue reclutado exitosamente al escuadron " + this.nombre + ".");
        } else {
            System.out.println("No se pudo reclutar el personaje (o es nulo o ya pertenece al escuadron).");
        }
    }

    public void expulsarPersonaje(Personaje p) {
        // Validamos si 'p' no es nulo y si realmente forma parte del escuadrón
        if (p != null && this.miembros.contains(p) == true) {
            this.miembros.remove(p);
            // Nota de agregación: 'p' sigue existiendo en el sistema sin alterarse
            System.out.println("El personaje " + p.getNombre() + " salio del escuadron " + this.nombre + ". Su objeto sigue vivo e intacto en memoria.");
        } else {
            System.out.println("El personaje no se encuentra en el escuadron.");
        }
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Personaje> getMiembros() {
        return this.miembros;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Escuadron: ").append(this.nombre).append(" (").append(this.miembros.size()).append(" integrantes)\n");

        if (this.miembros.isEmpty() == true) {
            sb.append("  (Sin combatientes reclutados)");
        } else {
            for (int i = 0; i < this.miembros.size(); i = i + 1) {
                Personaje p = this.miembros.get(i);
                sb.append("  - [").append(i + 1).append("] ").append(p.getNombre())
                  .append(" (Nivel ").append(p.getNivel())
                  .append(", Ataque: ").append(p.calcularAtaqueTotal()).append(")\n");
            }
        }

        return sb.toString();
    }
}
