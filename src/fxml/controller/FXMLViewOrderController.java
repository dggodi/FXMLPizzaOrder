/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml.controller;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

/**
 *
 * @author dggodi
 */
public class FXMLViewOrderController
{
    @FXML private Text txtNameView;
    
    @FXML private Text txtFlavor;
    
    @FXML private Text txtSize;
    
    @FXML private Text txtTopping;
    
    @FXML private Text txtCost;
    
    private FXMLOrderController mainWindow;
    private boolean viewActive;
    /**
     * set parent controller to mainWindow
     * @param mainWindow
     */
    public void setMainWindow(FXMLOrderController mainWindow){
        this.mainWindow = mainWindow;
    }
    
    /**
     * retrieve item from parent controller and set 
     * the new value to txtNameView 
     * @param item
     */
    public void setNameProperty(String item) {
        txtNameView.setText(item);
    }
    
    /**
     * retrieve item from parent controller and set 
     * the new value to txtFlavor 
     * @param item
     */
    public void setItemFlavor(String item) {
        txtFlavor.setText(item);
    }
    
    /**
     * retrieve item from parent controller and set 
     * the new value to txtSize 
     * @param item
     */
    public void setItemSize(String item) {
        txtSize.setText(item);
    }
    
    /**
     * retrieve item from parent controller and set 
     * the new value to txtTopping
     * @param item
     */
    public void setExtraItem(String item) {
        txtTopping.setText(item);
    }
    
    /**
     * retrieve item from parent controller and set 
     * the new value to txtCost
     * @param item
     */
    public void setItemCost(String item) {
        txtCost.setText(item);
    }  
    
    /**
     * event button for hiding the window
     */
    @FXML
    private void btnOrderAction(ActionEvent event)throws IOException 
    {
        if( !viewActive )
            viewActive = true;
        else
        {
            ((Button)event.getSource()).getScene().getWindow().hide();
            viewActive = false;
        }     
    }
}
