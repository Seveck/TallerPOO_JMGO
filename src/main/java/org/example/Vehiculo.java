package org.example;

/*
 * Justificación de tipos:
 * Se usan tipos primitivos (double, int, boolean) porque consumen menos memoria
 * y no necesitan valores nulos (null). Los Wrappers consumen mas memoria porque son objetos.
 */
public class Vehiculo {

    private String MarcaVehiculo;
    private String ModeloVehiculo;
    private double KilometrajeActual;
    private int VelocidadMaxima;
    private boolean EstadoMotor;

    public Vehiculo() {
        this.MarcaVehiculo = "Genérico";
        this.ModeloVehiculo = "Sin Modelo";
        this.KilometrajeActual = 0.0;
        this.VelocidadMaxima = 120;
        this.EstadoMotor = false;
    }

    public Vehiculo(String marcaVehiculo, String modeloVehiculo, double kilometrajeActual, int velocidadMaxima, boolean estadoMotor) {
        this.MarcaVehiculo = marcaVehiculo;
        this.ModeloVehiculo = modeloVehiculo;
        this.KilometrajeActual = kilometrajeActual;
        this.VelocidadMaxima = velocidadMaxima;
        this.EstadoMotor = estadoMotor;
    }

    public String getMarcaVehiculo() {
        return MarcaVehiculo;
    }

    public void setMarcaVehiculo(String marcaVehiculo) {
        MarcaVehiculo = marcaVehiculo;
    }

    public String getModeloVehiculo() {
        return ModeloVehiculo;
    }

    public void setModeloVehiculo(String modeloVehiculo) {
        ModeloVehiculo = modeloVehiculo;
    }

    public double getKilometrajeActual() {
        return KilometrajeActual;
    }

    public void setKilometrajeActual(double kilometrajeActual) {
        KilometrajeActual = kilometrajeActual;
    }

    public int getVelocidadMaxima() {
        return VelocidadMaxima;
    }

    public void setVelocidadMaxima(int velocidadMaxima) {
        VelocidadMaxima = velocidadMaxima;
    }

    public boolean isEstadoMotor() {
        return EstadoMotor;
    }

    public void setEstadoMotor(boolean estadoMotor) {
        EstadoMotor = estadoMotor;
    }

    @Override
    public String toString() {
        return "Vehiculo{" +
                "MarcaVehiculo='" + MarcaVehiculo + '\'' +
                ", ModeloVehiculo='" + ModeloVehiculo + '\'' +
                ", KilometrajeActual=" + KilometrajeActual +
                ", VelocidadMaxima=" + VelocidadMaxima +
                ", EstadoMotor=" + EstadoMotor +
                '}';
    }
}
