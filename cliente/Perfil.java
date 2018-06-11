package mytwitter.cliente;

import java.util.Vector;
import mytwitter.cliente.Tweet;

public abstract class Perfil{
	private String usuario;
	private Vector<String> seguidores;
	private Vector<Tweet> timeline;
	private boolean ativo;

	public Perfil(String usuario){
		this.usuario = new String(usuario);
		seguidores = new Vector<String>();
		timeline = new Vector<Tweet>();
	}

	public void addSeguidor(String usuario){
		if(!seguidores.contains(usuario))
			seguidores.add(usuario);
	}

	public void addTweet(Tweet tweet){
		if(!timeline.contains(tweet))
			timeline.add(tweet);
	}

	public void setUsuario(String usuario){
		this.usuario = usuario;
	}

	public String getUsuario(){
		return usuario;
	}

	public Vector<String> getSeguidores(){
		return seguidores;
	}

	public Vector<Tweet> getTimeline(){
		return timeline;
	}

	public void setAtivo(boolean ativo){
		this.ativo = ativo;
	}

	public boolean isAtivo(){
		return ativo;
	}

}
