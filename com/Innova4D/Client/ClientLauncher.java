package com.Innova4D.Client;

/**
 * Created by Guillermo Del Río Acevedo
 * ID: 150313
 * guillermo.delrioao@udlap.mx
 * Ricardo Rosas Juárez
 * ID: 150371
 * ricardo.rosasjz@udlap.mx
 * on 01/03/2017.
 */
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.Innova4D.Interface.Boeing;
import com.Innova4D.Interface.UFO;
import com.Innova4D.Interface.Auto;
import com.Innova4D.Interface.Avion;
import com.Innova4D.Interface.Constant;
import com.Innova4D.Interface.RemoteInterface;

public class ClientLauncher {

    public static void main(String[] args) throws RemoteException, NotBoundException {
        if(args.length==4){
            Constant.RMI_IP = args[0];//Cambiar la dirección IP que sera empleada para conectarse al servidor RMI

            switch (Integer.parseInt(args[1])){
                case 1:
                    System.out.println("Iniciar el cliente para mandar un Avión...");
                    ClientLauncher.avionClient();
                    ClientLauncher.guiClient();
                    break;
                case 2:
                    System.out.println("Iniciar el cliente para mandar un Auto...");
                    ClientLauncher.autoClient();
                    ClientLauncher.guiClient();
                    break;

                case 3:
                    System.out.println("Iniciar el cliente para mandar un UFO...");
                    ClientLauncher.ufoClient();
		            ClientLauncher.guiClient();
                    break;
                case 4:
                    System.out.println("Iniciar el cliente para mandar un Boeing...");
                    ClientLauncher.boeingClient();
		            ClientLauncher.guiClient();
                    break;

            }
        }else {
		/*
		 * Iniciar el cliente de la interfaz gráfica.
		 */
            ClientLauncher.guiClient();
		/*
		 * Iniciar el cliente para mandar un avión...
		 */
            ClientLauncher.avionClient();
		/*
		 * Iniciar el cliente para mandar un auto...
		 */
            ClientLauncher.autoClient();
        /*
		 * Iniciar el cliente para mandar un UFO...
		 */
            ClientLauncher.ufoClient();
		/*
		 * Iniciar el cliente para mandar un Boeing...
		 */
            ClientLauncher.boeingClient();
        }
    }
    /**
     * Se crea un cliente que administra el avión.
     */
    private static void avionClient () throws RemoteException, NotBoundException {

        Registry registry = LocateRegistry.getRegistry(Constant.RMI_IP, 8081);
        final RemoteInterface remote = (RemoteInterface) registry.lookup(Constant.RMI_ID);

		/*
		 * Crear una nave, con un ID y una Ubicación (0,0 default).
		 */
        final Avion a1 = new Avion("Lufthansa",0,0); //"L" Aparece en el mapa.
        remote.checkInAvion(a1);
		/*
		 * Temporizador que ejecuta movePlane cada 3 segundos.
		 */
        ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
        exec.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                try {
                    remote.moverAvion(remote.getAvion(a1.getId(), 0),0);
                } catch (Exception e) {
                }
            }
        }, 0, 1, TimeUnit.SECONDS);
    }
    /**
     * Se crea un cliente que administra el auto.
     */
    private static void autoClient () throws RemoteException, NotBoundException {

        Registry registry = LocateRegistry.getRegistry(Constant.RMI_IP, 8081);
        final RemoteInterface remote = (RemoteInterface) registry.lookup(Constant.RMI_ID);

		/*
		 * Crear un auto, con un ID y una Ubicación (1,0 default).
		 */
        final Auto a2 = new Auto("Auto",1,0); // "A" Aparece en el mapa.
        remote.checkInAuto(a2);
		/*
		 * Temporizador que ejecuta moverAuto cada 2 segundos.
		 */
        ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
        exec.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                try {
                    remote.moverAuto(remote.getAuto(a2.getId(), 1),1);
                } catch (Exception e) {
                }
            }
        }, 0, 2, TimeUnit.SECONDS);
    }

    private static void ufoClient () throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry(Constant.RMI_IP, 8081);
        final RemoteInterface remote = (RemoteInterface) registry.lookup(Constant.RMI_ID);
		/*
		 * Crear un UFO, con un ID y una Ubicación (2,0 default).
		 */
        final UFO a3 = new UFO("UFO",2,0); //"U" Aparece en el mapa.
        remote.checkInUFO(a3);
		/*
		 * Temporizador que ejecuta moveUFO cada 3 segundos.
		 */
        ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
        exec.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                try {
                    remote.moverUFO(remote.getUFO(a3.getId(), 2),2);
                } catch (Exception e) {
                }
            }
        }, 0, 3, TimeUnit.SECONDS);
    }

    private static void boeingClient () throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry(Constant.RMI_IP, 8081);
        final RemoteInterface remote = (RemoteInterface) registry.lookup(Constant.RMI_ID);
		/*
		 * Crear un Boeing, con un ID y una Ubicación (3,0 default).
		 */
        final Boeing a4 = new Boeing("Boeing",3,0); // "B" Aparece en el mapa.
        remote.checkInBoeing(a4);
		/*
		 * Temporizador que ejecuta moverAuto cada 2 segundos.
		 */
        ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
        exec.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                try {
                    remote.moverBoeing(remote.getBoeing(a4.getId(), 3),3);
                } catch (Exception e) {
                }
            }
        }, 0, 4, TimeUnit.SECONDS);
    }

    /**
     * Cliente que imprime la GUI (Graphical user interface) de las pistas.
     */
    private static void guiClient () throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry(Constant.RMI_IP, 8081);
        final RemoteInterface remote = (RemoteInterface) registry.lookup(Constant.RMI_ID);
		/*
		 * Obtiene la pista y se imprime.
		 */
        ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
        exec.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(ClientLauncher.printPista(remote.getMapaPistas()));
                } catch (Exception e) {
                }
            }
        }, 0, 1, TimeUnit.SECONDS);
    }
    /**
     * Imprime en consola el espacio aéreo desde la
     * torre de control (Servidor) en el Cliente.
     */
    private static String printPista(Object[][] a) throws RemoteException {
        Object o = null;
        String output = "=== Pista ===\n";
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 8; j++) {
                if(a[i][j] != null) {
                    o = a[i][j];
                    if(o.getClass().getSimpleName().equals("Auto"))
                        output = output + "["+((Auto)o).getId().charAt(0)+"]";
                    if(o.getClass().getSimpleName().equals("Avion"))
                        output = output + "["+((Avion)o).getId().charAt(0)+"]";
                    if(o.getClass().getSimpleName().equals("UFO"))
                        output = output + "["+((UFO)o).getId().charAt(0)+"]";
                    if(o.getClass().getSimpleName().equals("Boeing"))
                        output = output + "["+((Boeing)o).getId().charAt(0)+"]";
                } else {
                    output = output + "[ ]";
                }

            }
            if (i == 0 ) output = output + "<- Avión";
            if (i == 1 ) output = output + "<- Auto ";
            if (i == 2 ) output = output + "<- UFO ";
            if (i == 3 ) output = output + "<- Boeing ";
            output = output + "\n";
        }
        return output = output + "\n";
    }
}