package mytwitter.cliente;

public class SIException extends Exception {
	public String seguidor, seguido;
	SIException(String seguidor, String seguido){
		this.seguidor = new String(seguidor);
		this.seguido = new String(seguido);
	}
}
