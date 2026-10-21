package co.edu.puj.rpg.modelo;

public class Guerrero extends Personaje {

    protected int fuerzaEscudo;

    public Guerrero(String nombre, int vida, int ataqueBase, int posX, int posY, int fuerzaEscudo) {
        super(nombre, vida, ataqueBase, posX, posY);
        this.fuerzaEscudo = fuerzaEscudo;
    }

    @Override
    public void realizarAtaqueEspecial(Personaje objetivo) {
        int danoEspecial = this.ataqueBase + this.fuerzaEscudo;
        System.out.println(this.nombre + " realiza un Golpe de Escudo brutal contra " + objetivo.getNombre() + " causando " + danoEspecial + " de dano!");
        objetivo.defender(danoEspecial);
    }

    public void atacar(Personaje p) {
        int dano = this.ataqueBase;
        if (Math.random() < 0.15) {
            dano = dano * 2;
            System.out.println("¡Golpe critico!");
        }
        System.out.println(this.nombre + " ataca con su espada a " + p.getNombre() + " causando " + dano + " de dano.");
        p.defender(dano);
    }

    public void atacar(Personaje p, boolean modoFuria) {
        if (modoFuria == true) {
            int danoFuria = this.ataqueBase * 2;
            System.out.println(this.nombre + " ataca con MODO FURIA a " + p.getNombre() + " causando " + danoFuria + " de dano!");
            p.defender(danoFuria);
        } else {
            atacar(p);
        }
    }

    @Override
    public void defender(int danoRecibido) {
        int danoFinal = danoRecibido - this.fuerzaEscudo;
        if (danoFinal < 0) {
            danoFinal = 0;
        }
        System.out.println(this.nombre + " bloquea con su escudo y mitiga " + this.fuerzaEscudo + " de dano. Dano final: " + danoFinal);
        recibirDano(danoFinal);
    }

    public int getFuerzaEscudo() {
        return this.fuerzaEscudo;
    }

    public void setFuerzaEscudo(int fuerzaEscudo) {
        this.fuerzaEscudo = fuerzaEscudo;
    }
}
