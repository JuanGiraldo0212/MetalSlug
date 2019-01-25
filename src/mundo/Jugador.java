package mundo;

import interfaz.InterfazJuego;

public class Jugador extends Personaje implements Movimiento, Vida, Danino{
	
	public final static int SALTO_MAXIMO=120;
	
	public final static int INC_CORRE_JUGADOR = 10;
	
	public final static int INC_SALTO_JUGADOR = 2;
	
	public final static int INC_SALTOPAR_JUGADOR = 3;
	
	public final static char AIM_UP='U';
	
	public final static char AIM_DOWN='A';
	
	public final static char NO_AIM='N';
	
	private boolean enMovimiento;
	
	private boolean activo;
	
	private boolean saltando;
	
	private boolean bajando;
	
	private boolean saltoParabolico;
	
	private boolean subiendo;
	
	private boolean disparando;
	
	private boolean estaEnPlataforma;
	
	private char aim;
	
	private int posiccionActualMovimiento;
	
	private int posicionActualIdle;
	
	private int poscicionActualDisparo;
	
	private int puntaje;
	
	private Escenario escenario;
	
	private int posicionMuerte;
	
	private boolean gano;

	public Jugador(Escenario e){
		escenario=e;
		setVida(100);
		setPosX(520);
		setPosY(615);
		setActivo(true);
		enMovimiento=false;
		saltando=false;
		bajando=false;
		setGano(false);
		setPosXIni(getPosX());
		setPosYIni(getPosY());
		setDireccion(IZQUIERDA);
		aim=NO_AIM;
		posiccionActualMovimiento=1;
		posicionActualIdle=1;
		poscicionActualDisparo=1;
		setImagenActual("./data/Jugador/Iddle/Idle1D.png");
		posicionMuerte = 1;
	}
	

	public Jugador(int posX, int posY, int posXIni, int posYIni, int vida, boolean estaEnPlataforma,
			String imagenActual, boolean activo, char direccion, int posicionMovimiento, int posicionIdle,
			boolean disparando, boolean enMovimiento, boolean saltando, boolean bajando, boolean saltoParabolico,
			boolean subiendo, char aim, int posicionActualDisparo, int enemigosMuertos,Escenario escenario, int posicionMuerte) {
		this.escenario=escenario;
		setVida(vida);
		setPosX(posX);
		setPosY(posY);
		setActivo(activo);
		setEstaEnPlataforma(estaEnPlataforma);
		this.enMovimiento=enMovimiento;
		this.saltando=saltando;
		this.bajando=bajando;
		setPosXIni(posXIni);
		setPosYIni(posYIni);
		setDireccion(direccion);
		this.aim=aim;
		this.posiccionActualMovimiento=posicionMovimiento;
		this.posicionActualIdle=posicionIdle;
		this.poscicionActualDisparo=posicionActualDisparo;
		puntaje =enemigosMuertos;
		setImagenActual(imagenActual);
		this.posicionMuerte = posicionMuerte;
	}
	
	public void movimiento(){
		if(getDireccion()==DERECHA){
			if(getPosX()<=InterfazJuego.ANCHO-80){
				setPosX(getPosX()+INC_CORRE_JUGADOR);
				if(posiccionActualMovimiento<=11){
					
					setImagenActual("./data/Jugador/Running/Run"+posiccionActualMovimiento+DERECHA+".png");
					
					posiccionActualMovimiento++;
				}
				else{
					posiccionActualMovimiento=1;
				}
			}else{
				idle();
			}
	
		}
		else if(getDireccion()==IZQUIERDA){
			if(getPosX()>=0){
				setPosX(getPosX()-INC_CORRE_JUGADOR);
				if(posiccionActualMovimiento<=11){
					setImagenActual("./data/Jugador/Running/Run"+posiccionActualMovimiento+IZQUIERDA+".png");
					posiccionActualMovimiento++;
				}
				else{
					posiccionActualMovimiento=1;
				}
			}else{
				idle();
			}
			
			
		}
	}
	
	public void movimientoEnSalto(){
		if(getDireccion()==DERECHA){
			if(getPosX()<=InterfazJuego.ANCHO-80){
				setPosX(getPosX()+INC_SALTOPAR_JUGADOR);
			}
		}else if(getDireccion() == IZQUIERDA){
			if(getPosX()>=0){
				setPosX(getPosX()-INC_SALTOPAR_JUGADOR);
			}
		}
	}
	
