/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bake.examenbanco;

/**
 *
 * @author Acer
 */
public class Retiro extends Transaccion {

    public Retiro(String numeroIdentidadCliente, double monto) {
        super(numeroIdentidadCliente, monto);
    }

    @Override
    public String obtenerInformacion() {
        return "Retiro - Numero de identidad: " + numeroIdentidadCliente + ", Monto: " + monto;
    }
    
}
