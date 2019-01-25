package hilos;


import mundo.Escenario;

public class HiloBloques extends Thread{
	
	private Escenario escenario;
	
	public HiloBloques(Escenario e){
		escenario=e;
	}
	
	@Override
	public void run() {
		super.run();
		while(escenario.getObstaculosDaninos().size() != 0){

			for(int i = 0; i < escenario.getObstaculosDaninos().size(); i++){
				escenario.getObstaculosDaninos().get(i).Dano();
				if(escenario.getObstaculosDaninos().get(i).isEnEspera()){
					try {
						sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					escenario.getObstaculosDaninos().get(i).setEnEspera(false);
				}
			}
		}
	}

}
