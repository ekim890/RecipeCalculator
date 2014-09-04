package my.recipecalculator;

// Developed By: Edwin Kim
// ReciCalcTester.java: Tests the functionality of the application without the GUI.
// Prints out the calculations in the console.

public class ReciCalcTester 
{
    public static void main(String[] args)
    {
    	// Add ingredients and recipe parts to test the functionality without the GUI.
    	ReciCalcModel model = new ReciCalcModel();
    	
    	// The ingredients added here are identical to the ingredients provided by the PDF.
    	String ingredientName1 = "clove of Organic Garlic";
    	boolean isOrganic1 = true;
    	double ingredientCost1 = 0.67;
    	String ingredientType1 = "Produce";
    	
    	String ingredientName2 = "Lemon";
    	boolean isOrganic2 = false;
    	double ingredientCost2 = 2.03;
    	String ingredientType2 = "Produce";
    	
    	String ingredientName3 = "Cup of Corn";
    	boolean isOrganic3 = false;
    	double ingredientCost3 = 0.87;
    	String ingredientType3 = "Produce";
    	
    	String ingredientName4 = "Chicken Breast";
    	boolean isOrganic4 = false;
    	double ingredientCost4 = 2.19;
    	String ingredientType4 = "Meat";
    	
    	String ingredientName5 = "Slice of Bacon";
    	boolean isOrganic5 = false;
    	double ingredientCost5 = 0.24;
    	String ingredientType5 = "Meat";
    	
    	String ingredientName6 = "ounce of Pasta";
    	boolean isOrganic6 = false;
    	double ingredientCost6 = 0.31;
    	String ingredientType6 = "Pantry";
    	
    	String ingredientName7 = "cup of Organic Olive Oil";
    	boolean isOrganic7 = true;
    	double ingredientCost7 = 1.92;
    	String ingredientType7 = "Pantry";
    	
    	String ingredientName8 = "cup of Vinegar";
    	boolean isOrganic8 = false;
    	double ingredientCost8 = 1.26;
    	String ingredientType8 = "Pantry";
    	
    	String ingredientName9 = "teaspoon of Salt";
    	boolean isOrganic9 = false;
    	double ingredientCost9 = 0.16;
    	String ingredientType9 = "Pantry";
    	
    	String ingredientName10 = "teaspoon of Pepper";
    	boolean isOrganic10 = false;
    	double ingredientCost10 = 0.17;
    	String ingredientType10 = "Pantry";
    	
    	model.addIngredient(ingredientName1, isOrganic1, ingredientCost1, ingredientType1);
    	model.addIngredient(ingredientName2, isOrganic2, ingredientCost2, ingredientType2);
    	model.addIngredient(ingredientName3, isOrganic3, ingredientCost3, ingredientType3);
    	model.addIngredient(ingredientName4, isOrganic4, ingredientCost4, ingredientType4);
    	model.addIngredient(ingredientName5, isOrganic5, ingredientCost5, ingredientType5);
    	model.addIngredient(ingredientName6, isOrganic6, ingredientCost6, ingredientType6);
    	model.addIngredient(ingredientName7, isOrganic7, ingredientCost7, ingredientType7);
    	model.addIngredient(ingredientName8, isOrganic8, ingredientCost8, ingredientType8);
    	model.addIngredient(ingredientName9, isOrganic9, ingredientCost9, ingredientType9);
    	model.addIngredient(ingredientName10, isOrganic10, ingredientCost10, ingredientType10);
    	
    	// Example: 'Salad Dressing'
//    	model.addRecipePart(ingredientName1, 1);
//    	model.addRecipePart(ingredientName2, 1);
//    	model.addRecipePart(ingredientName7, 0.75);
//    	model.addRecipePart(ingredientName9, 0.75);
//    	model.addRecipePart(ingredientName10, 0.5);
    	
    	// Example: 'Healthy Chicken Breast'
//    	model.addRecipePart(ingredientName1, 1);
//    	model.addRecipePart(ingredientName4, 4);
//    	model.addRecipePart(ingredientName7, 0.5);
//    	model.addRecipePart(ingredientName8, 0.5);
    	
    	// Example: 'Pasta Fiesta'
    	model.addRecipePart(ingredientName1, 1);
    	model.addRecipePart(ingredientName3, 4);
    	model.addRecipePart(ingredientName5, 4);
    	model.addRecipePart(ingredientName6, 8);
    	model.addRecipePart(ingredientName7, 0.333);
    	model.addRecipePart(ingredientName9, 1.25);
    	model.addRecipePart(ingredientName10, 0.75);
    	
    	// Print out the calculations to check if they are correct
        double totalPrice = model.calculateTotalPrice();
        double salesTax = model.calculateSalesTax();
        double wellnessDiscount = model.calculateWellnessDiscount();
        double totalCost = model.calculateTotalCost(totalPrice, salesTax, wellnessDiscount);
        
        System.out.println("Total Price: " + totalPrice);
        System.out.println("Sales Tax: " + salesTax);
        System.out.println("Wellness Discount: " + wellnessDiscount);
        System.out.println("Total Cost: " + totalCost);
    }
}
