package CinqueFilosofi;
import java.util.concurrent.Semaphore;

public class Filosofo implements Runnable 
{
	private Semaphore bacchettaSx, bacchettaDx;
	private int tMangia, tPensa;
	
	public Filosofo(Semaphore bacchettaSx, Semaphore bacchettaDx, int tMangia, int tPensa) 
	{
		this.bacchettaDx = bacchettaDx;
		this.bacchettaSx = bacchettaSx;
		this.tMangia = tMangia;
		this.tPensa = tPensa;
	}
	
	
	//metodo per stampare i messaggi di stato e aspettare un tempo passato per parametro
	private void Azione(String azione, int t)
	{		
		//controllo per verificare quale azione si sta svolgendo a seconda del tempo
		//un tempo diverso da 500 indica che il filosofo sta mangiando o pensando
		if(t != 500)
			System.out.println(Thread.currentThread().getName() + azione + " per " + t + " ms");
		else
			System.out.println(Thread.currentThread().getName() + azione);
		
		try 
		{
			Thread.sleep(t);
		} 
		catch (InterruptedException e) 
		{
			
		}
	}
	
	public void run()
	{
		while(true)
			try 
			{				
				//azioni filosofo
				//pensa, acquisizione e rilascio bacchette
				Azione(": pensa", 501 + (int)(Math.random() * tPensa));
				bacchettaSx.acquire();
				Azione(": ha preso la bacchetta sinistra", 500);
				bacchettaDx.acquire();
				Azione(": ha preso la bacchetta destra e mangia", 501 + (int)(Math.random() * tMangia));
				bacchettaDx.release();
				Azione(": ha rilasciato la bacchetta destra", 500);
				bacchettaSx.release();
				Azione(": ha rilasciato la bacchetta sinistra", 500);
			} 
			catch (InterruptedException e) 
			{
				
			}
	}
}
