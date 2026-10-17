package co.edu.puj.rpg.principal;

import co.edu.puj.rpg.modelo.Personaje;
import co.edu.puj.rpg.modelo.enemigo.Enemigo;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner consola = new Scanner(System.in);

        Personaje heroe = new Personaje("Aragorn", 100, 20, 50.0);
        Enemigo enemigo = new Enemigo("Orco", 80, 15);

        int pociones = 1;
        int experiencia = 0;
        int umbralNivel = 100;
        boolean combateActivo = true;

        System.out.println("¡Comienza la batalla en la Arena de Heroes!");

        do {
            System.out.printf("%n=== ESTADO DEL COMBATE ===%n");
            System.out.printf("Heroe: %s | Vida: %d/%d | Ataque: %d | Nivel: %d | Oro: %.2f%n",
                    heroe.getNombre(), heroe.getPuntosVida(), heroe.getVidaMaxima(),
                    heroe.getPuntosAtaque(), heroe.getNivel(), heroe.getOro());
            System.out.printf("Enemigo: %s | Vida: %d/%d | Ataque: %d%n",
                    enemigo.getNombre(), enemigo.getPuntosVida(), enemigo.getVidaMaxima(),
                    enemigo.getPuntosAtaque());

            System.out.println("\n--- MENU DE ACCIONES ---");
            System.out.println("1. Atacar");
            System.out.println("2. Usar Pocion (" + pociones + " disponibles)");
            System.out.println("3. Ver Estado");
            System.out.println("4. Huir");
            System.out.println("5. Tienda");
            System.out.print("Seleccione una opcion: ");

            int opcion = consola.nextInt();

            if (opcion < 1 || opcion > 5) {
                System.out.println("Opcion invalida. Intente de nuevo.");
            } else {
                StringBuilder historialRonda = new StringBuilder();

                switch (opcion) {
                    case 1:
                        int vidaAntesEnemigo = enemigo.getPuntosVida();
                        heroe.atacar(enemigo);
                        int danoCausado = vidaAntesEnemigo - enemigo.getPuntosVida();
                        historialRonda.append(heroe.getNombre()).append(" ataco a ")
                                .append(enemigo.getNombre()).append(" causando ")
                                .append(danoCausado).append(" de dano.\n");

                        if (enemigo.isEstaVivo()) {
                            int vidaAntesHeroe = heroe.getPuntosVida();
                            enemigo.atacar(heroe);
                            int danoRecibido = vidaAntesHeroe - heroe.getPuntosVida();
                            historialRonda.append(enemigo.getNombre()).append(" contraataco causando ")
                                    .append(danoRecibido).append(" de dano.\n");
                        } else {
                            historialRonda.append(enemigo.getNombre()).append(" ha sido derrotado.\n");
                        }
                        break;

                    case 2:
                        if (pociones > 0) {
                            pociones = pociones - 1;
                            int vidaAntes = heroe.getPuntosVida();
                            heroe.usarPocion(30);
                            int curado = heroe.getPuntosVida() - vidaAntes;
                            historialRonda.append(heroe.getNombre()).append(" uso una pocion y recupero ")
                                    .append(curado).append(" puntos de vida.\n");

                            if (enemigo.isEstaVivo()) {
                                int vidaAntesHeroe = heroe.getPuntosVida();
                                enemigo.atacar(heroe);
                                int danoRecibido = vidaAntesHeroe - heroe.getPuntosVida();
                                historialRonda.append(enemigo.getNombre()).append(" aprovecho y ataco causando ")
                                        .append(danoRecibido).append(" de dano.\n");
                            }
                        } else {
                            System.out.println("No tienes pociones disponibles. Puedes comprar en la tienda.");
                        }
                        break;

                    case 3:
                        System.out.printf("%n=== DETALLE DE ESTADO ===%n");
                        System.out.printf("Nombre: %s%n", heroe.getNombre());
                        System.out.printf("Vida actual: %d / %d%n", heroe.getPuntosVida(), heroe.getVidaMaxima());
                        System.out.printf("Puntos de ataque: %d%n", heroe.getPuntosAtaque());
                        System.out.printf("Nivel: %d%n", heroe.getNivel());
                        System.out.printf("Oro: %.2f%n", heroe.getOro());
                        System.out.printf("Esta vivo: %s%n", heroe.isEstaVivo() ? "Si" : "No");
                        System.out.printf("Pociones restantes: %d%n", pociones);
                        break;

                    case 4:
                        System.out.println("Has huido del combate.");
                        combateActivo = false;
                        break;

                    case 5:
                        boolean enTienda = true;
                        while (enTienda) {
                            System.out.println("\n--- TIENDA DE ARTICULOS ---");
                            System.out.printf("Oro disponible: %.2f%n", heroe.getOro());
                            System.out.println("1. Comprar Pocion Grande de Salud (Costo: 20.00 de oro)");
                            System.out.println("2. Volver al combate");
                            System.out.print("Opcion: ");
                            int opcionTienda = consola.nextInt();

                            if (opcionTienda == 1) {
                                double saldoAnterior = heroe.getOro();
                                heroe.setOro(saldoAnterior - 20.0);
                                if (heroe.getOro() < saldoAnterior) {
                                    pociones = pociones + 1;
                                    System.out.println("Compra exitosa. Obtuviste una pocion grande.");
                                }
                            } else if (opcionTienda == 2) {
                                enTienda = false;
                            } else {
                                System.out.println("Opcion invalida en la tienda.");
                            }
                        }
                        break;
                }

                if (historialRonda.length() > 0) {
                    System.out.print(historialRonda.toString());
                }
            }

            if (!enemigo.isEstaVivo()) {
                System.out.println("\n¡Victoria! Has derrotado a " + enemigo.getNombre() + ".");
                experiencia = experiencia + 100;
                System.out.println("Ganaste 100 puntos de experiencia.");
                if (experiencia >= umbralNivel) {
                    heroe.setNivel(heroe.getNivel() + 1);
                    System.out.println("¡Subiste al nivel " + heroe.getNivel() + "!");
                }
            } else if (!heroe.isEstaVivo()) {
                System.out.println("\nHas sido derrotado en combate...");
            }

        } while (combateActivo && heroe.isEstaVivo() && enemigo.isEstaVivo());
    }
}
