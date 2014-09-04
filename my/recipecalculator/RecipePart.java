package my.recipecalculator;

// Developed By: Edwin Kim
// RecipePart.java: Holds the recipe part's matching ingredient, amount, and price.

public class RecipePart 
{
    private String name;
    private double amount;
    private double price;
    private Ingredient ingredientMatch;
    
    public RecipePart(Ingredient ingredientMatch, double amount)
    {
        this.ingredientMatch = ingredientMatch;
        this.amount = amount;
        name = ingredientMatch.getName();
        
        double ingredientCost = ingredientMatch.getCost();
        price = ingredientCost * amount;
    }
    
    public String getName()
    {
        return name;
    }
    
    public double getAmount()
    {
        return amount;
    }
    
    public double getPrice()
    {
        return price;
    }
    
    public Ingredient getIngredientMatch()
    {
        return ingredientMatch;
    }
}
