
package bake.examenbanco;

import java.io.*;
import javax.swing.JOptionPane;

public class Banco {
    private ListaSE<Cliente> clientes;
    private ListaSE<Cuenta> cuentas;
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
    public void almacenarTransaccionDeposito(Transaccion transaccion) {
        transaccionesDeposito.Adiconar(transaccion);
    }

    // Método para almacenar una transacción de retiro en el log
    public void almacenarTransaccionRetiro(Transaccion transaccion) {
        transaccionesRetiro.Adiconar(transaccion);
    }

    // Método para consultar el log de depósitos
    public void consultarLogDepositos() {
        File archivoDepositos = new File("log_depositos.txt");
        StringBuilder contenidoLog = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(archivoDepositos))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                contenidoLog.append(linea).append("\n");
            }
            JOptionPane.showMessageDialog(null, contenidoLog.toString(), "Log de Depósitos", JOptionPane.PLAIN_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al leer el archivo de log de depósitos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para consultar el log de retiros
    public void consultarLogRetiros() {
        File archivoRetiros = new File("log_retiros.txt");
        StringBuilder contenidoLog = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(archivoRetiros))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                contenidoLog.append(linea).append("\n");
            }
            JOptionPane.showMessageDialog(null, contenidoLog.toString(), "Log de Retiros", JOptionPane.PLAIN_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al leer el archivo de log de retiros", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
