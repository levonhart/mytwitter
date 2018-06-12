package mytwitter.repositorio;

import java.util.Vector;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import mytwitter.cliente.Perfil;
import mytwitter.cliente.PessoaFisica;
import mytwitter.cliente.PessoaJuridica;
import mytwitter.repositorio.RepositorioVector;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;



public class RepositorioXml extends RepositorioVector implements IRepositorioUsuario {
	private Vector<Perfil> contas;
	private File repositorio;
	private XStream driver;

	public RepositorioXml(String path){
		contas = new Vector<Perfil>();
		driver = new XStream(new DomDriver());
		repositorio = new File(path);
		
		//IMPORTAR
	}

	public RepositorioXml(Vector<Perfil> contas){
		super(contas);
		driver = new XStream(new DomDriver());
		repositorio = new File("Usuarios.xml");
	}

	public RepositorioXml(){
		contas = new Vector<Perfil>();
		driver = new XStream(new DomDriver());
		repositorio = new File("Usuarios.xml");
	}

	public Perfil buscar(String usuario){
		return super.buscar(usuario);
	}

	public void cadastrar(Perfil usuario) throws UJCException{
		super.cadastrar(usuario);
		escrever();
	}

	public void atualizar(Perfil usuario) throws UNCException{
		super.atualizar(usuario);
		escrever();
	}

	private void escrever(){
		try{
			PrintStream escritor = new PrintStream(repositorio);
			for(Perfil p : contas)
				escritor.println(driver.toXML(p));
			escritor.close();
		}
		catch(FileNotFoundException exception){
		}

	}

}