	public void idle(){
			if(aim!=NO_AIM){
				setImagenActual("./data/Jugador/View/"+aim+getDireccion()+".png");
			}
			else{
				if(posicionActualIdle<=6){
					setImagenActual("./data/Jugador/Iddle/Idle"+posicionActualIdle+getDireccion()+".png");
					posicionActualIdle++;
				}	
				else{
					posicionActualIdle=1;
				}
			}
	}
	
	
	public void saltar(){
		
		if(getPosYIni()-getPosY()<SALTO_MAXIMO && !bajando){
			
			setPosY(getPosY()-INC_SALTO_JUGADOR);
			if(getPosYIni()-getPosY()>=0 && getPosYIni()-getPosY()<=(SALTO_MAXIMO/3)-35){
				
			setImagenActual("./data/Jugador/Jump/jump1"+getDireccion()+".png");
			}
			else if(getPosYIni()-getPosY()>5 && getPosYIni()-getPosY()<=(SALTO_MAXIMO*2/3)-20){	
				setImagenActual("./data/Jugador/Jump/jump2"+getDireccion()+".png");
			}
			else if(getPosYIni()-getPosY()>40 && getPosYIni()-getPosY()<=SALTO_MAXIMO){
			
				setImagenActual("./data/Jugador/Jump/jump3"+getDireccion()+".png");
				if(getPosYIni()-getPosY()==SALTO_MAXIMO && !saltoParabolico){
					enMovimiento=false;
				}
			
			}
		}else{
			gravedadJugador();
			
		}	
		
	}
	
	public void gravedadJugador(){
		if(estaEnPlataforma){
			saltando=false;
		}
		bajando=true;
		setPosY(getPosY()+5);
		if(enMovimiento){
		setSaltoParabolico(true);
		}
		if(activo){
			
			setImagenActual("./data/Jugador/Jump/jump3"+getDireccion()+".png");
		}
		else{
			setImagenActual("./data/Jugador/Dead/dead4"+getDireccion()+".png");
		}
	}
	
	public void muerte(){
		if(posicionMuerte<=4){
			setImagenActual("./data/Jugador/Dead/dead"+posicionMuerte+getDireccion()+".png");
			posicionMuerte++;
		}
		else{
			
			
				
				setDestruido(true);
			
		}
	}

	public void disparar(){
		
		if(aim==AIM_UP){
			if(poscicionActualDisparo<=2){
				setImagenActual("./data/Jugador/Shoot/fire"+poscicionActualDisparo+"U"+getDireccion()+".png");
				poscicionActualDisparo++;
			}
			else{
				poscicionActualDisparo=1;
				disparando=false;
			}
		}
		else if(aim==AIM_DOWN){
			if(poscicionActualDisparo<=3){
				setImagenActual("./data/Jugador/Shoot/CrouchFire"+poscicionActualDisparo+getDireccion()+".png");
				poscicionActualDisparo++;
			}
			else{
				poscicionActualDisparo=1;
				disparando=false;
			}
		}
		else{
		if(poscicionActualDisparo<=4){
			setImagenActual(saltando || !estaEnPlataforma?"./data/Jugador/JumpShoot/jumpShoot"+poscicionActualDisparo+getDireccion()+".png":"./data/Jugador/Shoot/fire"+poscicionActualDisparo+getDireccion()+".png");
			poscicionActualDisparo++;
		}
		else{
			poscicionActualDisparo=1;
			disparando=false;
		}
		}
		
	}

	
	public void detenerSaltoParabolico(){
		saltoParabolico=false;
	}


	

	public boolean isEnMovimiento() {
		return enMovimiento;
	}

	public void setEnMovimiento(boolean enMovimiento) {
		if(!enMovimiento){
			posiccionActualMovimiento=2;
		}
		this.enMovimiento = enMovimiento;
		
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public boolean isSaltando() {
		return saltando;
	}

	public void setSaltando(boolean saltando) {
		this.saltando = saltando;
	}

	public int getPosiccionActual() {
		return posiccionActualMovimiento;
	}

	public void setPosiccionActual(int posiccionActual) {
		this.posiccionActualMovimiento = posiccionActual;
	}

	public boolean isSaltoParabolico() {
		return saltoParabolico;
	}

	public void setSaltoParabolico(boolean saltoParabolico) {
		this.saltoParabolico = saltoParabolico;
	}

	public boolean isDisparando() {
		return disparando;
	}

	public void setDisparando(boolean disparando) {
		this.disparando = disparando;
	}

	public char getAim() {
		return aim;
	}

	public void setAim(char aim) {
		this.aim = aim;
	}

	public boolean isBajando() {
		return bajando;
	}

	public void setBajando(boolean bajando) {
		this.bajando = bajando;
	}



	public int getPuntaje() {
		return puntaje;
	}



	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}



	public boolean isEstaEnPlataforma() {
		return estaEnPlataforma;
	}



