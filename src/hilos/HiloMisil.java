package hilos;

import mundo.Escenario;

public class HiloMisil extends Thread{

	private Escenario actual;
	
	public HiloMisil(Escenario a) {
		
	actual = a;	
		
	}
	
	@Override
	public void run() {
		super.run();
		while(actual.getProyectilesBoss().size()!=0){
			
		for(int i = 0; i < actual.getProyectilesBoss().size(); i++){
			actual.getProyectilesBoss().get(i).golpeado();
			actual.getProyectilesBoss().get(i).movimiento();
			actual.getProyectilesBoss().get(i).animacionDisp();
			if(!actual.getProyectilesBoss().get(i).isActivo()){
			actual.getProyectilesBoss().get(i).explosion();	
			actual.getProyectilesBoss().get(i).setDestruido(true);
			}
			
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
	

