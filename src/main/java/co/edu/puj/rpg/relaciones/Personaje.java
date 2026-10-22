package co.edu.puj.rpg.relaciones;

public class Personaje {

    private String nombre;
    private int nivel;
    private Arma armaEquipada; // Asociación Unidireccional (0..1)
    private Gremio gremio; // Asociación Bidireccional (0..* a 1)
    private CorazonRPG corazon; // Composición (1..1): Relación fuerte

    public Personaje(String nombre, int nivel) {
        this.nombre = nombre;
        this.nivel = nivel;
        this.armaEquipada = null; // Inicialmente desarmado
        this.gremio = null; // Inicialmente sin gremio

        // Composición: Creamos el corazón directamente aquí adentro.
        // No viene de afuera ni se puede reemplazar con un setter.
        this.corazon = new CorazonRPG(100, 50);
    }

    // --- MÉTODOS DE ASOCIACIÓN UNIDIRECCIONAL (Personaje -> Arma) ---

    public void equiparArma(Arma nuevaArma) {
        // Asignamos el arma al atributo del personaje
        this.armaEquipada = nuevaArma;
        if (nuevaArma != null) {
            System.out.println(this.nombre + " ha equipado: " + nuevaArma.getNombre());
        }
    }

    public void desequiparArma() {
        // Validamos si tiene un arma para no tener NullPointerException
        if (this.armaEquipada != null) {
            System.out.println("Se ha desequipado el arma " + this.armaEquipada.getNombre() + " de " + this.nombre + ".");
            this.armaEquipada = null;
        } else {
            System.out.println("El personaje " + this.nombre + " no tiene ningun arma para retirar.");
        }
    }

    public int calcularAtaqueTotal() {
        int ataqueBase = this.nivel * 5;

        // Verificamos si tiene arma equipada antes de llamar a su metodo
        if (this.armaEquipada != null) {
            ataqueBase = ataqueBase + this.armaEquipada.getDanioBase();
        }

        return ataqueBase;
    }

    // --- MÉTODOS DE ASOCIACIÓN BIDIRECCIONAL (Personaje <-> Gremio) ---

    public void unirseAGremio(Gremio nuevoGremio) {
        // 1. Validar que el nuevo gremio no sea null y no sea el mismo en el que ya estamos
        if (nuevoGremio != null && this.gremio != nuevoGremio) {
            // 2. Si ya estabamos en otro gremio diferente, nos salimos de ese primero
            if (this.gremio != null) {
                Gremio gremioViejo = this.gremio;
                this.gremio = null;
                gremioViejo.expulsarMiembro(this);
            }

            // 3. Guardamos el nuevo gremio
            this.gremio = nuevoGremio;

            // 4. Sincronizacion cruzada: le avisamos al gremio que nos agregue
            nuevoGremio.agregarMiembro(this);
        }
    }

    // --- GETTERS Y SETTERS ---

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNivel() {
        return this.nivel;
    }

    public void setNivel(int nivel) {
        if (nivel >= 1) {
            this.nivel = nivel;
        }
    }

    public Arma getArmaEquipada() {
        return this.armaEquipada;
    }

    public void setArmaEquipada(Arma armaEquipada) {
        this.armaEquipada = armaEquipada;
    }

    public Gremio getGremio() {
        return this.gremio;
    }

    public void setGremio(Gremio gremio) {
        this.gremio = gremio;
    }

    // IMPORTANTE: Por la regla de Composición, solo existe getter para el corazón.
    // No debe existir un setter público para evitar que se cambie el objeto desde el exterior.
    public CorazonRPG getCorazon() {
        return this.corazon;
    }

    @Override
    public String toString() {
        String textoArma = "Desarmado";
        if (this.armaEquipada != null) {
            textoArma = this.armaEquipada.getNombre() + " (+" + this.armaEquipada.getDanioBase() + ")";
        }

        String textoGremio = "Sin Gremio";
        if (this.gremio != null) {
            // Solo imprimimos el nombre del gremio para evitar el error de StackOverflowError
            textoGremio = this.gremio.getNombre();
        }

        return "Personaje: " + this.nombre +
                " | Nivel: " + this.nivel +
                " | Ataque: " + calcularAtaqueTotal() +
                " | " + this.corazon.toString() +
                " | Arma: " + textoArma +
                " | Gremio: " + textoGremio;
    }
}
