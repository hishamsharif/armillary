Files in this folder
--------------------
readme.txt                          This file
recipeChecker.sh					Checks if we can fulfill a customer's orders with the stocks of ingredients based on inventory file i.e. fruitList.txt


Instructions
--------------------
1) Open shell commend line
2) Ensure a file exists with all orders (i.e. fruitList.txt) 
3) Execute recipeChecker.sh, with args as the absolute path of inventory file (i.e. fruitList.txt), and chooses dessert name 
	i.e. ./recipeChecker.sh fruitList.txt "APPLE PIE"
4) Results displayed as :
   ● If there are enough ingredients to create the specified dessert, output ​exactly (​including all punctuation)​ : ​You shall have (recipe name in block letters)!
   ● Else, output: ​You shall not have (recipe name in block letters)
   ● If an unrecognised recipe is provided (eg Pineapple Pie), output: ​We do not have that on the menu