/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pizzaapp;

/**
 *
 * @author dggodi
 */

/**
 * ItemTypeNode class stores the item and its cost 
 * also this class can set and return their values
 */
class ItemTypeNode
{
   String item;               // item
   double cost;               // cost of item
   
   /**
    * initialize default data
    */
   private ItemTypeNode()
   {}
   
   /**
    * initialize default data
    */
   ItemTypeNode(String item, double cost)
   {
      
      this.item = item;
      this.cost = cost;
   }
 
   /**
    * set the description of item
    */
   public void setItem(String item)
   {
      this.item = item;
   }
   
   /**
    * set the cost of item
    */
   public void setCost(int item)
   {
      this.cost = cost;
   }
   
   /**
    * retrieves the value of item
    * @returns the description of item
    */
   public String getItem()
   {
      return item;
   }
   
   /**
    * retrieves the value of cost
    * @returns the cost of the item
    */
   public double getCost()
   {
      return cost;
   }
}
