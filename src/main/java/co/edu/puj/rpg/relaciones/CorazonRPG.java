package co.edu.puj.rpg.relaciones;

public class CorazonRPG {

    private int saludBase;
    private int manaBase;

    public CorazonRPG(int saludBase, int manaBase) {
        this.saludBase = saludBase;
        this.manaBase = manaBase;
    }

    public int getSaludBase() {
        return this.saludBase;
    }

    public void setSaludBase(int saludBase) {
        this.saludBase = saludBase;
    }

    public int getManaBase() {
        return this.manaBase;
    }

    public void setManaBase(int manaBase) {
        this.manaBase = manaBase;
    }

    // Métodos para alterar la salud y el maná
    public void modificarSalud(int cantidad) {
        this.saludBase = this.saludBase + cantidad;
        if (this.saludBase < 0) {
            this.saludBase = 0;
        }
    }

    public void modificarMana(int cantidad) {
        this.manaBase = this.manaBase + cantidad;
        if (this.manaBase < 0) {
            this.manaBase = 0;
        }
    }

    @Override
    public String toString() {
        return "Corazón [Salud: " + this.saludBase + ", Maná: " + this.manaBase + "]";
    }
}
