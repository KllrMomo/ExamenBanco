
package bake.examenbanco;

import java.io.*;
import javax.swing.JOptionPane;

public class Banco {
    protected ListaSE<Cliente> clientes;
    public ListaSE<Cuenta> cuentas;
    private ListaSE<Transaccion> transaccionesDeposito;
    private ListaSE<Transaccion> transaccionesRetiro;

    // Constructor
    public Banco() {
        this.clientes = new ListaSE<>();
        this.cuentas = new ListaSE<>();
        this.transaccionesDeposito = new ListaSE<>();
        this.transaccionesRetiro = new ListaSE<>();
    }

    // Método para agregar un cliente al banco
    public void agregarCliente(Cliente cliente) {
        clientes.Adiconar(cliente);
    }

    // Método para eliminar un cliente del banco
    public void eliminarCliente(int indice) {
        clientes.Eliminar(indice);
    }

    // Método para agregar una cuenta a un cliente
    public void agregarCuenta(String numeroIdentidad, Cuenta cuenta) {
        // Buscar el cliente por su número de identidad
        int indiceCliente = clientes.BuscarCuenta(numeroIdentidad);
        if (indiceCliente != -1) {
            // Agregar la cuenta al cliente encontrado
            clientes.Obtener(indiceCliente).agregarCuenta(cuenta);
            // Agregar la cuenta a la lista de cuentas del banco
            cuentas.Adiconar(cuenta);
        } else {
            JOptionPane.showMessageDialog(null,"Cliente no encontrado.");
        }
    }

    // Método para eliminar una cuenta de un cliente
    public void eliminarCuenta(String numeroCuenta) {
        // Buscar la cuenta por su número de cuenta
        int indiceCuenta = cuentas.BuscarCuenta(numeroCuenta);
        if (indiceCuenta != -1) {
            // Eliminar la cuenta de la lista de cuentas del banco
            cuentas.Eliminar(indiceCuenta);
        } else {
            JOptionPane.showMessageDialog(null,"Cuenta no encontrada.");
        }
    }

    // Método para realizar un depósito en una cuenta
    public void depositar(String numeroIdentidad, String numeroCuenta, double monto, String nombrePersona) {
        // Verificar que la cuenta exista
        int indiceCuenta = cuentas.BuscarCuenta(numeroCuenta);
        if (indiceCuenta != -1) {
            // Actualizar el saldo de la cuenta
            Cuenta cuenta = cuentas.Obtener(indiceCuenta);
            cuenta.depositar(monto);
            // Registrar la transacción de depósito
            transaccionesDeposito.Adiconar(new Transaccion(numeroIdentidad, numeroCuenta, monto));
        } else {
            JOptionPane.showMessageDialog(null,"Cuenta no encontrada.");
        }
    }

    // Método para realizar un retiro de una cuenta
    public void retirar(String numeroIdentidad, String numeroCuenta, double monto) {
        // Verificar que la cuenta exista
        int indiceCuenta = cuentas.BuscarCuenta(numeroCuenta);
        if (indiceCuenta != -1) {
            // Verificar que haya saldo suficiente en la cuenta
            Cuenta cuenta = cuentas.Obtener(indiceCuenta);
            if (cuenta.getSaldo() >= monto) {
                // Actualizar el saldo de la cuenta
                cuenta.retirar(monto);
                // Registrar la transacción de retiro
                transaccionesRetiro.Adiconar(new Transaccion(numeroIdentidad, numeroCuenta, monto));
            } else {
                JOptionPane.showMessageDialog(null,"Saldo insuficiente.");
            }
        } else {
            JOptionPane.showMessageDialog(null,"Cuenta no encontrada.");
        }
    }

    // Método para visualizar las cuentas asociadas a un cliente y su saldo
    public String visualizarCuentasCliente(String numeroIdentidad) {
        // Buscar el cliente por su número de identidad
        int indiceCliente = clientes.BuscarCuenta(numeroIdentidad);
        if (indiceCliente != -1) {
            // Mostrar las cuentas asociadas al cliente
            Cliente cliente = clientes.Obtener(indiceCliente);
            //Obtener lista de las cuentas
            ListaSE<Cuenta> cuentasCliente = cliente.getCuentas();
            
            for (int i = 0; i < cuentasCliente.Longitud(); i++) {
                Cuenta cuenta = cuentasCliente.Obtener(i);
                JOptionPane.showMessageDialog(null,"Numero de cuenta: " + cuenta.getNumeroCuenta() + ", Saldo: " + cuenta.getSaldo());
            }
        } else {
            JOptionPane.showMessageDialog(null,"Cliente no encontrado.");
        }
        // Si el cliente no tiene cuentas asociadas
        return "El cliente con número de identidad " + numeroIdentidad + " no tiene cuentas asociadas.";
}

    // Método para almacenar una transacción de depósito en el log
    public void almacenarLogDeposito() {
        try {
            FileWriter writer = new FileWriter("logs_depositos.txt");
            writer.write(consultarLogDepositos());
            writer.close();
            JOptionPane.showMessageDialog(null,"Logs de deposito almacenados correctamente");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al almacenar los logs de deposito: " + e.getMessage());
        }
    }

    // Método para almacenar una transacción de retiro en el log
    public void almacenarLogsRetiro() {
        try {
            FileWriter writer = new FileWriter("logs_retiros.txt");
            writer.write(consultarLogDepositos());
            writer.close();
            JOptionPane.showMessageDialog(null,"Logs de retiros almacenados correctamente");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al almacenar los logs de retiro: " + e.getMessage());
        }
    }

    // Método para consultar el log de transacciones de depósito de un cliente
    public String consultarLogDepositos() {
        StringBuilder logDepositos = new StringBuilder();
        // Recorrer la lista de depósitos y construir el log
        for (int i = 0; i < transaccionesDeposito.Longitud(); i++) {
            Transaccion deposito = transaccionesDeposito.Obtener(i);
        logDepositos.append("Cliente: ").append(deposito.getNumeroCuenta()).append("\n");
        logDepositos.append("Cuenta: ").append(deposito.getNumeroCuenta()).append("\n");
        logDepositos.append("Monto Depositado: ").append(deposito.getMonto()).append("\n");
        logDepositos.append("\n");
        }
        return logDepositos.toString();
    }

    // Método para consultar el log de transacciones de retiro de un cliente
    public String consultarLogRetiros() {
        StringBuilder logRetiros = new StringBuilder();
        // Recorrer la lista de depósitos y construir el log
        for (int i = 0; i < transaccionesRetiro.Longitud(); i++) {
            Transaccion retiro = transaccionesRetiro.Obtener(i);
        logRetiros.append("Cliente: ").append(retiro.getNumeroCuenta()).append("\n");
        logRetiros.append("Cuenta: ").append(retiro.getNumeroCuenta()).append("\n");
        logRetiros.append("Monto Retirado: ").append(retiro.getMonto()).append("\n");
        logRetiros.append("\n");
        }
        return logRetiros.toString();
    }
}
