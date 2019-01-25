package hilos;

import mundo.CrabBot;

public class HiloCrabBot extends Thread{
	
	private CrabBot crab;
	
	public HiloCrabBot(CrabBot crabP){
		crab=crabP;
		
	}
	
	@Override
	public void run() {
		super.run();
		while(crab.getactivo()){
			crab.movimiento();
			crab.golpeado();
			crab.enRango();
			if(crab.isDisparando()){
				crab.Dano();
			}
			try {
				sleep(25);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			sleep(400);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		crab.setDestruido(true);
	}

}
