package co.edu.puj.rpg.modelo.enemigo;

import co.edu.puj.rpg.modelo.Personaje;

public class Enemigo extends Personaje {

    public Enemigo() {
        super();
    }

    public Enemigo(String nombre, int vida, int ataqueBase, int posicionX, int posicionY) {
        super(nombre, vida, ataqueBase, posicionX, posicionY);
    }

    public Enemigo(String nombre, int vidaMaxima, int puntosAtaque) {
        super(nombre, vidaMaxima, puntosAtaque, 0, 0);
    }

    public void atacar(Personaje personaje) {
        personaje.defender(this.ataqueBase);
    }

    @Override
    public void realizarAtaqueEspecial(Personaje objetivo) {
        int danoTotal = this.ataqueBase + 5;
        System.out.println(this.nombre + " realiza un Mordisco Feroz contra " + objetivo.getNombre() + " causando " + danoTotal + " de dano!");
        objetivo.defender(danoTotal);
    }

    @Override
    public void defender(int danoRecibido) {
        System.out.println(this.nombre + " recibe " + danoRecibido + " de dano.");
        recibirDano(danoRecibido);
    }
}
