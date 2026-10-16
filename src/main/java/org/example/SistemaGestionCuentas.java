package org.example;

import java.util.Scanner;

public class SistemaGestionCuentas {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        CuentaBancaria[] cuentas = new CuentaBancaria[3];
        cuentas[0] = new CuentaBancaria("1001", "Carlos Perez", 1250.75, 500.0);
        cuentas[1] = new CuentaBancaria("1002", "Ana Gomez", 50.0, 100.0);
        cuentas[2] = new CuentaBancaria("1003", "Luis Diaz", 300.0, 200.0);

        int opcion = 0;

        do {
            System.out.println("");
            System.out.println("=== SISTEMA DE GESTION DE CUENTAS ===");
            System.out.println("1. Depositar en una cuenta");
            System.out.println("2. Retirar de una cuenta");
            System.out.println("3. Ver reporte consolidado del sistema");
            System.out.println("4. Salir");
            System.out.print("Ingrese una opcion: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el numero de cuenta: ");
                    String numDeposito = scanner.next();
                    System.out.print("Ingrese el monto a depositar: ");
                    double montoDeposito = scanner.nextDouble();

                    if (montoDeposito <= 0) {
                        System.out.printf("Error: El monto a depositar debe ser mayor a 0.%n");
                    } else {
                        CuentaBancaria cuentaEncontrada = null;
                        for (int i = 0; i < cuentas.length; i++) {
                            if (cuentas[i].getNumeroCuenta().equals(numDeposito)) {
                                cuentaEncontrada = cuentas[i];
                            }
                        }

                        if (cuentaEncontrada == null) {
                            System.out.printf("Error: No se encontro la cuenta numero %s.%n", numDeposito);
                        } else {
                            cuentaEncontrada.setSaldo(cuentaEncontrada.getSaldo() + montoDeposito);
                            System.out.printf("Deposito exitoso. Nuevo saldo: $%.2f%n", cuentaEncontrada.getSaldo());
                        }
                    }
                    break;

                case 2:
                    System.out.print("Ingrese el numero de cuenta: ");
                    String numRetiro = scanner.next();
                    System.out.print("Ingrese el monto a retirar: ");
                    double montoRetiro = scanner.nextDouble();

                    if (montoRetiro <= 0) {
                        System.out.printf("Error: El monto a retirar debe ser mayor a 0.%n");
                    } else {
                        CuentaBancaria cuentaEncontrada = null;
                        for (int i = 0; i < cuentas.length; i++) {
                            if (cuentas[i].getNumeroCuenta().equals(numRetiro)) {
                                cuentaEncontrada = cuentas[i];
                            }
                        }

                        if (cuentaEncontrada == null) {
                            System.out.printf("Error: No se encontro la cuenta numero %s.%n", numRetiro);
                        } else {
                            double limite = 0.0;
                            if (cuentaEncontrada.getLimiteCredito() != null) {
                                limite = cuentaEncontrada.getLimiteCredito();
                            }
                            double totalDisponible = cuentaEncontrada.getSaldo() + limite;

                            if (montoRetiro <= totalDisponible) {
                                cuentaEncontrada.setSaldo(cuentaEncontrada.getSaldo() - montoRetiro);
                                System.out.printf("Retiro exitoso. Nuevo saldo: $%.2f%n", cuentaEncontrada.getSaldo());
                            } else {
                                System.out.printf("Error: Fondos insuficientes para retirar $%.2f.%n", montoRetiro);
                            }
                        }
                    }
                    break;

                case 3:
                    double totalSaldos = 0.0;
                    StringBuilder detalleCuentas = new StringBuilder();

                    for (int i = 0; i < cuentas.length; i++) {
                        totalSaldos = totalSaldos + cuentas[i].getSaldo();
                        String linea = String.format("Cuenta: %s | Titular: %s | Saldo: $%.2f | Credito: $%.2f%n",
                                cuentas[i].getNumeroCuenta(),
                                cuentas[i].getTitular(),
                                cuentas[i].getSaldo(),
                                cuentas[i].getLimiteCredito());
                        detalleCuentas.append(linea);
                    }

                    System.out.printf("=== REPORTE CONSOLIDADO DEL SISTEMA ===%n");
                    System.out.printf("%s", detalleCuentas.toString());
                    System.out.printf("Saldo total acumulado en el sistema: $%.2f%n", totalSaldos);
                    break;

                case 4:
                    System.out.printf("Gracias por usar el sistema. Hasta luego.%n");
                    break;

                default:
                    System.out.printf("Opcion invalida, por favor intente nuevamente.%n");
                    break;
            }

        } while (opcion != 4);
    }
}
