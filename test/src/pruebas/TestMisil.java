package pruebas;

import org.junit.Test;
import mundo.Escenario;
import mundo.Jugador;
import mundo.Misil;
import mundo.Proyectil;

import static org.junit.Assert.*;

public class TestMisil {

	private Misil misil;
	private Escenario escenario;
	
	private void setUpEscenario1(){
	escenario = new Escenario(1,0);
	misil = new Misil(Jugador.AIM_DOWN, Jugador.AIM_DOWN, 500, 500, Proyectil.MISIL, 50, escenario);	
	escenario.agregarProyectil(new Proyectil(Jugador.DERECHA, Jugador.NO_AIM, 621, 666,Proyectil.JUGADOR,20));
	}
	
	@Test
	public void testGolpeado(){
		setUpEscenario1();
		misil.golpeado();
		
		assertEquals(20, misil.getVida());
	}
	
	
	
}
