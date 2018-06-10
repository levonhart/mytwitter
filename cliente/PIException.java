package mytwitter.cliente;

public class PIException extends Exception {
	public String usuario;
	PIException(String usuario){
		this.usuario = new String(usuario);
	}
}

