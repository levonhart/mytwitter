/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytwitter.ui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.text.Text;
import javafx.scene.control.ToggleGroup;
import mytwitter.cliente.*;
        

public class ControladorTelaPrincipal implements Initializable{

    private MyTwitter cliente; 
    private Perfil usuario;
    
    @FXML private ScrollPane scrollPane;
    @FXML private TextArea textAreaTweet;
    @FXML private ListView listaTweets;


    @FXML private void handleTimelineButtonAction(ActionEvent event){

    }

    @FXML private void handleTweetsButtonAction(ActionEvent event){

    }

    @FXML private void handleSeguidoresButtonAction(ActionEvent event){

    }

    @FXML private void handleSairButtonAction(ActionEvent event){

    }

    @FXML public void initialize(URL location, ResourceBundle resources){
	   scrollPane.setFitToHeight(true);
       scrollPane.setFitToWidth(true);
    }

    public void setCliente(MyTwitter cliente){
        this.cliente = cliente;
    }
    public void setUsuario(Perfil usuario){
        this.usuario = usuario;
    }
    
}
