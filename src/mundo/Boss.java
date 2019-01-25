package mundo;

public class Boss extends Personaje implements Vida, Danino, Movimiento{

	private int posicionIdle;
	private int posicionMovimiento;
	private int posicionCompuerta;
	private Escenario escenario;
	
	private boolean enAtaque;
	
	private boolean disparando;
	private boolean enMovimiento;
	
	public Boss(Escenario e) {
		escenario=e;
		setactivo(true);
		setVida(2000);
		setDireccion(DERECHA);
		setPosYIni(-70);
		setPosXIni(700);
		setPosX(getPosXIni());
		setPosY(getPosYIni());
		setImagenActual("./data/BossHelicopter/Idle0.png");
		disparando=false;
		posicionIdle = 0;
		posicionMovimiento = 0;
		enMovimiento = false;
		posicionCompuerta = 0;
		setDestruido(false);
	}


	public Boss(int posX, int posY, int posXIni, int posYIni, int vida, String imagenActual, boolean activo,
			char direccion, boolean disparando, int posicionIdle, int posicionMovimiento, boolean enMovimiento,
			int posicionCompuerta, boolean destruido, Escenario escenario) {
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
		this.posicionIdle = posicionIdle;
		this.posicionMovimiento = posicionMovimiento;
		this.enMovimiento = enMovimiento;
		this.posicionCompuerta = posicionCompuerta;
		setDestruido(destruido);
	}

	public void idle() {
		if(posicionIdle<=7){
			setImagenActual("./data/BossHelicopter/Idle"+posicionIdle+".png");
			posicionIdle++;
		}	
		else{
			posicionIdle=0;
		}
		
	}


	@Override
	public void movimiento() {
		if(getDireccion()==DERECHA){
			
			setPosX(getPosX()+5);
			if(posicionMovimiento<=1){
				
				setImagenActual("./data/BossHelicopter/Movimiento/Mov"+posicionMovimiento+DERECHA+".png");
				
				posicionMovimiento++;
			}
			else{
				posicionMovimiento=0;
				
			}
			
			if(getPosX()>700){
				enMovimiento = false;
				setDireccion(IZQUIERDA);
			}

		}else if(getDireccion()==IZQUIERDA){
		
			setPosX(getPosX()-5);
			if(posicionMovimiento<=1){
				setImagenActual("./data/BossHelicopter/Movimiento/Mov"+posicionMovimiento+IZQUIERDA+".png");
				posicionMovimiento++;
			}
			else{
				posicionMovimiento=0;
			
			}
			
			if(getPosX()<20){
				enMovimiento = false;
//				enAtaque = false;
				setDireccion(DERECHA);
			}
	}
		
	}
	
	public void golpeado() {
		Proyectil actual=escenario.getPrimerProyectil();
		while(actual!=null){
			int posX=actual.getPosX();
			int posY= actual.getPosY();
			if((posX>=getPosX() && posX<=getPosX()+400) && (posY>=getPosY()+30 && posY<=getPosY()+300) && actual.getTipo()==Proyectil.JUGADOR){
				escenario.eleminarProyectil(actual);
				if(!enAtaque || !enMovimiento) setVida(getVida()-actual.getDano());
				
			}
			actual=actual.getSiguiente();
		}
		if(getVida() % 300 == 0){
		enAtaque=true;
		}
		if(getVida()<=0){
			escenario.getJugador().setPuntaje(escenario.getJugador().getPuntaje()+200);
			setImagenActual("./data/EnemigosGif/Explotion.gif");
			setactivo(false);
			setPosX(getPosX()-50);
			setPosY(getPosY()-180);
			escenario.getJugador().setGano(true);
			escenario.getJugador().setActivo(false);
			playSound("./data/Music/Explo1.wav");
		}
		
	}
	
	public void mecanicaMov() {
			if(posicionCompuerta <=5){
				setImagenActual("./data/BossHelicopter/AbrirCompuerta/Compuerta"+posicionCompuerta+".png");
				posicionCompuerta++;
			}else{
				enMovimiento = true;
				enAtaque=false;
			}
		}
		
	


	@Override
	public void Dano() {
		if(escenario.getProyectilesBoss().size() == 0){
			escenario.getProyectilesBoss().add(new Misil(Jugador.AIM_DOWN, Jugador.AIM_DOWN, getPosX(),getPosY(),Proyectil.MISIL,50,escenario));
			escenario.iniciarHiloProyectilBoss();
		}else if(System.currentTimeMillis()-escenario.getProyectilesBoss().get(escenario.getProyectilesBoss().size()-1).getTiempo()>450){
			escenario.getProyectilesBoss().add(new Misil(Jugador.AIM_DOWN, Jugador.AIM_DOWN, getPosX(),getPosY(),Proyectil.MISIL,50, escenario));
		}
	

}


	public int getPosicionIdle() {
		return posicionIdle;
	}


	public void setPosicionIdle(int posicionIdle) {
		this.posicionIdle = posicionIdle;
	}


	public int getPosicionMovimiento() {
		return posicionMovimiento;
	}


	public void setPosicionMovimiento(int posicionMovimiento) {
		this.posicionMovimiento = posicionMovimiento;
	}


	public Escenario getEscenario() {
		return escenario;
	}


	public void setEscenario(Escenario escenario) {
		this.escenario = escenario;
	}


	public boolean isDisparando() {
		return disparando;
	}


	public void setDisparando(boolean disparando) {
		this.disparando = disparando;
	}

	public boolean isEnMovimiento() {
		return enMovimiento;
	}

	public void setEnMovimiento(boolean enMovimiento) {
		this.enMovimiento = enMovimiento;
	}

	public boolean isEnAtaque() {
		return enAtaque;
	}

	public void setEnAtaque(boolean enAtaque) {
		this.enAtaque = enAtaque;
	}

	public int getPosicionCompuerta() {
		return posicionCompuerta;
	}

	public void setPosicionCompuerta(int posicionCompuerta) {
		this.posicionCompuerta = posicionCompuerta;
	}

	
	
	
}
