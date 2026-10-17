package org.example;

/*
 * PREGUNTAS DE ANÁLISIS:
 * 1. ¿Cuál es el mensaje de error emitido por el compilador de Java en la línea donde se invoca
 * encenderMotor()?
 * RTA: "non-static method encenderMotor() cannot be referenced from a static context".
 *
 * 2. Explique desde la perspectiva de la POO por qué un método con el modificador static (como
 * main) no puede invocar directamente un método de instancia que requiere un estado de objeto.
 * RTA: Porque un metodo static pertenece a la clase y no opera sobre una instancia en particular, mientras que un metodo de instancia necesita un objeto con su propio estado en memoria.
 *
 * 3. Describa las dos alternativas de corrección válidas para que el código compile y ejecute
 * correctamente el método.
 * RTA:
 * 1. Crear una instancia de la clase: GestorVehiculos g = new GestorVehiculos(); g.encenderMotor();
 * 2. Cambiar el metodo encenderMotor para que sea static: public static void encenderMotor()
 */
public class GestorVehiculos {

    public void encenderMotor() {
        System.out.println("El motor del vehiculo ha sido encendido.");
    }

    public static void main(String[] args) {
        GestorVehiculos gestor = new GestorVehiculos();
        gestor.encenderMotor();
    }
}
