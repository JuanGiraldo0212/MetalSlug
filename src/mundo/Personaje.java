package mundo;

public abstract class Personaje extends Entidad{
	
	private boolean activo;
	private boolean destruido;
	private int vida;
	
	
	public boolean getactivo() {
		return activo;
	}
	public void setactivo(boolean activo) {
		this.activo = activo;
	}
	public int getVida() {
		return vida;
	}
	public void setVida(int vida) {
	if(vida<=0){
		this.vida=0;
	}
	else{
		
		this.vida = vida;
	}
	}
	

	public boolean getDestruido() {
		return destruido;
	}
	public void setDestruido(boolean destruido) {
		this.destruido = destruido;
	}
	
	
}
