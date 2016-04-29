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
public class Menu 
{
    // array to store two differtn items and their cost
   ItemTypeNode[] items = new ItemTypeNode[2];
   
   // item cost arrays
   double[] smallCost = {5.00, 7.00, 6.00, 7.00, 8.00};
   double[] mediumCost = {7.00, 9.00, 8.00, 9.00, 10.00};
   double[] largeCost = {9.00, 11.00, 10.00, 11.00, 12.00};
   
   // array that contains the cost by size
   double[][] costArray = {smallCost, mediumCost, largeCost};
   
   String[] pizzaType = {"Pepperoni", "Hawaiian", "Veggie", "Meat"};
   String[] sizeType = {"Small", "Medium"};
   
   final double SMALL = .5;         // cost of small size
   final double MED = 1;            // cost of medium size
   final double LARGE = 1.5;        // cost of large size
   
   private String size = "";        // size of item
   
    /**
    * initialize default data
    */
   Menu()
   {
      this("Pepperoni", "Large", "");
   }
   
   /**
    * initialize and set data form parameters
    * @param itemType - the type of item
    * @param itemSize - the size of the item
    * @param extraItem - extra item added
    */
   Menu(String itemType, String itemSize, String extraItem)
   {
      // cost of flavor
      double itemCost = getCostOfItem(itemType, itemSize); 
      items[0] = new ItemTypeNode(itemType, itemCost);
      
      // cost of large
      items[1] = new ItemTypeNode(extraItem, 0.00);
      
      this.size = itemSize;
   }
   
   /**
    * Determine description of the item and return the cost
    * @param item - the type of item
    * @param itemSize - the size of the item
    * @return double - value of item
    */
   private double getCostOfItem(String item, String itemSize)
   {
      if (item.equals(pizzaType[0]))
         return getSizeCost(0, itemSize);
      else if (item.equals(pizzaType[1]))
         return getSizeCost(1, itemSize);
      else if (item.equals(pizzaType[2]))
         return getSizeCost(2, itemSize);
      else if (item.equals(pizzaType[3]))
         return getSizeCost(3, itemSize);
      else 
         return getSizeCost(4, itemSize);   
   }
   
   /**
    * Determine the cost of the item and return the cost
    * @param index - location in array
    * @param itemSize - the size of the item
    * @return double - value of item
    */
   private double getSizeCost(int index, String itemSize)
   {  
      if (itemSize.equals(sizeType[0]))
         return costArray[0][index];
      else if (itemSize.equals(sizeType[1]))
         return costArray[1][index];
      else
         return costArray[2][index]; 
   }
   
   /**
    * Determine the cost of the exta item and return the cost
    * @param itemSize - the size of the item
    * @return double - value of item
    */
   private double getExtraItemCost(String itemSize)
   {  
      if (itemSize.equals(sizeType[0]))
         return SMALL;
      else if (itemSize.equals(sizeType[1]))
         return MED;
      else
         return LARGE;
   }
     
    /**
    * Set the new item and its cost
    * @param item - the type of item
    */
    public void setItemType(String item)
   {
      double itemCost = getCostOfItem(item, size);
      items[0] = new ItemTypeNode(item, itemCost);
   }
   
   /**
    * Set the new size of item
    * @param itemType - the type of item
    * @param itemSize - the size of the item
    */
   public void setItemSize(String item)
   {
       this.size = item;
       
       double itemCost = getCostOfItem(items[0].getItem(), size);
       items[0] = new ItemTypeNode(items[0].getItem(), itemCost);
       
       double extraItemCost = getExtraItemCost(size);
       items[1] = new ItemTypeNode(items[1].getItem(), extraItemCost);
   }
   
    /**
    * Set the new extra item and its cost
    * @param itemType - the type of item
    */
   public void setExtraItem(String item)
   {
      double itemCost = getExtraItemCost(size);
      items[1] = new ItemTypeNode(item, itemCost);
   }
   
   /**
    * retrieves the value of item
    * @returns the description of item
    */
   public String getItemType()
   {
      return items[0].getItem();
   }
   
   /**
    * retrieves the value of the extra item
    * @returns the description of extra item
    */
   public String getExtraItem()
   {
      return items[1].getItem();
   }
   
   /**
    * retrieves the the size of the item
    * @returns String - the size of the item
    */
   public String getItemSize()
   {
      return this.size;
   }
   
   /**
    * retrieves the cost of the item
    * @returns double - the cost of the item
    */
   public double getItemCost()
   {
      return items[0].getCost();
   }
   
   /**
    * retrieves the cost of the extra item
    * @returns double - the cost of the extra item
    */
   public double getExtraItemCost()
   {
      return items[1].getCost();
   }
}
