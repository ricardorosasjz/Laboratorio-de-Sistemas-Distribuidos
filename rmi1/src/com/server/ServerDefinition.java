package com.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import com.interf.test.TestRemote;

/**
 * @author   Francisco Gutiérrez <fsalvador23@gmail.com>
 * @version  0.1
 * @since    2015-01-10
 */

/**
 * Laboratorio de Sistemas Distribuidos. Comentarios del código por:
 * Ricardo Rosas Juárez, 1503471, ricardo.rosasjz@udlap.mx
 * Guillermo del Río Acevedo, 150313, guillermo.delrioao@udlap.mx
 */
/*
En esta clase se implementan los métodos declarados en la Interfaz "TestRemote"
 */
public class ServerDefinition extends UnicastRemoteObject implements TestRemote {


    private static final long serialVersionUID = 1L;

    protected ServerDefinition() throws RemoteException {
        super();
    }

    @Override
    public Boolean test(String test) throws RemoteException { // Método de prueba, recibe una cadena y la compara con otra para obtener un resultado booleano
        if (test.equalsIgnoreCase("test")) {
            return true;
        }
        return false;
    }

    public int multiplicacion(int a, int b) throws RemoteException {// Método que implementa la multiplicación de dos enteros y regresa otro entero como resultado
        int c;
        c = a * b;
        return c;
    }

    public Boolean palindromo(String x) throws RemoteException {// Método que procesa una cadena para determinar si es un palíndromo, regresa un valor booleano como resultado
        String y = "";

        int i = x.length() - 1;

        while (i >= 0) {
            y = y + x.charAt(i);
            i--;
        }

        if (x.equals(y) ) {
            return true;
        } else return false;
    }



}