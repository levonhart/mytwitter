package mytwitter.cliente;

public class Tweet {
	private String usuario, mensagem;

	Tweet(){}

	public void setUsuario(String usuario){
			this.usuario = usuario;
	}

	public void setMensagem(String mensagem){
			this.mensagem = mensagem;
	}

	public String getUsuario(){
			return usuario;
	}

	public String getMensagem(){
			return mensagem;
	}
}

