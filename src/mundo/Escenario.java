package mundo;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import hilos.HiloArana;
import hilos.HiloBloques;
import hilos.HiloControlEnemigos;
import hilos.HiloMisil;
import hilos.HiloProyectil;
import hilos.HiloProyectilesCrab;
import hilos.HiloProyectilesRV;
import hilos.HiloProyectilesSpider;
import sun.audio.*;
public class Escenario {
	
	private ArrayList<ObsNoDanino> obstaculosNoDaninos;
	private ArrayList<ObsDanino> obstaculosDaninos;
	
	private Jugador jugador;
	private ArrayList<Personaje> enemigos;
	private Boss boss;
	private ControlEnemigos control;
	
	private Proyectil primerProyectilJugador;
	private ArrayList<Proyectil> proyectilesRobotVolador;
	private ArrayList<Proyectil> proyectilesSpider;
	private ArrayList<Proyectil> proyectilesCrab;
	private ArrayList<Misil> proyectilesBoss;
	
	private HiloProyectil hProyectil;
	private HiloProyectilesRV hProyectilesRV;
	private HiloProyectilesSpider hProyectilesSpider;
	private HiloProyectilesCrab hProyectilesCrab;
	private HiloMisil hProyectilMisil;
	private HiloBloques hiloBloques;
	private HiloControlEnemigos hiloControl;

	public Escenario( int nivel,int oleada) {
	obstaculosNoDaninos = new ArrayList<ObsNoDanino>();
	obstaculosDaninos = new ArrayList<ObsDanino>();
	jugador = new Jugador(this);
	enemigos = new ArrayList<Personaje>();
	proyectilesRobotVolador = new ArrayList<Proyectil>();
	setProyectilesSpider(new ArrayList<Proyectil>());
	setProyectilesCrab(new ArrayList<Proyectil>());
	proyectilesBoss = new ArrayList<Misil>();
	control = new ControlEnemigos(this,oleada);
	iniciarNivel1();
	
	hiloBloques=new HiloBloques(this);
	hiloBloques.start();
	setHiloControl(new HiloControlEnemigos(control));
	}
	
