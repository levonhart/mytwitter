package mytwitter.cliente;

public class PDException extends Exception {
	public String usuario;
	public PDException(String usuario){
		this.usuario = new String(usuario);
	}
}
