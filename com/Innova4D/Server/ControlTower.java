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
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import com.Innova4D.Interface.*;

public class ControlTower extends UnicastRemoteObject implements RemoteInterface {

    protected ControlTower() throws RemoteException {
        super();
    }

    private Object[][] mapaPista = new Object[4][8];

    private static final long serialVersionUID = 1L;

    /**
     * Regresa el mapa de las pistas.
     */
    @Override
    public Object[][] getMapaPistas() throws RemoteException {
        return this.mapaPista;
    }

    /**
     * Mueve un avión en la matriz, un bloque a la vez. Izq. Der.
     */
    @Override
    public Boolean moverAvion(Avion a, int c) throws RemoteException {
        Boolean flag = false;
        int newY = a.getY() + 1;
        if (newY < 8) {
            try {
                this.mapaPista[c][a.getY()] = null;
                this.mapaPista[c][newY] = a;
                a.setX(0);
                a.setY(newY);
                flag = true;
            } catch (Exception e) {
                flag = false;
            }
        }
        return flag;
    }

    /**
     * Busca por un avión en el mapa aéreo usando su ID.
     */
    @Override
    public Avion getAvion(String id, int c) throws RemoteException {
        Avion a = null;
        for (int i = 0; i < 8; i++) {
            if(this.mapaPista[c][i] != null){
                a = (Avion) this.mapaPista[c][i];
                if(!a.getId().equals(id))
                    a = null;
            }
        }
        return a;
    }

    @Override
    public Boolean checkInAvion(Avion a) throws RemoteException {
        if(this.mapaPista[a.getX()][a.getY()] == null)
            this.mapaPista[a.getX()][a.getY()] = a;
        return true;
    }

	/*
	 * Aquí comienzan los métodos del auto.
	 */

    @Override
    public Boolean checkInAuto(Auto a) throws RemoteException {
        if(this.mapaPista[a.getX()][a.getY()] == null)
            this.mapaPista[a.getX()][a.getY()] = a;
        return true;
    }
    @Override
    public Auto getAuto(String id, int c) throws RemoteException {
        Auto a = null;
        for (int i = 0; i < 8; i++) {
            if(this.mapaPista[c][i] != null){
                a = (Auto) this.mapaPista[c][i];
                if(!a.getId().equals(id))
                    a = null;
            }
        }
        return a;
    }

    @Override
    public Boolean moverAuto(Auto a, int c) throws RemoteException {
        Boolean flag = false;
        int newY = a.getY() + 1;
        if (newY < 8) {
            try {
                this.mapaPista[c][a.getY()] = null;
                this.mapaPista[c][newY] = a;
                a.setX(0);
                a.setY(newY);
                flag = true;
            } catch (Exception e) {
                flag = false;
            }
        }
        return flag;
    }

    /**
     * Mueve un UFO en la matriz, un bloque a la vez. Izq. Der.
     */
    @Override
    public Boolean moverUFO(UFO a, int c) throws RemoteException {
        Boolean flag = false;
        int newY = a.getY() + 1;
        if (newY < 8) {
            try {
                this.mapaPista[c][a.getY()] = null;
                this.mapaPista[c][newY] = a;
                a.setX(0);
                a.setY(newY);
                flag = true;
            } catch (Exception e) {
                flag = false;
            }
        }
        return flag;
    }

    /**
     * Busca por un UFO en el mapa aéreo usando su ID.
     */
    @Override
    public UFO getUFO(String id, int c) throws RemoteException {
        UFO a = null;
        for (int i = 0; i < 8; i++) {
            if(this.mapaPista[c][i] != null){
                a = (UFO) this.mapaPista[c][i];
                if(!a.getId().equals(id))
                    a = null;
            }
        }
        return a;
    }

    @Override
    public Boolean checkInUFO(UFO a) throws RemoteException {
        if(this.mapaPista[a.getX()][a.getY()] == null)
            this.mapaPista[a.getX()][a.getY()] = a;
        return true;
    }

    /*
	 * Aquí comienzan los métodos del Boeing.
	 */

    @Override
    public Boolean checkInBoeing(Boeing a) throws RemoteException {
        if(this.mapaPista[a.getX()][a.getY()] == null)
            this.mapaPista[a.getX()][a.getY()] = a;
        return true;
    }
    @Override
    public Boeing getBoeing(String id, int c) throws RemoteException {
        Boeing a = null;
        for (int i = 0; i < 8; i++) {
            if(this.mapaPista[c][i] != null){
                a = (Boeing) this.mapaPista[c][i];
                if(!a.getId().equals(id))
                    a = null;
            }
        }
        return a;
    }

    @Override
    public Boolean moverBoeing(Boeing a, int c) throws RemoteException {
        Boolean flag = false;
        int newY = a.getY() + 1;
        if (newY < 8) {
            try {
                this.mapaPista[c][a.getY()] = null;
                this.mapaPista[c][newY] = a;
                a.setX(0);
                a.setY(newY);
                flag = true;
            } catch (Exception e) {
                flag = false;
            }
        }
        return flag;
    }
}