package mundo;

public class Spider extends Personaje implements Danino, Movimiento, Vida{

	public final static int RANGO_DISPARO=300;
	private boolean estaEnPlataforma;
	private int posicionIdle;
	private int posicionMovimiento;
	
	private Escenario escenario;
	
	private boolean disparando;
	
	
	public Spider(int posX, int posY,Escenario escenario) {
		estaEnPlataforma = false;
		posicionIdle = 1;
		posicionMovimiento = 1;
		this.escenario = escenario;
		disparando = false;
		
		setPosX(posX);
		setPosY(posY);
		setPosXIni(posX);
		setPosYIni(posY);
		setVida(60);
		setImagenActual("./data/Spider/Idle/I1.png");
		setactivo(true);
		setDireccion(Entidad.DERECHA);
		setDestruido(false);

	}
	
	public Spider(int posX, int posY, int posXIni, int posYIni,  int vida, boolean estaEnPlataforma, String imagenActual, boolean activo, char direccion,int posicionMovimiento, int posicionIdle,
			boolean disparando, boolean destruido,Escenario escenario) {
		this.estaEnPlataforma = estaEnPlataforma;
		this.posicionIdle = posicionIdle;
		this.posicionMovimiento = posicionMovimiento;
		this.escenario = escenario;
		this.disparando = disparando;
		
		setPosX(posX);
		setPosY(posY);
		setPosXIni(posXIni);
		setPosYIni(posXIni);
		setVida(vida);
		setEstaEnPlataforma(estaEnPlataforma);
		setImagenActual(imagenActual);
		setactivo(activo);
		setDireccion(direccion);
		setPosicionIdle(posicionIdle);
		setPosicionMovimiento(posicionMovimiento);
		setDisparando(disparando);
		setDestruido(destruido);

	}
	
	public void enRango(){
		if(Math.abs(getPosX()-escenario.getJugador().getPosX())<=RANGO_DISPARO && getPosY()-escenario.getJugador().getPosY() >= 0 && getPosY()-escenario.getJugador().getPosY() <= 80){
			setDisparando(true);
		}
		else{
			setDisparando(false);
		}
	}
	
	public void gravedadAranita(){
		setPosY(getPosY()+5);
		setImagenActual("./data/Spider/Idle/I3.png");
	}
	
	@Override
	public void movimiento() {
		if(getDireccion()==DERECHA){
			
				setPosX(getPosX()+2);
				if(posicionMovimiento<=4){
					
					setImagenActual("./data/Spider/Walk/walk"+posicionMovimiento+DERECHA+".png");
					
					posicionMovimiento++;
				}
				else{
					posicionMovimiento=1;
					
				}
				
				if(getPosX()-getPosXIni()>=80){
					setDireccion(IZQUIERDA);
				}
	
		}else if(getDireccion()==IZQUIERDA){
			
				setPosX(getPosX()-2);
				if(posicionMovimiento<=4){
					setImagenActual("./data/Spider/Walk/walk"+posicionMovimiento+IZQUIERDA+".png");
					posicionMovimiento++;
				}
				else{
					posicionMovimiento=1;
				
				}
				
				if(getPosX()-getPosXIni()<=-30){
					setDireccion(DERECHA);
				}
		}
	}
	public void idle() {
		if(posicionIdle<=3){
			setImagenActual("./data/Spider/Idle/I"+posicionIdle+".png");
			posicionIdle++;
		}	
		else{
			posicionIdle=1;
		}
		
	}
	
	public void Dano() {
		if(escenario.getProyectilesSpider().size() == 0){
			if(getDireccion()==DERECHA){
				escenario.getProyectilesSpider().add(new Proyectil(getDireccion(), getDireccion(), getPosX()-40,getPosY()-60,Proyectil.SPIDER,5));
				escenario.iniciarHiloProyectilSpider();
			}else{
				escenario.getProyectilesSpider().add(new Proyectil(getDireccion(), getDireccion(), getPosX()+20,getPosY()-15,Proyectil.SPIDER,5));
				escenario.iniciarHiloProyectilSpider();
			}
		}
		else if(System.currentTimeMillis()-escenario.getProyectilesSpider().get(escenario.getProyectilesSpider().size()-1).getTiempo()>=400){
			if(getDireccion()==DERECHA){
				escenario.getProyectilesSpider().add(new Proyectil(getDireccion(), getDireccion(), getPosX()-40,getPosY()-60,Proyectil.SPIDER,5));
			}else{
				escenario.getProyectilesSpider().add(new Proyectil(getDireccion(), getDireccion(), getPosX(),getPosY()-15,Proyectil.SPIDER,5));
			}
		}
	}



	public boolean isEstaEnPlataforma() {
		return estaEnPlataforma;
	}

	public void setEstaEnPlataforma(boolean estaEnPlataforma) {
		this.estaEnPlataforma = estaEnPlataforma;
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
			if((posX>=getPosX() && posX<=getPosX()+40) && (posY>=getPosY()-10 && posY<=getPosY()+60) && actual.getTipo()==Proyectil.JUGADOR){
				escenario.eleminarProyectil(actual);
				setVida(getVida()-actual.getDano());
			}
			actual=actual.getSiguiente();
		}
		
		if(getVida()<=0){

			escenario.getJugador().setPuntaje(escenario.getJugador().getPuntaje()+10);
			setImagenActual("./data/EnemigosGif/Explotion.gif");
			setactivo(false);
			setPosX(getPosX()-50);
			setPosY(getPosY()-180);
			playSound("./data/Music/Explo1.wav");
		}
	}

	public Escenario getEscenario() {
		return escenario;
	}

	public void setEscenario(Escenario escenario) {
		this.escenario = escenario;
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

	
	
}
