package pruebas;

import org.junit.Test;
import static org.junit.Assert.*;

import mundo.Boss;
import mundo.Escenario;
import mundo.Jugador;
import mundo.Proyectil;

public class TestBoss {

	private Boss boss;
	private Escenario escenario;
	
	private void setUpEscenario1(){
		escenario = new Escenario(1,0);
		boss= new Boss(escenario);
	}
	
	private void setUpEscenario2(){
		escenario = new Escenario(1,0);
		boss= new Boss(escenario);
		escenario.agregarProyectil(new Proyectil(Jugador.DERECHA, Jugador.NO_AIM, 705, 30,Proyectil.JUGADOR,20));
	}
	
	@Test
	public void testMovimiento(){
		setUpEscenario1();
		boss.movimiento();
		
		assertEquals(705, boss.getPosX());
		assertEquals("./data/BossHelicopter/Movimiento/Mov0D.png", boss.getImagenActual());
		
		boss.setPosX(700);
		boss.movimiento();
		boss.movimiento();
		
		assertEquals('I', boss.getDireccion());
		assertEquals(690, boss.getPosX());
	}
	
	@Test
	public void testGolpeado(){
		setUpEscenario2();
		boss.golpeado();
		
		assertEquals(1980, boss.getVida());
		
		boss.setVida(1800);
		boss.golpeado();
		
		assertEquals(true, boss.isEnAtaque());
		
		boss.setVida(0);
		boss.golpeado();
		
		assertEquals(false, boss.getactivo());
	}
	
	@Test
	public void testDano(){
		setUpEscenario1();
		boss.Dano();
		
		assertNotNull(escenario.getProyectilesBoss().get(0));
		}
	
}
