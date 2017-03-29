/**
 * Guillermo Del Río Acevedo
 * guillermo.delrioao@udlap.mx
 *
 * Ricardo Rosas Juarez
 * ricardo.rosasjz@udlap.mx
 */
class Monitor {
	// El monitor conoce los diferentes estados para cada filósofo.
	private enum State {PENSANDO, HAMBRIENTO, COMIENDO};

	// Un vector que contiene el estado de cada filósofo.
	private State[] estadoFilosofo;

	public Monitor (int numFilosofos) {
		estadoFilosofo = new State[numFilosofos];
		for (int i = 0; i < estadoFilosofo.length; i++) {
			estadoFilosofo[i] = State.PENSANDO;
		}
	}

	public synchronized void levantaCubiertos(int idFilosofo) throws InterruptedException{
		// Si levanta los cubiertos, come.
		estadoFilosofo[idFilosofo] = State.HAMBRIENTO;
		System.out.println("Filósofo: " + idFilosofo + " está hambriento.\n");
		// Mientras los vecinos comen, esperar...
		while (losVecinosComen(idFilosofo)) {
			//Esta linea hace que el filosofo espere
			wait();
		}

		estadoFilosofo[idFilosofo] = State.COMIENDO;
		System.out.println("Filósofo: " + idFilosofo + " está comiendo.\n");
		System.out.flush();
	}

	private boolean losVecinosComen(int idFilosofo) {
		// Verificar filósofo de un lado.
		if (estadoFilosofo[(idFilosofo + 1) % estadoFilosofo.length] == State.COMIENDO)
			return true;
		// Verificar filósofo del otro.
		if (estadoFilosofo[(idFilosofo + estadoFilosofo.length - 1) % estadoFilosofo.length] == State.COMIENDO)
			return true;
		// Ninguno está comiendo
		return false;
	}

	public synchronized void bajarCubiertos(int idFilosofo) {
		estadoFilosofo[idFilosofo] = State.PENSANDO;
		//notifyAll se encarga de notificar a sus dos dilosofos alrededor que ya no esta ocupando sus cubierto
		notifyAll();
	}
}
