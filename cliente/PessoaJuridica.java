package mytwitter.cliente;

public class PessoaJuridica extends Perfil{
	private long cnpj;

	public PessoaJuridica(String nome){
		super(nome);
	}
	
	public void setCnpj(long cnpj){
		this.cnpj = cnpj;
	}

	public long getCnpj(){
		return cnpj;
	}

}
