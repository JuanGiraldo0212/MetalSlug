package hilos;
import mundo.ControlEnemigos;

public class HiloControlEnemigos extends Thread{

	private ControlEnemigos control;
	
	public HiloControlEnemigos(ControlEnemigos actual){
		control=actual;
	}
	
	@Override
	public void run() {
		super.run();
		while(control.getEscenario().getJugador().isActivo()){
			if(control.getEscenario().getEnemigos().size()==0){
				control.generarEnemigos();
			}
			try {
				sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
}
