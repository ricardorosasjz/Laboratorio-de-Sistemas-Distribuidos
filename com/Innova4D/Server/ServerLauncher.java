package com.Innova4D.Server;

/**
 * Created by Guillermo Del Río Acevedo
 * ID: 150313
 * guillermo.delrioao@udlap.mx
 * Ricardo Rosas Juárez
 * ID: 150371
 * ricardo.rosasjz@udlap.mx
 * on 01/03/2017.
 */
import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import com.Innova4D.Interface.Constant;

public class ServerLauncher {

    public static void main(String[] args) throws AccessException, RemoteException, AlreadyBoundException {
        ControlTower control = new ControlTower();
        Registry registry = LocateRegistry.createRegistry(8081);
        registry.bind(Constant.RMI_ID, control);
        System.out.println("Control Tower (Server) has started");
    }
}