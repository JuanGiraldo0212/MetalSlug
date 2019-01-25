package pruebas;

import static org.junit.Assert.*;

import org.junit.Test;

import mundo.Entidad;
import mundo.Escenario;
import mundo.Jugador;
import mundo.Personaje;
import mundo.Proyectil;
import mundo.Spider;

public class TestEscenario {
	
	private Escenario escenario;
	
	private void setUpEscenario1(){
		escenario = new Escenario(1,0);
	}
	
	@Test
	public void testIniciarNivel1(){
		setUpEscenario1();
		
		assertEquals(46,escenario.getObstaculos().size());
		assertEquals(7, escenario.getObstaculosDaninos().size());
	}
	
	@Test
	public void testEstaEnPlataforma(){
		setUpEscenario1();
		escenario.getJugador().setPosY(810);
		assertEquals(true, escenario.estaEnPlataforma());
	}
	
	@Test
	public void testEstaEnPlataformaArana(){
		setUpEscenario1();
		Spider b = new Spider(600,-30,600,-30,60,false,"./data/Spider/Idle/I3.png",true, Entidad.DERECHA,0,0,false,false,escenario);
		b.setPosY(860);
		assertEquals(true,	escenario.estaEnPlataformaArana(b));
	}
	
	@Test
	public void testAgregarProyectil(){
		setUpEscenario1();
		Proyectil a = new Proyectil(Jugador.DERECHA, Jugador.NO_AIM, 0, 0,Proyectil.JUGADOR,20);
		escenario.agregarProyectil(a);
		assertNotNull(escenario.getPrimerProyectil());
	}
	
	@Test
	public void testEliminarProyectil(){
		setUpEscenario1();
		Proyectil a = new Proyectil(Jugador.DERECHA, Jugador.NO_AIM, 0, 0,Proyectil.JUGADOR,20);
		escenario.agregarProyectil(a);
		escenario.eleminarProyectil(a);
		assertNull(escenario.getPrimerProyectil());	
	}
	
	@Test
	public void testBuscarProyectil(){
		setUpEscenario1();
		Proyectil a = new Proyectil(Jugador.DERECHA, Jugador.NO_AIM, 0, 0,Proyectil.JUGADOR,20);
		escenario.agregarProyectil(a);
		assertEquals(a, escenario.buscarProyectilRecursivo(a));
	}
}
