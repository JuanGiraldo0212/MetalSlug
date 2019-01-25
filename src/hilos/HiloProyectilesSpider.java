package hilos;

import mundo.Escenario;

public class HiloProyectilesSpider extends Thread{

	private Escenario actual;
	
	public HiloProyectilesSpider(Escenario e){
		actual=e;
	}
	
	@Override
	public void run() {
		super.run();
		while(actual.getProyectilesSpider().size()>0){
			
			for(int i = 0; i < actual.getProyectilesSpider().size(); i++){
				if(actual.getProyectilesSpider().get(i)!=null){
					
					actual.getProyectilesSpider().get(i).movimiento();
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

