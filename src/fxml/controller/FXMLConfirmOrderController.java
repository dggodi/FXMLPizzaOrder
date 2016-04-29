/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javax.swing.JOptionPane;

/**
 *
 * @author dggodi
 */
public class FXMLConfirmOrderController implements Initializable
{
    @FXML private Label lblConfirmOrder;
    
    // parent controller
    private FXMLOrderController mainWindow;

    /**
     * set parent controller to mainWindow
     * @param mainWindow
     */
    public void setMainWindow(FXMLOrderController mainWindow){
        this.mainWindow = mainWindow;
    }
    
    /**
     * retrieve item from parent controller and set 
     * the new value to lblConfirmOrder 
     * @param item
     */
    public void setMessageProperty(String item) {
        lblConfirmOrder.setText(item);
    }
    
    /**
     * button event that terminates the program
     * @param event
     */
    public void btnOKOrderAction(ActionEvent event){
        ((Button)event.getSource()).getScene().getWindow().hide();
        JOptionPane.showMessageDialog(null, "Thank you for your order");
        Platform.exit();
    }
    
    /**
     * button event that closes the confirm order window
     */
    @FXML
    private void btnCancelOrderAction(ActionEvent event){   
        ((Button)event.getSource()).getScene().getWindow().hide();
    }
    
    /**
     * Initializes the ConfirmStage_FXMLController.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
}
