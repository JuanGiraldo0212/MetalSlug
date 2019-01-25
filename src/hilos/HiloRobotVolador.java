package hilos;

import mundo.RobotVolador;

public class HiloRobotVolador extends Thread{

	private RobotVolador rob;
	
	
	public HiloRobotVolador(RobotVolador v){
		rob=v;
	}
	
	@Override
	public void run() {
		super.run();
		while(rob.getactivo()){
			rob.movimiento();
			rob.golpeado();
			rob.enRango();
			if(rob.isDisparando()){
				rob.Dano();
			}
			try {
				sleep(35);
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
		rob.setDestruido(true);
	}
}
