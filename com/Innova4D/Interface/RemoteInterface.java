package com.Innova4D.Interface;

/**
 * Created by Guillermo Del Río Acevedo
 * ID: 150313
 * guillermo.delrioao@udlap.mx
 * Ricardo Rosas Juárez
 * ID: 150371
 * ricardo.rosasjz@udlap.mx
 * on 01/03/2017.
 */
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteInterface extends Remote {

    /*
     * Aquí se definen los métodos a implementarse en el servidor.
     */
    public Object[][] getMapaPistas()  throws RemoteException;

    /*
     * Métodos para el avión
     */
    public Boolean checkInAvion(Avion a)      throws RemoteException;
    public Avion   getAvion(String id, int c) throws RemoteException;
    public Boolean moverAvion(Avion a, int c) throws RemoteException;

	/*
	 * Métodos para el auto.
	 */
    public Boolean checkInAuto(Auto a)       throws RemoteException;
    public Auto    getAuto(String id, int c) throws RemoteException;
    public Boolean moverAuto(Auto a, int c)  throws RemoteException;

    /*
     * Métodos para el UFO
     */
    public Boolean checkInUFO(UFO a)      throws RemoteException;
    public UFO   getUFO(String id, int c) throws RemoteException;
    public Boolean moverUFO(UFO a, int c) throws RemoteException;

    /*
	 * Métodos para el Boeing
	 */
    public Boolean checkInBoeing(Boeing a)       throws RemoteException;
    public Boeing    getBoeing(String id, int c) throws RemoteException;
    public Boolean moverBoeing(Boeing a, int c)  throws RemoteException;

}