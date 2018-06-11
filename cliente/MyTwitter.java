package mytwitter.cliente;

import java.util.Vector;
import mytwitter.repositorio.*;

public class MyTwitter implements ITwitter{
	
	private IRepositorioUsuario repositorio;
	
	public MyTwitter(IRepositorioUsuario repositorio){
		this.repositorio = repositorio;
	}

	public void criarPerfil(Perfil usuario) throws PEException{
		try{
			repositorio.cadastrar(usuario);
		}
		catch(UJCException e){
			throw new PEException(e.usuario);
		}
	}

	public void cancelarPerfil(String usuario) throws PIException, PDException{
		Perfil u = repositorio.buscar(usuario);
		if(u != null)
			if(u.isAtivo())
				u.setAtivo(false);
			else throw new PDException(usuario);
		else throw new PIException(usuario);
	}

	public void tweetar(String usuario, String mensagem) throws PIException, MFPException{
		Perfil s, u = repositorio.buscar(usuario);
		Tweet tweet = new Tweet();
		tweet.setUsuario(usuario);
		tweet.setMensagem(mensagem);
		if(u != null){
			if (u.isAtivo()){
				if(mensagem.length() < 141 && mensagem.length() > 0){
					u.addTweet(tweet);
					for(int i=0; i<u.getSeguidores().size(); i++){
						s = repositorio.buscar(u.getSeguidores().get(i));
						if(s != null && s.isAtivo())
							s.addTweet(tweet);
					}
				}
				else throw new MFPException(mensagem);
			}
		}
		else throw new PIException(usuario);

	}

	public Vector<Tweet> timeline(String usuario) throws PIException, PDException{
		Perfil u = repositorio.buscar(usuario);
		if(u != null){
			if(u.isAtivo()){
				return u.getTimeline();
			}
			else throw new PDException(usuario);
		}
		else throw new PIException(usuario);
	}


	public Vector<Tweet> tweets(String usuario) throws PIException, PDException{
		Perfil u = repositorio.buscar(usuario);
		Vector<Tweet> tweets = new Vector<Tweet>();
		if(u != null){
			if(u.isAtivo()){
				for(int i=0; i<u.getTimeline().size(); i++){
					if(u.getTimeline().get(i).getUsuario().equals(usuario))
						tweets.add(u.getTimeline().get(i));
				}
				return tweets;
			}
			else throw new PDException(usuario);
		}
		else throw new PIException(usuario);
	}
	public void seguir(String seguidor, String seguido) throws PIException, PDException, SIException{
		Perfil seguidoPerfil = repositorio.buscar(seguido);
		Perfil seguidorPerfil = repositorio.buscar(seguidor);
		if(!seguidorPerfil.getUsuario().equals(seguidoPerfil.getUsuario())){
			if(seguidorPerfil != null){
				if(seguidoPerfil != null){
					if(seguidorPerfil.isAtivo()){
						if(seguidoPerfil.isAtivo()){
							seguidoPerfil.addSeguidor(seguidor);
						}
						else throw new PDException(seguido);
					}
					else throw new PDException(seguidor);
				}
				else throw new PIException(seguido);
			}
			else throw new PIException(seguidor);
		}
		else throw new SIException(seguidor, seguido);
	}
	public int numeroSeguidores(String usuario) throws PIException, PDException{
		Perfil u = repositorio.buscar(usuario);
		if(u != null){
			if(u.isAtivo()){
				return u.getSeguidores().size();
			}
			else throw new PDException(usuario);
		}
		else throw new PIException(usuario);
	}
	public Vector<Perfil> seguidores(String usuario) throws PIException, PDException{
		Perfil u = repositorio.buscar(usuario);
		Vector<Perfil> seguidores = new Vector<Perfil>();
		if(u != null){
			if(u.isAtivo()){
				for(int i=0; i<u.getSeguidores().size(); i++)
					seguidores.add(repositorio.buscar(u.getSeguidores().get(i)));
				return seguidores;
			}
			else throw new PDException(usuario);
		}
		else throw new PIException(usuario);
	}
}

