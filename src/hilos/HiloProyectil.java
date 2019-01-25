package hilos;

import mundo.Escenario;
import mundo.Proyectil;

public class HiloProyectil extends Thread{

	private Escenario actual;
	
	public HiloProyectil(Escenario j){
		actual=j;
	}
	
	@Override
	public void run() {
		super.run();
		while(actual.getPrimerProyectil()!=null){
			Proyectil evaluar=actual.getPrimerProyectil();
			while(evaluar!=null){
				evaluar.movimiento();
				evaluar=evaluar.getSiguiente();
			}
			try {
				sleep(5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
