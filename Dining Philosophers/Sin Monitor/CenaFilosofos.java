
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Guillermo Del Río Acevedo
 * ID: 150313
 * guillermo.delrioao@udlap.mx
 * Ricardo Rosas Juárez
 * ID: 150371
 * ricardo.rosasjz@udlap.mx
 * on 25/03/2017.
 */
public class CenaFilosofos {
	// El número de filósofos...
	private static final int NUM_FILOSOFOS = 4;

	public static void main (String[] args) {
		/*Se definen los recursos compartidos (De tipo lock)*/
		Lock[]	tenedores = new ReentrantLock[NUM_FILOSOFOS];

		for (int i = 0; i < NUM_FILOSOFOS; i++) {
			tenedores[i] = new ReentrantLock();
		}
		/*Se crea un arreglo de 5 filosofos*/
		Filosofo[] filosofos = new Filosofo[NUM_FILOSOFOS]; //NUM_FILOSOFOS = 5
		
		for (int i = 0; i < NUM_FILOSOFOS; i++) {	
			filosofos[i] = new Filosofo(i, tenedores[i], tenedores[(i+1)%NUM_FILOSOFOS]); /* Se crea en cada posición del vector un filósofo con tenedores*/

			new Thread(filosofos[i]).start(); /* Se inicializa cada filósofo en threads separados*/

		}
		
		
	}

}
