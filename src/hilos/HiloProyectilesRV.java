package hilos;

import mundo.Escenario;

public class HiloProyectilesRV extends Thread{

	private Escenario actual;
	
	public HiloProyectilesRV(Escenario j){
		actual=j;
	}
	
	@Override
	public void run() {
		super.run();
		while(actual.getproyectilesRobotVolador().size()>0){
			
			for(int i = 0; i < actual.getproyectilesRobotVolador().size(); i++){
				if(actual.getproyectilesRobotVolador().get(i)!=null){
					actual.getproyectilesRobotVolador().get(i).movimiento();
					
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

