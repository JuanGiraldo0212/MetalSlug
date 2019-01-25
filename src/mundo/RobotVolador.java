package mundo;

import interfaz.InterfazJuego;

public class RobotVolador extends Personaje implements Movimiento,Vida,Danino{
	
	public final static int RANGO_DISPARO=600;
	
	private Escenario escenario;
	
	private boolean disparando;
	
	public RobotVolador(Escenario e,int posX,int posY) {
		escenario=e;
		setactivo(true);
		setVida(80);
		if(escenario.getJugador().getDireccion()==Entidad.DERECHA){
			setDireccion(Entidad.IZQUIERDA);	
		}
		else{
			setDireccion(Entidad.DERECHA);
		}
		if(getDireccion()==Entidad.IZQUIERDA){
			setPosXIni(InterfazJuego.ANCHO-200);	
		}
		else{
			setPosXIni(0);
		}
		setPosX(posX);
		setPosY(posY);
		setPosYIni(getPosX());
		setPosXIni(getPosY());
		setImagenActual("./data/EnemigosGif/FlyingRobot.gif");
		disparando=false;
	}

	public RobotVolador(int posX, int posY, int posXIni, int posYIni, int vida, String imagenActual, boolean activo,
			char direccion, boolean disparando, boolean destruido, Escenario escenario) {
		this.escenario=escenario;
		setactivo(activo);
		setVida(vida);
		setDireccion(direccion);
		setPosYIni(posYIni);
		setPosXIni(posXIni);
		setPosX(posX);
		setPosY(posY);
		setImagenActual(imagenActual);
		this.disparando=disparando;
		setDestruido(destruido);
	}

	@Override
	public void movimiento() {
		
				if(escenario.getJugador().isEnMovimiento()){
					
					if(escenario.getJugador().getPosX()-getPosX()>0){
						setPosX(getPosX()+8);
						
					}
					else if(escenario.getJugador().getPosX()-getPosX()<0){
						setPosX(getPosX()-8);
						
					}	
				}
			
	}
	
	public void enRango(){
		if(Math.abs(getPosX()-escenario.getJugador().getPosX())<=RANGO_DISPARO){
			disparando=true;
		}
		else{
			disparando=false;
		}
	}


	public boolean isDisparando() {
		return disparando;
	}

	public void setDisparando(boolean disparando) {
		this.disparando = disparando;
	}

	@Override
	public void golpeado() {
		Proyectil actual=escenario.getPrimerProyectil();
		while(actual!=null){
			int posX=actual.getPosX();
			int posY= actual.getPosY();
			if((posX>=getPosX() && posX<=getPosX()+119) && (posY>=getPosY() && posY<=getPosY()+121) && actual.getTipo()==Proyectil.JUGADOR){
				escenario.eleminarProyectil(actual);
				setVida(getVida()-actual.getDano());
			}
			actual=actual.getSiguiente();
		}
		
		if(getVida()<=0){
			escenario.getJugador().setPuntaje(escenario.getJugador().getPuntaje()+20);
			setImagenActual("./data/EnemigosGif/Explotion.gif");
			setactivo(false);
			setPosX(getPosX()-30);
			setPosY(getPosY()-70);
			playSound("./data/Music/Explo1.wav");
		}
	}

	@Override
	public void Dano() {
			if(escenario.getproyectilesRobotVolador().size() == 0){
				escenario.getproyectilesRobotVolador().add(new Proyectil(Jugador.AIM_DOWN, Jugador.AIM_DOWN, getPosX(),getPosY(),Proyectil.ROBOT_VOLADOR,10));
				escenario.iniciarHiloProyectilEnemigos();
			}else if(System.currentTimeMillis()-escenario.getproyectilesRobotVolador().get(escenario.getproyectilesRobotVolador().size()-1).getTiempo()>400){
				escenario.getproyectilesRobotVolador().add(new Proyectil(Jugador.AIM_DOWN, Jugador.AIM_DOWN, getPosX(),getPosY(),Proyectil.ROBOT_VOLADOR,10));
			}
		
	
	}

	public Escenario getEscenario() {
		return escenario;
	}

	public void setEscenario(Escenario escenario) {
		this.escenario = escenario;
	}
	
	
	
}
