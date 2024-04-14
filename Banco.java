
package bake.breadbank;

public class Banco {
    private ListaSE<Cliente> clientes;
    private ListaSE<Cuenta> cuentas;
    private Logger logger;

    public Banco () {
        clientes = new ListaSE<>();
        cuentas = new ListaSE<>();
        logger = new Logger();
    }

    public void altaCliente (Cliente cliente) {
        clientes.Adiconar(cliente);
    }

    public void bajaCliente (String numIdentidad) {
        //Buscar cliente
        Cliente clienteABajar = null;
        int indiceCliente = -1;
        for (int i = 0; i < clientes.Longitud(); i++) {
            Cliente cliente = clientes.Obtener(i);
            if (cliente.getIdentidad() == numIdentidad) {
                clienteABajar = cliente;
                break;
            }
        }

        if (clienteABajar != null) {
            //Eliminar cuenta asocidas al cliente
            for (int i = cuentas.Longitud() - 1; i >= 0; i--) {
                Cuenta cuenta = cuentas.Obtener(i);
                if (cuenta.getCliente().getIdentidad() == numIdentidad) {
                    cuentas.Eliminar(i);
                }
            }

            //Eliminar al cliente
            clientes.Eliminar(indiceCliente);
            System.out.println("Cliente dado de baja exitosamente");
        } else {
            System.out.println("Cliente no encontrado");
        }
    }

    public int verificarCuenta (String numeroIdentidad, String numeroCuenta) {
        for (int i = 0; i < cuentas.Longitud(); i++) {
            Cuenta cuenta = cuentas.Obtener(i);
            if (cuenta.getCliente().getIdentidad() == numeroCuenta) {
                return i; //La cuenta existe
            }
        }
        //Crear cuenta nueva si el cliente existe
        Cliente clienteExistente = buscarCliente(numeroIdentidad);
        if (clienteExistente != null) {
            CuentaAhorro cuentaNueva = new CuentaAhorro (numeroCuenta,"MXN",0.0,clienteExistente);
            cuentas.Adiconar(cuentaNueva);
            return cuentas.Longitud() - 1;
        } else {
            System.out.println("El cliente no existe");
            return -1; //Cliente no encontrado
        }
    }

    public void realizarOperacion (String numeroIdentidad, String numeroCuenta, double monto, String tipoOperacion) {
        Cliente cliente = buscarCliente(numeroIdentidad);
        if (cliente != null) {
            Cuenta cuenta = buscarCuenta(cliente, numeroCuenta);
            if (cuenta != null) {
                if (tipoOperacion == "depositos") {
                    cuenta.depositar(monto);
                    logger.registrarOperaciones(new Operacion(numeroIdentidad,numeroCuenta,monto), "deposito");
                    System.out.println("Deposito realizado exitosamente");
                } else if (tipoOperacion == "retiro") {
                    boolean exito = cuenta.retirar(monto);
                    if (exito) {
                        logger.registrarOperaciones(new Operacion(numeroIdentidad,numeroCuenta,monto),"retiro");
                        System.out.println("Retiro realizado excitosamente");
                    } else {
                        System.out.println("No hay suficiente saldo para realizar el retiro");
                    }
                } else {
                    System.out.println("Tipo de operacion no valido");
                }
            } else {
                System.out.println("La cuenta no existe");
            }
        } else {
            System.out.println("El cliente no existe");
        }
    }

    public ListaSE<Cuenta> getCuentasPorCliente (String numeroIdentidad) {
        ListaSE<Cuenta> cuentaCliente = new ListaSE<>();
        for (int i = 0; i < clientes.Longitud(); i++) {
            Cliente cliente = clientes.Obtener(i);
            if (cliente.getIdentidad() == numeroIdentidad) {
                for (int j = 0; j < cuentas.Longitud(); j++) {
                    Cuenta cuenta = cuentas.Obtener(j);
                    if (cuenta.getCliente().getIdentidad() == numeroIdentidad) {
                        cuentaCliente.Adiconar(cuenta);
                    }
                }
                break;//Termina la busqueda cuando se encuentra al cliente
            }
        }
        return cuentaCliente;
    }

    public void almacenarOperaciones (Operacion operacion, String tipo) {
        logger.registrarOperaciones(operacion,tipo);
    }

    public ListaSE<Operacion> consultarOperaciones (String tipo) {
        return logger.consultarOperaciones(tipo);
    }

    private Cliente buscarCliente (String numeroIdentidad) {
        for (int i = 0; i < clientes.Longitud(); i++) {
            Cliente cliente = clientes.Obtener(i);
            if (cliente.getIdentidad() == numeroIdentidad) {
                return cliente;
            }
        }
        return null;
    }

