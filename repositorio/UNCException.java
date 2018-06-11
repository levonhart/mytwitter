package mytwitter.repositorio;

public class UNCException extends Exception {
	public String usuario;
	public UNCException(String usuario){
		this.usuario = new String(usuario);
	}
}

