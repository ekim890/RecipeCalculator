RecipeCalculator
================
Developed By: Edwin Kim<br>
<br>
Description of each class:<br>
1. Ingredient.java: Holds an ingredient's name, cost, type, and if it's organic or not.<br>
2. RecipePart.java: Holds the recipe part's matching ingredient, amount, and price.<br>
3. ReciCalcModel.java: Handles all the list and calculations after information is passed from the Controller.<br>
4. ReciCalcView.java: Creates the look and feel of the GUI.<br>
5. ReciCalcController.java: Each button's functionality in the GUI is implemented here instead of the view. To do this, the controller fetches information from the GUI and passes it to the model for calculations.<br>
6. RecipeCalculator.java: Used to execute the MVC Calculator.<br>
7. ReciCalcTester.java: Tests the functionality of the application without the GUI. Prints out the calculations in the console.<br>
<br>
How the application works:<br>
1. The user inputs ingredients to the list.<br>
2. The user inputs the name and amount of each ingredient needed for a recipe. Afterwards, a cost breakdown is given for that recipe.<br>
<br>
Notes:<br>
1. The ingredient name in Step 2 has to be identical to the name provided in Step 1. So if you added a 'Strip of Bacon' in Step 1, you must use 'Strip of Bacon' in Step 2.<br>
2. Fractions do NOT work when inputting amounts in Step 2. Furthermore, use 0.333 and 0.666 for '1/3 and 2/3' amounts.