	public void iniciarNivel1(){
		obstaculosNoDaninos.add(new ObsNoDanino(240,200,"./data/Bloques/Block.png"));
		obstaculosNoDaninos.add(new ObsNoDanino(290,200,"./data/Bloques/Block.png"));
		obstaculosNoDaninos.add(new ObsNoDanino(340,200,"./data/Bloques/Block.png"));
		obstaculosNoDaninos.add(new ObsNoDanino(190,200,"./data/Bloques/Block.png"));
		
		obstaculosNoDaninos.add(new ObsNoDanino(240,350,"./data/Bloques/Block.png"));
		obstaculosNoDaninos.add(new ObsNoDanino(290, 350,"./data/Bloques/Block.png"));
		obstaculosNoDaninos.add(new ObsNoDanino(340, 350,"./data/Bloques/Block.png"));
		obstaculosNoDaninos.add(new ObsNoDanino(400, 350,"./data/Bloques/Block.png"));
		

		obstaculosNoDaninos.add(new ObsNoDanino(530, 275,"./data/Bloques/Block.png"));
		
		obstaculosNoDaninos.add(new ObsNoDanino(710,200,"./data/Bloques/Block.png"));
		obstaculosNoDaninos.add(new ObsNoDanino(760,200,"./data/Bloques/Block.png"));
		obstaculosNoDaninos.add(new ObsNoDanino(810,200,"./data/Bloques/Block.png"));
		obstaculosNoDaninos.add(new ObsNoDanino(860,200,"./data/Bloques/Block.png"));
		
		obstaculosNoDaninos.add(new ObsNoDanino(660,350,"./data/Bloques/Block.png"));
		obstaculosNoDaninos.add(new ObsNoDanino(710, 350,"./data/Bloques/Block.png"));
		obstaculosNoDaninos.add(new ObsNoDanino(760, 350,"./data/Bloques/Block.png"));
		obstaculosNoDaninos.add(new ObsNoDanino(810, 350,"./data/Bloques/Block.png"));
		
		obstaculosNoDaninos.add(new ObsNoDanino(340, 600,"./data/Bloques/Block.png"));
		obstaculosNoDaninos.add(new ObsNoDanino(390, 600,"./data/Bloques/Block.png"));
		obstaculosNoDaninos.add(new ObsNoDanino(440, 600,"./data/Bloques/Block.png"));
		
		obstaculosNoDaninos.add(new ObsNoDanino(610, 600,"./data/Bloques/Block.png"));
		obstaculosNoDaninos.add(new ObsNoDanino(660, 600,"./data/Bloques/Block.png"));
		obstaculosNoDaninos.add(new ObsNoDanino(710, 600,"./data/Bloques/Block.png"));
		
		obstaculosNoDaninos.add(new ObsNoDanino(810, 500,"./data/Bloques/Block.png"));

		obstaculosNoDaninos.add(new ObsNoDanino(240, 500,"./data/Bloques/Block.png"));
		obstaculosNoDaninos.add(new ObsNoDanino(530, 700,"./data/Bloques/Block.png"));
		obstaculosNoDaninos.add(new ObsNoDanino(940, 700,"./data/Bloques/Block.png"));
		obstaculosNoDaninos.add(new ObsNoDanino(710, 800,"./data/Bloques/Block.png"));
		obstaculosNoDaninos.add(new ObsNoDanino(110, 430,"./data/Bloques/Block.png"));
		obstaculosNoDaninos.add(new ObsNoDanino(110, 700,"./data/Bloques/Block.png"));
		obstaculosNoDaninos.add(new ObsNoDanino(340, 800,"./data/Bloques/Block.png"));
		obstaculosNoDaninos.add(new ObsNoDanino(940, 430,"./data/Bloques/Block.png"));
		//Suelo
		obstaculosNoDaninos.add(new ObsNoDanino(0, 895,"./data/Bloques/Grass.jpg"));
		obstaculosNoDaninos.add(new ObsNoDanino(85, 895,"./data/Bloques/Grass.jpg"));
		obstaculosNoDaninos.add(new ObsNoDanino(170, 895,"./data/Bloques/Grass.jpg"));
		obstaculosNoDaninos.add(new ObsNoDanino(255, 895,"./data/Bloques/Grass.jpg"));
		obstaculosNoDaninos.add(new ObsNoDanino(340, 895,"./data/Bloques/Grass.jpg"));
		obstaculosNoDaninos.add(new ObsNoDanino(425, 895,"./data/Bloques/Grass.jpg"));
		obstaculosNoDaninos.add(new ObsNoDanino(510, 895,"./data/Bloques/Grass.jpg"));
		obstaculosNoDaninos.add(new ObsNoDanino(595, 895,"./data/Bloques/Grass.jpg"));
		obstaculosNoDaninos.add(new ObsNoDanino(680, 895,"./data/Bloques/Grass.jpg"));
		obstaculosNoDaninos.add(new ObsNoDanino(765, 895,"./data/Bloques/Grass.jpg"));
		obstaculosNoDaninos.add(new ObsNoDanino(850, 895,"./data/Bloques/Grass.jpg"));
		obstaculosNoDaninos.add(new ObsNoDanino(935, 895,"./data/Bloques/Grass.jpg"));
		obstaculosNoDaninos.add(new ObsNoDanino(1020, 895,"./data/Bloques/Grass.jpg"));
		obstaculosNoDaninos.add(new ObsNoDanino(1105, 895,"./data/Bloques/Grass.jpg"));

		obstaculosDaninos.add(new ObsDanino(810,475,"./data/Bloques/BloqueDanino.png",this));
		obstaculosDaninos.add(new ObsDanino(660,575,"./data/Bloques/BloqueDanino.png",this));
		obstaculosDaninos.add(new ObsDanino(300,325,"./data/Bloques/BloqueDanino.png",this));
		obstaculosDaninos.add(new ObsDanino(110,675,"./data/Bloques/BloqueDanino.png",this));
		obstaculosDaninos.add(new ObsDanino(200,870,"./data/Bloques/BloqueDanino.png",this));
		obstaculosDaninos.add(new ObsDanino(500,870,"./data/Bloques/BloqueDanino.png",this));
		obstaculosDaninos.add(new ObsDanino(800,870,"./data/Bloques/BloqueDanino.png",this));
	
	}
	public boolean estaEnPlataforma(){
		boolean estaEnPlat = false;
		for(int i = 0; i < obstaculosNoDaninos.size(); i++){
			ObsNoDanino actual = obstaculosNoDaninos.get(i);
				if(jugador.getPosX()-actual.getPosX() >= -60 && jugador.getPosX()-actual.getPosX() <= 60  && actual.getPosY()-jugador.getPosY()==85){
					jugador.setPosYIni(jugador.getPosY());
					jugador.setEstaEnPlataforma(true);
					estaEnPlat = true;
				
			}
		}
		jugador.setEstaEnPlataforma(estaEnPlat);
		return estaEnPlat;
	}
	
	public boolean estaEnPlataformaArana(Spider a){
		boolean estaEnPlat = false;
			for(int j = 0; j < obstaculosNoDaninos.size() && !estaEnPlat; j++){
				if(a.getPosX()-obstaculosNoDaninos.get(j).getPosX() >= -60 && a.getPosX()-obstaculosNoDaninos.get(j).getPosX() <= 60  && obstaculosNoDaninos.get(j).getPosY()-a.getPosY()==35){
					
					estaEnPlat = true;
				}	
				
			}
			
			if(!a.isEstaEnPlataforma()){
			a.setPosXIni(a.getPosX());	
			}
			
			a.setEstaEnPlataforma(estaEnPlat);
		
	return estaEnPlat;	
	}
	
