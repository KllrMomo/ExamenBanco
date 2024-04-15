
package bake.examenbanco;

public abstract class Transaccion {
    protected String numeroIdentidadCliente;
    protected double monto;

    // Constructor
    public Transaccion(String numeroIdentidadCliente, double monto) {
        this.numeroIdentidadCliente = numeroIdentidadCliente;
        this.monto = monto;
    }
    
    //Metodo 
    public abstract void realizarTransaccion();
    
    // Getters
    public String getNumeroIdentidadCliente() {
        return numeroIdentidadCliente;
    }

    public double getMonto() {
        return monto;
    }

    // Setters
    public void setNumeroIdentidadCliente(String numeroIdentidadCliente) {
        this.numeroIdentidadCliente = numeroIdentidadCliente;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }
}
