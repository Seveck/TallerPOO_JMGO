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
        CuentaBancaria c2 = new CuentaBancaria("1001", "Carlos Perez", 1250.75, 500.0);
        CuentaBancaria c3 = new CuentaBancaria("1002", "Ana Gomez", 50.00, 100.0);

        c2.setSaldo(1250.75);
        c3.setSaldo(50.00);

        double conversion = ConversorMoneda.convertirDolaresAEuros(500.0, 0.92);
        String moneda = ConversorMoneda.formatearMoneda(conversion);

        System.out.printf("=== REPORTE DE VEHICULOS ===%n");
        System.out.printf("[1] Marca: %s | Modelo: %s | KM: %.2f | VelMax: %d km/h | Encendido: %b%n",
                v2.getMarcaVehiculo(), v2.getModeloVehiculo(), v2.getKilometrajeActual(), v2.getVelocidadMaxima(), v2.isEstadoMotor());
        System.out.printf("[2] Marca: %s | Modelo: %s | KM: %.2f | VelMax: %d km/h | Encendido: %b%n",
                v3.getMarcaVehiculo(), v3.getModeloVehiculo(), v3.getKilometrajeActual(), v3.getVelocidadMaxima(), v3.isEstadoMotor());

        System.out.printf("%n=== REPORTE DE ESTUDIANTES ===%n");
        System.out.printf("Estudiante: %s | Promedio: %.2f | Matriculado: %b%n",
                e1.getNombreCompleto(), e1.getPromedioCalificaciones(), e1.isEstaMatriculado());
        System.out.printf("Estudiante: %s | Promedio: %.2f | Matriculado: %b%n",
                e2.getNombreCompleto(), e2.getPromedioCalificaciones(), e2.isEstaMatriculado());
        System.out.printf("Estudiante: %s | Promedio: %.2f | Matriculado: %b%n",
                e3.getNombreCompleto(), e3.getPromedioCalificaciones(), e3.isEstaMatriculado());

        System.out.printf("%n=== REPORTE DE CUENTAS BANCARIAS ===%n");
        System.out.printf("Cuenta: %s | Titular: %s | Saldo Final: $%.2f | Credito: $%.2f%n",
                c2.getNumeroCuenta(), c2.getTitular(), c2.getSaldo(), c2.getLimiteCredito());
        System.out.printf("Cuenta: %s | Titular: %s | Saldo Final: $%.2f | Credito: $%.2f%n",
                c3.getNumeroCuenta(), c3.getTitular(), c3.getSaldo(), c3.getLimiteCredito());

        System.out.printf("%n=== CONVERSOR DE MONEDA ===%n");
        System.out.printf("500 dolares equivalen a: %s%n", moneda);
    }
}
