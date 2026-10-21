package co.edu.puj.rpg.modelo;

public class Mago extends Personaje {

    protected int puntosMana;

    public Mago(String nombre, int vida, int ataqueBase, int posX, int posY, int puntosMana) {
        super(nombre, vida, ataqueBase, posX, posY);
        this.puntosMana = puntosMana;
    }

    @Override
    public void realizarAtaqueEspecial(Personaje objetivo) {
        if (this.puntosMana >= 15) {
            this.puntosMana = this.puntosMana - 15;
            int danoMagico = this.ataqueBase + 20;
            System.out.println(this.nombre + " lanza una Rafaga Arcana contra " + objetivo.getNombre() + " causando " + danoMagico + " de dano magico! Mana restante: " + this.puntosMana);
            objetivo.defender(danoMagico);
        } else {
            System.out.println(this.nombre + " no tiene suficiente mana! Lanza un proyectil menor de " + this.ataqueBase + " de dano.");
            objetivo.defender(this.ataqueBase);
        }
    }

    @Override
    public void defender(int danoRecibido) {
        if (this.puntosMana >= 5) {
            this.puntosMana = this.puntosMana - 5;
            int danoFinal = danoRecibido - 10;
            if (danoFinal < 0) {
                danoFinal = 0;
            }
            System.out.println(this.nombre + " absorbe dano con su Escudo Magico gastando 5 de mana. Dano recibido: " + danoFinal + ". Mana restante: " + this.puntosMana);
            recibirDano(danoFinal);
        } else {
            System.out.println(this.nombre + " no tiene mana para escudo magico. Recibe " + danoRecibido + " de dano.");
            recibirDano(danoRecibido);
        }
    }

    public int getPuntosMana() {
        return this.puntosMana;
    }

    public void setPuntosMana(int puntosMana) {
        this.puntosMana = puntosMana;
    }
}
