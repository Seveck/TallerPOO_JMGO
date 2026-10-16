package org.example;

public class Vehiculo {

    private String MarcaVehiculo;
    private String ModeloVehiculo;
    private double KilometrajeActual;
    private int VelocidadMaxima;
    private boolean EstadoMotor;

    public Vehiculo() {
    }

    public Vehiculo(String marcaVehiculo, String modeloVehiculo, double kilometrajeActual, int velocidadMaxima, boolean estadoMotor) {
        MarcaVehiculo = marcaVehiculo;
        ModeloVehiculo = modeloVehiculo;
        KilometrajeActual = kilometrajeActual;
        VelocidadMaxima = velocidadMaxima;
        EstadoMotor = estadoMotor;
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
