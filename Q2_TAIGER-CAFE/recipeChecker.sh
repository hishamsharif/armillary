#!/bin/bash

# find fruits inventory to fulfill orders

# Check for input file on command line.
ARGS=2
E_BADARGS=65
E_NOFILE=66

if [ $# -ne "$ARGS" ]  # Correct number of arguments passed to script?
then
  echo "Usage: `basename $0` filename recipename"
  exit $E_BADARGS
fi

# read the file name input
filename=$1
if [ ! -f "$filename" ]       # Check if file exists.
then
  echo "File \"$filename\" does not exist."
  exit $E_NOFILE
fi

# read recipe name input
menuItems=("PINEAPPLE PIE" "APPLE PIE" "FRUIT PARFAIT")
recipeName=$2

function getInventory() {
  
  awk '
     BEGIN {    
         # to ignore ignore comma  
        FS="[,]+";
    }

     {
         for (i=1; i<=NF; i++) {
             item = tolower($i)
             #words[word]++
             if ("'$2'"==item) {
                itemCount++
             }
         }
     }

     END {
         printf("%3d", itemCount)
     }
 ' $1
}

function checkIngredientsAvailability(){
    
    filename=$1
    recipeName=$2
    applesRequired=0
    pinepplesRequired=0
    
    case $recipeName in
        "PINEAPPLE PIE")
            pinepplesRequired=3
            ;;
        "APPLE PIE")
            applesRequired=3
            ;;
        "FRUIT PARFAIT")
            applesRequired=2
            pinepplesRequired=2
            ;;
    esac

    applesAvailable=0
    pinepplesAvailable=0
   
    # if recipe has apples
    if [ $applesRequired -gt 0 ]; then
      applesAvailable=$(getInventory $filename apple)
    fi
    
    # if recipe has pineapples
    
    if [ $pinepplesRequired -gt 0 ]; then
      pinepplesAvailable=$(getInventory $filename pinepple)
    fi
    
    if [ $applesRequired -le $applesAvailable ] &&  [ $pinepplesRequired -le $pinepplesAvailable ] ; then
        echo 0
    else
        echo 1
    fi


    #echo $applesAvailable
    #echo 'not'
}

# validate if the menuItems list contains recipe name  
if [[ " ${menuItems[@]} " =~ " ${recipeName} " ]]; then
    # if input recipe available in the menu, check availability
    hasSufficientIngredients=$(checkIngredientsAvailability $filename "$recipeName")
    if [ $hasSufficientIngredients -eq 0 ]; then
        printf "%s %s %s\n" "You shall have" \""$recipeName"\" "!" 
    else
        printf "%s %s\n" "You shall not have " \""$recipeName"\" 
    fi
else
    printf "%s\n" "We do not have that on the menu"
fi