	public void agregarProyectil(Proyectil uno){
		Proyectil actual=getPrimerProyectil();
		if(actual==null){
			setPrimerProyectil(uno);
		}
		else{
			while(actual.getSiguiente()!=null){
				actual=actual.getSiguiente();
			}
			actual.setSiguiente(uno);
		}
	}
	
	public void eleminarProyectil(Proyectil eliminar){
		Proyectil actual=getPrimerProyectil();
		if(getPrimerProyectil()!=null){
			if(getPrimerProyectil().equals(eliminar)){
				setPrimerProyectil(actual.getSiguiente());
			}else{
			if(actual.getSiguiente()!=null && !actual.getSiguiente().equals(eliminar)){
				actual=buscarProyectilRecursivo(actual);
			}

			if(actual.getSiguiente()!=null){
				actual.setSiguiente(actual.getSiguiente().getSiguiente());
			}
			else{
				actual.setSiguiente(null);
			}
			}
		}
	
	}
	
	public Proyectil buscarProyectilRecursivo(Proyectil actual){
		if(actual.getSiguiente()!=null && !actual.getSiguiente().equals(actual)){
			return buscarProyectilRecursivo(actual.getSiguiente());
		}
		return actual;
	}
	
	public void iniciarHiloProyectil(){
		 hProyectil=new HiloProyectil(this);
		 hProyectil.start();
	}
	
	public void iniciarHiloProyectilEnemigos(){
		 hProyectilesRV = new HiloProyectilesRV(this);
		 hProyectilesRV.start();
	}
	public void iniciarHiloProyectilSpider(){
		 hProyectilesSpider = new HiloProyectilesSpider(this);
		 hProyectilesSpider.start();
	}
	public void iniciarHiloProyectilCrab(){
		 hProyectilesCrab = new HiloProyectilesCrab(this);
		 hProyectilesCrab.start();
	}
	
	public void iniciarHiloProyectilBoss(){
		 hProyectilMisil = new HiloMisil(this);
		 hProyectilMisil.start();
	}

	
	public ArrayList<Personaje> getEnemigos() {
		return enemigos;
	}

	public void setEnemigos(Personaje enemigo) {
		enemigos.add(enemigo);
	}

	public Boss getBoss() {
		return boss;
	}

	public void setBoss(Boss boss) {
		this.boss = boss;
	}

	public ArrayList<ObsNoDanino> getObstaculos() {
		return obstaculosNoDaninos;
	}

	public Jugador getJugador() {
		return jugador;
	}

	public Proyectil getPrimerProyectil() {
		return primerProyectilJugador;
	}

	public void setPrimerProyectil(Proyectil primerProyectil) {
		this.primerProyectilJugador = primerProyectil;
	}

	public ArrayList<Proyectil> getproyectilesRobotVolador() {
		return proyectilesRobotVolador;
	}

	public void setproyectilesRobotVolador(ArrayList<Proyectil> proyectilesRobotVolador) {
		this.proyectilesRobotVolador = proyectilesRobotVolador;
	}

	public ArrayList<Misil> getProyectilesBoss() {
		return proyectilesBoss;
	}

	public void setProyectilesBoss(ArrayList<Misil> proyectilesBoss) {
		this.proyectilesBoss = proyectilesBoss;
	}

	public ArrayList<ObsDanino> getObstaculosDaninos() {
		return obstaculosDaninos;
	}

	public void setObstaculosDaninos(ArrayList<ObsDanino> obstaculosDaninos) {
		this.obstaculosDaninos = obstaculosDaninos;
	}

	public ControlEnemigos getControl() {
		return control;
	}

	public void setControl(ControlEnemigos control) {
		this.control = control;
	}

	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}

	public void setEnemigos(ArrayList<Personaje> enemigos) {
		this.enemigos = enemigos;
	}

	public ArrayList<Proyectil> getProyectilesSpider() {
		return proyectilesSpider;
	}

	public void setProyectilesSpider(ArrayList<Proyectil> proyectilesSpider) {
		this.proyectilesSpider = proyectilesSpider;
	}

	public ArrayList<Proyectil> getProyectilesCrab() {
		return proyectilesCrab;
	}

	public void setProyectilesCrab(ArrayList<Proyectil> proyectilesCrab) {
		this.proyectilesCrab = proyectilesCrab;
	}

	public HiloControlEnemigos getHiloControl() {
		return hiloControl;
	}

	public void setHiloControl(HiloControlEnemigos hiloControl) {
		this.hiloControl = hiloControl;
	}
	
	
	
}
