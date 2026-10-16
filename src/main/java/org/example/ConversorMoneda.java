package org.example;

public class ConversorMoneda {

    private ConversorMoneda() {
    }

    public static double convertirDolaresAEuros(double montoDolares, double tasaCambio) {
        if (montoDolares <= 0 || tasaCambio <= 0) {
            return 0.0;
        } else {
            double total = montoDolares * tasaCambio;
            return total;
        }
    }

    public static String formatearMoneda(double monto) {
        String texto = String.format("$%.2f", monto);
        return texto;
    }
}
