/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;
import javafx.scene.text.Text;

import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import pizzaapp.CustomerOrder;
import pizzaapp.PizzaApp;
/**
 *
 * @author dggodi
 */
public class FXMLOrderController extends StackPane{
    
    @FXML private Button btnView;
    
    @FXML private TextField txtname;
    
    @FXML private Text txtNameView;
    
    @FXML private Text txtFlavor;
    
    @FXML private Text txtSize;
    
    @FXML private Text txtTopping;
    
    @FXML private Text txtCost;  
    
    @FXML private Text txtrequired;
    
    @FXML private Label lblCost;
    
    // Value injected by FXMLLoader
    @FXML private ComboBox<String> flavorComboBox; 
    
    @FXML private ComboBox<String> sizeComboBox;
    
    @FXML private ComboBox<String> extraComboBox;
    
    private final String fxmlViewOrderFile = "/fxml/document/FXMLViewOrder.fxml";
    private final String fxmlConfirmOrderFile = "/fxml/document/FXMLConfirmOrder.fxml";
    private final String cssFile = "/resources/pizzaApp.css";
    
    private CustomerOrder customerOrder;
    
    private boolean typeOrder;
    private boolean sizeOrder;
    private boolean viewActive;
 
     /**
     * This method is called by the FXMLLoader when initialization is complete
     * Also the combox values are set and their events 
     */
    @FXML 
    private void initialize() {
        
        customerOrder = PizzaApp.getCustomerOrder();
 
        assert flavorComboBox != null : "fx:id=\"flavorComboBox \" was not injected: check your FXML file 'OrderStage_FXML.fxml'.";
        assert sizeComboBox != null : "fx:id=\"sizeComboBox \" was not injected: check your FXML file 'OrderStage_FXML.fxml'.";
        assert extraComboBox != null : "fx:id=\"extraComboBox \" was not injected: check your FXML file 'OrderStage_FXML.fxml'.";
       
        // Initialize your logic here: all @FXML variables will have been injected
        flavorComboBox.getItems().clear();
        sizeComboBox.getItems().clear();
        extraComboBox.getItems().clear();
        
        // set combobox values
        flavorComboBox.getItems().addAll("Pepperoni", "Hawaiian", "Veggie", "Meat", "Special");
        sizeComboBox.getItems().addAll("Small", "Medium", "Large");
        extraComboBox.getItems().addAll("Extra Cheese", "Green Pepper", "Onion", "Mushroom", 
                                        "Black Olive", "Tomato", "Jalapeno Pepper");
        
        /**
         * set up an event for selecting values from combobox
         * and store its new value 
         */
        flavorComboBox.setOnAction(e -> 
        {
            String flavor = flavorComboBox.getSelectionModel().getSelectedItem();
            customerOrder.setItemType(flavor);
            this.lblCost.setText(customerOrder.getTotalCost());
            typeOrder = true;
        });
        
        /**
         * set up an event for selecting values from combobox
         * and store its new value 
         */
        sizeComboBox.setOnAction(e -> 
        {
            String size = sizeComboBox.getSelectionModel().getSelectedItem();
            customerOrder.setItemSize(size);
            this.lblCost.setText(customerOrder.getTotalCost());
            sizeOrder = true;
        });
         
        /**
         * set up an event for selecting values from combobox
         * and store its new value 
         */
        extraComboBox.setOnAction(e -> 
        {
            String topping = extraComboBox.getSelectionModel().getSelectedItem();
            customerOrder.setExtraItem(topping);
            this.lblCost.setText(customerOrder.getTotalCost());
        });
    }
    
    
    
    /**
     * event button for constructing a new window to view 
     * pizza order.  Also the parent controller is sent to the child 
     * controller for window communication.  This is necessary for updating the 
     * text values in the view order window
     */
    @FXML
    private void btnViewAction(ActionEvent event)throws IOException 
    {
        viewActive = true;

        if (!txtname.getText().equals(""))
            txtname.setText( capitalizeText(txtname.getText()) );
        
        // store name in class
        customerOrder.setName(txtname.getText());
        
        // update cost displayed
        fillOrder(); 
    }
    
