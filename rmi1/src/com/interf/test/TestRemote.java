package com.interf.test;

import java.rmi.Remote;
import java.rmi.RemoteException;

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

/*"TestRemote" es una interfaz en la cual se encuentran declarados los métodos a ser implementados por el servidor.
* El contrato que el servidor debe seguir
* */
public interface TestRemote extends Remote{


    public Boolean test(String test) throws RemoteException;// Método de prueba que recibe una cadena a ser procesada y devuelve un estado booleano
    public int multiplicacion(int a, int b) throws RemoteException;// Método de multiplicación que recibe dos números enteros y devuelve un entero
    public Boolean palindromo(String x)throws  RemoteException;// Método que recibe una cadena a ser procesada y devuelve un estado booleano

}