package com.client;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import com.interf.test.TestRemote;
import java.util.Scanner;

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

// Clase del Cliente, aquí se encuentra la conección con el servidor y la llamada a los métodos remotos del lado del cliente

public class TestClient {

    public static final String RMI_ID = "TestRMI"; //ID del servidor registrado

    public static final int RMI_PORT = 8082; // Especifica el puerto al cual debe conectarse el cliente

    static Scanner sc = new Scanner(System.in); // Para recibir input del usuario

    public static void main(String[] args) throws RemoteException, NotBoundException {
        /*
        * El objeto registry obtiene una referencia a un servidor que se encuentra registrado según un nombre y puerto específicos
        * Posteriormente mediante el objeto remote, es posible invocar métodos de manera remota siempre y cuando el ID del servidor que(RMI ID)
        * contiene los métodos competentes se encuentre registrado.
        *
         */
        Registry registry = LocateRegistry.getRegistry("localhost", RMI_PORT);
        TestRemote remote = (TestRemote) registry.lookup(RMI_ID);

        /*Menú presentado al usuario*/

        System.out.println("¿Qué métodos deseas aplicar?");
        System.out.println("1)Probar el servidor\n" +
                "2)Hacer una multiplicación\n" +
                "3)Verificar palindromo");

        /* Try and catch en el cual se hacen las llamadas a los métodos remotos del servidor, dependiendo de la
        * selección del cliente en el menú
        */
        try {
            int num = sc.nextInt();
            switch (num) {
                case 1:
                    System.out.println("Introduce un número");
                    String case1 = sc.next();
                    System.out.println(remote.test(case1)); // Llamada al método remoto "test"
                    break;

                case 2:
                    System.out.println("Introduce un número a multiplicar");
                    int num1 = sc.nextInt();
                    System.out.println("Introduce otro número a multiplicar");
                    int num2 = sc.nextInt();
                    System.out.println(remote.multiplicacion(num1, num2)); // Llamada al método remoto "multiplicación, al cual se le mandan dos enteros
                    break;

                case 3:
                    System.out.println("Introduce una palabra");
                    String pal = sc.next();
                    System.out.println(remote.palindromo(pal));// Llamada al método remoto "palindromo", al cual se le envía una cadena a procesar.
                    break;
            }
        }
        catch (Exception e){ // Excepción en caso de que el usuario ingrese un número que no se encuentra en el menú presentado (1, 2 y 3)
            System.out.print("Introduce un número del 1 al 3");
        }
    }

}