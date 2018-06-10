package mytwitter.repositorio;

public class UNCException extends Exception {
	public String usuario;
	UNCException(String usuario){
		this.usuario = new String(usuario);
	}
}

