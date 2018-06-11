package mytwitter.repositorio;

public class UJCException extends Exception {
	public String usuario;
	public UJCException(String usuario){
		this.usuario = new String(usuario);
	}
}

