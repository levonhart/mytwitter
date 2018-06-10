package mytwitter.cliente;

public class MFPException extends Exception{
	public String mensagem;

	MFPException(String mensagem){
		this.mensagem = mensagem;
	}

}
