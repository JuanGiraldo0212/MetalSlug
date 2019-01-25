package hilos;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class HiloMusica implements Runnable{

	private String ruta;
	public HiloMusica(String ruta){
		this.ruta=ruta;
		
	}
	public void run() {
		try{
		Clip clip = AudioSystem.getClip();
        AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(ruta));
        clip.open(inputStream);
        clip.start(); 
    } catch (Exception e) {
        System.out.println("play sound error: " + e.getMessage() + " for " + ruta);
    }
		
	}
}


