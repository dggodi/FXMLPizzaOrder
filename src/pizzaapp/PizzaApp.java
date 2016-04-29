/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizzaapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author dggodi
 */
public class PizzaApp extends Application 
{
    private static CustomerOrder customerOrder = new CustomerOrder(); 
    
    private final String fxmlOrderFile = "/fxml/document/FXMLOrder.fxml";
    private final String cssFile = "/resources/pizzaApp.css";
    private final String title = "Order Pizza";
    
    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource(fxmlOrderFile));
        FXMLLoader fxmlLoader = new FXMLLoader();
      
        // create stage container
        Scene scene = new Scene(root, 480, 220);
        scene.getStylesheets().add(getClass().getResource(cssFile).toExternalForm());
        stage.centerOnScreen();
        stage.setTitle(title); 
        stage.setScene(scene);
        stage.show();
        
        customerOrder = new CustomerOrder();
    }
    
    public static CustomerOrder getCustomerOrder()
    {
        return customerOrder;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        launch(args);
    }
    
}
