package mytwitter.cliente;

public class MFPException extends Exception{
	public String mensagem;

	public MFPException(String mensagem){
		this.mensagem = mensagem;
	}

}
