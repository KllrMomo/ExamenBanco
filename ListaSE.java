/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bake.breadbank;

import javax.swing.JOptionPane;

/**
 *
 * @author Acer
 */
public class ListaSE<T> implements IListaSE<T>{
    private NodoSE<T> cabeza;
    
    public void setCabeza (NodoSE<T> cabeza){
        cabeza = cabeza;
    }

    public ListaSE() {
        this.cabeza = null;
    }

    @Override
    public void Adiconar(T dato) {
        if (EsVacia()) {
            cabeza = new NodoSE<T>(dato);
        } else {
            NodoSE<T> actual = cabeza;
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(new NodoSE<T>(dato));
        }
    }

    @Override
    public T Obtener(int indice) {
        if (indice < 0 || indice >= Longitud()) {
            JOptionPane.showMessageDialog(null, "Posicion fuera de rango");
        }
        NodoSE<T> actual = cabeza;
        for (int i = 0; i < indice; i++) {
            actual = actual.getSiguiente();
        }
        return actual.getDato();
    }

    @Override
    public void Eliminar(int indice) {
        if (indice < 0 || indice >= Longitud()) {
            JOptionPane.showMessageDialog(null, "Posicion fuera de rango");
        }
        if (indice == 0) {
            cabeza = cabeza.getSiguiente();
        } else {
            NodoSE<T> anterior = null;
            NodoSE<T> actual = cabeza;
            for (int i = 0; i < indice; i++) {
                anterior = actual;
                actual = actual.getSiguiente();
            }
            anterior.setSiguiente(actual.getSiguiente());
        }
    }

    @Override
    public int Longitud() {
        int contador = 0;
        
        NodoSE<T> actual = cabeza;
        
        while (actual != null) {
            contador++;
            actual = actual.getSiguiente();
        }
        return contador;
    }

    @Override
    public boolean EsVacia() {
        return cabeza == null;
    }

    @Override
    public void Insertar(T dato, int indice) {
        if (indice < 0 || indice > Longitud()) {
            JOptionPane.showMessageDialog(null, "Posocion fuera de rango");
            return;
        }
        
        if (indice == 0) {
            NodoSE<T> nuevoNodo = new NodoSE<T>(dato);
            nuevoNodo.setSiguiente(cabeza);
            cabeza = nuevoNodo;
        } else {
            NodoSE<T> anterior = null;
            NodoSE<T> actual = cabeza;
            for (int i = 0; i < indice; i++) {
                anterior = actual;
                actual = actual.getSiguiente();
            }
            NodoSE<T> nuevoNodo = new NodoSE<T>(dato);
            anterior.setSiguiente(nuevoNodo);
            nuevoNodo.setSiguiente(actual);
        }
    }


    public int BuscarCuenta(int numID) {
        NodoSE<T> actual = cabeza;
        int posicion = 0;
        
        while (actual != null) {            
            if (actual.getDato().equals(numID)) {
                return posicion;
            }
            actual = actual.getSiguiente();
            posicion++;
        }
        return -1;
    }

    @Override
    public int Buscar(T dato) {
        NodoSE<T> actual = cabeza;
        int posicion = 0;
        
        while (actual != null) {            
            if (actual.getDato().equals(dato)) {
                return posicion;
            }
            actual = actual.getSiguiente();
            posicion++;
        }
        return -1;
    }
    
}
