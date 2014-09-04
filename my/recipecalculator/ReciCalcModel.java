package my.recipecalculator;

import java.util.ArrayList;
import java.util.regex.Pattern;

// Developed By: Edwin Kim
// ReciCalcModel.java: Handles all the list and calculations after information is passed from the Controller.

public class ReciCalcModel 
{
    private ArrayList<Ingredient> ingredients;
    private ArrayList<RecipePart> recipeParts;
    
	public ReciCalcModel()
	{
        ingredients = new ArrayList<Ingredient>();
        recipeParts = new ArrayList<RecipePart>();
	}
	
	// Creates a new ingredient with the provided parameters and adds it to the list.
    public void addIngredient(String name, boolean isOrganic, double cost, String type)
    {
    	Ingredient i = new Ingredient(name, isOrganic, cost, type);
        ingredients.add(i);
    }
    
    // Removes the ingredient from the list with the same name as the parameter.
    public void removeIngredient(String name)
    {
    	for (int i = 0; i < ingredients.size(); i++)
    	{
    		if (name.equalsIgnoreCase(ingredients.get(i).getName()))
    		{
    			ingredients.remove(i);
    			break;
    		}
    	}
    }

    // Removes all the ingredients from the list.
    public void removeAllIngredients()
    {
    	ingredients.removeAll(ingredients);
    }
    
    // Returns a boolean if there is an ingredient with the same name as the parameter
    public boolean findIngredientByName(String name)
    {
    	boolean matchFound = false;
    	
    	for (Ingredient i : ingredients)
    	{
    		if(name.equalsIgnoreCase(i.getName()))
    		{
    			matchFound = true;
    			return matchFound;
    		}
    	}
    	
    	return matchFound;
    }
    
    // Returns a large concatenated string with all the ingredients' information
    public String listAllIngredients()
    {
    	String message = "";
    	
        for (Ingredient i : ingredients)
        {
        	message += i.getName() + "    -    " + i.getCost() + "    -    " + i.getType();
            
            if(i.getIsOrganic())
            {
                message += "    -    Organic \n\n";
            }
            else
            {
                message += "    -    Not Organic \n\n";
            }
        }
        
        return message;
    }
    
    // Replaces the old ingredient with a new one if the old and new one have the same name
    public void replaceIngredient(String name, boolean isOrganic, double cost, String type)
    {
    	for (Ingredient i : ingredients)
    	{
    		if(name.equalsIgnoreCase(i.getName()))
    		{
    			i.setName(name);
    			i.setIsOrganic(isOrganic);
    			i.setCost(cost);
    			i.setType(type);
    			break;
    		}
    	}
    }
    
    // Adds a recipe part to the list if the parameter matches a name in the ingredient list.
    public void addRecipePart(String name, double amount)
    {
    	for (Ingredient i : ingredients)
    	{
    		if (name.equalsIgnoreCase(i.getName()))
    		{
    			RecipePart rp = new RecipePart(i, amount);
    			recipeParts.add(rp);
    			break;
    		}
    	}
    }
    
    // Removes all the recipe parts from the list.
    public void removeRecipe()
    {
    	recipeParts.removeAll(recipeParts);
    }
    
    // Calculates the price of all the ingredients
    public double calculateTotalPrice()
    {
        double totalPrice = 0.00;
        
        for (RecipePart rp : recipeParts)
        {
            double sum = totalPrice + rp.getPrice();
            totalPrice = Math.round(sum * 100.00) / 100.00;
        }
        
        return totalPrice;
    }
    
    // Calculates the total sales tax of all ingredients that are not Produce.
    public double calculateSalesTax()
    {
        double priceSum = 0.00;
        double salesTax = 0.00;
        double roundedSalesTax = 0.00;
        final double tax = 0.086;
        
        for (RecipePart rp : recipeParts)
        {
            // Calculates the price for everything except Produce
            if (!rp.getIngredientMatch().getType().equals("Produce"))
            {
                priceSum = priceSum + rp.getPrice();
            }
        }
        
        // Sales Tax is 8.6% of all ingredients excluding produce.
        salesTax = priceSum * tax;
        // Rounds the sales tax to two decimal places.
        roundedSalesTax = Math.ceil(salesTax * 100.00) / 100.00;
        
        // Splits the sales tax into two strings using "." as a delimiter.
        String salesTaxString = String.valueOf(roundedSalesTax);
        String[] parts = salesTaxString.split(Pattern.quote("."));
        
        // Cents should only be a value between 0-99
        String centsString = parts[1];
        int cents = Integer.parseInt(centsString);
        
        // Handles cases where cents is only one digit.
        // Example: .1, .2, .3, .4, .5, .6, .7, .8, .9
        // .1 is actually 10 cents, but cents is parsed as .1
        // so these cases needs to be addressed accordingly.
        switch (cents)
        {
            case 1: cents = 10; break;
            case 2: cents = 20; break;
            case 3: cents = 30; break;
            case 4: cents = 40; break;
            case 5: cents = 50; break;
            case 6: cents = 60; break;
            case 7: cents = 70; break;
            case 8: cents = 80; break;
            case 9: cents = 90; break;
        }
        
        // Rounds up the sales tax to the nearest 7 cents.
        int modulo7 =  cents % 7;
        
        if (modulo7 > 0)
        {
            int subtraction = 7 - modulo7;
            double roundUp = subtraction / 100.00;
            roundedSalesTax = roundedSalesTax + roundUp;
        }
                
        return roundedSalesTax;
    }
    
    public double calculateWellnessDiscount()
    {
        double wellnessDiscount = 0.00;
        double roundedDiscount = 0.00;
        
        // Searches the entire recipe for organic ingredients and adds
        // 5% of its cost to the total wellness discount
        for (RecipePart rp : recipeParts)
        {
            if (rp.getIngredientMatch().getIsOrganic())
            {
                double price = rp.getPrice() * 0.05;
                wellnessDiscount = wellnessDiscount + price;
            }
        }
        
        // Rounds up the wellness discount to the nearest cent.
        roundedDiscount = Math.ceil(wellnessDiscount * 100.00) / 100.00;
        
        return roundedDiscount;
    }
    
    public double calculateTotalCost(double totalPrice, double salesTax, double wellnessDiscount)
    {
        // Calculates the total cost of the recipe factoring in price, tax, and discounts.
        double tc = totalPrice + salesTax - wellnessDiscount;
        double totalCost = Math.round(tc * 100.00) / 100.00;
        return totalCost;
    }
}
