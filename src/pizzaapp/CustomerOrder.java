/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizzaapp;

import java.util.ArrayList;

/**
 * CustomerOrder class stores the customers name and 
 * calculates the cost.  This class calls also stores 
 * and retrieves the items selected in the menu class 
 * within a ArrayList. 
 */
public class CustomerOrder 
{
   final private double TAX = .06;          // tax
   private String name;                     // customer name
   private int count;                       // index counter for slected items in ArrayList
   
   // holds the selections and cost of each pizza
   private ArrayList <Menu> orderArray = new ArrayList<>(); 
   
   /**
    * initialize default data
    */
   public CustomerOrder()
   {
      this("Pepperoni", "Large", "");
   }
   
   /**
    * initialize and set data
    */
   public CustomerOrder(String itemType, String itemSize, String extraItem)
   {
      orderArray.add(new Menu(itemType, itemSize, extraItem));
   }
   
   /**
    * resets the current index location for the ArrayList
    */
   public void resetIndex()
   {
      count = 0;
   }
   
   /**
    * sets the customers name
    */
   public void setName(String name)
   {
      this.name = name;
   }
   
   /**
    * sets the item
    * @param item - the type of item
    */
   public void setItemType(String item)
   {
       Menu tmpMenu = orderArray.get(count);
       tmpMenu.setItemType(item);
   }
   
   /**
    * sets the item's size
    * @param item - item's size
    */
   public void setItemSize(String itemSize)
   {
       Menu tmpMenu = orderArray.get(count);
       tmpMenu.setItemSize(itemSize);
   }
   
   /**
    * sets the item
    * @param item - the type of item
    */
   public void setExtraItem(String item)
   {
       Menu tmpMenu = orderArray.get(count);
       tmpMenu.setExtraItem(item);
   }

   /**
    * add 1 pizza order to ArrayList
    */
   public void addToOrder()
   {
      orderArray.add(new Menu(getItem(0), getItem(1), getItem(2)));
      count = orderArray.size();
   }
        
   /**
    * retrieves the customers name
    * @returns String - customers name
    */
   public String getName()
   {
      return name;
   }
   
   /**
    * Calculates the cost of a pizza stored
    * @param index - the index value to pull from the ArrayList
    * @returns String - cost of pizza
    */
   public String getCost(int index)
   {
      double total = 0.00;
      
      Menu tmpMenu = orderArray.get(index);
      total += tmpMenu.getItemCost();  
      total += tmpMenu.getExtraItemCost();
      
      return String.format("$%.2f", total);
   }
   
   /**
    * Calculates the cost of only one pizza
    * @returns String - cost of pizza
    */
   public String getCost()
   {
      double total = 0.00;
      
      Menu tmpMenu = orderArray.get(orderArray.size()-1);
      total += tmpMenu.getItemCost();  
      total += tmpMenu.getExtraItemCost();
      
      return String.format("$%.2f", total);
   }

   /**
    * Calculates the total cost of all pizza
    * @returns String - total cost
    */
   public String getTotalCost()
   {
      double total = 0;
      
      // loop for adding cost of each pizza
      for (int i = 0; i < orderArray.size(); i++)
      {
         Menu tmpMenu = orderArray.get(i);
         total += tmpMenu.getItemCost();  
         total += tmpMenu.getExtraItemCost();
      }
      
      return String.format("$%.2f", total * (1 + TAX));
   }
   
   /**
    * retrieves pizza from ArrayList
    * @returns Menu - the pizza in ArrayList 
    */
   public Menu getOrder(int count)
   {
      return orderArray.get(count-1);
   }
   
   /**
    * retrieves the total pizza ordered
    * @returns Menu - customers name
    */
   public int getSize()
   {
      return orderArray.size();
   }
   
   /**
    * retrieves the current index location
    * @returns integer - the current index location
    */
   public int nextIndex()
   {
      return count++;
   }
   
   /**
    * retrieves the item
    * @param index - reference to type of item 
    * @returns String - description of item
    */
   public String getItem(int index)
   {
      String tmpItem = "";
      Menu tmpMenu = orderArray.get(count);

      switch(index)
      {
         case 0: tmpItem = tmpMenu.getItemType();
                 break;
                 
         case 1: tmpItem = tmpMenu.getItemSize();
                 break;
         
         case 2: tmpItem = tmpMenu.getExtraItem();
                 break;
      }
      
      return tmpItem;
   }
}
