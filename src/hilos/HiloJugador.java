package hilos;

import mundo.Juego;

public class HiloJugador extends Thread{

	private Juego actual;
	
	public HiloJugador(Juego a){
		actual=a;
		
	}
	
	@Override
	public void run() {
		super.run();
		while(!actual.getEscenario().getJugador().getDestruido()){
		while(actual.getEscenario().getJugador().isActivo()){
			actual.getEscenario().getJugador().golpeado();
			if(actual.getEscenario().estaEnPlataforma()){
				actual.getEscenario().getJugador().setBajando(false);
				while(actual.getEscenario().getJugador().isSaltando()){
					actual.getEscenario().getJugador().golpeado();
					actual.getEscenario().getJugador().saltar();
					if(actual.getEscenario().getJugador().isSaltoParabolico()){
						if(actual.getEscenario().getJugador().isDisparando()){
							actual.getEscenario().getJugador().disparar();
							actual.getEscenario().getJugador().movimientoEnSalto();
							try {
								sleep(20);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						else{
						actual.getEscenario().getJugador().movimientoEnSalto();
						}
					}
						try {
							sleep(5);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					if(actual.getEscenario().getJugador().isDisparando()){
							actual.getEscenario().getJugador().disparar();
							try {
								sleep(30);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
					
						actual.getEscenario().getJugador().setEnMovimiento(false);
					}
					try {
						sleep(4);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
				}
				actual.getEscenario().getJugador().detenerSaltoParabolico();
		
				if(actual.getEscenario().getJugador().isEnMovimiento()){
					if(actual.getEscenario().getJugador().isDisparando()){
						actual.getEscenario().getJugador().setEnMovimiento(false);
						actual.getEscenario().getJugador().disparar();
						try {
							sleep(50);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						actual.getEscenario().getJugador().setEnMovimiento(true);
					}
					else{
						
						actual.getEscenario().getJugador().movimiento();
						try {
							sleep(50);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
				else if(actual.getEscenario().getJugador().isDisparando()){
					
						actual.getEscenario().getJugador().disparar();
						try {
							sleep(50);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					
				}else{
					actual.getEscenario().getJugador().idle();
					try {
						sleep(120);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}else{
				actual.getEscenario().getJugador().gravedadJugador();
				if(actual.getEscenario().getJugador().isSaltoParabolico()){
					if(actual.getEscenario().getJugador().isDisparando()){
						actual.getEscenario().getJugador().disparar();
						try {
							sleep(30);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					actual.getEscenario().getJugador().movimientoEnSalto();
					try {
						sleep(3);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				if(actual.getEscenario().getJugador().isDisparando()){
					actual.getEscenario().getJugador().disparar();
					try {
						sleep(30);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				try {
					sleep(15);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		if(!actual.getEscenario().getJugador().isGano()){
			
			actual.getEscenario().getJugador().muerte();
			actual.stopMusic();
			
		}
		else{
			actual.getEscenario().getJugador().setDestruido(true);
			
		}
		try {
			sleep(30);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		}
		
	}
}
