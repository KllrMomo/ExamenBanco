
package bake.examenbanco;

public class Transaccion {
    private String numeroIdentidadCliente;
    private int numeroCuenta;
    private double monto;

    // Constructor
    public Transaccion(String numeroIdentidadCliente, int numeroCuenta, double monto) {
        this.numeroIdentidadCliente = numeroIdentidadCliente;
        this.numeroCuenta = numeroCuenta;
        this.monto = monto;
    }
    
    // Getters
    public String getNumeroIdentidadCliente() {
        return numeroIdentidadCliente;
    }

    public int getNumeroCuenta() {
        return numeroCuenta;
    }

    public double getMonto() {
        return monto;
    }

    // Setters
    public void setNumeroIdentidadCliente(String numeroIdentidadCliente) {
        this.numeroIdentidadCliente = numeroIdentidadCliente;
    }

    public void setNumeroCuenta(int numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }
}