    private Cuenta buscarCuenta (Cliente cliente, String numeroCuenta) {
        for (int i = 0; i < clientes.Longitud(); i++) {
            Cuenta cuenta = cuentas.Obtener(i);
            if (cuenta.getNumeroCuenta() == numeroCuenta) {
                return cuenta;
            }
        }
        return null;
    }
    
    public boolean esNumeroUnico (String numeroIdentidad) {
        //Recorre lista de clietne y ve si tiene alguno el mismo numero
        for (int i = 0; i < clientes.Longitud(); i++) {
            Cliente cliente = clientes.Obtener(i);
            if (cliente.getIdentidad() == numeroIdentidad) {
                return false; //Numero ya existe
            }
        }
        return false;
    }  
}
/*
  

 // Método para buscar un cliente por su ID
    private Cliente buscarCliente(int idCliente) {
        for (int i = 0; i < clientes.Longitud(); i++) {
            Cliente cliente = clientes.Obtener(i);
            if (cliente.getIdCliente() == idCliente) {
                return cliente;
            }
        }
        return null;
    }
//c) Operaciones sobre la cuenta
    public void OperacionesCuenta (int idCliente, int idCuenta, double monto, String tipoOperacion) {
        Cliente cliente = null;
        for (int i = 0; i < clientes.Longitud(); i++) {
            Cliente c = clientes.Obtener(i);
            if (c.getIdCliente() == idCliente) {
                cliente = c;
                break;
            }
        }
        if (cliente != null) {
            ListaSE<Cuenta> cuentasCliente = cliente.getCuentas();
            boolean cuentaEncontrada = false;
            
            for (int i = 0; i < cuentasCliente.Longitud(); i++) {
                Cuenta cuenta = cuentasCliente.Obtener(i);
                if (cuenta.getNumCuenta() == idCuenta) {
                    cuentaEncontrada = true;
                    switch (tipoOperacion) {
                        case "retiro":
                            if (cuenta.getSaldoActual() >= monto) {
                                cuenta.reitrar(monto);
                                System.out.println("Retiro exisotos");
                                //Guardar en logs
                            } else {
                                System.out.println("Saldo insuficiente");
                            }
                            break;
                        case "depositar":
                            cuenta.depositar(monto);
                            System.out.println("Deposito exitoso");
                            //Guardar en logs
                            break;
                        default:
                            System.out.println("Tipo de operacion no valido");
                            break;
                    }
                    break;
                }
            }
            if (!cuentaEncontrada) {
                System.out.println("Cuenta no encontrada");
            }
        } else {
            System.out.println("Cliente no encontrado");
        }
    }

//d) Visualizacion de cuentas por lientes
    public void VisualizarCuenta (int idCliente) {
        Cliente cliente = null;
        if (cliente != null) {
            System.out.println("Cuentas de " +cliente.getNombre() + " " + cliente.getApellidos() + ":");
            for (int i = 0; i < cliente.getCuentas().Longitud(); i++) {
                Cuenta cuenta = cliente.getCuentas().Obtener(i);
                System.out.println("Numero de cuenta: " + cuenta.getNumCuenta() + ", Saldo: " + cuenta.getSaldoActual());
            }
        } else {
            System.out.println("Cliente no encontrado");
        }
    }
//e) Implementar la funcionalidad almacenar log
    public void almacenarLogs(int idCliente, int idCuenta, double monto) {
        try {
            FileWriter fileDepositos = new FileWriter("depositos.txt");
            FileWriter fileRetiros = new FileWriter("retiros.txt");
            
            for (int i = 0; i < operaciones.Longitud(); i++) {
                Operacion operacion = operaciones.Obtener(i);
                if (operacion.getTipoOp() == "Deposito") {
                    fileDepositos.write(operacion.toString() + "\n");
                } else if (operacion.getTipoOp() == "Retiro") {
                    fileRetiros.write(operacion.toString() + "\n");
                }
            }
            
            fileDepositos.close();
            fileRetiros.close();
            
            System.out.println("Logs almacenados excitosamente");
            
        } catch (IOException ex) {
            System.out.println("Error al almacenar los logs: " + ex.getMessage());
        }
    }

//f) Implementar la funcionalidad consultar log
    public void consultarLogs (int idCliente) {
        System.out.println("Trasacciones del cliente con numero de identidad " + idCliente + ":");
        for (int i = 0; i < operaciones.Longitud(); i++) {
            Operacion operacion = operaciones.Obtener(i);
            if (operacion.getIdCliente() == idCliente) {
                System.out.println(operacion);
            }
        }
    }
*/