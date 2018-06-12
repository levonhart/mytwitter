/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytwitter.ui;

import java.net.URL;
import java.io.IOException;
import java.util.ResourceBundle;
import java.util.Vector;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.text.Text;
import javafx.scene.control.ToggleGroup;
import mytwitter.cliente.*;
import mytwitter.repositorio.IRepositorioUsuario;
import mytwitter.cliente.PIException;
        

public class ControladorTelaPrincipal implements Initializable{

    private MyTwitter cliente; 
    private String usuario;
    private IRepositorioUsuario repositorio;
    
    @FXML private ScrollPane scrollPane;
    @FXML private TextArea textAreaTweet;
    @FXML private ListView listaCentro;
    @FXML private Button timelineBtn;
    @FXML private Button seguidoresBtn;
    @FXML private Button sairBtn;

    @FXML private void handleTimelineButtonAction(ActionEvent event){
        try{
            Vector<String> conteudo = new Vector<String>();
            for(Tweet t : cliente.timeline(usuario))
                conteudo.add(t.getUsuario() + ":  " + t.getMensagem());
            ObservableList<String> timeline = FXCollections.observableArrayList(conteudo);
            listaCentro.setItems(timeline);

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @FXML private void handleTweetsButtonAction(ActionEvent event){
        try{
            Vector<String> conteudo = new Vector<String>();
            for(Tweet t : cliente.tweets(usuario))
                conteudo.add(t.getUsuario() + ":  " + t.getMensagem());
            ObservableList<String> timeline = FXCollections.observableArrayList(conteudo);
            listaCentro.setItems(timeline);

        }catch(Exception e){
            e.printStackTrace();
        }
    }  

    @FXML private void handleTweetarButtonAction(ActionEvent event){
        try{
        cliente.tweetar(usuario, textAreaTweet.getText());
        timelineBtn.fire();
        }
        catch(Exception e){
             e.printStackTrace();
        }
    }

    @FXML private void handleSeguirButtonAction(ActionEvent event){
        try{
        cliente.seguir(textAreaTweet.getText(), usuario);
        seguidoresBtn.fire();
        }
        catch(Exception e){
             e.printStackTrace();
        }
    }

    @FXML private void handleSeguidoresButtonAction(ActionEvent event){
         try{
            Vector<String> conteudo = new Vector<String>();
            for( Perfil p : cliente.seguidores(usuario) )
                conteudo.add(p.getUsuario());
            ObservableList<String> timeline = FXCollections.observableArrayList(conteudo);
            listaCentro.setItems(timeline);

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @FXML private void handleSairButtonAction(ActionEvent event) throws IOException{
        Stage stage;
        Parent root;
        stage = (Stage)sairBtn.getScene().getWindow();
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("TelaLogin.fxml"));
            root = (Parent)loader.load();
            ControladorLogin controlador = loader.<ControladorLogin>getController();
            controlador.setCliente(cliente);
            controlador.setRepositorio(repositorio);

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("MyTwitter ~ Cadastro");
            stage.show();

        }
        catch(IOException exception){ 
            throw new IOException("TelaLogin.fxml não encontrado."); 
        }
    }

    @FXML public void initialize(URL location, ResourceBundle resources){       
	   ObservableList<String> timeline = FXCollections.observableArrayList("Bem vindo");
       listaCentro.setItems(timeline);
    }

    public void setCliente(MyTwitter cliente){
        this.cliente = cliente;
    }
    public void setUsuario(String usuario){
        this.usuario = usuario;
    }
    public void setRepositorio(IRepositorioUsuario repositorio){
        this.repositorio = repositorio;
    }
}
