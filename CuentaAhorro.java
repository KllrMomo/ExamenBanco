
package bake.breadbank;

public class CuentaAhorro extends Cuenta{
    private static final double comisionRetiro = 0.05;

    public CuentaAhorro(String numeroCuenta, String moneda, double saldo, Cliente cliente) {
        super(numeroCuenta, moneda, saldo, cliente);
    }

    @Override
    public void depositar(double monto) {
        saldo += monto;
    }

    @Override
    public boolean retirar(double monto) {
        double comision = monto*comisionRetiro;
        if (saldo >= (monto+comision)){
            saldo -= (monto+comision);
            return true;
        } else {
            return false; //No hay saldo suficiente
        }
    }
}
