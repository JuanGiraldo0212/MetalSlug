package hilos;

import interfaz.InterfazJuego;

public class HiloFps extends Thread{

	private InterfazJuego ventana;
	private boolean hiloActivo;
	
	public HiloFps(InterfazJuego v){
		ventana=v;
		hiloActivo = true;
	}
	
	@Override
    public void run() {
        super.run();
        int contador=30;
        while(hiloActivo){
            if(contador==30){
                ventana.actualizar();
                contador=0;
            }
            if(ventana.darJuego().getEscenario().getJugador().getDestruido()){
                try {
                    sleep(100);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                ventana.guardarPuntaje();
                hiloActivo=false;
            }
            try {
                ventana.actualizar();
                sleep(33);
                contador++;
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }
        }
    }
}
