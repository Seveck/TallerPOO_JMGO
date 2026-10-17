package org.example;

public class Principal {
    public static void main(String[] args) {
        Vehiculo v1 = new Vehiculo();
        Vehiculo v2 = new Vehiculo("Toyota", "Corolla", 15000.50, 180, true);
        Vehiculo v3 = new Vehiculo("Mazda", "3", 50000.00, 200, false);

        Estudiante e1 = new Estudiante("E101", "Carlos Perez", 4.50, true);
        Estudiante e2 = new Estudiante("E102", "Ana Gomez", 3.80, true);
        Estudiante e3 = new Estudiante("E103", "Luis Diaz", 2.90, false);

        CuentaBancaria c1 = new CuentaBancaria();
        CuentaBancaria c2 = new CuentaBancaria("1001", "Carlos Perez");
        CuentaBancaria c3 = new CuentaBancaria("1002", "Ana Gomez", 100.00, 100.0);

        c2.setLimiteCredito(500.0);
        c2.depositar(1250.75);
        c3.retirar(50.00);

        double conversion = ConversorMoneda.convertirDolaresAEuros(500.0, 0.92);
        String moneda = ConversorMoneda.formatearMoneda(conversion);

        System.out.printf("=== REPORTE DE VEHICULOS ===%n");
        System.out.printf("[1] Marca: %s | Modelo: %s | KM: %.2f | VelMax: %d km/h | Encendido: %b%n",
                v2.getMarcaVehiculo(), v2.getModeloVehiculo(), v2.getKilometrajeActual(), v2.getVelocidadMaxima(), v2.isEstadoMotor());
        System.out.printf("[2] Marca: %s | Modelo: %s | KM: %.2f | VelMax: %d km/h | Encendido: %b%n",
                v1.getMarcaVehiculo(), v1.getModeloVehiculo(), v1.getKilometrajeActual(), v1.getVelocidadMaxima(), v1.isEstadoMotor());
        System.out.printf("[3] Marca: %s | Modelo: %s | KM: %.2f | VelMax: %d km/h | Encendido: %b%n",
                v3.getMarcaVehiculo(), v3.getModeloVehiculo(), v3.getKilometrajeActual(), v3.getVelocidadMaxima(), v3.isEstadoMotor());

        System.out.printf("%n=== REPORTE DE ESTUDIANTES ===%n");
        System.out.printf("%s%n", e1.obtenerReporteAcademicos());
        System.out.printf("%s%n", e2.obtenerReporteAcademicos());
        System.out.printf("%s%n", e3.obtenerReporteAcademicos());

        System.out.printf("%n=== REPORTE DE CUENTAS BANCARIAS ===%n");
        System.out.printf("Cuenta: %s | Titular: %s | Saldo Final: $%.2f | Credito: $%.2f%n",
                c2.getNumeroCuenta(), c2.getTitular(), c2.getSaldo(), c2.getLimiteCredito());
        System.out.printf("Cuenta: %s | Titular: %s | Saldo Final: $%.2f | Credito: $%.2f%n",
                c3.getNumeroCuenta(), c3.getTitular(), c3.getSaldo(), c3.getLimiteCredito());
        System.out.printf("Cuenta: %s | Titular: %s | Saldo Final: $%.2f | Credito: $%.2f%n",
                c1.getNumeroCuenta(), c1.getTitular(), c1.getSaldo(), c1.getLimiteCredito());

        System.out.printf("%n=== CONVERSOR DE MONEDA ===%n");
        System.out.printf("500 dolares equivalen a: %s%n", moneda);
    }
}
