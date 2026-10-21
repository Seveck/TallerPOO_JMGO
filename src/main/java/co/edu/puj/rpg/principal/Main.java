package co.edu.puj.rpg.principal;

import co.edu.puj.rpg.modelo.Guerrero;
import co.edu.puj.rpg.modelo.Inventario;
import co.edu.puj.rpg.modelo.Mago;
import co.edu.puj.rpg.modelo.Personaje;
import co.edu.puj.rpg.modelo.Pocion;
import co.edu.puj.rpg.modelo.enemigo.Enemigo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner consola = new Scanner(System.in);

        char[][] tablero = new char[5][5];
        for (int i = 0; i < 5; i = i + 1) {
            for (int j = 0; j < 5; j = j + 1) {
                tablero[i][j] = '.';
            }
        }

        Guerrero thorin = new Guerrero("Thorin", 120, 15, 0, 0, 10);
        Mago gandalf = new Mago("Gandalf", 80, 25, 1, 1, 50);

        List<Personaje> escuadron = new ArrayList<>();
        escuadron.add(thorin);
        escuadron.add(gandalf);

        List<Personaje> enemigos = new ArrayList<>();
        enemigos.add(new Enemigo("Goblin 1", 50, 10, 0, 4));
        enemigos.add(new Enemigo("Orco", 70, 15, 4, 0));
        enemigos.add(new Enemigo("Goblin 2", 50, 10, 4, 4));

        tablero[0][0] = 'G';
        tablero[1][1] = 'M';
        tablero[0][4] = 'E';
        tablero[4][0] = 'E';
        tablero[4][4] = 'E';
        tablero[2][2] = 'C';

        Inventario<Pocion> inventario = new Inventario<>();
        inventario.agregar(new Pocion("Pocion de Vida Menor", 30));
        inventario.agregar(new Pocion("Pocion de Vida Mayor", 50));

        int experiencia = 0;
        int umbralNivel = 100;
        boolean cofreAlcanzado = false;
        boolean combateActivo = true;

        System.out.println("¡Comienza la batalla en la Arena de Heroes con POO Avanzada!");

        do {
            System.out.println("\n--- TABLERO DE BATALLA ---");
            mostrarTablero(tablero);

            System.out.printf("%n=== ESTADO DEL ESCUADRON ===%n");
            for (int i = 0; i < escuadron.size(); i = i + 1) {
                Personaje heroe = escuadron.get(i);
                System.out.printf("Heroe: %s | Vida: %d/%d | Ataque: %d | Nivel: %d | Oro: %.2f | Posicion: [%d][%d]",
                        heroe.getNombre(), heroe.getVida(), heroe.getVidaMaxima(),
                        heroe.getAtaqueBase(), heroe.getNivel(), heroe.getOro(),
                        heroe.getPosicionX(), heroe.getPosicionY());
                if (heroe instanceof Mago) {
                    Mago m = (Mago) heroe;
                    System.out.printf(" | Mana: %d", m.getPuntosMana());
                }
                if (heroe instanceof Guerrero) {
                    Guerrero g = (Guerrero) heroe;
                    System.out.printf(" | Escudo: %d", g.getFuerzaEscudo());
                }
                System.out.println();
            }

            System.out.printf("%n=== ESTADO DE ENEMIGOS ===%n");
            for (int i = 0; i < enemigos.size(); i = i + 1) {
                Personaje enemigo = enemigos.get(i);
                System.out.printf("Enemigo: %s | Vida: %d/%d | Ataque: %d | Posicion: [%d][%d]%n",
                        enemigo.getNombre(), enemigo.getVida(), enemigo.getVidaMaxima(),
                        enemigo.getAtaqueBase(), enemigo.getPosicionX(), enemigo.getPosicionY());
            }

            for (Personaje heroeActual : escuadron) {
                if (heroeActual.estaVivo() == true && combateActivo == true) {
                    System.out.println("\n--- MENU DE ACCIONES: " + heroeActual.getNombre() + " ---");
                    System.out.println("1. Atacar");
                    System.out.println("2. Usar Inventario (" + inventario.getItems().size() + " disponibles)");
                    System.out.println("3. Mover en Tablero");
                    System.out.print("Seleccione una opcion: ");
                    int opcion = consola.nextInt();

                    switch (opcion) {
                        case 1:
                            if (enemigos.isEmpty() == true) {
                                System.out.println("Ya no quedan enemigos activos para atacar!");
                            } else {
                                System.out.println("Seleccione un enemigo para atacar:");
                                for (int e = 0; e < enemigos.size(); e = e + 1) {
                                    System.out.println((e + 1) + ". " + enemigos.get(e).getNombre() + " (Vida: " + enemigos.get(e).getVida() + ")");
                                }
                                System.out.print("Opcion de objetivo: ");
                                int opcObjetivo = consola.nextInt();
                                int indiceEnemigo = opcObjetivo - 1;

                                if (indiceEnemigo >= 0 && indiceEnemigo < enemigos.size()) {
                                    Personaje enemigoSeleccionado = enemigos.get(indiceEnemigo);
                                    heroeActual.realizarAtaqueEspecial(enemigoSeleccionado);
                                } else {
                                    System.out.println("Indice invalido! Pierdes el turno de ataque.");
                                }
                            }
                            break;

                        case 2:
                            if (inventario.getItems().size() > 0) {
                                System.out.println("Pociones disponibles en el inventario:");
                                for (int p = 0; p < inventario.getItems().size(); p = p + 1) {
                                    Pocion poc = inventario.obtener(p);
                                    System.out.println((p + 1) + ". " + poc.getNombre() + " (Cura +" + poc.getPuntosCuracion() + ")");
                                }
                                System.out.print("Seleccione que pocion usar: ");
                                int opcPocion = consola.nextInt();
                                int indicePocion = opcPocion - 1;

                                if (indicePocion >= 0 && indicePocion < inventario.getItems().size()) {
                                    Pocion pocionElegida = inventario.remover(indicePocion);
                                    int vidaAntes = heroeActual.getVida();
                                    heroeActual.usarPocion(pocionElegida.getPuntosCuracion());
                                    int curado = heroeActual.getVida() - vidaAntes;
                                    System.out.println(heroeActual.getNombre() + " uso " + pocionElegida.getNombre() + " y recupero " + curado + " puntos de vida! Vida actual: " + heroeActual.getVida() + "/" + heroeActual.getVidaMaxima());
                                } else {
                                    System.out.println("Opcion invalida en el inventario.");
                                }
                            } else {
                                System.out.println("El inventario esta vacio!");
                            }
                            break;

                        case 3:
                            System.out.println("Seleccione la direccion a mover:");
                            System.out.println("1. Arriba (Fila - 1)");
                            System.out.println("2. Abajo (Fila + 1)");
                            System.out.println("3. Izquierda (Columna - 1)");
                            System.out.println("4. Derecha (Columna + 1)");
                            System.out.print("Direccion: ");
                            int direccion = consola.nextInt();

                            int nuevaFila = heroeActual.getPosicionX();
                            int nuevaColumna = heroeActual.getPosicionY();

                            if (direccion == 1) {
                                nuevaFila = nuevaFila - 1;
                            } else if (direccion == 2) {
                                nuevaFila = nuevaFila + 1;
                            } else if (direccion == 3) {
                                nuevaColumna = nuevaColumna - 1;
                            } else if (direccion == 4) {
                                nuevaColumna = nuevaColumna + 1;
                            } else {
                                System.out.println("Direccion desconocida!");
                            }

                            if (nuevaFila < 0 || nuevaFila >= 5 || nuevaColumna < 0 || nuevaColumna >= 5) {
                                System.out.println("Movimiento fuera del tablero! Te quedas en la misma posicion.");
                            } else {
                                if (nuevaFila == 2 && nuevaColumna == 2) {
                                    if (heroeActual == thorin) {
                                        cofreAlcanzado = true;
                                        System.out.println("¡" + thorin.getNombre() + " (Heroe Principal) ha alcanzado el Cofre del Tesoro [C]!");
                                        if (enemigos.isEmpty() == true) {
                                            combateActivo = false;
                                        }
                                    } else {
                                        System.out.println(heroeActual.getNombre() + " alcanzo el cofre pero solo el heroe principal puede abrirlo.");
                                    }
                                }

                                tablero[heroeActual.getPosicionX()][heroeActual.getPosicionY()] = '.';
                                heroeActual.setPosicionX(nuevaFila);
                                heroeActual.setPosicionY(nuevaColumna);

                                if (heroeActual instanceof Guerrero) {
                                    tablero[nuevaFila][nuevaColumna] = 'G';
                                } else if (heroeActual instanceof Mago) {
                                    tablero[nuevaFila][nuevaColumna] = 'M';
                                }
                                System.out.println(heroeActual.getNombre() + " se ha movido a [" + nuevaFila + "][" + nuevaColumna + "].");
                            }
                            break;

                        default:
                            System.out.println("Opcion no reconocida. Se pierde el turno.");
                            break;
                    }

                    if (combateActivo == false) {
                        break;
                    }
                }
            }

            if (enemigos.isEmpty() == false && escuadron.isEmpty() == false) {
                System.out.println("\n=============================");
                System.out.println("    TURNO DEL ENTE ENEMIGO   ");
                System.out.println("=============================");
                for (int i = 0; i < enemigos.size(); i = i + 1) {
                    if (escuadron.isEmpty() == false) {
                        Personaje enemigoActual = enemigos.get(i);
                        Personaje heroeObjetivo = escuadron.get(0);
                        System.out.println(enemigoActual.getNombre() + " ataca a " + heroeObjetivo.getNombre() + ":");
                        enemigoActual.realizarAtaqueEspecial(heroeObjetivo);
                    }
                }
            }

            for (int i = 0; i < escuadron.size(); i = i + 1) {
                Personaje p = escuadron.get(i);
                if (p.estaVivo() == false) {
                    tablero[p.getPosicionX()][p.getPosicionY()] = '.';
                    System.out.println("¡El heroe " + p.getNombre() + " ha caido en batalla!");
                }
            }
            for (int i = 0; i < enemigos.size(); i = i + 1) {
                Personaje e = enemigos.get(i);
                if (e.estaVivo() == false) {
                    tablero[e.getPosicionX()][e.getPosicionY()] = '.';
                    System.out.println("¡El enemigo " + e.getNombre() + " ha sido eliminado!");
                }
            }

            escuadron.removeIf(p -> !p.estaVivo());
            enemigos.removeIf(e -> !e.estaVivo());

            int vidaTotalEscuadron = 0;
            for (int i = 0; i < escuadron.size(); i = i + 1) {
                vidaTotalEscuadron = vidaTotalEscuadron + escuadron.get(i).getVida();
            }

            if (vidaTotalEscuadron <= 0 || escuadron.isEmpty() == true) {
                System.out.println("\n**************************************************");
                System.out.println("                 DERROTA TOTAL                    ");
                System.out.println("  Todo el escuadron de heroes ha sido derrotado.  ");
                System.out.println("**************************************************");
                combateActivo = false;
            } else if (enemigos.isEmpty() == true && cofreAlcanzado == true) {
                System.out.println("\n****************************************************************");
                System.out.println("                       VICTORIA TOTAL                           ");
                System.out.println(" ¡Todos los enemigos han sido eliminados y el cofre asegurado!  ");
                System.out.println("****************************************************************");
                experiencia = experiencia + 100;
                System.out.println("¡El escuadron gana 100 puntos de experiencia y 50 de oro!");
                for (int i = 0; i < escuadron.size(); i = i + 1) {
                    Personaje h = escuadron.get(i);
                    h.setOro(h.getOro() + 50.0);
                    if (experiencia >= umbralNivel) {
                        h.setNivel(h.getNivel() + 1);
                        System.out.println("¡" + h.getNombre() + " subio al nivel " + h.getNivel() + "!");
                    }
                }
                combateActivo = false;
            } else if (enemigos.isEmpty() == true && cofreAlcanzado == false) {
                System.out.println("\nTodos los enemigos fueron derrotados, pero debes moverte hasta el cofre [C] en [2][2] para ganar!");
            }

        } while (combateActivo == true);
    }

    public static void mostrarTablero(char[][] matriz) {
        for (int i = 0; i < matriz.length; i = i + 1) {
            for (int j = 0; j < matriz[i].length; j = j + 1) {
                System.out.print("[" + matriz[i][j] + "]");
            }
            System.out.println();
        }
    }
}
