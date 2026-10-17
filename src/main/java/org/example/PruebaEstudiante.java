package org.example;

/*
 * PREGUNTAS DE ANÁLISIS:
 * 1. ¿Qué error indica el compilador de Java o qué excepción se lanza en tiempo de ejecución al
 * intentar compilar/ejecutar el código anterior?
 * RTA: Da error de compilacion porque la variable local no fue inicializada ("variable estudiante1 might not have been initialized"). Si fuera atributo con valor null, lanzaria NullPointerException en ejecucion.
 *
 * 2. ¿Por qué la variable estudiante1 no puede invocar el método de instancia
 * obtenerReporteAcademicos() en su estado actual?
 * RTA: Porque no apunta a ningun objeto en memoria, no ha sido instanciada con new.
 *
 * 3. Indique la instrucción exacta de código que debe agregarse para instanciar correctamente el
 * objeto estudiante1 mediante el operador new antes de invocar el método.
 * RTA: estudiante1 = new Estudiante("E101", "Carlos Perez", 4.5, true);
 */
public class PruebaEstudiante {
    public static void main(String[] args) {
        Estudiante estudiante1 = new Estudiante("E101", "Carlos Perez", 4.50, true);
        String reporte = estudiante1.obtenerReporteAcademicos();
        System.out.println(reporte);
    }
}
