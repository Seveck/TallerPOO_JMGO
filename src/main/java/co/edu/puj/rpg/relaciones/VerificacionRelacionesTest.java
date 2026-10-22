package co.edu.puj.rpg.relaciones;

public class VerificacionRelacionesTest {

    public static void main(String[] args) {
        System.out.println("=================================================");
        System.out.println(" INICIANDO BATERÍA DE PRUEBAS RIGUROSAS (UNIT 2.5)");
        System.out.println("=================================================\n");

        int pruebasPasadas = 0;
        int totalPruebas = 0;

        // PRUEBA 1: Composición (Corazón RPG)
        totalPruebas++;
        System.out.println("[TEST 1] Verificando Composición (Personaje *-- CorazonRPG)...");
        Personaje p1 = new Personaje("Aragorn", 5);
        if (p1.getCorazon() != null &&
            p1.getCorazon().getSaludBase() == 100 &&
            p1.getCorazon().getManaBase() == 50) {
            System.out.println("  -> PASÓ: El corazón se crea internamente dentro del constructor con valores correctos.");
            pruebasPasadas++;
        } else {
            System.out.println("  -> FALLÓ: El corazón no se instanció correctamente.");
        }

        // PRUEBA 2: Asociación Unidireccional & Ataque Total sin Arma (Prevención NullPointerException)
        totalPruebas++;
        System.out.println("\n[TEST 2] Verificando Asociación Unidireccional (Ataque base sin arma)...");
        if (p1.getArmaEquipada() == null && p1.calcularAtaqueTotal() == 25) {
            System.out.println("  -> PASÓ: Ataque sin arma es nivel * 5 (5 * 5 = 25). No hubo NullPointerException.");
            pruebasPasadas++;
        } else {
            System.out.println("  -> FALLÓ: Cálculo incorrecto para personaje desarmado.");
        }

        // PRUEBA 3: Desequipar sin tener arma (Control de Nulos)
        totalPruebas++;
        System.out.println("\n[TEST 3] Verificando desequiparArma() con arma == null...");
        try {
            p1.desequiparArma();
            System.out.println("  -> PASÓ: El método gestionó correctamente la referencia null sin lanzar excepción.");
            pruebasPasadas++;
        } catch (Exception e) {
            System.out.println("  -> FALLÓ: Lanzó excepción: " + e.getMessage());
        }

        // PRUEBA 4: Equipar Arma y Recalcular Ataque
        totalPruebas++;
        System.out.println("\n[TEST 4] Verificando equipamiento de Arma (Unidireccional)...");
        Arma anduril = new Arma("Andúril", 35);
        p1.equiparArma(anduril);
        if (p1.getArmaEquipada() != null && p1.calcularAtaqueTotal() == 60) {
            System.out.println("  -> PASÓ: Arma equipada y ataque total recalculado (25 + 35 = 60).");
            pruebasPasadas++;
        } else {
            System.out.println("  -> FALLÓ: Ataque calculado incorrecto: " + p1.calcularAtaqueTotal());
        }

        // PRUEBA 5: Desequipar Arma existente
        totalPruebas++;
        System.out.println("\n[TEST 5] Verificando desequiparArma() con arma equipada...");
        p1.desequiparArma();
        if (p1.getArmaEquipada() == null && p1.calcularAtaqueTotal() == 25) {
            System.out.println("  -> PASÓ: Arma desequipada a null y ataque regresa a base.");
            pruebasPasadas++;
        } else {
            System.out.println("  -> FALLÓ: El arma no quedó en null tras desequipar.");
        }

        // PRUEBA 6: Asociación Bidireccional Cruzada (Personaje -> Gremio)
        totalPruebas++;
        System.out.println("\n[TEST 6] Verificando Asociación Bidireccional (p.unirseAGremio)...");
        Gremio gremioA = new Gremio("Compañía del Anillo");
        p1.unirseAGremio(gremioA);
        if (p1.getGremio() == gremioA && gremioA.getMiembros().contains(p1)) {
            System.out.println("  -> PASÓ: Sincronización cruzada exitosa. Ambos se reconocen.");
            pruebasPasadas++;
        } else {
            System.out.println("  -> FALLÓ: Falla en consistencia mutua.");
        }

        // PRUEBA 7: Asociación Bidireccional Cruzada Inversa (Gremio -> Personaje)
        totalPruebas++;
        System.out.println("\n[TEST 7] Verificando Asociación Bidireccional Inversa (gremio.agregarMiembro)...");
        Personaje p2 = new Personaje("Legolas", 6);
        gremioA.agregarMiembro(p2);
        if (p2.getGremio() == gremioA && gremioA.getMiembros().contains(p2)) {
            System.out.println("  -> PASÓ: Gremio agregó a Legolas y Legolas reconoce al gremio.");
            pruebasPasadas++;
        } else {
            System.out.println("  -> FALLÓ: Falla en consistencia mutua inversa.");
        }

        // PRUEBA 8: Cambio de Gremio (Desvinculación del anterior y vinculación al nuevo)
        totalPruebas++;
        System.out.println("\n[TEST 8] Verificando cambio de gremio...");
        Gremio gremioB = new Gremio("Guardianes de Gondor");
        p1.unirseAGremio(gremioB);
        if (p1.getGremio() == gremioB &&
            gremioB.getMiembros().contains(p1) &&
            !gremioA.getMiembros().contains(p1)) {
            System.out.println("  -> PASÓ: Aragorn fue removido de Compañía del Anillo e ingresó a Gondor.");
            pruebasPasadas++;
        } else {
            System.out.println("  -> FALLÓ: Desincronización al transferir de gremio.");
        }

        // PRUEBA 9: Agregación Débil (Escuadrón -> Personaje)
        totalPruebas++;
        System.out.println("\n[TEST 9] Verificando Agregación (Escuadron o-- Personaje)...");
        Escuadron escuadron1 = new Escuadron("Montaraces");
        escuadron1.reclutarPersonaje(p1);
        if (escuadron1.getMiembros().contains(p1)) {
            System.out.println("  -> PASÓ: Personaje reclutado exitosamente al escuadrón.");
            pruebasPasadas++;
        } else {
            System.out.println("  -> FALLÓ: El escuadrón no tiene al personaje.");
        }

        // PRUEBA 10: Independencia del ciclo de vida en Agregación (Expulsar / Disolver)
        totalPruebas++;
        System.out.println("\n[TEST 10] Verificando independencia de ciclo de vida en Agregación...");
        escuadron1.expulsarPersonaje(p1);
        escuadron1 = null; // Simula disolución completa del escuadrón
        if (p1 != null && p1.getNombre().equals("Aragorn") && p1.getCorazon() != null) {
            System.out.println("  -> PASÓ: El escuadrón se disolvió pero la instancia de Aragorn y su corazón siguen intactos en memoria.");
            pruebasPasadas++;
        } else {
            System.out.println("  -> FALLÓ: El estado del personaje se vio alterado.");
        }

        // PRUEBA 11: Prevención de StackOverflowError en toString()
        totalPruebas++;
        System.out.println("\n[TEST 11] Verificando prevención de StackOverflowError en toString()...");
        try {
            String strPersonaje = p1.toString();
            String strGremio = gremioB.toString();
            if (strPersonaje.length() > 0 && strGremio.length() > 0) {
                System.out.println("  -> PASÓ: toString() ejecutado sin recursión infinita.");
                pruebasPasadas++;
            }
        } catch (StackOverflowError e) {
            System.out.println("  -> FALLÓ: Se produjo StackOverflowError por ciclo de llamadas cruzadas.");
        }

        // RESUMEN FINAL
        System.out.println("\n=================================================");
        System.out.println(" RESULTADO DE LAS PRUEBAS: " + pruebasPasadas + "/" + totalPruebas + " PASADAS");
        System.out.println("=================================================");
        if (pruebasPasadas == totalPruebas) {
            System.out.println("¡TODO EL SISTEMA OPERA CON TOTAL ROBUSTEZ Y PRECISIÓN!");
        } else {
            System.out.println("Hubo pruebas fallidas que requieren revisión.");
        }
    }
}
