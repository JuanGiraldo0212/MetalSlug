package mundo;

import java.io.File;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

public abstract class Entidad {
	
	public final static char IZQUIERDA = 'I';
	
	public final static char DERECHA = 'D';

	private int posY;
	private int posX;
	private int posXIni;
	private int posYIni;
	private char direccion;
	private String imagenActual;
	
	public int getPosY() {
		return posY;
	}
	public void setPosY(int posY) {
		this.posY = posY;
	}
	public int getPosX() {
		return posX;
	}
	public void setPosX(int posX) {
		this.posX = posX;
	}
	public int getPosXIni() {
		return posXIni;
	}
	public void setPosXIni(int posXIni) {
		this.posXIni = posXIni;
	}
	public int getPosYIni() {
		return posYIni;
	}
	public void setPosYIni(int posYIni) {
		this.posYIni = posYIni;
	}
	public char getDireccion() {
		return direccion;
	};
	public void setDireccion(char direccion) {
		this.direccion = direccion;
	}
	
	public String getImagenActual() {
		return imagenActual;
	}
	public void setImagenActual(String imagenActual) {
		this.imagenActual = imagenActual;
	}
	
public void playSound(String ruta){
		
		try {
			File audioFile = new File(ruta);
			AudioInputStream ais = AudioSystem.getAudioInputStream(audioFile);
			AudioFormat format =ais.getFormat();
			DataLine.Info info=new DataLine.Info(Clip.class, format);
			Clip sound = (Clip)AudioSystem.getLine(info);
			sound.open(ais);
			sound.start();
			
			//clip.getMicrosecondLength();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
