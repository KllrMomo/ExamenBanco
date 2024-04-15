
package bake.examenbanco;

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
            System.out.println("Cliente no encontrado.");
        }
    }

    // Método para eliminar una cuenta de un cliente
    public void eliminarCuenta(String numeroIdentidad, int numeroCuenta) {
        // Buscar el cliente por su número de identidad
        int indiceCliente = clientes.BuscarCuenta(numeroIdentidad);
        if (indiceCliente != -1) {
            // Eliminar la cuenta del cliente encontrado
            clientes.Obtener(indiceCliente).eliminarCuenta(numeroCuenta);
            // Eliminar la cuenta de la lista de cuentas del banco
            int indiceCuenta = cuentas.Buscar(numeroCuenta);
            if (indiceCuenta != -1) {
                cuentas.Eliminar(indiceCuenta);
            } else {
                System.out.println("Cuenta no encontrada.");
            }
        } else {
            System.out.println("Cliente no encontrado.");
        }
    }

    // Método para realizar un depósito en una cuenta
    public void depositar(String numeroIdentidad, int numeroCuenta, double monto, String nombrePersona) {
        // Verificar que la cuenta exista
        int indiceCuenta = cuentas.Buscar(numeroCuenta);
        if (indiceCuenta != -1) {
            // Actualizar el saldo de la cuenta
            Cuenta cuenta = cuentas.Obtener(indiceCuenta);
            cuenta.depositar(monto);
            // Registrar la transacción de depósito
            transaccionesDeposito.Adiconar(new Transaccion(numeroIdentidad, numeroCuenta, monto));
        } else {
            System.out.println("Cuenta no encontrada.");
        }
    }

    // Método para realizar un retiro de una cuenta
    public void retirar(String numeroIdentidad, int numeroCuenta, double monto) {
        // Verificar que la cuenta exista
        int indiceCuenta = cuentas.Buscar(numeroCuenta);
        if (indiceCuenta != -1) {
            // Verificar que haya saldo suficiente en la cuenta
            Cuenta cuenta = cuentas.Obtener(indiceCuenta);
            if (cuenta.getSaldo() >= monto) {
                // Actualizar el saldo de la cuenta
                cuenta.retirar(monto);
                // Registrar la transacción de retiro
                transaccionesRetiro.Adiconar(new Transaccion(numeroIdentidad, numeroCuenta, monto));
            } else {
                System.out.println("Saldo insuficiente.");
            }
        } else {
            System.out.println("Cuenta no encontrada.");
        }
    }

    // Método para visualizar las cuentas asociadas a un cliente y su saldo
    public void visualizarCuentasCliente(String numeroIdentidad) {
        // Buscar el cliente por su número de identidad
        int indiceCliente = clientes.BuscarCuenta(numeroIdentidad);
        if (indiceCliente != -1) {
            // Mostrar las cuentas asociadas al cliente
            Cliente cliente = clientes.Obtener(indiceCliente);
            for (Cuenta cuenta : cliente.getCuentas()) {
                System.out.println("Número de cuenta: " + cuenta.getNumeroCuenta() + ", Saldo: " + cuenta.getSaldo());
            }
        } else {
            System.out.println("Cliente no encontrado.");
        }
    }

    // Método para almacenar una transacción de depósito en el log
    public void almacenarTransaccionDeposito(Transaccion transaccion) {
        transaccionesDeposito.Adiconar(transaccion);
    }

    // Método para almacenar una transacción de retiro en el log
    public void almacenarTransaccionRetiro(Transaccion transaccion) {
        transaccionesRetiro.Adiconar(transaccion);
    }

    // Método para consultar el log de transacciones de depósito de un cliente
    public void consultarLogDepositos(String numeroIdentidad) {
        for (Transaccion transaccion : transaccionesDeposito) {
            if (transaccion.getNumeroIdentidadCliente().equals(numeroIdentidad)) {
                System.out.println(transaccion);
            }
        }
    }

    // Método para consultar el log de transacciones de retiro de un cliente
    public void consultarLogRetiros(String numeroIdentidad) {
        for (Transaccion transaccion : transaccionesRetiro) {
            if (transaccion.getNumeroIdentidadCliente().equals(numeroIdentidad)) {
                System.out.println(transaccion);
            }
        }
    }
}
