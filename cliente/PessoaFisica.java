package mytwitter.cliente;

public class PessoaFisica extends Perfil{
	private long cpf;

	PessoaFisica(String nome){
		super(nome);
	}

	public void setCpf(long cpf){
		this.cpf = cpf;
	}

	public long getCpf(){
		return cpf;
	}

}


