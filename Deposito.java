/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bake.examenbanco;

/**
 *
 * @author Acer
 */
public class Deposito extends Transaccion{
    private String nombreDepositante;

    public Deposito(String numeroIdentidadCliente, double monto, String nombreDepositanteString) {
        super(numeroIdentidadCliente, monto);
        this.nombreDepositante = nombreDepositante;
    }

    @Override
    public String obtenerInformacion(){
        return "Deposito - Número de identidad: " + numeroIdentidadCliente + ", Monto: " + monto + ", Depositante: " + nombreDepositante;
    }
    
}
