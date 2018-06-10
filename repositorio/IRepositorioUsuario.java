package mytwitter.repositorio;

import mytwitter.cliente.Perfil;

public interface IRepositorioUsuario{
	public void cadastrar(Perfil usuario) throws UJCException;
	public Perfil buscar(String usuario);
	public void atualizar(Perfil usuario) throws UNCException;
}
