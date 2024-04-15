
package bake.examenbanco;

public class Transaccion {
    private String numeroIdentidadCliente;
    private String numeroCuenta;
    private double monto;

    // Constructor
    public Transaccion(String numeroIdentidadCliente, String numeroCuenta, double monto) {
        this.numeroIdentidadCliente = numeroIdentidadCliente;
        this.numeroCuenta = numeroCuenta;
        this.monto = monto;
    }
    
    // Getters
    public String getNumeroIdentidadCliente() {
        return numeroIdentidadCliente;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public double getMonto() {
        return monto;
    }

    // Setters
    public void setNumeroIdentidadCliente(String numeroIdentidadCliente) {
        this.numeroIdentidadCliente = numeroIdentidadCliente;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }
}
