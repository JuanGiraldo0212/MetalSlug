package mundo;

import java.io.Serializable;
import java.util.ArrayList;

public class Puntaje implements Serializable {
	
	private String usuario;
	
	private int puntaje;
	
	private long fecha;
	
	private Puntaje derecha;
	
	private Puntaje izquierda;
	
	public Puntaje(String usuarioP,int puntajeP){
		usuario=usuarioP;
		puntaje=puntajeP;
		fecha=System.currentTimeMillis();
	}
	
public int compararPuntaje(Puntaje actual){
		
		int retorno=0;
		if(puntaje>actual.getPuntaje()){
			retorno=1;
		}
		else if(puntaje<actual.getPuntaje()){
			retorno=-1;
		}
		return retorno;
		
		
	}

public int compararNombre(String actual){
	
	int retorno=0;
	if(usuario.compareToIgnoreCase(actual)>0){
		retorno=1;
	}
	else if(usuario.compareToIgnoreCase(actual)<0){
		retorno=-1;
	}
	return retorno;
}

	public void insertarNuevoPuntaje(Puntaje nuevo) throws UsuarioYaExisteException{
		if(usuario.equalsIgnoreCase(nuevo.getUsuario())){
			throw new UsuarioYaExisteException("Ese usuario ya existe");
		}
		if(fecha>nuevo.getFecha()){
			if(izquierda==null){
				izquierda=nuevo;
			}
			else{
				izquierda.insertarNuevoPuntaje(nuevo);
			}
		}
		else{
			if(derecha==null){
				derecha=nuevo;
			}
			else{
				derecha.insertarNuevoPuntaje(nuevo);
			}
		}
	}
	
	public void inorden(ArrayList<Puntaje> acumulado )
    {
       
        if( izquierda != null )
            izquierda.inorden( acumulado );
       
        acumulado.add(this );
    
        if( derecha != null )
            derecha.inorden( acumulado );
    }

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public int getPuntaje() {
		return puntaje;
	}

	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}

	public long getFecha() {
		return fecha;
	}

	public void setFecha(long fecha) {
		this.fecha = fecha;
	}

	public Puntaje getDerecha() {
		return derecha;
	}

	public void setDerecha(Puntaje derecha) {
		this.derecha = derecha;
	}

	public Puntaje getIzquierda() {
		return izquierda;
	}

	public void setIzquierda(Puntaje izquierda) {
		this.izquierda = izquierda;
	}
	
	

}
