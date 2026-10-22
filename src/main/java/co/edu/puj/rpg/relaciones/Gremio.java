package co.edu.puj.rpg.relaciones;

import java.util.ArrayList;
import java.util.List;

public class Gremio {

    private String nombre;
    private List<Personaje> miembros; // Asociación Bidireccional (0..* a 1)

    public Gremio(String nombre) {
        this.nombre = nombre;
        this.miembros = new ArrayList<>();
    }

    // --- MÉTODOS DE SINCRONIZACIÓN BIDIRECCIONAL ---

    public void agregarMiembro(Personaje p) {
        // 1. Validar que el personaje no sea null
        if (p != null) {
            // 2. Verificar que no este ya en la lista para evitar duplicados y ciclos infinitos
            if (this.miembros.contains(p) == false) {
                // 3. Añadir a la lista de miembros
                this.miembros.add(p);

                // 4. Sincronizacion cruzada: si el personaje todavia no tiene este gremio, se lo asignamos
                if (p.getGremio() != this) {
                    p.unirseAGremio(this);
                }
            }
        }
    }

    public void expulsarMiembro(Personaje p) {
        // Si el personaje esta en el gremio, lo removemos y limpiamos su referencia
        if (p != null && this.miembros.contains(p) == true) {
            this.miembros.remove(p);
            if (p.getGremio() == this) {
                p.setGremio(null);
            }
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
        // Para evitar StackOverflowError rompemos la recursion:
        // En lugar de llamar a p.toString(), solo mostramos el nombre de cada miembro
        StringBuilder sb = new StringBuilder();
        sb.append("Gremio: ").append(this.nombre).append(" | Miembros (").append(this.miembros.size()).append("): [");

        for (int i = 0; i < this.miembros.size(); i = i + 1) {
            Personaje p = this.miembros.get(i);
            sb.append(p.getNombre());
            if (i < this.miembros.size() - 1) {
                sb.append(", ");
            }
        }

        sb.append("]");
        return sb.toString();
    }
}
