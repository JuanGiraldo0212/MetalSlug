package interfaz;
import javax.swing.*;

import mundo.JugadorNoEncontradoException;
import mundo.Puntaje;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;
public class PanelPuntaje extends JPanel implements ActionListener{

	public final static String ORDENAR="Ordenar";
	public final static String BUSCAR="Buscar";
	public final static String REPORTE_1="Reporte1";
	public final static String REPORTE_2="Reporte2";
	private JPanel aux;
	private JPanel aux2;
	private JPanel busqueda;
	private JButton btnBuscar;
	private JButton btnOrdenar;
	private JButton btnReporte1;
	private JButton btnReporte2;
	private JTextField txtlNombre;
	private JTextArea txtAreaPuntajes;
	private JTextArea txtAreaReporte;
	private InterfazJuego principal;
	private JScrollPane scroll;
	
	public PanelPuntaje(InterfazJuego v){
		principal=v;
		setLayout(new BorderLayout());
		aux=new JPanel();
		aux2=new JPanel();
		busqueda=new JPanel();
		btnBuscar=new JButton("Buscar");
		btnBuscar.setActionCommand(BUSCAR);
		btnBuscar.addActionListener(this);
		btnOrdenar=new JButton("Odenar");
		btnOrdenar.setActionCommand(ORDENAR);
		btnOrdenar.addActionListener(this);
		btnReporte1 = new JButton("Puntaje mas antiguo");
		btnReporte1.addActionListener(this);
		btnReporte1.setActionCommand(REPORTE_1);
		btnReporte2 = new JButton("Puntaje mas nuevo");
		btnReporte2.addActionListener(this);
		btnReporte2.setActionCommand(REPORTE_2);
		txtAreaPuntajes = new JTextArea();
		txtAreaPuntajes.setBackground(new Color(7, 22, 52));
		txtAreaPuntajes.setFont(new Font("Verdana", Font.PLAIN, 20));
		txtAreaPuntajes.setForeground(new Color(234, 128, 40));;
		txtAreaPuntajes.setEditable(false);
		txtAreaPuntajes.setText("");
		scroll= new JScrollPane(txtAreaPuntajes);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setPreferredSize(new Dimension(400, 400));
		txtAreaReporte=new JTextArea();
		txtAreaReporte.setBackground(new Color(7, 22, 52));
		txtAreaReporte.setPreferredSize(new Dimension(400, 400));
		txtAreaReporte.setFont(new Font("Verdana", Font.PLAIN, 20));
		txtAreaReporte.setForeground(new Color(234, 128, 40));
		txtAreaReporte.setEditable(false);
		txtAreaReporte.setText("Reporte:");
		txtlNombre= new JTextField();
		txtlNombre.setPreferredSize(new Dimension(120, 30));
		busqueda.add(txtlNombre);
		busqueda.add(btnBuscar);
		aux.add(btnOrdenar);
		aux.add(busqueda);
		aux.add(btnReporte1);
		aux.add(btnReporte2);
		aux2.add(scroll);
		aux2.add(txtAreaReporte);
		add(aux,BorderLayout.SOUTH);
		add(aux2,BorderLayout.CENTER);
	}
	
	public void setTextoArea(String reporte){
		txtAreaPuntajes.setText(reporte);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String accion=arg0.getActionCommand();
		if(accion.equals(ORDENAR)){
			principal.ordenarPuntajes();
			principal.reportePuntajes();
		}
		else if(accion.equals(BUSCAR)){
			Puntaje actual;
			try {
				actual = principal.buscarUsuario(txtlNombre.getText());
				txtlNombre.setText("");
				txtAreaReporte.setText("Reporte:\nEl jugador "+actual.getUsuario()+" tiene\n"+actual.getPuntaje()+" puntos");
			} catch (JugadorNoEncontradoException e) {
				txtAreaReporte.setText("Reporte:\nEl jugador "+txtlNombre.getText()+" no existe");
			}
		}
		else if(accion.equals(REPORTE_1)){
			Puntaje actual =principal.reporteJugadores(1);
			Date fecha = new Date(actual.getFecha());
			txtAreaReporte.setText("Reporte:\nEl puntaje mas antiguo:\n"+"Jugador: "+actual.getUsuario()+"\nPuntaje: "+
			actual.getPuntaje()+"\nFecha: "+fecha.toString());
		}
		
		else if(accion.equals(REPORTE_2)){
			Puntaje actual =principal.reporteJugadores(2);
			Date fecha = new Date(actual.getFecha());
			txtAreaReporte.setText("Reporte:\nEl puntaje mas nuevo:\n"+"Jugador: "+actual.getUsuario()+"\nPuntaje: "+
			actual.getPuntaje()+"\nFecha: "+fecha.toString());
		}
		
	}
}
