/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytwitter.ui;

import java.io.IOException;
import java.lang.Long;
import java.lang.NumberFormatException;
import java.net.URL;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Modality;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.text.Text;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import mytwitter.cliente.MyTwitter;
import mytwitter.cliente.Perfil;
import mytwitter.cliente.PessoaFisica;
import mytwitter.cliente.PessoaJuridica;
import mytwitter.repositorio.IRepositorioUsuario;
import mytwitter.cliente.PEException;
import mytwitter.ui.ControladorLogin;

public class ControladorLogin {
    
    private MyTwitter cliente; 
    private IRepositorioUsuario repositorio;


    @FXML private Button submitButton;

    @FXML private TextField userField;

    @FXML private PasswordField passwordField;
    
    @FXML private void handleSignupButtonAction(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        stage = (Stage)submitButton.getScene().getWindow();
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("TelaCadastro.fxml"));
            root = (Parent)loader.load();
            ControladorCadastro controlador = loader.<ControladorCadastro>getController();
            controlador.setCliente(cliente);
            controlador.setRepositorio(repositorio);

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("MyTwitter ~ Cadastro");
            stage.show();
        }
        catch(IOException exception){ 
            throw new IOException("TelaCadastro.fxml não encontrado."); 
        }

        
    //*/

    }

    @FXML private void handleSubmitButtonAction(ActionEvent event) throws IOException {
       
        String usuario = userField.getText();
        try{
            long senha = Long.parseLong(passwordField.getText());
            Perfil p = repositorio.buscar(usuario);
            if(p instanceof PessoaFisica && ((PessoaFisica)p).getCpf() == senha || p instanceof PessoaJuridica && ((PessoaJuridica)p).getCnpj() == senha){

                Stage stage;
                Parent root;
                stage = (Stage)submitButton.getScene().getWindow();

                try{
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("TelaPrincipal.fxml"));
                    root = (Parent)loader.load();
                    ControladorTelaPrincipal controlador = loader.<ControladorTelaPrincipal>getController();
                    controlador.setCliente(cliente);
                    controlador.setUsuario(p);
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.setTitle("MyTwitter ~ Início");
                    stage.setResizable(true);
                    stage.setMaximized(true);
                    stage.show();
                }
                catch(IOException exception){ 
                    throw new IOException("TelaPrincipal.fxml não encontrado."); 
                }

        }
        else{
            alertPopup("Aviso","Falha de Autenticacao");
        }
    }
    catch(NumberFormatException exception){
        alertPopup("ERRO:Entrada Invalida",exception.getMessage());
    }
        

    }


    public void setCliente(MyTwitter cliente){
        this.cliente = cliente;
    }
    public void setRepositorio(IRepositorioUsuario repositorio){
        this.repositorio = repositorio;
    }


    public void alertPopup(String titulo, String msg){
        Stage janela = new Stage();

        janela.initModality(Modality.APPLICATION_MODAL);
        janela.setTitle(titulo);
        janela.setMinWidth(330);
        janela.setMinHeight(150);


        Label mensagem = new Label();
        mensagem.setText(msg);
        Button btn = new Button("Fechar");
        btn.setOnAction(e -> janela.close());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(mensagem,btn);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(30, 50, 30 , 50));

        Scene cena = new Scene(layout);
        janela.setScene(cena);
        janela.showAndWait();
    }
    

//ControladorLogin controlador = loader.getController();
        //controlador.setUsuario();
    
}
