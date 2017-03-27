
import java.util.Random;
import java.util.concurrent.locks.Lock;
/**
 * Created by Guillermo Del Río Acevedo
 * ID: 150313
 * guillermo.delrioao@udlap.mx
 * Ricardo Rosas Juárez
 * ID: 150371
 * ricardo.rosasjz@udlap.mx
 * on 25/03/2017.
 */
class Filosofo implements Runnable {
	// Tiempo en que come un filósofo
	private Random numGenerator = new Random();
	// El Id del filosofo
	private int id;

	//Definición de los recursos compartidos. En este caso son los tenedores de la mesa
	private Lock cubiertoIzquierdo;
	private Lock cubiertoDerecho;


	public Filosofo (int id, Lock cubiertoIzquierdo, Lock cubiertoDerecho) { // Concstrucción del filósofo (con ID y el tenedor izquierdo y derecho)
		this.id = id;
		this.cubiertoIzquierdo = cubiertoIzquierdo;
		this.cubiertoDerecho = cubiertoDerecho;
	}


	public void run() {/*Ejecución del thread: piensa, levanta cubiertos, come y baja cubiertos*/
		try {
			while (true) {
				piensa();
				levantaCubiertoIzquierdo();
				levantaCubiertoDerecho();
				come();
				bajaCubiertos();
			}
		} catch (InterruptedException e) {
			System.out.println("El Filósofo " + id + " fue interrumpido.\n");			
		}
	}


	private void piensa() throws InterruptedException {
		System.out.println("El Filósofo " + id + " está pensando.\n");
		System.out.flush(); /*Se hace un flush del buffer de la consola*/
		Thread.sleep (numGenerator.nextInt(10)); /*El filosofo se duerme por un tiempo aleatorio*/
	}

	/** 
	 * El filósofo ocupa el cubierto (Recurso compartido)
	 */
	private void levantaCubiertoIzquierdo() {
		cubiertoIzquierdo.lock(); /*El filósofo levanta el cubierto izquierdo para comer (Hace un lock del recurso para que nadie más pueda ocuparlo)*/
		System.out.println("El filósofo " + id + " tiene en la mano un cubierto.\n");
	}

	/** 
	 * El filósofo ocupa el cubierto (Recurso compartido)
	 */
	private void levantaCubiertoDerecho() {
		//El filósofo levanta el cubierto derecho para comer (Hace un lock del recurso para que nadie más pueda ocuparlo)
		cubiertoDerecho.lock();
	}

	/**
	 * El filósofo come...
	 * @throws InterruptedException
	 */
	private void come() throws InterruptedException {
		System.out.println("El filósofo " + id + " está comiendo.\n");
		Thread.sleep (numGenerator.nextInt(10)); /*El filósofo come por un timepo aleatorio*/
	}

	/**
	 * El filósofo baja los cubiertos
	 */
	private void bajaCubiertos() { /*Se desbloquean los recursos, es decir, el filósofo deja de comer y libera ambos cubiertos*/
		cubiertoIzquierdo.unlock();
		cubiertoDerecho.unlock();
		
	}
}