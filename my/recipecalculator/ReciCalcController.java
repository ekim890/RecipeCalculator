package my.recipecalculator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

// Developed By: Edwin Kim
// ReciCalcController.java: Each button's functionality in the GUI is implemented here
// instead of the view. To do this, the controller fetches information from the GUI and
// passes it to the model for calculations.

public class ReciCalcController
{
	private ReciCalcModel model;
	private ReciCalcView view;
	
	public ReciCalcController(ReciCalcModel rcm, ReciCalcView rcv)
	{
		model = rcm;
		view = rcv;
		
		view.addIngredientListener(new addIngredientListener());
		view.clearIngredientListener(new clearIngredientListener());
		view.deleteIngredientListener(new deleteIngredientListener());
		view.deleteAllListener(new deleteAllListener());
		view.listAllListener(new listAllListener());
		view.calculateListener(new calculateListener());
		view.clearRecipeListener(new clearRecipeListener());
	}
	
	class addIngredientListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			// Fetches information from the view.
			String name = view.getIngredientName();
			boolean isOrganic = view.isOrganic();
			double cost = 0.00;
			String type = "";
			
			boolean validName = true;
			boolean validCost = true;
			boolean typeIsSelected = false;
			
			// Checks if the given name exists in the list of ingredients.
			boolean duplicateNameFound = model.findIngredientByName(name);
			
			// Parses the ingredient cost into a double.
			// Displays a message if nothing is parsed or the parsed text is not a number.
			try
			{
				cost = Double.parseDouble(view.getIngredientCost());
			}
			catch (NumberFormatException ex)
			{
				validCost = false;
				String message = "You need to enter a number in Cost.";
				String title = "Warning";
				int messageType = JOptionPane.WARNING_MESSAGE;
				JOptionPane.showMessageDialog(view, message, title, messageType);
			}
			
			// Checks which radio button is selected to get ingredient type.
			// Displays a message if the user didn't select an ingredient type.
			if (view.isProduce())
			{
				typeIsSelected = true;
				type = "Produce";
			}
			else if (view.isMeat())
			{
				typeIsSelected = true;
				type = "Meat";
			}
			else if (view.isPantry())
			{
				typeIsSelected = true;
				type = "Pantry";
			}
			else
			{
				typeIsSelected = false;
				String message = "You need to select an ingredient type.";
				String title = "Warning";
				int messageType = JOptionPane.WARNING_MESSAGE;
				JOptionPane.showMessageDialog(view, message, title, messageType);
			}
			
			// Displays a message if name is already in the ingredients list.
			if (name.equals(""))
			{
				validName = false;
				String message = "You need to enter a name in Name.";
				String title = "Warning";
				int messageType = JOptionPane.WARNING_MESSAGE;
				JOptionPane.showMessageDialog(view, message, title, messageType);
			}
			// Asks if the user wants to overwrite the old ingredient with the new one.
			// Overwrites the old ingredient if the user selects 'Yes'.
			else if (duplicateNameFound)
			{
				String message = "This ingredient already exists. Do you want to overwrite it?";
				String title = "Overwrite Confirmation";
				int optionType = JOptionPane.YES_NO_OPTION;
				int messageType = JOptionPane.QUESTION_MESSAGE;
				
				int reply = JOptionPane.showConfirmDialog(view, message, title, optionType, messageType);
				
				if (reply == JOptionPane.YES_OPTION)
				{
					model.replaceIngredient(name, isOrganic, cost, type);
				}
			}
			
