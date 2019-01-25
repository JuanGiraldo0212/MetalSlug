package mundo;

import java.util.ArrayList;

import hilos.HiloArana;
import hilos.HiloBloques;
import hilos.HiloControlEnemigos;
import hilos.HiloCrabBot;
import hilos.HiloHelicopter;
import hilos.HiloRobotVolador;

public class ControlEnemigos {
	
	private Escenario escenario;
	private int wave;
	
	public ControlEnemigos (Escenario c,int oleada){
		escenario=c;
		wave=oleada;
		
	}
	
	public void generarEnemigos(){
		if(wave==0 && escenario.getEnemigos().size()==0){
			Spider spider1=new Spider(20, -30, escenario);
			HiloArana hiloa1=new HiloArana(spider1);
			hiloa1.start();
			Spider spider2=new Spider(500, -30, escenario);
			HiloArana hiloa2=new HiloArana(spider2);
			hiloa2.start();
			RobotVolador robotVolador =new RobotVolador(escenario,600,20);
			HiloRobotVolador hiloRV1=new HiloRobotVolador(robotVolador);
			hiloRV1.start();
			escenario.getEnemigos().add(spider1);
			escenario.getEnemigos().add(spider2);
			escenario.getEnemigos().add(robotVolador);
			wave=1;
			escenario.getJugador().setVida(100);
			
			
		}
		else if(wave==1){
			Spider spider1=new Spider(20, -30, escenario);
			HiloArana hiloa1=new HiloArana(spider1);
			hiloa1.start();
			Spider spider2=new Spider(500, -30, escenario);
			HiloArana hiloa2=new HiloArana(spider2);
			hiloa2.start();
			Spider spider3=new Spider(700, -30, escenario);
			HiloArana hiloa3=new HiloArana(spider3);
			hiloa3.start();
			RobotVolador robotVolador1 =new RobotVolador(escenario,600,20);
			HiloRobotVolador hiloRV1=new HiloRobotVolador(robotVolador1);
			hiloRV1.start();
			escenario.getEnemigos().add(spider1);
			escenario.getEnemigos().add(spider2);
			escenario.getEnemigos().add(spider3);
			escenario.getEnemigos().add(robotVolador1);
			wave=2;
			escenario.getJugador().setVida(100);
		}
		else if(wave==2){
			Spider spider1=new Spider(20, -30, escenario);
			HiloArana hiloa1=new HiloArana(spider1);
			hiloa1.start();
			Spider spider2=new Spider(500, -30, escenario);
			HiloArana hiloa2=new HiloArana(spider2);
			hiloa2.start();
			Spider spider3=new Spider(700, -30, escenario);
			HiloArana hiloa3=new HiloArana(spider3);
			hiloa3.start();
			Spider spider4=new Spider(900, -30, escenario);
			HiloArana hiloa4=new HiloArana(spider4);
			hiloa4.start();
			RobotVolador robotVolador1 =new RobotVolador(escenario,600,20);
			HiloRobotVolador hiloRV1=new HiloRobotVolador(robotVolador1);
			hiloRV1.start();
			CrabBot crab = new CrabBot(escenario,200,680,Entidad.DERECHA);
			HiloCrabBot hiloCrab = new HiloCrabBot(crab);
			hiloCrab.start();
			escenario.getEnemigos().add(spider1);
			escenario.getEnemigos().add(spider2);
			escenario.getEnemigos().add(spider3);
			escenario.getEnemigos().add(spider4);
			escenario.getEnemigos().add(robotVolador1);
			escenario.getEnemigos().add(crab);
			wave=3;
			escenario.getJugador().setVida(100);
		}
		else if(wave==3){
			Spider spider1=new Spider(20, -30, escenario);
			HiloArana hiloa1=new HiloArana(spider1);
			hiloa1.start();
			Spider spider2=new Spider(500, -30, escenario);
			HiloArana hiloa2=new HiloArana(spider2);
			hiloa2.start();
			Spider spider3=new Spider(700, -30, escenario);
			HiloArana hiloa3=new HiloArana(spider3);
			hiloa3.start();
			Spider spider4=new Spider(900, -30, escenario);
			HiloArana hiloa4=new HiloArana(spider4);
			hiloa4.start();
			Spider spider5=new Spider(200, -30, escenario);
			HiloArana hiloa5=new HiloArana(spider5);
			hiloa5.start();
			RobotVolador robotVolador1 =new RobotVolador(escenario,600,20);
			HiloRobotVolador hiloRV1=new HiloRobotVolador(robotVolador1);
			hiloRV1.start();
			CrabBot crab1= new CrabBot(escenario,200,680,Entidad.DERECHA);
			HiloCrabBot hiloCrab1= new HiloCrabBot(crab1);
			hiloCrab1.start();
			escenario.getEnemigos().add(spider1);
			escenario.getEnemigos().add(spider2);
			escenario.getEnemigos().add(spider3);
			escenario.getEnemigos().add(spider4);
			escenario.getEnemigos().add(spider5);
			escenario.getEnemigos().add(robotVolador1);
			escenario.getEnemigos().add(crab1);
			wave=4;
			escenario.getJugador().setVida(100);
						
		}
		else if(wave==4){
			
			Spider spider1=new Spider(20, -30, escenario);
			HiloArana hiloa1=new HiloArana(spider1);
			hiloa1.start();
			Spider spider2=new Spider(500, -30, escenario);
			HiloArana hiloa2=new HiloArana(spider2);
			hiloa2.start();
			Spider spider3=new Spider(700, -30, escenario);
			HiloArana hiloa3=new HiloArana(spider3);
			hiloa3.start();
			Spider spider4=new Spider(900, -30, escenario);
			HiloArana hiloa4=new HiloArana(spider4);
			hiloa4.start();
			Spider spider5=new Spider(200, -30, escenario);
			HiloArana hiloa5=new HiloArana(spider5);
			hiloa5.start();
			Spider spider6=new Spider(400, 350, escenario);
			HiloArana hiloa6=new HiloArana(spider6);
			hiloa6.start();
			Spider spider7=new Spider(700, 200, escenario);
			HiloArana hiloa7=new HiloArana(spider7);
			hiloa7.start();
			RobotVolador robotVolador1 =new RobotVolador(escenario,600,20);
			HiloRobotVolador hiloRV1=new HiloRobotVolador(robotVolador1);
			hiloRV1.start();
			CrabBot crab1= new CrabBot(escenario,200,680,Entidad.DERECHA);
			HiloCrabBot hiloCrab1= new HiloCrabBot(crab1);
			hiloCrab1.start();
			Boss boss = new Boss(escenario);
			HiloHelicopter helicopter = new HiloHelicopter(boss);
			helicopter.start();
			escenario.setBoss(boss);
			escenario.getEnemigos().add(spider1);
			escenario.getEnemigos().add(spider2);
			escenario.getEnemigos().add(spider3);
			escenario.getEnemigos().add(spider4);
			escenario.getEnemigos().add(spider5);
			escenario.getEnemigos().add(spider6);
			escenario.getEnemigos().add(spider7);
			escenario.getEnemigos().add(robotVolador1);
			escenario.getEnemigos().add(crab1);
			//wave=5;
		}
		
		else if(wave==5){
			Spider spider1=new Spider(20, -30, escenario);
			HiloArana hiloa1=new HiloArana(spider1);
			hiloa1.start();
			Spider spider2=new Spider(500, -30, escenario);
			HiloArana hiloa2=new HiloArana(spider2);
			hiloa2.start();
			Spider spider3=new Spider(700, -30, escenario);
			HiloArana hiloa3=new HiloArana(spider3);
			hiloa3.start();
			Spider spider4=new Spider(900, -30, escenario);
			HiloArana hiloa4=new HiloArana(spider4);
			hiloa4.start();
			Spider spider5=new Spider(200, -30, escenario);
			HiloArana hiloa5=new HiloArana(spider5);
			hiloa5.start();
			RobotVolador robotVolador1 =new RobotVolador(escenario,600,20);
			HiloRobotVolador hiloRV1=new HiloRobotVolador(robotVolador1);
			hiloRV1.start();
			escenario.getEnemigos().add(spider1);
			escenario.getEnemigos().add(spider2);
			escenario.getEnemigos().add(spider3);
			escenario.getEnemigos().add(spider4);
			escenario.getEnemigos().add(spider5);
			escenario.getEnemigos().add(robotVolador1);
			escenario.getJugador().setVida(escenario.getJugador().getVida()+20);
		}
		
	}
	
	public void iniciarEnemigos(ArrayList<Personaje> enemigos,Boss boss){
		for(int i = 0 ; i <enemigos.size();i++){
			if (enemigos.get(i) instanceof Spider) {
				Spider actual = (Spider) enemigos.get(i);
				HiloArana hilo = new HiloArana(actual);
				hilo.start();
			}
			else if (enemigos.get(i) instanceof RobotVolador) {
				RobotVolador actual = (RobotVolador) enemigos.get(i);
				HiloRobotVolador hilo = new HiloRobotVolador(actual);
				hilo.start();
			}
			else if (enemigos.get(i) instanceof CrabBot) {
				CrabBot actual = (CrabBot) enemigos.get(i);
				HiloCrabBot hilo = new HiloCrabBot(actual);
				hilo.start();
			}
		}
		if(boss!=null){
			
			HiloHelicopter hilo = new HiloHelicopter(boss);
			hilo.start();
		}

	}

	public Escenario getEscenario() {
		return escenario;
	}

	public void setEscenario(Escenario escenario) {
		this.escenario = escenario;
	}

	public int getWave() {
		return wave;
	}

	public void setWave(int wave) {
		this.wave = wave;
	}
	

}
