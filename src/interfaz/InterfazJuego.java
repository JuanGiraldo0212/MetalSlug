package interfaz;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.swing.*;

import hilos.HiloJugador;
import hilos.HiloFps;
import hilos.HiloProyectil;
import mundo.ControlEnemigos;
import mundo.Escenario;
import mundo.Juego;
import mundo.Jugador;
import mundo.JugadorNoEncontradoException;
import mundo.Proyectil;
import mundo.Puntaje;
import mundo.UsuarioYaExisteException;
public class InterfazJuego extends JFrame{

	public final static int ANCHO=1200;
	public final static int LARGO=980;
	private Juego juego;
	private PanelJuego dibujo;
	private PanelInicio inicio;
	private PanelPuntaje puntaje;
	private JPopupMenu popUp;
	private HiloJugador hilo;
	private HiloFps fps;
	public InterfazJuego(){
		setTitle("Pruebas");
		setLayout(new BorderLayout());
		setSize(new Dimension(ANCHO, LARGO));
		setResizable(false);
		inicio=new PanelInicio(this);
		popUp = new JPopupMenu();
		puntaje=new PanelPuntaje(this);
		juego=new Juego();
		popUp.add(puntaje);
		popUp.setVisible(false);
		add(inicio,BorderLayout.CENTER);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	public static void main(String[] args) {
		
			
			InterfazJuego vent = new InterfazJuego();
			vent.setVisible(true);
			vent.cargarPuntaje();
		
		
		
	}
	
	
	
	public void iniciarJuego(){
		juego.iniciarEscenario();
		remove(inicio);
		dibujo=new PanelJuego(juego,this);
		add(dibujo,BorderLayout.CENTER);
		iniciarHilos();
		dibujo.requestFocusInWindow();
		pack();
		setSize(new Dimension(ANCHO, LARGO));
		juego.stopMusic();
		juego.playMusic("./data/Music/Juego.wav");
		
		
	}
	
	public void cargarNuevoJuego(){
		juego.stopMusic();
		try {
			remove(inicio);
			juego.guardarPuntaje();
			juego=new Juego();
			juego.stopMusic();
			juego.playMusic("./data/Music/Juego.wav");
			juego.cargarPuntaje();
			juego.cargarJuego(Juego.RUTA_GUARDADO);
			dibujo=new PanelJuego(juego,this);
			add(dibujo,BorderLayout.CENTER);
			iniciarHilos();
			dibujo.requestFocusInWindow();
			pack();
			setSize(new Dimension(ANCHO, LARGO));
		} catch (IOException e) {
		
			JOptionPane.showMessageDialog(this, "No se encontro ninguna partida guardada");
		}
	}
	
	public void movimiento(char direccion){
		if(juego.getEscenario().getJugador().isEnMovimiento()){
			if(juego.getEscenario().getJugador().isSaltando()){
				juego.getEscenario().getJugador().setDireccion(direccion);
				juego.getEscenario().getJugador().setSaltoParabolico(true);
			}
		}else if(!juego.getEscenario().getJugador().isEnMovimiento() ){
			juego.getEscenario().getJugador().setDireccion(direccion);
			juego.getEscenario().getJugador().setEnMovimiento(true);
		} 
		}
	
	

	public void detenerMovimiento(){
		juego.getEscenario().getJugador().setEnMovimiento(false);
	
	}
	
	public void saltar(){
		juego.getEscenario().getJugador().setSaltando(true);
	}
	
	public void actualizar(){
		dibujo.repaint();
	}
	
	public void disparar(){
		if(juego.getEscenario().getPrimerProyectil()==null){
			Proyectil uno = new Proyectil(juego.getEscenario().getJugador().getDireccion(), juego.getEscenario().getJugador().getAim(), juego.getEscenario().getJugador().getPosX(), juego.getEscenario().getJugador().getPosY(),Proyectil.JUGADOR,20);
			juego.getEscenario().getJugador().setDisparando(true);
			juego.getEscenario().agregarProyectil(uno);
			juego.getEscenario().iniciarHiloProyectil();
		}
		else{
			Proyectil uno = new Proyectil(juego.getEscenario().getJugador().getDireccion(), juego.getEscenario().getJugador().getAim(), juego.getEscenario().getJugador().getPosX(), juego.getEscenario().getJugador().getPosY(),Proyectil.JUGADOR,20);
			juego.getEscenario().getJugador().setDisparando(true);
			juego.getEscenario().agregarProyectil(uno);
		}
		juego.getEscenario().getJugador().playSound("./data/Music/Shoot1.wav");
	}
	
	public void iniciarHilos(){
		fps=new HiloFps(this);
		fps.start();
		hilo=new HiloJugador(juego);
		hilo.start();
		juego.getEscenario().getHiloControl().start();
	}
	
	public void mostrarPuntajes(){
		popUp.show(this, 700, 500);
		popUp.setVisible(true);
		reportePuntajes();
	}
	
	public void reportePuntajes(){
		puntaje.setTextoArea(juego.reportePuntajes());
	}
	
	public Puntaje reporteJugadores(int reporte){
		ArrayList<Puntaje> actual= juego.darListaJugadores();
		Puntaje current = null;
		if(reporte==1){
			current=actual.get(0);
		}
		else{
			current=actual.get(actual.size()-1);
		}
		return current;
	}
	
	public void guardarPuntaje(){
		if(JOptionPane.showConfirmDialog(this, "Le gustaria guardar su puntaje: ")==JOptionPane.OK_OPTION){
			boolean correcto=false;
			while(!correcto){
				
				try {
					juego.agregarPuntaje(JOptionPane.showInputDialog("Ingrese su usuario"), juego.getEscenario().getJugador().getPuntaje());
					correcto=true;
				} catch (HeadlessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (UsuarioYaExisteException e) {
					JOptionPane.showMessageDialog(this, e.getMessage());
				}
			}
		}
	}
	
	public void cargarPuntaje(){
		juego.cargarPuntaje();
	}
	
	public Juego darJuego(){
		return juego;
	}
	
	public void cambiarPantallaInicio(){
		juego.setEscenario(null);
		remove(dibujo);
		inicio=new PanelInicio(this);
		add(inicio,BorderLayout.CENTER);
		inicio.requestFocusInWindow();
		pack();
		setSize(new Dimension(ANCHO, LARGO));
		juego.playMusic("./data/Music/Menu.wav");
		
	}
	
	public void ordenarPuntajes(){
		juego.ordenarPorPuntaje();
	}
	
	public Puntaje buscarUsuario(String nombre) throws JugadorNoEncontradoException{
		juego.ordenarPorNombre();
		return 	juego.buscarPuntaje(nombre);
		
	}
	
	public void salir(){
		juego.guardarPuntaje();		
	}
	
	public void guardarAArchivo(){
		try{
		juego.guardarJuego(Juego.RUTA_GUARDADO);
		JOptionPane.showMessageDialog(this, "El nivel fue guardado satisfactoriamente");
		}catch(Exception ioexc){
		JOptionPane.showMessageDialog(this, "Problemas guardando el archivo\nEs probable que no tenga permisos de escritura o\nel archivo puede estar bloqueado por otro programa.");
		}
				
	}
	
	public void cargarArchivo(){
		
	}
	
	@Override
	public void dispose() {
		salir();
		System.exit(0);
	}
}
	