			if (validName && validCost && typeIsSelected && !duplicateNameFound)
			{
				model.addIngredient(name, isOrganic, cost, type);
				
				String message = "The ingredient has been added.";
				String title = "Information";
				int messageType = JOptionPane.INFORMATION_MESSAGE;
				JOptionPane.showMessageDialog(view, message, title, messageType);
			}
		}
	}
	
	class clearIngredientListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			// Clears all information from the text fields.
			view.clearName();
			view.clearCost();
			view.clearOrganic();
			view.clearType();
		}
	}
	
	class deleteIngredientListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			String name = view.getIngredientName();
			boolean matchFound = model.findIngredientByName(name);
			
			// Confirms if the user wants to delete the ingredient.
			// If yes, then ingredient is deleted from the ingredient list.
			// Otherwise nothing is deleted from list of ingredients.
			if (matchFound)
			{
				String message = "Are you sure you want to delete '" + name + "' from the list of ingredients?";
                String title = "Delete Confirmation";
                int optionType = JOptionPane.YES_NO_OPTION;
                int messageType = JOptionPane.QUESTION_MESSAGE;
                
                int reply = JOptionPane.showConfirmDialog(view, message, title, optionType, messageType);
                
                if (reply == JOptionPane.YES_OPTION)
                {
                	model.removeIngredient(name);
                	
    				String m = "'" + name + "' has been removed from the list.";
    				String t = "Deletion Completed";
    				int mt = JOptionPane.INFORMATION_MESSAGE;
    				
    				JOptionPane.showMessageDialog(view, m, t, mt);
                }
			}
            // If the ingredient provided by the user is not on the list, then
            // a message is displayed to the user stating that the deletion failed.
			else
			{
				String message = "The ingredient was not found in the ingredient list. Please try again.";
				String title = "Deletion Failed";
				int messageType = JOptionPane.WARNING_MESSAGE;
				
				JOptionPane.showMessageDialog(view, message, title, messageType);
			}
		}
	}
	
	class deleteAllListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
	        // Deletes all the ingredients from the list of ingredients if user clicks 'Yes'.
	        String message = "Are you sure you want to delete all ingredients from the list?";
	        String title = "Delete All Confirmation";
            int optionType = JOptionPane.YES_NO_OPTION;
            int messageType = JOptionPane.QUESTION_MESSAGE;
            
            int reply = JOptionPane.showConfirmDialog(view, message, title, optionType, messageType);
	        
	        if (reply == JOptionPane.YES_OPTION)
	        {
	            model.removeAllIngredients();
	            
	            String m = "All ingredients have been deleted from the list.";
				String t = "Delete All Completed";
				int mt = JOptionPane.INFORMATION_MESSAGE;
				
				JOptionPane.showMessageDialog(view, m, t, mt);
	        }
		}
	}
	
	class listAllListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			// Lists all the added ingredients in a window for the user to see.
			String message = model.listAllIngredients();
			String title = "List of Ingredients";
			int messageType = JOptionPane.PLAIN_MESSAGE;
			
			JOptionPane.showMessageDialog(view, message, title, messageType);
		}
	}
	
	class calculateListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
	        // Declare local amount values and booleans
	        double amount001 = 0.00;
	        boolean amount1IsValid = false;
	        boolean name1IsValid = !view.getName1().equals("");
	        
	        double amount002 = 0.00;
	        boolean amount2IsValid = false;
	        boolean name2IsValid = !view.getName2().equals("");
	        
	        double amount003 = 0.00;
	        boolean amount3IsValid = false;
	        boolean name3IsValid = !view.getName3().equals("");
	        
	        double amount004 = 0.00;
	        boolean amount4IsValid = false;
	        boolean name4IsValid = !view.getName4().equals("");
	        
	        double amount005 = 0.00;
	        boolean amount5IsValid = false;
	        boolean name5IsValid = !view.getName5().equals("");
	        
	        double amount006 = 0.00;
	        boolean amount6IsValid = false;
	        boolean name6IsValid = !view.getName6().equals("");
	        
	        double amount007 = 0.00;
	        boolean amount7IsValid = false;
	        boolean name7IsValid = !view.getName7().equals("");
	        
	        double amount008 = 0.00;
	        boolean amount8IsValid = false;
	        boolean name8IsValid = !view.getName8().equals("");
	        
	        double amount009 = 0.00;
	        boolean amount9IsValid = false;
	        boolean name9IsValid = !view.getName9().equals("");
	        
	        double amount010 = 0.00;
	        boolean amount10IsValid = false;
	        boolean name10IsValid = !view.getName10().equals("");
	        
	        boolean amountIsNumber = true;
	        
	        // If the amount text field is not blank, then
	        // the program checks if the text is a valid double.
	        // If so, then local boolean and amount are changed accordingly.
	        // If not, then a message prompts the user that an invalid number was given.
	        try
	        {
	            if (!view.getAmount1().equals(""))
	            {
	                amount001 = Double.parseDouble(view.getAmount1());
	                amount1IsValid = true;
	            }
	            
	            if (!view.getAmount2().equals(""))
	            {
	                amount002 = Double.parseDouble(view.getAmount2());
	                amount2IsValid = true;
	            }
	            
	            if (!view.getAmount3().equals(""))
	            {
	                amount003 = Double.parseDouble(view.getAmount3());
	                amount3IsValid = true;
	            }
	            
	            if (!view.getAmount4().equals(""))
	            {
	                amount004 = Double.parseDouble(view.getAmount4());
	                amount4IsValid = true;
	            }
	            
	            if (!view.getAmount5().equals(""))
	            {
	                amount005 = Double.parseDouble(view.getAmount5());
	                amount5IsValid = true;
	            }
	            
	            if (!view.getAmount6().equals(""))
	            {
	                amount006 = Double.parseDouble(view.getAmount6());
	                amount6IsValid = true;
	            }
	            
	            if (!view.getAmount7().equals(""))
	            {
	                amount007 = Double.parseDouble(view.getAmount7());
	                amount7IsValid = true;
	            }
	            
	            if (!view.getAmount8().equals(""))
	            {
	                amount008 = Double.parseDouble(view.getAmount8());
	                amount8IsValid = true;
	            }
	            
	            if (!view.getAmount9().equals(""))
	            {
	                amount009 = Double.parseDouble(view.getAmount9());
	                amount9IsValid = true;
	            }
	            
	            if (!view.getAmount10().equals(""))
	            {
	                amount010 = Double.parseDouble(view.getAmount10());
	                amount10IsValid = true;
	            }
	        }
	        catch (NumberFormatException ex)
	        {
	            amountIsNumber = false;
	            String message = "Only numbers are allowed in the first column. Please try again.";
	            String title = "Warning";
	            int messageType = JOptionPane.WARNING_MESSAGE;
	            
	            JOptionPane.showMessageDialog(view, message, title, messageType);
	        }
	        
	        // Series of if statements that add the ingredient to the recipe
	        // if and only if the name and amount are valid.
	        if (name1IsValid && amount1IsValid)
	        {
	        	model.addRecipePart(view.getName1(), amount001);
	        }
	        
	        if (name2IsValid && amount2IsValid)
	        {
	        	model.addRecipePart(view.getName2(), amount002);
	        }
	        
	        if (name3IsValid && amount3IsValid)
	        {
	        	model.addRecipePart(view.getName3(), amount003);
	        }
	        
	        if (name4IsValid && amount4IsValid)
	        {
	        	model.addRecipePart(view.getName4(), amount004);
	        }
	        
	        if (name5IsValid && amount5IsValid)
	        {
	        	model.addRecipePart(view.getName5(), amount005);
	        }
	        
	        if (name6IsValid && amount6IsValid)
	        {
	        	model.addRecipePart(view.getName6(), amount006);
	        }
	        
	        if (name7IsValid && amount7IsValid)
	        {
	        	model.addRecipePart(view.getName7(), amount007);
	        }
	        
	        if (name8IsValid && amount8IsValid)
	        {
	        	model.addRecipePart(view.getName8(), amount008);
	        }
	        
	        if (name9IsValid && amount9IsValid)
	        {
	        	model.addRecipePart(view.getName9(), amount009);
	        }
	        
	        if (name10IsValid && amount10IsValid)
	        {
	        	model.addRecipePart(view.getName10(), amount010);
	        }
	        
	        // Calculates the recipe cost if all input is valid
	        if (amountIsNumber)
	        {
	            double totalPrice = model.calculateTotalPrice();
	            double salesTax = model.calculateSalesTax();
	            double wellnessDiscount = model.calculateWellnessDiscount();
	            double totalCost = model.calculateTotalCost(totalPrice, salesTax, wellnessDiscount);
	        
	            String message = "Total Price: " + totalPrice + "\nSales Tax: " + salesTax
	                    + "\nWellness Discount: " + wellnessDiscount + "\nTotal Cost: " + totalCost;  
	            String title = "Recipe Calculation";
	            int messageType = JOptionPane.INFORMATION_MESSAGE;
	        
	            JOptionPane.showMessageDialog(view, message, title, messageType);
	                  
	            // Removes all the recipe parts after calculation
	            model.removeRecipe();
	        }  
	        else
	        {
	            // Removes all the recipe parts when invalid input is given.
	            model.removeRecipe();
	        }
		}
	}
	
	class clearRecipeListener implements ActionListener
	{
		// Clears all information from the text fields.
		public void actionPerformed(ActionEvent e)
		{
			view.clearAmount1();
			view.clearAmount2();
			view.clearAmount3();
			view.clearAmount4();
			view.clearAmount5();
			view.clearAmount6();
			view.clearAmount7();
			view.clearAmount8();
			view.clearAmount9();
			view.clearAmount10();
			
			view.clearName1();
			view.clearName2();
			view.clearName3();
			view.clearName4();
			view.clearName5();
			view.clearName6();
			view.clearName7();
			view.clearName8();
			view.clearName9();
			view.clearName10();
		}
	}
}
