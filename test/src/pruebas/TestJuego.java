package pruebas;

import org.junit.Test;
import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import mundo.Escenario;
import mundo.Juego;
import mundo.JugadorNoEncontradoException;
import mundo.UsuarioYaExisteException;

public class TestJuego {

	private Juego juego;
	
	private void setUpEscenario1(){
		juego = new Juego();
		juego.setEscenario(new Escenario(1, 0));
	}
	
	private void setUpEscenario2() throws UsuarioYaExisteException{
		juego = new Juego();
		juego.agregarPuntaje("David", 3);
		juego.agregarPuntaje("Juan", 2);
	}
	private void setUpEscenario3() throws UsuarioYaExisteException{
		juego = new Juego();
		juego.agregarPuntaje("Victor", 10);
		juego.agregarPuntaje("Juan", 20);
	}
	
	@Test
	public void testGuardarJuego(){
		setUpEscenario1();
		
		try{
		juego.guardarJuego(Juego.RUTA_GUARDADO);
		}catch(FileNotFoundException e){
		fail("Se lanzo excepcion no esperada: FileNotFoundException");
		}
	}
	
	@Test
	public void testCargarJuego(){
		setUpEscenario1();
		
		try{
		juego.cargarJuego(Juego.RUTA_GUARDADO);
		}catch(IOException e){
		fail("Se lanzo excepcion no esperada: IOException");
		}
	}
	
	@Test
	public void testReportePuntajes() throws UsuarioYaExisteException{
		setUpEscenario2();
		assertEquals("Usuario:	Puntaje:\n"+"David	3\n"+"Juan	2\n",juego.reportePuntajes());
	}
	
	@Test
	public void testBuscarPuntaje() throws UsuarioYaExisteException{
		setUpEscenario2();
		
		try {
			juego.buscarPuntaje("David");
		} catch (JugadorNoEncontradoException e) {
			fail("Lanzo excepcion no esperada: JugadorNoEncontradoException");
		}
		
		setUpEscenario2();
		
		try {
			juego.buscarPuntaje("A");
			fail("Lanzo excepcion no esperada: JugadorNoEncontradoException");
		} catch (JugadorNoEncontradoException e) {
		}
	}
	@Test
	public void testOrdenarPorPuntaje(){
		try {
			setUpEscenario3();
			juego.ordenarPorPuntaje();
			assertEquals(20, juego.getPuntajes().get(0).getPuntaje());
		} catch (UsuarioYaExisteException e) {
			e.printStackTrace();
		}
		
		
	}
	@Test
	public void testOrdenarPorNombre(){
		try {
			setUpEscenario3();
			juego.ordenarPorNombre();
			assertEquals("Juan", juego.getPuntajes().get(0).getUsuario());
		} catch (UsuarioYaExisteException e) {
			e.printStackTrace();
		}
		
		
	}
	
}
