package pruebas;
import org.junit.Test;

import mundo.Escenario;
import mundo.Puntaje;
import mundo.UsuarioYaExisteException;

import static org.junit.Assert.*;

import java.util.ArrayList;
public class TestPuntaje {

	private Puntaje puntaje;
	
	private void setUpEscenario1(){
		puntaje=new Puntaje("Juan", 100);
	}
	private void setUpEscenario2(){
		puntaje=new Puntaje("Juan", 100);
		try {
			puntaje.insertarNuevoPuntaje(new Puntaje("Hola", 50));
			puntaje.insertarNuevoPuntaje(new Puntaje("Andres", 80));
			puntaje.insertarNuevoPuntaje(new Puntaje("Pedro", 20));
		} catch (UsuarioYaExisteException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testCompararPuntaje(){
		setUpEscenario1();
		assertEquals(1, puntaje.compararPuntaje(new Puntaje("Hola", 50)));
		
	}
	@Test
	public void testCompararNombre(){
		setUpEscenario1();
		assertEquals(1, puntaje.compararNombre("Andres"));
		
	}
	@Test
	public void testInsertarNuevoPuntaje(){
		setUpEscenario1();
		try {
			Puntaje actual=new Puntaje("Pedro",50);
			puntaje.insertarNuevoPuntaje(actual);
			assertEquals(actual, puntaje.getDerecha());
			
		} catch (UsuarioYaExisteException e) {
			fail("Se lanzo la excepcion no esperada: UsuarioYaExisteException");
		}
		assertEquals(1, puntaje.compararNombre("Andres"));
		
	}
	@Test
	public void testInorden(){
		setUpEscenario2();
		ArrayList<Puntaje> puntajes=new ArrayList<Puntaje>();
		puntaje.inorden(puntajes);
		assertEquals(4, puntajes.size());
		
	}
}
