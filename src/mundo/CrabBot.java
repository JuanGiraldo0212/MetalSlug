package mundo;

import interfaz.InterfazJuego;

public class CrabBot extends Personaje implements Movimiento,Vida,Danino{
	
	public final static int RANGO_DISPARO=600;
	
	public final static char AIM_DIAGONAL='D';
	
	private Escenario escenario;
	
	private boolean disparando;

	public CrabBot(Escenario e,int posX,int posY,char direccion) {
		escenario=e;
		setactivo(true);
		setVida(120);
		setDireccion(direccion);
		setPosX(posX);
		setPosY(posY);
		setPosYIni(getPosY());
		setPosXIni(getPosX());
		setImagenActual("./data/EnemigosGif/Robot"+getDireccion()+".gif");
		setDisparando(false);
	}
	
	public CrabBot(int posX, int posY, int posXIni, int posYIni, int vida, String imagenActual, boolean activo,
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
	public void Dano() {
		
		if(escenario.getProyectilesCrab().size() == 0){
			if(getDireccion()==DERECHA){
				escenario.getProyectilesCrab().add(new Proyectil(getDireccion(), AIM_DIAGONAL, getPosX()-40,getPosY()-15,Proyectil.CRABBOT,5));
				escenario.iniciarHiloProyectilCrab();
			}else{
				escenario.getProyectilesCrab().add(new Proyectil(getDireccion(), AIM_DIAGONAL, getPosX()+20,getPosY()-15,Proyectil.CRABBOT,5));
				escenario.iniciarHiloProyectilCrab();
			}
		}
		else if(System.currentTimeMillis()-escenario.getProyectilesCrab().get(escenario.getProyectilesCrab().size()-1).getTiempo()>=100){
			if(getDireccion()==DERECHA){
				escenario.getProyectilesCrab().add(new Proyectil(getDireccion(), AIM_DIAGONAL, getPosX()-40,getPosY()-15,Proyectil.CRABBOT,5));
			}else{
				escenario.getProyectilesCrab().add(new Proyectil(getDireccion(), AIM_DIAGONAL, getPosX(),getPosY()-15,Proyectil.CRABBOT,5));
			}
		}
		
	}
		
	public void enRango(){
		if(Math.abs(getPosX()-escenario.getJugador().getPosX())<=RANGO_DISPARO){
			setDisparando(true);
		}
		else{
			setDisparando(false);
		}
	}

	@Override
	public void golpeado() {
		Proyectil actual=escenario.getPrimerProyectil();
		while(actual!=null){
			int posX=actual.getPosX();
			int posY= actual.getPosY();
			if((posX>=getPosX() && posX<=getPosX()+304) && (posY>=getPosY() && posY<=getPosY()+222) && actual.getTipo()==Proyectil.JUGADOR){
				escenario.eleminarProyectil(actual);
				setVida(getVida()-actual.getDano());
			}
			actual=actual.getSiguiente();
		}
		
		if(getVida()<=0){

			escenario.getJugador().setPuntaje(escenario.getJugador().getPuntaje()+40);
			setImagenActual("./data/EnemigosGif/Explotion.gif");
			setPosX(getPosX()+40);
			setactivo(false);
			playSound("./data/Music/Explo1.wav");
		}
		
	}

	@Override
	public void movimiento() {
		if(getDireccion()==DERECHA){
			setPosX(getPosX()+5);
			if(getPosX()>=900){
				setDireccion(IZQUIERDA);
				setImagenActual("./data/EnemigosGif/RobotI.gif");
			}
			
		}
		else if(getDireccion()==IZQUIERDA){
			setPosX(getPosX()-5);
			if(getPosX()<=20){
				setDireccion(DERECHA);
				setImagenActual("./data/EnemigosGif/RobotD.gif");
			}
		}
		
		
	}

	public boolean isDisparando() {
		return disparando;
	}

	public void setDisparando(boolean disparando) {
		this.disparando = disparando;
	}

}
