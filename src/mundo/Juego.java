package mundo;

import java.io.*;
import java.util.ArrayList;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

public class Juego {

	public final static String RUTA_PUNTAJE="./data/DatosJuego/puntaje.slug";
    public final static String RUTA_ARBOL="./data/DatosJuego/arbol.slug";
    public final static String RUTA_GUARDADO="./data/DatosJuego/Guardado.txt";
    
    private Puntaje raiz;
    private ArrayList<Puntaje> puntajes;
    
	private Escenario escenario;
	private int nivelActual;
	private Clip musica;
	public Juego() {
		nivelActual = 1;
		puntajes = new ArrayList<Puntaje>();
		playMusic("./data/Music/Menu.wav");
	}
	
public void playMusic(String ruta){
		
		try {
			File audioFile = new File(ruta);
			AudioInputStream ais = AudioSystem.getAudioInputStream(audioFile);
			AudioFormat format =ais.getFormat();
			DataLine.Info info=new DataLine.Info(Clip.class, format);
			musica = (Clip)AudioSystem.getLine(info);
			musica.open(ais);
			musica.start();
			
			//clip.getMicrosecondLength();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

public void stopMusic(){
	musica.stop();
}

	public void iniciarEscenario(){
		escenario = new Escenario(nivelActual,0);
	}
	public void agregarPuntaje(String usuario,int puntaje) throws UsuarioYaExisteException{
		if(usuario!=null){
			Puntaje actual=new Puntaje(usuario, puntaje);
			agregarPuntajeArbol(actual);
			puntajes.add(actual);
		}
	}
	public void ordenarPorPuntaje(){
		for(int i=puntajes.size();i>0;i--){
			for(int j=0;j<i-1;j++){
				if(puntajes.get(j).compararPuntaje(puntajes.get(j+1))<0){
					Puntaje pTemp=puntajes.get(j);
					puntajes.set(j, puntajes.get(j+1));
					puntajes.set(j+1, pTemp);
				}
			}
		}
	}
	public void ordenarPorNombre(){
		for(int i=puntajes.size();i>0;i--){
			for(int j=0;j<i-1;j++){
				if(puntajes.get(j).compararNombre(puntajes.get(j+1).getUsuario())>0){
					Puntaje pTemp=puntajes.get(j);
					puntajes.set(j, puntajes.get(j+1));
					puntajes.set(j+1, pTemp);
				}
			}
		}
	}
	
	public String reportePuntajes(){
		String reporte="Usuario:	Puntaje:\n";
		for(int i =0;i<puntajes.size();i++){
			reporte+=puntajes.get(i).getUsuario()+"	";
			reporte+=puntajes.get(i).getPuntaje()+"\n";
		}
		return reporte;
	}
	
	public Puntaje buscarPuntaje(String nombre) throws JugadorNoEncontradoException{
		Puntaje buscado=null;
		boolean encontrado=false;
		int inicio=0;
		int fin=puntajes.size()-1;
		while(inicio<=fin &&  !encontrado){
			int medio=(inicio+fin)/2;
			if(puntajes.get(medio).getUsuario().equals(nombre)){
				encontrado=true;
				buscado=puntajes.get(medio);
			}
			else if(puntajes.get(medio).compararNombre(nombre)>0){
				fin=medio-1;
			}
			else{
				inicio=medio+1;
			}
		}
		if(buscado==null){
			throw new JugadorNoEncontradoException("El usuario: "+nombre+" no existe");
		}
		return buscado;
	}
	
	public void guardarPuntaje(){
		try {
			File archivoPuntaje=new File(RUTA_PUNTAJE);
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivoPuntaje));
			oos.writeObject(puntajes);
			oos.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			File archivoPuntaje=new File(RUTA_ARBOL);
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivoPuntaje));
			oos.writeObject(raiz);
			oos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void cargarPuntaje(){
		File puntaje = new File(RUTA_PUNTAJE);
		File arbol = new File(RUTA_ARBOL);
		if(puntaje.exists() && arbol.exists()){
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(puntaje));
			puntajes = (ArrayList<Puntaje>)ois.readObject();
			ois.close();
			
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arbol));
			raiz = (Puntaje)ois.readObject();
			ois.close();
			
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		}
	}
	
	public void agregarPuntajeArbol(Puntaje actual) throws UsuarioYaExisteException{
		try {
			raiz.insertarNuevoPuntaje(actual);
		} catch (NullPointerException e) {
			raiz=actual;
		}
	}
	
	public ArrayList<Puntaje> darListaJugadores()
    {
        if( raiz == null )
            return null;
        else
        {
            ArrayList<Puntaje> resp = new ArrayList<Puntaje>( );
            raiz.inorden( resp );
            return resp;
        }
    }
	
	public Escenario getEscenario(){
		return escenario;
	}
	
	public void setEscenario(Escenario a){
		escenario = a;
	}

	public int getNivelActual() {
		return nivelActual;
	}

	public void setNivelActual(int nivelActual) {
		this.nivelActual = nivelActual;
	}
	
	public void guardarJuego(String ruta) throws FileNotFoundException{
		PrintWriter escritor = new PrintWriter(ruta);
		
		escritor.println("#nivel: ");
		escritor.println(escenario.getControl().getWave());
		escritor.println("#Jugador -----  PosX PosY PosXInI posYIni Vida estaEnPlataforma ImagenActual Activo Direccion posicionMovimiento posicionIdle Disparando enMovimiento "
				+ "saltando bajando saltoParabolico subiendo aim posicionActualDisparo enemigosMuertos posicionMuerte");
		Jugador b = escenario.getJugador();
		escritor.println(b.getPosX()+" "+b.getPosY()+" "+b.getPosXIni()+" "+b.getPosYIni()+" "+b.getVida()+" "+
		b.isEstaEnPlataforma()+" "+b.getImagenActual()+" "+b.isActivo()+" "+b.getDireccion()+" "
		+b.getPosiccionActualMovimiento()+" "+b.getPosicionActualIdle()+" "+b.isDisparando()+" "+false
		+" "+b.isSaltando()+" "+b.isBajando()+" "+b.isSaltoParabolico()+" "+b.isSubiendo()+" "+b.getAim()+" "+b.getPoscicionActualDisparo()
		+" "+b.getPuntaje()+" "+b.getPosicionMuerte());
		escritor.println("#Aranas -----  PosX PosY PosXInI posYIni Vida estaEnPlataforma ImagenActual Activo Direccion posicionMovimiento posicionIdle Disparando Destruido");
		for(int i = 0; i < escenario.getEnemigos().size(); i++){
		
		Personaje actual = escenario.getEnemigos().get(i);	
			
		if(actual instanceof Spider){
		Spider a = (Spider) actual;
		escritor.println(a.getPosX()+" "+a.getPosY()+" "+a.getPosXIni()+" "+a.getPosYIni()+" "+a.getVida()+" "+a.isEstaEnPlataforma()+" "+a.getImagenActual()+" "+a.getactivo()+" "+a.getDireccion()+" "+a.getPosicionMovimiento()+" "+a.getPosicionIdle()+" "+a.isDisparando()+" "+a.getDestruido());
		}
		}
		
		escritor.println("#Robots Voladores -----  PosX PosY PosXInI posYIni Vida ImagenActual Activo Direccion Disparando Destruido");
		for(int i = 0; i < escenario.getEnemigos().size(); i++){
		
		Personaje actual = escenario.getEnemigos().get(i);	
			
		if(actual instanceof RobotVolador){
		RobotVolador a = (RobotVolador) actual;
		escritor.println(a.getPosX()+" "+a.getPosY()+" "+a.getPosXIni()+" "+a.getPosYIni()+" "+a.getVida()+" "+a.getImagenActual()+" "+a.getactivo()+" "+a.getDireccion()+" "+a.isDisparando()+" "+a.getDestruido());
		}
		}
		
		escritor.println("#CrabBots -----  PosX PosY PosXInI posYIni Vida ImagenActual Activo Direccion Disparando Destruido");
		for(int i = 0; i < escenario.getEnemigos().size(); i++){
		
		Personaje actual = escenario.getEnemigos().get(i);	
			
		if(actual instanceof CrabBot){
		CrabBot a = (CrabBot) actual;
		escritor.println(a.getPosX()+" "+a.getPosY()+" "+a.getPosXIni()+" "+a.getPosYIni()+" "+a.getVida()+" "+a.getImagenActual()+" "+a.getactivo()+" "+a.getDireccion()+" "+a.isDisparando()+" "+a.getDestruido());
		}
		}
		
		escritor.println("#Boss -----  PosX PosY PosXInI posYIni Vida ImagenActual Activo Direccion Disparando posicionIdle posicionMovimiento enMovimiento posicionCompuerta Destruido");
		Boss a = escenario.getBoss();
		if(a!=null){
			escritor.println(a.getPosX()+" "+a.getPosY()+" "+a.getPosXIni()+" "+a.getPosYIni()+" "+a.getVida()+" "+a.getImagenActual()+" "+a.getactivo()+" "+a.getDireccion()+" "+a.isDisparando()+" "+a.getPosicionIdle()+" "+a.getPosicionMovimiento()+" "+a.isEnMovimiento()+" "+a.getPosicionCompuerta()+" "+a.getDestruido());
		}
		
		escritor.println();
		escritor.close( );
	}
	
	public void cargarJuego(String ruta) throws IOException{
		FileReader reader = new FileReader(ruta);
		BufferedReader lector = new BufferedReader(reader);
		nivelActual=1;
		String tipo = "";
		int wave=0;
		
		ArrayList<Personaje> enemigos = new ArrayList<Personaje>();
		
		String linea = lector.readLine();
		while(linea != null && !linea.equals("")){
		if(linea.startsWith("#")){
			if(linea.charAt(1) == 'A'){
			tipo = "spider";
			}else if(linea.charAt(1) == 'J'){
			tipo = "Jugador";
			}else if(linea.charAt(1) == 'R'){
			tipo = "RobotVolador";
			}else if(linea.charAt(1) == 'C'){
			tipo = "CrabBot";
			}else if(linea.charAt(1) == 'B'){
			tipo = "Boss";
			}
		}else{
		try {
		wave=Integer.parseInt(linea);
		escenario = new Escenario(nivelActual,wave);
		} catch (NumberFormatException e) {
		String[] datos = linea.split(" ");
		
		if(tipo.equals("spider")){
			
			int posX = Integer.parseInt(datos[0]);
			int posY = Integer.parseInt(datos[1]);
			int posXIni = Integer.parseInt(datos[2]);
			int posYIni = Integer.parseInt(datos[3]);
			int vida = Integer.parseInt(datos[4]);
			String imagenActual = datos[6];
			boolean activo = false;
			char direccion = datos[8].charAt(0);
			boolean disparando = false;	
			int posicionMovimiento = Integer.parseInt(datos[9]);
			int posicionIdle = Integer.parseInt(datos[10]);
			boolean destruido = false;
			boolean estaEnPlataforma = false;
		
		if(datos[5].equals("true"))estaEnPlataforma = true;
		if(datos[7].equals("true"))activo = true;
		if(datos[11].equals("true"))disparando = true;
		if(datos[12].equals("true"))destruido = true;
			
		Spider a = new Spider(posX,posY,posXIni,posYIni,vida,estaEnPlataforma,imagenActual,activo,direccion,posicionMovimiento,posicionIdle,disparando,destruido,escenario);
		enemigos.add(a);
		
		}else if(tipo.equals("Jugador")){
			int posX = Integer.parseInt(datos[0]);
			int posY = Integer.parseInt(datos[1]);
			int posXIni = Integer.parseInt(datos[2]);
			int posYIni = Integer.parseInt(datos[3]);
			int vida = Integer.parseInt(datos[4]);
			String imagenActual = datos[6];
			boolean activo = false;
			char direccion = datos[8].charAt(0);
			boolean disparando = false;	
			int posicionMovimiento = Integer.parseInt(datos[9]);
			int posicionIdle = Integer.parseInt(datos[10]);
			boolean enMovimiento = false;
			boolean estaEnPlataforma = false;
			boolean saltando = false;
			boolean bajando = false;
			boolean saltoParabolico = false;
			boolean subiendo = false;
			char aim = datos[17].charAt(0);
			int posicionActualDisparo = Integer.parseInt(datos[18]);
			int enemigosMuertos = Integer.parseInt(datos[19]);
			int posicionMuerte = Integer.parseInt(datos[20]);
			
			if(datos[5].equals("true"))estaEnPlataforma = true;
			if(datos[7].equals("true"))activo = true;
			if(datos[11].equals("true"))disparando = true;
			if(datos[12].equals("true"))enMovimiento = true;
			if(datos[13].equals("true"))saltando = true;
			if(datos[14].equals("true"))bajando = true;
			if(datos[15].equals("true"))saltoParabolico = true;
			if(datos[16].equals("true"))subiendo = true;
			
			Jugador a = new Jugador(posX,posY,posXIni,posYIni,vida,estaEnPlataforma,imagenActual,activo,direccion,posicionMovimiento,posicionIdle,disparando,enMovimiento,saltando,bajando,saltoParabolico,subiendo,aim,posicionActualDisparo,enemigosMuertos,escenario,posicionMuerte);
			escenario.setJugador(a);
			}else if(tipo.equals("RobotVolador")){
//				posX,posY,posXInI,posYIni,vida,imagenActual,activo,direccion,disparando,destruido

				int posX = Integer.parseInt(datos[0]);
				int posY = Integer.parseInt(datos[1]);
				int posXIni = Integer.parseInt(datos[2]);
				int posYIni = Integer.parseInt(datos[3]);
				int vida = Integer.parseInt(datos[4]);
				String imagenActual = datos[5];
				boolean activo = false;
				char direccion = datos[7].charAt(0);
				boolean disparando = false;	
				boolean destruido = false;
				
				if(datos[6].equals("true"))activo = true;
				if(datos[8].equals("true"))disparando = true;
				if(datos[9].equals("true"))destruido = true;
				
				RobotVolador a = new RobotVolador(posX,posY,posXIni,posYIni,vida,imagenActual,activo,direccion,disparando,destruido,escenario);
				enemigos.add(a);
			}else if(tipo.equals("CrabBot")){
			//PosX PosY PosXInI posYIni Vida ImagenActual Activo Direccion Disparando Destruido
				
				int posX = Integer.parseInt(datos[0]);
				int posY = Integer.parseInt(datos[1]);
				int posXIni = Integer.parseInt(datos[2]);
				int posYIni = Integer.parseInt(datos[3]);
				int vida = Integer.parseInt(datos[4]);
				String imagenActual = datos[5];
				boolean activo = false;
				char direccion = datos[7].charAt(0);
				boolean disparando = false;	
				boolean destruido = false;
				
				if(datos[6].equals("true"))activo = true;
				if(datos[8].equals("true"))disparando = true;
				if(datos[9].equals("true"))destruido = true;
				
				CrabBot a = new CrabBot(posX,posY,posXIni,posYIni,vida,imagenActual,activo,direccion,disparando,destruido,escenario);
				enemigos.add(a);
			}else if(tipo.equals("Boss")){
				
//				PosX PosY PosXInI posYIni Vida ImagenActual Activo Direccion Disparando posicionIdle posicionMovimiento enMovimiento posicionCompuerta Destruido
				
				int posX = Integer.parseInt(datos[0]);
				int posY = Integer.parseInt(datos[1]);
				int posXIni = Integer.parseInt(datos[2]);
				int posYIni = Integer.parseInt(datos[3]);
				int vida = Integer.parseInt(datos[4]);
				String imagenActual = datos[5];
				boolean activo = false;
				char direccion = datos[7].charAt(0);
				boolean disparando = false;	
				int posicionIdle = Integer.parseInt(datos[9]);
				int posicionMovimiento = Integer.parseInt(datos[10]);
				boolean enMovimiento = false;
				int posicionCompuerta = Integer.parseInt(datos[12]);
				boolean destruido = false;
				
				if(datos[6].equals("true"))activo = true;
				if(datos[8].equals("true"))disparando = true;
				if(datos[11].equals("true"))enMovimiento = true;
				if(datos[13].equals("true"))destruido = true;
				
				Boss a = new Boss(posX,posY,posXIni,posYIni,vida,imagenActual,activo,direccion,disparando,posicionIdle,posicionMovimiento,enMovimiento,posicionCompuerta,destruido,escenario);
				escenario.setBoss(a);
			}
		}
		}
		linea = lector.readLine();
		}
		lector.close();
		reader.close();
		escenario.setControl(new ControlEnemigos(escenario,wave));
		escenario.setEnemigos(enemigos);
		escenario.getControl().iniciarEnemigos(escenario.getEnemigos(), escenario.getBoss());
		//escenario.getHiloControl().start();
	
	
	}

	public ArrayList<Puntaje> getPuntajes() {
		return puntajes;
	}

	public void setPuntajes(ArrayList<Puntaje> puntajes) {
		this.puntajes = puntajes;
	}
	
	

	}
	

		
	

