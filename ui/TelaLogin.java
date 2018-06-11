/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytwitter.ui;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mytwitter.cliente.MyTwitter;
import mytwitter.repositorio.RepositorioVector;
import mytwitter.ui.ControladorLogin;

public class TelaLogin extends Application {
    private RepositorioVector dados;
    private MyTwitter cliente;

    @Override

    public void start(Stage stage) throws Exception {

        dados = new RepositorioVector();
        cliente = new MyTwitter(dados);

        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("TelaLogin.fxml"));
            Parent root = (Parent)loader.load();
            ControladorLogin controlador = loader.<ControladorLogin>getController();
            controlador.setCliente(cliente);
            controlador.setRepositorio(dados);
            Scene scene = new Scene(root);
            
            stage.setScene(scene);
            stage.setTitle("MyTwitter ~ Login");
            stage.setResizable(false);
            stage.show();
        }
        catch(IOException exception){ 
            System.out.println(exception.getMessage());
            exception.printStackTrace();
        }

        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
