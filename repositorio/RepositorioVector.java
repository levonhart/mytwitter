package mytwitter.repositorio;

import java.util.Vector;
import mytwitter.cliente.Perfil;
import mytwitter.cliente.PessoaFisica;
import mytwitter.cliente.PessoaJuridica;


public class RepositorioVector implements IRepositorioUsuario {
	private Vector<Perfil> contas;

	public RepositorioVector(Vector<Perfil> contas){
			this.contas = new Vector<Perfil>(contas);
	}

	public RepositorioVector(){
		contas = new Vector<Perfil>();
	}

	public Perfil buscar(String usuario){
		for(int i=0; i < contas.size(); i++){
				if(contas.get(i).getUsuario().equals(usuario))
						return contas.get(i);
		}
		return null;
	}

	public void cadastrar(Perfil usuario) throws UJCException{
		if(buscar(usuario.getUsuario()) == null)
			contas.add(usuario);
		else{
			throw new UJCException(usuario.getUsuario());
		}
	}

	public void atualizar(Perfil usuario) throws UNCException{
		Perfil p;
		p = buscar(usuario.getUsuario());
		if(p != null){
			p.setAtivo(usuario.isAtivo());
			for(int i=0; i<usuario.getSeguidores().size(); i++)
				p.addSeguidor(usuario.getSeguidores().get(i));
			for(int i=0; i<usuario.getTimeline().size(); i++)
				p.addTweet(usuario.getTimeline().get(i));
			if(usuario instanceof PessoaFisica && p instanceof PessoaFisica)
					((PessoaFisica)p).setCpf(((PessoaFisica)usuario).getCpf());
			else if (usuario instanceof PessoaJuridica && p instanceof PessoaJuridica)
					((PessoaJuridica)p).setCnpj(((PessoaJuridica)usuario).getCnpj());
		}
		else throw new UNCException(usuario.getUsuario());
	}
}
