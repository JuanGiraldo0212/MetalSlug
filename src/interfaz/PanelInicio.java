package interfaz;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.CubicCurve2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.QuadCurve2D;

import javax.swing.*;
import javax.swing.border.Border;
public class PanelInicio extends JPanel implements MouseListener{
	private InterfazJuego principal;
	private boolean draw;
	public PanelInicio(InterfazJuego v){
		draw=false;
		principal=v;
		setLayout(new BorderLayout());
		addMouseListener(this);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		g2.drawImage(new ImageIcon("./data/Bloques/Fondo.jpg").getImage(), 0, 0, this);
		g2.setColor(Color.red);
		//g2.draw(new Rectangle(400, 685, 200, 50));
		//g2.draw(new Rectangle(400, 740, 200, 50));
		//g2.draw(new Rectangle(400, 785, 200, 50));
		//g2.draw(new Rectangle(400, 840, 200, 50));
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		int posX=arg0.getX();
		int posY=arg0.getY();
		if((posX>=400 && posX<=600) && (posY>=685 && posY<=735)){
			principal.iniciarJuego();
		}
		else if((posX>=400 && posX<=600) && (posY>=785 && posY<=835)){
			principal.mostrarPuntajes();
		}
		else if((posX>=400 && posX<=600) && (posY>=740 && posY<=790)){
			principal.cargarNuevoJuego();
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
