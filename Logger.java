
package bake.breadbank;

import java.io.*;

public class Logger {
   private ListaSE<Operacion> depositos;
    private ListaSE<Operacion> retiros;

    public Logger () {
        depositos = new ListaSE<>();
        retiros = new ListaSE<>();
    }

    public void registrarOperaciones (Operacion operacion, String tipo) {
        if (tipo == "deposito") {
            depositos.Adiconar(operacion);
            escribirRegistro("depositos.txt",operacion.toString());
        } else if (tipo == "retiro") {
            retiros.Adiconar(operacion);
            escribirRegistro("retiros.txt", operacion.toString());
        }
    }

    public ListaSE<Operacion> consultarOperaciones (String tipo) {
        if (tipo == "deposito") {
            return depositos;
        } else if (tipo == "retiros") {
            return retiros;
        } else {
            return null;
        }
    }

    private void escribirRegistro (String archivo, String registro) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(archivo,true))) {
            pw.println(registro);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
