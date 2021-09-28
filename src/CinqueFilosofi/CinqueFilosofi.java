package CinqueFilosofi;
import java.util.concurrent.Semaphore;

public class CinqueFilosofi 
{
	public static void main(String[] args) 
	{
		//vettori di filosofi e semafori
		Filosofo[] vFilosofi = new Filosofo[5];
		Semaphore[] vBacchette = new Semaphore[vFilosofi.length];
		
		for (int i = 0; i < vBacchette.length; i++) 		
			//istanziazione del semaforo ad 1 in modo che lo usi un solo thread
	        vBacchette[i]=new Semaphore(1);	    
		
		for (int i = 0; i < vFilosofi.length; i++)
		{
			Semaphore BacchettaSx = vBacchette[i];
			Semaphore BacchettaDx = vBacchette[(i + 1) % vBacchette.length];

			//controllo per evitare il deadlock
			//quando arriva all'ultimo filosofo scambia le bacchette passate
			if (i == vFilosofi.length - 1)			
				vFilosofi[i] = new Filosofo(BacchettaDx, BacchettaSx, 2000, 1500);
			else             
            	vFilosofi[i] = new Filosofo(BacchettaSx, BacchettaDx, 2000, 1500);            
            
			//istanziazione e avvio di un thread per ogni filosofo del vettore
            Thread t = new Thread(vFilosofi[i], "Il Filosofo n°" + (i + 1));
            t.start();
        }
	}	
}