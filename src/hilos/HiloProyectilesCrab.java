package hilos;

import mundo.Escenario;

public class HiloProyectilesCrab extends Thread{
	
	private Escenario actual;
	
	public HiloProyectilesCrab(Escenario e){
		actual=e;
		
	}
	
	@Override
	public void run() {
		super.run();
		while(actual.getProyectilesCrab().size()>0){
			
			
			for(int i = 0; i < actual.getProyectilesCrab().size(); i++){
				if(actual.getProyectilesCrab().get(i)!=null){
					actual.getProyectilesCrab().get(i).movimiento();
					
				}
			}
			try {
				sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
	}
}
