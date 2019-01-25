package pruebas;

import static org.junit.Assert.*;

import org.junit.Test;

import mundo.Escenario;
import mundo.Jugador;
import mundo.Proyectil;

public class TestJugador {

	private Jugador jugador;
	private Escenario escenario;
	
	private void setUpEscenario1(){
	escenario = new Escenario(1,0);
	jugador = new Jugador(escenario);
	}
	
	private void setUpEscenario2(){
	escenario = new Escenario(1,0);
	jugador = new Jugador(escenario);
	escenario.getproyectilesRobotVolador().add(new Proyectil(Jugador.AIM_DOWN, Jugador.AIM_DOWN, 530,620,Proyectil.ROBOT_VOLADOR,10));
	}
	
	@Test
	public void testMovimiento(){
	setUpEscenario1();
	jugador.setDireccion(Jugador.IZQUIERDA);
	jugador.movimiento();
	
	assertEquals(510,jugador.getPosX());
	
	setUpEscenario1();
	
	jugador.setDireccion(Jugador.DERECHA);
	jugador.movimiento();
	
	assertEquals(530,jugador.getPosX());
	
	}
	
	@Test
	public void testMovimientoEnSalto(){
	setUpEscenario1();
	jugador.movimientoEnSalto();
	
	assertEquals(517,jugador.getPosX());
	
	jugador.setDireccion(Jugador.DERECHA);
	jugador.movimientoEnSalto();
	
	assertEquals(520,jugador.getPosX());
	}
	
	@Test
	public void testSaltar(){
	setUpEscenario1();
	jugador.saltar();
	
	assertEquals(613,jugador.getPosY());
	assertEquals("./data/Jugador/Jump/jump1I.png", jugador.getImagenActual());
	
	jugador.setPosY(565);
	jugador.saltar();
	assertEquals(563,jugador.getPosY());
	assertEquals("./data/Jugador/Jump/jump2I.png", jugador.getImagenActual());
	
	jugador.setPosY(515);
	jugador.saltar();
	assertEquals(513,jugador.getPosY());
	assertEquals("./data/Jugador/Jump/jump3I.png", jugador.getImagenActual());
	
	}
	
	@Test
	public void testGravedadJugador(){
	setUpEscenario1();
	jugador.gravedadJugador();

	assertEquals(620, jugador.getPosY());
	assertEquals("./data/Jugador/Jump/jump3I.png", jugador.getImagenActual());
	}
	
	@Test
	public void testGolpeado(){
	setUpEscenario2();
	jugador.golpeado();
	
	assertEquals(90,jugador.getVida());
	}
	
	@Test
	public void testDano(){
	setUpEscenario1();
	jugador.Dano();
	
	assertNotNull(escenario.getPrimerProyectil());
	}
	
	
	
}
