package org.example;

/*
 * PREGUNTAS DE ANÁLISIS:
 * 1. Si se llega a instanciar la clase mediante Vehiculo v = new Vehiculo(200, true); y luego se invoca
 * v.imprimirEstado();, ¿qué valores se proyectarán en la consola y por qué?
 * RTA: Imprime 0 y false, porque los parametros se reasignaron a si mismos en lugar de asignarse a los atributos de la clase.
 *
 * 2. Explique el fenómeno de sombreado de variables (variable shadowing) y cómo afecta la
 * asignación cuando el nombre del parámetro coincide con el del atributo.
 * RTA: Ocurre cuando el parametro oculta al atributo por llamarse igual. Sin el "this", cualquier mencion al nombre hace referencia a la variable local/parametro y no al atributo.
 *
 * 3. Reescriba el bloque del constructor corrigiendo el error mediante la utilización de la palabra clave this.
 * RTA:
 * this.velocidadMaxima = velocidadMaxima;
 * this.encendido = encendido;
 */
public class ErrorShadowing {
    private int velocidadMaxima;
    private boolean encendido;

    public ErrorShadowing(int velocidadMaxima, boolean encendido) {
        this.velocidadMaxima = velocidadMaxima;
        this.encendido = encendido;
    }

    public void imprimirEstado() {
        System.out.printf("Velocidad Maxima: %d | Encendido: %b%n", velocidadMaxima, encendido);
    }
}