    private void fillOrder()throws IOException 
    {
        // Loads an object hierarchy from an XML document
           FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlViewOrderFile) );
            StackPane newWindow = (StackPane)loader.load();
            FXMLViewOrderController controller = loader.getController();
            controller.setMainWindow(this);
            
            // create new stage
            Stage stage = new Stage();
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(btnView.getScene().getWindow());
            
            // container for all content in the fxml file
            Scene scene = new Scene(newWindow, 460, 300);
            stage.setScene(scene);
            scene.getStylesheets().add(getClass().getResource(cssFile).toExternalForm());
            
            // send info to child controller to update child fxml
            controller.setNameProperty(customerOrder.getName());
            
            if(typeOrder && sizeOrder)
            {
                controller.setItemFlavor(customerOrder.getItem(0));
                controller.setItemSize(customerOrder.getItem(1));
                controller.setExtraItem(customerOrder.getItem(2));
                controller.setItemCost(customerOrder.getTotalCost()); 
            }
            
            stage.show();  
    }
    
    /**
     * event button for constructing a new window to view 
     * confirm order.  Also the parent controller is sent to the child 
     * controller for window communication.  This is necessary for updating the 
     * text values in the confirm order window
     */
    @FXML
    private void btnPlaceOrderAction(ActionEvent event)throws IOException 
    {
        // if txtname is empty display message
        // else: open new window and set up controller communication
        if (txtname.getText().equals(""))
            txtrequired.setText("* required");
        else
        {
            if ( isNumber(txtname.getText()) )
                    JOptionPane.showMessageDialog(null, "Please provide your name");
            else
            {
                if(!typeOrder || !sizeOrder)
                    JOptionPane.showMessageDialog(null, "Please select The type and size of pizza.");
                else
                {
                    txtrequired.setText("");
                    txtname.setText( capitalizeText(txtname.getText()) );
                    
                    showComfirmOrder();
                }
            }
        }  
    }
    
    private String capitalizeText(String tmpTxt)
    {
        tmpTxt = tmpTxt.replaceAll("\\s+"," ");
        String[] split = tmpTxt.split(" ");
        tmpTxt="";
        for (int i = 0; i < split.length; i++) 
        {
            split[i]=Character.toUpperCase(split[i].charAt(0))+split[i].substring(1);
            tmpTxt+=split[i]+" ";
        }

        return tmpTxt;
    }
    
    private boolean isNumber(String name)
    {
        return name.matches("((-|\\+)?[0-9]+(\\.[0-9]+)?)+");
    }
    
    /**
     * creates confirm window
     * @throws IOException 
     */
    private void showComfirmOrder()throws IOException 
    {
        this.lblCost.setText(customerOrder.getTotalCost());
            // Loads an object hierarchy from an XML document
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlConfirmOrderFile));
            VBox newWindow = (VBox)loader.load();
            
            // get instanc eof controller
            FXMLConfirmOrderController controller = loader.getController();
            
            // send parent controller to child controller
            controller.setMainWindow(this);
            
            // create new stage
            Stage stage = new Stage();
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(btnView.getScene().getWindow());
            
            // container for all content in the fxml file
            Scene scene = new Scene(newWindow, 400, 150);
            stage.setScene(scene);
            scene.getStylesheets().add(getClass().getResource(cssFile).toExternalForm());
            
            // send info to child controller to update child fxml
            controller.setMessageProperty(getConfirmDialog());
            stage.show();
    }
            
    
    
     /**
      * inserts new text for confirmation of order in confirm stage
      */
    @FXML
    private String getConfirmDialog(){
         String message =  "You are about to place an order for the following "; 
             message += customerOrder.getItem(1) + " " + customerOrder.getItem(0);
             message += " pizza with " + customerOrder.getItem(2) + " topping ";
             message += "(" + customerOrder.getTotalCost() + ")";
             
        return message;
    }

}
