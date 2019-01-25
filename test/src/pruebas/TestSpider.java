package pruebas;
import org.junit.Test;

import mundo.Entidad;
import mundo.Escenario;
import mundo.Spider;
import mundo.UsuarioYaExisteException;

import static org.junit.Assert.*;
public class TestSpider {
	
	private Spider spider;
	private Escenario escenario;
	
	private void setUpEscenario1(){
		escenario=new Escenario(1,0);
		spider=new Spider(520, 675, 0, 0, 20, true, "imagen", true, Entidad.DERECHA, 1, 1, false, false, escenario);
	}
	@Test
	public void testEnRango(){
		setUpEscenario1();
		spider.enRango();
		assertEquals(true, spider.isDisparando());
	}
	@Test
	public void testMovimiento(){
		setUpEscenario1();
		spider.movimiento();
		assertEquals(522, spider.getPosX());
	}
	@Test
	public void testDano(){
		setUpEscenario1();
		spider.Dano();
		assertEquals(1, escenario.getProyectilesSpider().size());
	}

}
