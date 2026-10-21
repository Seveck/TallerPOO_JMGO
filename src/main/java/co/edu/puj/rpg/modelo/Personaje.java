package co.edu.puj.rpg.modelo;

public abstract class Personaje implements ICombatiente {

    protected String nombre;
    protected int vida;
    protected int vidaMaxima;
    protected int ataqueBase;
    protected int posicionX;
    protected int posicionY;
    protected int nivel;
    protected double oro;
    protected boolean estaVivo;

    public Personaje() {
        this.nivel = 1;
        this.oro = 0.0;
        this.estaVivo = true;
    }

    public Personaje(String nombre, int vida, int ataqueBase, int posicionX, int posicionY) {
        this.nombre = nombre;
        this.vida = vida;
        this.vidaMaxima = vida;
        this.ataqueBase = ataqueBase;
        this.posicionX = posicionX;
        this.posicionY = posicionY;
        this.nivel = 1;
        this.oro = 0.0;
        this.estaVivo = true;
    }

    public Personaje(String nombre, int vidaMaxima, int puntosAtaque, double oro) {
        this.nombre = nombre;
        this.vida = vidaMaxima;
        this.vidaMaxima = vidaMaxima;
        this.ataqueBase = puntosAtaque;
        this.oro = oro;
        this.posicionX = 0;
        this.posicionY = 0;
        this.nivel = 1;
        this.estaVivo = true;
    }

    public abstract void realizarAtaqueEspecial(Personaje objetivo);

    public boolean estaVivo() {
        if (this.vida > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isEstaVivo() {
        return estaVivo();
    }

    public void recibirDano(int cantidadDano) {
        this.vida = this.vida - cantidadDano;
        if (this.vida <= 0) {
            this.vida = 0;
            this.estaVivo = false;
        }
    }

    public void usarPocion(int cantidadRestaurada) {
        this.vida = this.vida + cantidadRestaurada;
        if (this.vida > this.vidaMaxima) {
            this.vida = this.vidaMaxima;
        }
        if (this.vida > 0) {
            this.estaVivo = true;
        }
    }

    public void curar(int cantidad) {
        usarPocion(cantidad);
    }

    @Override
    public void defender(int danoRecibido) {
        recibirDano(danoRecibido);
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getVida() {
        return this.vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
        if (this.vida <= 0) {
            this.vida = 0;
            this.estaVivo = false;
        } else {
            this.estaVivo = true;
        }
    }

    public int getPuntosVida() {
        return this.vida;
    }

    public void setPuntosVida(int vida) {
        setVida(vida);
    }

    public int getVidaMaxima() {
        return this.vidaMaxima;
    }

    public void setVidaMaxima(int vidaMaxima) {
        if (vidaMaxima > 0) {
            this.vidaMaxima = vidaMaxima;
        }
    }

    public int getAtaqueBase() {
        return this.ataqueBase;
    }

    public void setAtaqueBase(int ataqueBase) {
        if (ataqueBase > 0) {
            this.ataqueBase = ataqueBase;
        }
    }

    public int getPuntosAtaque() {
        return this.ataqueBase;
    }

    public void setPuntosAtaque(int puntosAtaque) {
        setAtaqueBase(puntosAtaque);
    }

    public int getPosicionX() {
        return this.posicionX;
    }

    public void setPosicionX(int posicionX) {
        this.posicionX = posicionX;
    }

    public int getPosicionY() {
        return this.posicionY;
    }

    public void setPosicionY(int posicionY) {
        this.posicionY = posicionY;
    }

    public int getNivel() {
        return this.nivel;
    }

    public void setNivel(int nivel) {
        if (nivel >= 1) {
            this.nivel = nivel;
        } else {
            System.out.println("Valor invalido!");
        }
    }

    public double getOro() {
        return this.oro;
    }

    public void setOro(double oro) {
        if (oro < 0.0) {
            System.out.println("Transaccion rechazada: saldo insuficiente.");
        } else {
            this.oro = oro;
        }
    }

    public void setEstaVivo(boolean estaVivo) {
        this.estaVivo = estaVivo;
    }
}
