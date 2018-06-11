package mytwitter.cliente;

public class PIException extends Exception {
	public String usuario;
	public PIException(String usuario){
		this.usuario = new String(usuario);
	}
}

