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

public class ControladorCadastro implements Initializable{
    private MyTwitter cliente; 
    private IRepositorioUsuario repositorio;
    
    @FXML private Button cancelButton;

    @FXML private ToggleGroup tipoDePerfil;

    @FXML private Text cpfCnpjText;

    @FXML private TextField userField;

    @FXML private TextField cpfCnpjField;

    @FXML private RadioButton cpfRadioButton;

    @FXML private void handleSubmitButtonAction(ActionEvent event) {
        Perfil p;
        if(tipoDePerfil.getSelectedToggle().equals(cpfRadioButton)){
            p = new PessoaFisica(userField.getText());
            try{
                long l = Long.parseLong(cpfCnpjField.getText());
                ((PessoaFisica)p).setCpf(l);
                if (((PessoaFisica)p).getCpf() == 0) {
                    alertPopup("ERRO:Entrada Inválida","Campo Obrigatório.");
                    p=null;    
                }
            }
            catch(NumberFormatException exception){
                alertPopup("ERRO:Entrada Inválida",exception.getMessage());
                p=null;
            }
        }
        else {
            p = new PessoaJuridica(userField.getText());

            try{
                long l = Long.parseLong(cpfCnpjField.getText());
                ((PessoaJuridica)p).setCnpj(l);
                if (((PessoaJuridica)p).getCnpj() == 0) {
                    alertPopup("ERRO:Entrada Inválida","Campo Obrigatório.");
                    p=null;    
                }
            }
            catch(NumberFormatException exception){
                alertPopup("ERRO:Entrada Invalida",exception.getMessage());
                p=null;
            }
        }
        try{
            if(p!=null) {
                cliente.criarPerfil(p);
                alertPopup("Sucesso","Usuario cadastrado com sucesso. Acesse a sua conta utilizando o numero do " + (p instanceof PessoaFisica ? "CPF" : "CNPJ") + " como senha.");
                cancelButton.fire();
            }
        }
        catch(PEException exception){
            alertPopup("Usuario ja Cadastrado","Nome de usuario nao disponivel. Tente Novamente.");
        }
            
        
    }

    @FXML private void handleCpfButtonAction(ActionEvent event){
    	cpfCnpjText.setText("Cpf: ");
    }

    @FXML private void handleCnpjButtonAction(ActionEvent event){
    	cpfCnpjText.setText("Cnpj: ");
    }

    @FXML private void handleCancelarButtonAction(ActionEvent event) throws IOException{
        Stage stage;
        Parent root;
        stage = (Stage)cancelButton.getScene().getWindow();
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
		cpfRadioButton.fire();
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
    
}
