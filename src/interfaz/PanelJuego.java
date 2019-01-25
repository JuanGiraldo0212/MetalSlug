package interfaz;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.QuadCurve2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

import javax.swing.*;

import org.w3c.dom.events.MouseEvent;

import mundo.Juego;
import mundo.Jugador;
import mundo.Misil;
import mundo.Proyectil;
public class PanelJuego extends JPanel implements KeyListener,MouseListener{

	private Juego juego;
	private InterfazJuego principal;
	public PanelJuego(Juego actual,InterfazJuego v) {
		setLayout(new BorderLayout());
		juego=actual;
		principal=v;
		setFocusable(true);
		addKeyListener(this);
		addMouseListener(this);
		
	}
	@Override
	protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D)g;
			g2.setColor(Color.RED);
			g2.setColor(Color.BLUE);
			g2.drawImage(new ImageIcon("./data/Bloques/Bck.gif").getImage(), 0, 0, this);
			for(int i = 0; i < juego.getEscenario().getObstaculos().size(); i++){
				g2.drawImage(new ImageIcon(juego.getEscenario().getObstaculos().get(i).getImagenActual()).getImage(), juego.getEscenario().getObstaculos().get(i).getPosX(), juego.getEscenario().getObstaculos().get(i).getPosY(), this);
			}
			for(int i = 0; i < juego.getEscenario().getObstaculosDaninos().size(); i++){
				g2.drawImage(new ImageIcon(juego.getEscenario().getObstaculosDaninos().get(i).getImagenActual()).getImage(), juego.getEscenario().getObstaculosDaninos().get(i).getPosX(), juego.getEscenario().getObstaculosDaninos().get(i).getPosY(), this);
			}
			
			
			////////////////////////////////////////////
			//////
			g2.setColor(Color.WHITE);
			g2.setFont(new Font("Britannic Bold", Font.PLAIN, 20));
			g2.drawImage(new ImageIcon("./data/Jugador/LifeBar.png").getImage(), 10, 10, this);
			g2.drawString("Vida: "+juego.getEscenario().getJugador().getVida(), 20, 65);
			g2.setColor(Color.red);
			g2.fillRect(12, 12, 258*(juego.getEscenario().getJugador().getVida())/100, 32);
			/////////////////////////////////////////////////
			/////Save///////////////////////////////////////
			g2.setColor(Color.LIGHT_GRAY);
			g2.fill(new Rectangle(1085, 15, 70, 80));
			g2.fill(new Rectangle(1152, 20, 8, 77));
			g2.setColor(Color.BLACK);
			g2.fill(new Rectangle(1080, 10, 70, 5));
			g2.fill(new RoundRectangle2D.Float(1080,10,5,87,10,10));
			g2.fill(new RoundRectangle2D.Float(1080,92,85,5,10,10));
			g2.fill(new RoundRectangle2D.Float(1160,25,5,72,10,10));
			g2.setStroke(new BasicStroke(5));
			g2.draw(new Line2D.Float(1150, 13, 1162, 28));
			g2.setColor(Color.WHITE);
			g2.fill(new Rectangle(1095, 50, 55, 38));
			g2.fill(new RoundRectangle2D.Float(1095,20,55,20,10,10));
			g2.setColor(Color.BLACK);
			g2.fill(new Rectangle(1130, 22, 8, 17));
			g2.setStroke(new BasicStroke(3));
			g2.draw(new Line2D.Float(1100, 60, 1145, 60));
			g2.draw(new Line2D.Float(1100, 70, 1145, 70));
			g2.draw(new Line2D.Float(1100, 80, 1145, 80));
			////////////////////////////////////////////////////////////
			g2.drawString("Puntaje: "+juego.getEscenario().getJugador().getPuntaje(), 1075, 120);
			Proyectil actual=juego.getEscenario().getPrimerProyectil();
			while(actual!=null){
				if(actual.isActivo()){
					g2.drawImage(new ImageIcon(actual.getImagenActual()).getImage(), actual.getPosX(), actual.getPosY(), this);
				}
				else{
					juego.getEscenario().eleminarProyectil(actual);
				}
				actual=actual.getSiguiente();
			}
			//////////////////////////////////////////////////
			///Pinta Pryectiles Robot Volador////////////////
			////////////////////////////////////////////////
			for(int i = 0; i<juego.getEscenario().getproyectilesRobotVolador().size();i++){
				Proyectil a = juego.getEscenario().getproyectilesRobotVolador().get(i);
				if(a.isActivo()){
				g2.drawImage(new ImageIcon(a.getImagenActual()).getImage(), a.getPosX(), a.getPosY(), this);
				}else{
					juego.getEscenario().getproyectilesRobotVolador().remove(a);
				}
			}
			//////////////////////////////////////////////////
			///Pinta Pryectiles Arana////////////////
			////////////////////////////////////////////////
			for(int i = 0; i<juego.getEscenario().getProyectilesSpider().size();i++){
				Proyectil a = juego.getEscenario().getProyectilesSpider().get(i);
				if(a.isActivo()){
				g2.drawImage(new ImageIcon(a.getImagenActual()).getImage(), a.getPosX(), a.getPosY(), this);
				}else{
					juego.getEscenario().getProyectilesSpider().remove(a);
				}
			}
			//////////////////////////////////////////////////
			///Pinta Pryectiles Crab////////////////
			////////////////////////////////////////////////
			for(int i = 0; i<juego.getEscenario().getProyectilesCrab().size();i++){
				Proyectil a = juego.getEscenario().getProyectilesCrab().get(i);
				if(a.isActivo()){
				g2.drawImage(new ImageIcon(a.getImagenActual()).getImage(), a.getPosX(), a.getPosY(), this);
				}else{
					juego.getEscenario().getProyectilesCrab().remove(a);
				}
			}
			////////////////////////////////////////////////
			/////Pinta Boss/////////////////////////////////
			///////////////////////////////////////////////
			if(juego.getEscenario().getBoss()!=null){
				if(juego.getEscenario().getBoss().getactivo()){
					if(juego.getEscenario().getBoss().getPosicionIdle()%2==0){
						
						g2.drawImage(new ImageIcon(juego.getEscenario().getBoss().getImagenActual()).getImage(), juego.getEscenario().getBoss().getPosX(), juego.getEscenario().getBoss().getPosY(), this);
					}
					else{
						g2.drawImage(new ImageIcon(juego.getEscenario().getBoss().getImagenActual()).getImage(), juego.getEscenario().getBoss().getPosX(), juego.getEscenario().getBoss().getPosY()+2, this);
						
					}
					
				}else{
					juego.getEscenario().setBoss(null);
				}
			}
			
			for(int i = 0; i<juego.getEscenario().getProyectilesBoss().size();i++){
				Misil b = juego.getEscenario().getProyectilesBoss().get(i);
				if(!b.isDestruido()){
				g2.drawImage(new ImageIcon(b.getImagenActual()).getImage(), b.getPosX(), b.getPosY(), this);
				}else{
				
				juego.getEscenario().getProyectilesBoss().remove(b);
				}
			}
			/////////////////////////////////////////////////////
			///////////Eliminar enemigos////////////////////////
			////////////////////////////////////////////////////
			for(int i=0;i<juego.getEscenario().getEnemigos().size();i++){
				if(juego.getEscenario().getEnemigos().get(i).getactivo()){
					g2.drawImage(new ImageIcon(juego.getEscenario().getEnemigos().get(i).getImagenActual()).getImage(), juego.getEscenario().getEnemigos().get(i).getPosX(), juego.getEscenario().getEnemigos().get(i).getPosY(), this);
				}
				else{
					g2.drawImage(new ImageIcon(juego.getEscenario().getEnemigos().get(i).getImagenActual()).getImage(), juego.getEscenario().getEnemigos().get(i).getPosX(), juego.getEscenario().getEnemigos().get(i).getPosY(), this);
					if(juego.getEscenario().getEnemigos().get(i).getDestruido()){
						juego.getEscenario().getEnemigos().remove(juego.getEscenario().getEnemigos().get(i));	
					}
				}
			}
			//////////////////////////////////////////
			//Correcciones///////////////////////////
			////////////////////////////////////////
			if(juego.getEscenario().getJugador().getImagenActual().equals("./data/Jugador/Shoot/fire3I.png") || juego.getEscenario().getJugador().getImagenActual().equals("./data/Jugador/Shoot/fire1I.png")
					|| juego.getEscenario().getJugador().getImagenActual().equals("./data/Jugador/Shoot/fire2I.png") || juego.getEscenario().getJugador().getImagenActual().equals("./data/Jugador/Shoot/fire4I.png")){
				g2.drawImage(new ImageIcon(juego.getEscenario().getJugador().getImagenActual()).getImage(),juego.getEscenario().getJugador().getPosX()-130,juego.getEscenario().getJugador().getPosY(),this);
			}
			else if(juego.getEscenario().getJugador().getImagenActual().equals("./data/Jugador/View/UD.png") || juego.getEscenario().getJugador().getImagenActual().equals("./data/Jugador/View/UI.png")||
					juego.getEscenario().getJugador().getImagenActual().equals("./data/Jugador/Shoot/fire1UD.png") || juego.getEscenario().getJugador().getImagenActual().equals("./data/Jugador/Shoot/fire2UD.png")
					|| juego.getEscenario().getJugador().getImagenActual().equals("./data/Jugador/Shoot/fire1UI.png") || juego.getEscenario().getJugador().getImagenActual().equals("./data/Jugador/Shoot/fire2UI.png")){
				g2.drawImage(new ImageIcon(juego.getEscenario().getJugador().getImagenActual()).getImage(),juego.getEscenario().getJugador().getPosX(),juego.getEscenario().getJugador().getPosY()-60,this);
			}
			else if(juego.getEscenario().getJugador().getImagenActual().equals("./data/Jugador/Shoot/CrouchFire2I.png") || juego.getEscenario().getJugador().getImagenActual().equals("./data/Jugador/Shoot/CrouchFire3I.png")){
				g2.drawImage(new ImageIcon(juego.getEscenario().getJugador().getImagenActual()).getImage(),juego.getEscenario().getJugador().getPosX()-130,juego.getEscenario().getJugador().getPosY(),this);
			}
			else{
			g2.drawImage(new ImageIcon(juego.getEscenario().getJugador().getImagenActual()).getImage(),juego.getEscenario().getJugador().getPosX(),juego.getEscenario().getJugador().getPosY(),this);
			}
			
			if(juego.getEscenario().getJugador().getDestruido() && !juego.getEscenario().getJugador().isGano()){
				g2.setFont(new Font("Britannic Bold", Font.PLAIN, 50));
				g2.setColor(Color.orange);
				g2.drawImage(new ImageIcon("./data/Jugador/GameOver.gif").getImage(),100, 50, this);
				g2.drawString("Presione ENTER para continuar", 250, 500);
			}
			else if(juego.getEscenario().getJugador().isGano()){
				g2.setFont(new Font("Britannic Bold", Font.BOLD, 100));
				g2.setColor(Color.orange);
				g2.drawString("Mission Complete", 150, 400);
				g2.setFont(new Font("Britannic Bold", Font.PLAIN, 50));
				g2.drawString("Presione ENTER para continuar", 250, 500);
			}
			
			
		}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int comando = e.getKeyCode();
		if(comando==KeyEvent.VK_LEFT || comando==KeyEvent.VK_A){
			principal.movimiento(Jugador.IZQUIERDA);
		}
		else if(comando==KeyEvent.VK_RIGHT || comando==KeyEvent.VK_D){
			principal.movimiento(Jugador.DERECHA);
		}
		else if(comando==KeyEvent.VK_SPACE){
			if(!juego.getEscenario().getJugador().isSaltando() && !juego.getEscenario().getJugador().isBajando()){
				principal.saltar();
				principal.movimiento(juego.getEscenario().getJugador().getDireccion());	
			}
			
		}
		else if(comando==KeyEvent.VK_F || comando==KeyEvent.VK_K){
			if(!juego.getEscenario().getJugador().isDisparando()){
				principal.disparar();	
			}
		
		}
		else if(comando==KeyEvent.VK_UP || comando==KeyEvent.VK_W){
			if(!juego.getEscenario().getJugador().isSaltando()){
				juego.getEscenario().getJugador().setAim('U');	
			}
		}
		else if(comando==KeyEvent.VK_DOWN || comando==KeyEvent.VK_S){
			if(!juego.getEscenario().getJugador().isSaltando()){
				juego.getEscenario().getJugador().setAim('A');	
			}
		}
		else if(comando==KeyEvent.VK_ENTER){
			if(!juego.getEscenario().getJugador().isActivo()){
				principal.cambiarPantallaInicio();
			}
			
		}
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		int comando = e.getKeyCode();
		if(comando==KeyEvent.VK_LEFT || comando==KeyEvent.VK_A){
			principal.detenerMovimiento();
		}
		else if(comando==KeyEvent.VK_RIGHT || comando==KeyEvent.VK_D){
			principal.detenerMovimiento();
		}
		else if(comando==KeyEvent.VK_UP || comando==KeyEvent.VK_W){
			juego.getEscenario().getJugador().setAim('N');
		}
		else if(comando==KeyEvent.VK_DOWN || comando==KeyEvent.VK_S){
			juego.getEscenario().getJugador().setAim('N');
		}
		
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseClicked(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
		int posX=e.getX();
		int posY=e.getY();
		if((posX>=1075 && posX<=1170) && (posY>=5 && posY<=100)){
			
			principal.guardarAArchivo();
		}
		
	
	}
	@Override
	public void mouseEntered(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}

