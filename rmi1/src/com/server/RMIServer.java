package com.server;

import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

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
* Esta clase se encarga de registrar al servidor en el Registry y levantar el servidor
 */
public class RMIServer {

    public static final String RMI_ID = "TestRMI"; // ID del server en el registry

    public static final int RMI_PORT = 8082;//Puerto donde se encuentra el servidor

    /**
     *
     * @param args
     * @throws AccessException
     * @throws RemoteException
     * @throws AlreadyBoundException
     */
    public static void main(String[] args) throws AccessException, RemoteException, AlreadyBoundException {

        ServerDefinition impl = new ServerDefinition(); //Se define un nuevo servidor
        Registry registry = LocateRegistry.createRegistry(RMI_PORT);// Se crea un registry en el puerto establecido
        registry.bind(RMI_ID, impl);// Enlaza al servidor con su ID en el Registry

        System.out.println("Server is running..."); //Se imprime en la consola un mensaje para indicarle al usuario que el servidor está corriendo
    }
}