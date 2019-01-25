package pruebas;

import org.junit.Test;
import static org.junit.Assert.*;

import mundo.Escenario;
import mundo.Jugador;
import mundo.Proyectil;
import mundo.CrabBot;
import mundo.Entidad;

public class TestCrabBot {
	
	private CrabBot crabBot;
	private Escenario escenario;
	
	private void setUpEscenario1(){
		escenario = new Escenario(1,0);
		crabBot= new CrabBot(escenario,680,680,Entidad.DERECHA);
	}
	
	private void setUpEscenario2 (){
		escenario = new Escenario(1,0);
		crabBot= new CrabBot(escenario,680,680,Entidad.DERECHA);
		escenario.agregarProyectil(new Proyectil(Jugador.DERECHA, Jugador.NO_AIM, 700, 700,Proyectil.JUGADOR,20));
	}
	
	@Test
	public void testMovimiento(){
		setUpEscenario1();
		crabBot.setDireccion(Jugador.DERECHA);
		crabBot.setPosX(680);
		crabBot.movimiento();
		assertEquals(685, crabBot.getPosX());
	}
	
	@Test
	public void testDano(){
		setUpEscenario1();
		crabBot.Dano();
		
		assertNotNull(escenario.getProyectilesCrab().get(0));
	}
	
	@Test
	public void testEnRango(){
		setUpEscenario1();
		crabBot.setPosX(680);
		escenario.getJugador().setPosX(680);
		crabBot.enRango();
		
		assertEquals(true, crabBot.isDisparando());
	}
	
	@Test
	public void testGolpeado(){
		setUpEscenario2();
		crabBot.setPosX(680);
		crabBot.golpeado();
		
		assertEquals(100, crabBot.getVida());
		
		crabBot.setVida(0);
		crabBot.golpeado();
		
		assertEquals(false, crabBot.getactivo());
	
	}
}
