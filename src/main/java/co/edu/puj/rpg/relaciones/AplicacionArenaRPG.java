package co.edu.puj.rpg.relaciones;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AplicacionArenaRPG {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        // Colecciones globales para almacenar los datos de la arena
        List<Personaje> listaPersonajes = new ArrayList<>();
        List<Gremio> listaGremios = new ArrayList<>();
        List<Escuadron> listaEscuadrones = new ArrayList<>();

        // Creamos algunos datos iniciales para probar más rápido si el usuario lo desea
        Gremio gremioValientes = new Gremio("Los Cruzados de Jade");
        listaGremios.add(gremioValientes);

        Escuadron escuadronAlfa = new Escuadron("Escuadron Vanguardia");
        listaEscuadrones.add(escuadronAlfa);

        do {
            System.out.println("");
            System.out.println("=== MENÚ ARENA DE HÉROES RPG ===");
            System.out.println("1. Crear Personaje");
            System.out.println("2. Crear Gremio");
            System.out.println("3. Reclutar Personaje a Escuadrón");
            System.out.println("4. Equipar Arma a Personaje");
            System.out.println("5. Mostrar Estado Detallado (Personaje y Corazón RPG)");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");

            // Validamos que el usuario ingrese un numero entero
            if (scanner.hasNextInt() == false) {
                System.out.println("Por favor ingrese un numero valido.");
                scanner.next(); // Limpiamos la entrada erronea
                continue;
            }

            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiamos el salto de linea pendiente

            switch (opcion) {
                case 1:
                    // Caso 1: Crear nuevo Personaje
                    System.out.print("Ingrese el nombre del personaje: ");
                    String nombreHeroe = scanner.nextLine();

                    System.out.print("Ingrese el nivel del personaje: ");
                    int nivelHeroe = 1;
                    if (scanner.hasNextInt() == true) {
                        nivelHeroe = scanner.nextInt();
                        scanner.nextLine();
                    } else {
                        scanner.nextLine();
                    }

                    // Al hacer new Personaje, se aplica la COMPOSICIÓN creando su CorazonRPG internamente
                    Personaje nuevoP = new Personaje(nombreHeroe, nivelHeroe);
                    listaPersonajes.add(nuevoP);

                    System.out.printf("Personaje '%s' creado con exito (Nivel: %d). Se le ha creado un CorazonRPG propio.%n",
                            nuevoP.getNombre(), nuevoP.getNivel());
                    break;

                case 2:
                    // Caso 2: Crear nuevo Gremio
                    System.out.print("Ingrese el nombre del nuevo Gremio: ");
                    String nombreGremio = scanner.nextLine();

                    Gremio nuevoG = new Gremio(nombreGremio);
                    listaGremios.add(nuevoG);

                    System.out.printf("Gremio '%s' registrado exitosamente.%n", nuevoG.getNombre());

                    // Si hay personajes, damos opcion de unir uno para probar la Asociación Bidireccional
                    if (listaPersonajes.isEmpty() == false) {
                        System.out.print("¿Desea afiliar un personaje a este gremio ahora? (s/n): ");
                        String afiliar = scanner.nextLine();
                        if (afiliar.equalsIgnoreCase("s") == true) {
                            System.out.println("Seleccione el personaje a afiliar:");
                            for (int i = 0; i < listaPersonajes.size(); i = i + 1) {
                                System.out.printf("[%d] %s%n", i + 1, listaPersonajes.get(i).getNombre());
                            }
                            System.out.print("Numero de personaje: ");
                            int numP = scanner.nextInt();
                            scanner.nextLine();

                            if (numP >= 1 && numP <= listaPersonajes.size()) {
                                Personaje pSeleccionado = listaPersonajes.get(numP - 1);
                                // Sincronizacion bidireccional
                                pSeleccionado.unirseAGremio(nuevoG);
                                System.out.printf("Sincronizacion bidireccional exitosa: %s ahora pertenece a '%s'.%n",
                                        pSeleccionado.getNombre(), nuevoG.getNombre());
                            }
                        }
                    }
                    break;

                case 3:
                    // Caso 3: Reclutar Personaje a Escuadrón (Agregación)
                    if (listaPersonajes.isEmpty() == true) {
                        System.out.println("No hay personajes registrados en la arena. Cree uno primero en la opcion 1.");
                        break;
                    }

                    if (listaEscuadrones.isEmpty() == true) {
                        System.out.print("No existen escuadrones. Ingrese el nombre para crear uno: ");
                        String nombreNuevoEscuadron = scanner.nextLine();
                        listaEscuadrones.add(new Escuadron(nombreNuevoEscuadron));
                    }

                    System.out.println("--- Seleccione el Escuadron de destino ---");
                    for (int i = 0; i < listaEscuadrones.size(); i = i + 1) {
                        System.out.printf("[%d] %s%n", i + 1, listaEscuadrones.get(i).getNombre());
                    }
                    System.out.print("Numero de escuadron: ");
                    int idxEsc = scanner.nextInt();
                    scanner.nextLine();

                    if (idxEsc < 1 || idxEsc > listaEscuadrones.size()) {
                        System.out.println("Indice de escuadron no valido.");
                        break;
                    }
                    Escuadron escuadronDestino = listaEscuadrones.get(idxEsc - 1);

                    System.out.println("--- Seleccione el Personaje a reclutar ---");
                    for (int i = 0; i < listaPersonajes.size(); i = i + 1) {
                        System.out.printf("[%d] %s%n", i + 1, listaPersonajes.get(i).getNombre());
                    }
                    System.out.print("Numero de personaje: ");
                    int idxPer = scanner.nextInt();
                    scanner.nextLine();

                    if (idxPer < 1 || idxPer > listaPersonajes.size()) {
                        System.out.println("Indice de personaje no valido.");
                        break;
                    }

                    Personaje personajeAReclutar = listaPersonajes.get(idxPer - 1);
                    // Agregación débil: el escuadrón agrupa al personaje existente
                    escuadronDestino.reclutarPersonaje(personajeAReclutar);
                    break;

                case 4:
                    // Caso 4: Equipar Arma a Personaje (Asociación Unidireccional)
                    if (listaPersonajes.isEmpty() == true) {
                        System.out.println("No hay personajes disponibles. Cree un personaje primero.");
                        break;
                    }

                    System.out.println("--- Seleccione el Personaje a equipar ---");
                    for (int i = 0; i < listaPersonajes.size(); i = i + 1) {
                        Personaje p = listaPersonajes.get(i);
                        String tieneArma = (p.getArmaEquipada() != null) ? p.getArmaEquipada().getNombre() : "Desarmado";
                        System.out.printf("[%d] %s (Arma actual: %s)%n", i + 1, p.getNombre(), tieneArma);
                    }
                    System.out.print("Numero de personaje: ");
                    int idxP = scanner.nextInt();
                    scanner.nextLine();

                    if (idxP < 1 || idxP > listaPersonajes.size()) {
                        System.out.println("Opcion fuera de rango.");
                        break;
                    }

                    Personaje personajeElegido = listaPersonajes.get(idxP - 1);

                    System.out.print("Ingrese el nombre del arma a equipar: ");
                    String nombreArma = scanner.nextLine();
                    System.out.print("Ingrese el daño base del arma: ");
                    int danioArma = 15;
                    if (scanner.hasNextInt() == true) {
                        danioArma = scanner.nextInt();
                        scanner.nextLine();
                    } else {
                        scanner.nextLine();
                    }

                    Arma nuevaArma = new Arma(nombreArma, danioArma);
                    // Asociación Unidireccional: Personaje conoce el Arma
                    personajeElegido.equiparArma(nuevaArma);
                    System.out.printf("Nuevo ataque total calculado para %s: %d puntos.%n",
                            personajeElegido.getNombre(), personajeElegido.calcularAtaqueTotal());
                    break;

                case 5:
                    // Caso 5: Mostrar Estado Detallado con StringBuilder y System.out.printf
                    if (listaPersonajes.isEmpty() == true) {
                        System.out.println("No hay personajes para mostrar el reporte.");
                        break;
                    }

                    StringBuilder reporte = new StringBuilder();
                    reporte.append("\n================ REPORTE DETALLADO DE LA ARENA RPG ================\n");

                    for (int i = 0; i < listaPersonajes.size(); i = i + 1) {
                        Personaje p = listaPersonajes.get(i);
                        CorazonRPG c = p.getCorazon();

                        String armaStr = "Desarmado";
                        if (p.getArmaEquipada() != null) {
                            armaStr = p.getArmaEquipada().getNombre() + " (+" + p.getArmaEquipada().getDanioBase() + ")";
                        }

                        String gremioStr = "Sin Gremio";
                        if (p.getGremio() != null) {
                            gremioStr = p.getGremio().getNombre();
                        }

                        String linea = String.format("[%d] %s | Nivel: %d | Ataque Total: %d | Salud: %d | Maná: %d | Arma: %s | Gremio: %s%n",
                                i + 1,
                                p.getNombre(),
                                p.getNivel(),
                                p.calcularAtaqueTotal(),
                                c.getSaludBase(),
                                c.getManaBase(),
                                armaStr,
                                gremioStr);

                        reporte.append(linea);
                    }

                    reporte.append("--------------------------------------------------------------------\n");
                    reporte.append("=== ESTADO DE ESCUADRONES (AGREGACIÓN) ===\n");
                    for (int i = 0; i < listaEscuadrones.size(); i = i + 1) {
                        Escuadron esc = listaEscuadrones.get(i);
                        reporte.append(esc.toString()).append("\n");
                    }

                    reporte.append("=== ESTADO DE GREMIOS (ASOCIACIÓN BIDIRECCIONAL) ===\n");
                    for (int i = 0; i < listaGremios.size(); i = i + 1) {
                        Gremio g = listaGremios.get(i);
                        reporte.append(g.toString()).append("\n");
                    }
                    reporte.append("====================================================================\n");

                    System.out.printf("%s", reporte.toString());
                    break;

                case 6:
                    System.out.println("Cerrando la Arena de Héroes...");
                    break;

                default:
                    System.out.println("Opción inválida.");
                    break;
            }

        } while (opcion != 6);
    }
}
