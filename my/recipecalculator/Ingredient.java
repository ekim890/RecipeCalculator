package my.recipecalculator;

// Developed By: Edwin Kim
// Ingredient.java: Holds an ingredient's name, cost, type, and if it's organic or not.

public class Ingredient 
{
    private String name;
    private boolean isOrganic;
    private double cost;
    private String type;
    
    public Ingredient(String name, boolean isOrganic, double cost, String type)
    {
        this.name = name;
        this.isOrganic = isOrganic;
        this.cost = cost;
        this.type = type;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public void setIsOrganic(boolean isOrganic)
    {
        this.isOrganic = isOrganic;
    }
    
    public void setCost(double cost)
    {
        this.cost = cost;
    }
    
    public void setType(String type)
    {
        this.type = type;
    }
    
    public String getName()
    {
        return name;
    }
    
    public boolean getIsOrganic()
    {
        return isOrganic;
    }
    
    public double getCost()
    {
        return cost;
    }
    
    public String getType()
    {
        return type;
    }
}