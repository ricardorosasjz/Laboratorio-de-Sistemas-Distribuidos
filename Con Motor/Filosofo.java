/**
 * Guillermo Del Río Acevedo
 * guillermo.delrioao@udlap.mx
 *
 * Ricardo Rosas Juarez
 * ricardo.rosasjz@udlap.mx
 */

import java.util.Random;

public class Filosofo implements Runnable {
	// Genera un número aleatorio para cuanto tiempo come un filósofo.
	private Random numGenerator = new Random();

	// El id único de cada filósofo.
	private int id;

	// Un monitor que controla los recursos.
	private Monitor monitor;

	public Filosofo (int id, Monitor monitor) {
		this.id = id;
		this.monitor = monitor;
	}

	public void run() {
		try {
			while (true) {
				piensa();
				monitor.levantaCubiertos(id);
				come();
				monitor.bajarCubiertos(id);
			}
		} catch (InterruptedException e) {
			System.out.println("Filósofo: " + id + " interrumpido.\n");
		}
	}

	private void piensa() throws InterruptedException {
		System.out.println("Filósofo " + id + " está pensando.\n");
		Thread.sleep (numGenerator.nextInt(10));
	}

	private void come() throws InterruptedException {
		// En esta linea se realizó un random en un rango del 0 - 10
		Thread.sleep (numGenerator.nextInt(10));
	}
}