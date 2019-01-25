package pruebas;
import org.junit.Test;

import mundo.Jugador;
import mundo.Proyectil;

import static org.junit.Assert.*;

public class TestProyectil {
	
	private Proyectil proyectil;
	
	private void setUpEscenario1(){
		proyectil=new Proyectil(Jugador.DERECHA, Jugador.NO_AIM, 0, 0, Proyectil.JUGADOR, 20);
	}
	private void setUpEscenario2(){
		proyectil=new Proyectil(Jugador.IZQUIERDA, Jugador.NO_AIM, 0, 0, Proyectil.JUGADOR, 20);
	}
	private void setUpEscenario3(){
		proyectil=new Proyectil(Jugador.DERECHA, Jugador.AIM_UP, 0, 0, Proyectil.JUGADOR, 20);
	}
	private void setUpEscenario4(){
		proyectil=new Proyectil(Jugador.DERECHA, Jugador.AIM_DOWN, 0, 0, Proyectil.JUGADOR, 20);
	}
	
	
	@Test
	public void testMovimiento(){
		setUpEscenario1();
		proyectil.movimiento();
		assertEquals(85, proyectil.getPosX());
		setUpEscenario2();
		proyectil.movimiento();
		assertEquals(-45, proyectil.getPosX());
		setUpEscenario3();
		proyectil.movimiento();
		assertEquals(-45, proyectil.getPosY());
		setUpEscenario4();
		proyectil.movimiento();
		assertEquals(43, proyectil.getPosY());
	}

}
