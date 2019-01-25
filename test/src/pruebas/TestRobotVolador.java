package pruebas;
import org.junit.Test;
import static org.junit.Assert.*;

import mundo.Escenario;
import mundo.Jugador;
import mundo.Proyectil;
import mundo.RobotVolador;

public class TestRobotVolador {

	private RobotVolador robotV;
	private Escenario escenario;
	
	private void setUpEscenario1(){
		escenario = new Escenario(1,0);
		robotV= new RobotVolador(escenario,800,10);
	}
	
	private void setUpEscenario2 (){
		escenario = new Escenario(1,0);
		robotV= new RobotVolador(escenario,800,10);
		escenario.agregarProyectil(new Proyectil(Jugador.DERECHA, Jugador.NO_AIM, 800, 15,Proyectil.JUGADOR,20));
	}
	
	@Test
	public void testMovimiento(){
		setUpEscenario1();
		escenario.getJugador().setEnMovimiento(true);
		escenario.getJugador().setPosX(792);
		robotV.movimiento();
		
		assertEquals(792, robotV.getPosX());
		
		escenario.getJugador().setEnMovimiento(false);
		robotV.movimiento();
		
		assertEquals(792, robotV.getPosX());
		
	}
	
	@Test
	public void testGolpeado(){
		setUpEscenario2();
		robotV.golpeado();
		
		assertEquals(60, robotV.getVida());
		
		robotV.setVida(0);
		robotV.golpeado();
		
		assertEquals(false, robotV.getactivo());
	}
	
	@Test
	public void testDano(){
		setUpEscenario1();
		robotV.Dano();
		
		assertNotNull(escenario.getproyectilesRobotVolador().get(0));
	}
}
