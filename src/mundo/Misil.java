package mundo;

public class Misil extends Proyectil implements Vida{

	private int posicionMisil;
	private int vida;
	private Escenario escenario;
	private boolean destruido;
	
	public Misil(char direccion, char view, int x, int y, char tipoP, int danoA , Escenario escenario) {
		super(direccion, view, x, y, tipoP, danoA);
		this.escenario = escenario;
		setDireccion(direccion);
		setPosXIni(x+200);
		setPosYIni(y+190);
		setPosX(getPosXIni());
		setPosY(getPosYIni());
		setTipo(tipoP);
		setDano(danoA);
		setRango(500);
		setActivo(true);
		setTiempo(System.currentTimeMillis());
		destruido = false;
		posicionMisil = 1;
		setImagenActual("./data/BossHelicopter/Misiles/Misil"+posicionMisil+".png");
		setVida(40);
		
	}
	
	public void animacionDisp(){
		if(posicionMisil <=17){
			setImagenActual("./data/BossHelicopter/Misiles/Misil"+posicionMisil+".png");
			posicionMisil++;
		}
	}
	
	public void explosion(){
			setImagenActual("./data/EnemigosGif/Explotion.gif");
		
	}

	@Override
	public void golpeado() {
		Proyectil actual=escenario.getPrimerProyectil();
		while(actual!=null){
			int posX=actual.getPosX();
			int posY= actual.getPosY();
			if((posX>=getPosX() && posX<=getPosX()+50) && (posY>=getPosY() && posY<=getPosY()+250) && actual.getTipo()==Proyectil.JUGADOR){
				escenario.eleminarProyectil(actual);
				setVida(getVida()-actual.getDano());
			}
			actual=actual.getSiguiente();
		}
		
		if(getVida()<=0){
			escenario.getJugador().setPuntaje(escenario.getJugador().getPuntaje()+10);
			setImagenActual("./data/EnemigosGif/Explotion.gif");
			setDestruido(true);
			setPosX(getPosX()-30);
			setPosY(getPosY()-70);
			playSound("./data/Music/Explo1.wav");
		}
	}

	public int getVida() {
		return vida;
	}

	public void setVida(int vida) {
		this.vida = vida;
	}

	public boolean isDestruido() {
		return destruido;
	}

	public void setDestruido(boolean destruido) {
		this.destruido = destruido;
	}

}
