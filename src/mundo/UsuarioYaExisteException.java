package mundo;

public class UsuarioYaExisteException extends Exception{
	public UsuarioYaExisteException(String mensaje) {
		super(mensaje);
	}
}
