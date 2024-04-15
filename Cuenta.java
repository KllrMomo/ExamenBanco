
package bake.examenbanco;

public class Cuenta {
    private int numeroCuenta;
    private double saldo;
    private String moneda;

    public Cuenta(int numeroCuenta, double saldo, String moneda) {
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldo;
        this.moneda = moneda;
    }

    // Getters y setters

    public int getNumeroCuenta() {
        return numeroCuenta;
    }

    public double getSaldo() {
        return saldo;
    }

    public String getMoneda() {
        return moneda;
    }

    public void depositar(double monto) {
        saldo += monto;
    }

    public void retirar(double monto) {
        saldo -= monto;
    }
}
