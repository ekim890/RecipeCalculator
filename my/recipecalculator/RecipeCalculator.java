package my.recipecalculator;

// Developed By: Edwin Kim
// RecipeCalculator.java: Used to execute the MVC Calculator.

public class RecipeCalculator 
{
    public static void main(String[] args)
    {
    	ReciCalcModel model = new ReciCalcModel();
    	
    	ReciCalcView view = new ReciCalcView();
        
        @SuppressWarnings("unused")
		ReciCalcController controller = new ReciCalcController(model, view);
        
        view.setVisible(true);
    }
}