	public void setEstaEnPlataforma(boolean estaEnPlataforma) {
		this.estaEnPlataforma = estaEnPlataforma;
	}



	public boolean isSubiendo() {
		return subiendo;
	}



	public void setSubiendo(boolean subiendo) {
		this.subiendo = subiendo;
	}



	public int getPoscicionActualDisparo() {
		return poscicionActualDisparo;
	}



	public void setPoscicionActualDisparo(int poscicionActualDisparo) {
		this.poscicionActualDisparo = poscicionActualDisparo;
	}


	@Override
	public void golpeado() {
		if(escenario.getBoss()!=null){
			
			int x=escenario.getBoss().getPosX();
			int y=escenario.getBoss().getPosY();
			if((getPosX()>=x && getPosX()<=x+290)&&(getPosY()>=y+80 && getPosY()<=y+250)){
				setVida(getVida()-1);
				playSound("./data/Music/Damage.wav");
			}
		}
		
		for(int i = 0; i < escenario.getproyectilesRobotVolador().size(); i ++){
			Proyectil actual = escenario.getproyectilesRobotVolador().get(i);
			int posX=actual.getPosX();
			int posY= actual.getPosY();
			if((posX>=getPosX() && posX<=getPosX()+96) && (posY>=getPosY() && posY<=getPosY()+96) && actual.getTipo()!=Proyectil.JUGADOR){
				actual.setActivo(false);
				setVida(getVida()-actual.getDano());
				
				playSound("./data/Music/Damage.wav");
			}
		}
		
		for(int i = 0; i < escenario.getProyectilesCrab().size(); i ++){
			Proyectil actual = escenario.getProyectilesCrab().get(i);
			int posX=actual.getPosX();
			int posY= actual.getPosY();
			if((posX>=getPosX() && posX<=getPosX()+96) && (posY>=getPosY() && posY<=getPosY()+96) && actual.getTipo()!=Proyectil.JUGADOR){
				actual.setActivo(false);
				setVida(getVida()-actual.getDano());
				
				playSound("./data/Music/Damage.wav");
			}
		}
		
		for(int i = 0; i < escenario.getProyectilesSpider().size(); i ++){
			Proyectil actual = escenario.getProyectilesSpider().get(i);
			int posX=actual.getPosX();
			int posY= actual.getPosY();
			if((posX>=getPosX() && posX<=getPosX()+96) && (posY>=getPosY() && posY<=getPosY()+96) && actual.getTipo()!=Proyectil.JUGADOR){
				actual.setActivo(false);
				setVida(getVida()-actual.getDano());
				
				playSound("./data/Music/Damage.wav");
			}
		}
		
		for(int i = 0; i < escenario.getProyectilesBoss().size(); i ++){
			Proyectil actual = escenario.getProyectilesBoss().get(i);
			int posX=actual.getPosX();
			int posY= actual.getPosY();
			if((posX>=getPosX() && posX<=getPosX()+60) && (posY>=getPosY() && posY<=getPosY()+260) && actual.getTipo()!=Proyectil.JUGADOR){
				actual.setActivo(false);
				setVida(getVida()-actual.getDano());
			
				playSound("./data/Music/Damage.wav");
			}
		}
		
		if(getVida()<=0){
			setImagenActual("./data/Jugador/Dead/DeadI.gif");
			setActivo(false);
		}

	}



	public int getPosiccionActualMovimiento() {
		return posiccionActualMovimiento;
	}



	public void setPosiccionActualMovimiento(int posiccionActualMovimiento) {
		this.posiccionActualMovimiento = posiccionActualMovimiento;
	}



	public int getPosicionActualIdle() {
		return posicionActualIdle;
	}



	public void setPosicionActualIdle(int posicionActualIdle) {
		this.posicionActualIdle = posicionActualIdle;
	}




	public int getPosicionMuerte() {
		return posicionMuerte;
	}




	public void setPosicionMuerte(int posicionMuerte) {
		this.posicionMuerte = posicionMuerte;
	}




	@Override
	public void Dano() {
		if(escenario.getPrimerProyectil()==null){
			Proyectil uno = new Proyectil(getDireccion(), aim, getPosX(), getPosY(),Proyectil.JUGADOR,20);
			disparando = true;
			escenario.agregarProyectil(uno);
			escenario.iniciarHiloProyectil();
		}
		else{
			Proyectil uno = new Proyectil(getDireccion(), aim, getPosX(), getPosY(),Proyectil.JUGADOR,20);
			disparando =true;
			escenario.agregarProyectil(uno);
		}
	}




	public boolean isGano() {
		return gano;
	}




	public void setGano(boolean gano) {
		this.gano = gano;
	}
	
	
	
	
}
