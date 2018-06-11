package mytwitter.cliente;

public class PEException extends Exception {
	public String usuario;
	public PEException(String usuario){
		this.usuario = new String(usuario);
	}
}

