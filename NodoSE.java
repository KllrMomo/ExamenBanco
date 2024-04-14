/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bake.breadbank;

/**
 *
 * @author Acer
 */
public class NodoSE <T> {
    private T dato;
    
    private NodoSE<T> siguiente;
    
    public NodoSE<T> getSiguiente () {
        return siguiente;
    }
    
    public void setSiguiente (NodoSE<T> siguienteE) {
        this.siguiente = siguienteE;
    }
    
    public T getDato () {
        return dato;
    }
    
    public void setDato (T dato) {
        this.dato = dato;
    }
    
    public NodoSE(T dato, NodoSE<T> pSiguiente) {
        this.dato = dato;
        this.siguiente = pSiguiente;
    }
    
    public NodoSE (T pDato) {
        this.dato = pDato;
        this.siguiente = null;
    }
    
}
