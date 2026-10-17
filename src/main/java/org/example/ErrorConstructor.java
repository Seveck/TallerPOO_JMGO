package org.example;

/*
 * PREGUNTAS DE ANÁLISIS:
 * 1. ¿Por qué la inclusión de la palabra clave void altera la naturaleza del método y hace que Java
 * deje de reconocerlo como un constructor?
 * RTA: Porque los constructores no tienen tipo de retorno. Al ponerle void, Java lo interpreta como un metodo normal y no como un constructor.
 *
 * 2. Si desde otra clase se intenta ejecutar CuentaBancaria cuenta = new CuentaBancaria("A101", 500.0);,
 * ¿por qué la compilación reportará que no se encuentra un constructor adecuado?
 * RTA: Porque al convertirlo en metodo con void, la clase se queda sin ese constructor parametrizado y Java no encuentra ninguno que reciba esos parametros.
 *
 * 3. Escriba la firma y declaración correcta del constructor omitiendo el tipo de retorno.
 * RTA:
 * public CuentaBancaria(String numeroCuenta, double saldoInicial) {
 *     this.numeroCuenta = numeroCuenta;
 *     this.saldo = saldoInicial;
 * }
 */
public class ErrorConstructor {
    private String numeroCuenta;
    private double saldo;

    public ErrorConstructor(String numeroCuenta, double saldoInicial) {
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldoInicial;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public double getSaldo() {
        return saldo;
    }
}
