
package bake.breadbank;

public abstract class Cuenta{
    protected String numeroCuenta;
    protected String moneda;
    protected double saldo;
    protected Cliente cliente;

    public Cuenta(String numeroCuenta, String moneda, double saldo, Cliente cliente) {
        this.numeroCuenta = numeroCuenta;
        this.moneda = moneda;
        this.saldo = saldo;
        this.cliente = cliente;
    }

    public abstract void depositar (double monto);
    public abstract boolean retirar (double monto);


    //Getters
    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public String getMoneda() {
        return moneda;
    }

    public double getSaldo() {
        return saldo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    //Setters
    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}