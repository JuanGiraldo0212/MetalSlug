package hilos;

import mundo.Boss;


public class HiloHelicopter extends Thread{
	
	private Boss hel;
	
	public HiloHelicopter(Boss h){
		hel = h;
	}
	
	@Override
	public void run() {
		super.run();
		while(hel.getactivo()){
			hel.golpeado();
			if(hel.isEnAtaque()){
			hel.mecanicaMov();
			try {
				sleep(70);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
			if(!hel.isEnMovimiento() && !hel.isEnAtaque()){
			hel.idle();	
			
			try {
				sleep(80);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}else if(hel.isEnMovimiento()){
			hel.movimiento();
			hel.Dano();
			try {
				sleep(20);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
			
			
		}
		
	}
}
