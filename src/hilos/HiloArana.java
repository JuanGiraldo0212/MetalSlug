package hilos;

import mundo.Escenario;
import mundo.Spider;

public class HiloArana extends Thread{

	private Spider arana;
	
	public HiloArana(Spider a) {		
		arana = a;
		
	}
	
	public void run(){
		super.run();
		while(arana.getactivo()){
		if(arana.getEscenario().estaEnPlataformaArana(arana)){
		arana.movimiento();
		arana.enRango();
		arana.golpeado();
		if(arana.isDisparando()){
		arana.Dano();
		}
		try {
			sleep(30);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		}else{
		arana.gravedadAranita();
		try {
			sleep(15);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		}
		try {
			sleep(400);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		arana.setDestruido(true);
	}
	
}
