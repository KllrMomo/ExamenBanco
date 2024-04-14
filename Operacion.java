
package bake.breadbank;

public class Operacion {
    private String idCliente;
    private String idCuenta;
    private double monto;

    public Operacion (String idCliente, String idCuenta, double monto) {
        this.idCliente = idCliente;
        this.idCuenta = idCuenta;
        this.monto = monto;
    }

    //Getters
    public String getIdCliente() { return idCliente;}
    public String getIdCuenta() { return idCuenta; }
    public double getMonto() { return monto; }

    //Setters
    public void setIdCliente(String idCliente) { this.idCliente = idCliente; }
    public void setIdCuenta(String idCuenta) { this.idCuenta = idCuenta; }
    public void setMonto(double monto) { this.monto = monto; }
}
