package mundo;

public class ObsDanino extends Obstaculo implements Danino{

	
	public static final int DANO = 20;
	
	private Escenario escenario;
	private boolean enEspera;
	
	public ObsDanino(int posX, int posY,String textura, Escenario e){
		escenario = e;
		setImagenActual(textura);
		setPosX(posX);
		setPosY(posY);
		setEnEspera(false);
	}

	@Override
	public void Dano() {
		
		if((escenario.getJugador().getPosX()>getPosX()-50 && escenario.getJugador().getPosX() - getPosX() < 50)
			&& (getPosY()-escenario.getJugador().getPosY() < 80 && escenario.getJugador().getPosY()-getPosY() < 25)){
			escenario.getJugador().setVida(escenario.getJugador().getVida()-DANO);
			escenario.getJugador().playSound("./data/Music/Damage.wav");
			setEnEspera(true);
			
		}
	}

	public boolean isEnEspera() {
		return enEspera;
	}

	public void setEnEspera(boolean enEspera) {
		this.enEspera = enEspera;
	}
	
	
}
