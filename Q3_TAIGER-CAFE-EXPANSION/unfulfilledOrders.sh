#!/bin/bash

# Inputs 
#   arg1 - absolute file path of the order list (eg orderLists.txt)
#   arg2 - the customer's order (eg "STRAWBERRY SHORTCAKE")
#   arg3 - the order date in YYYY-MM-DD format (eg 2019-01-18)

# Outputs the 3 (or less) most recent unfulfilled orders 
#     filter by specified date and customer's order, 
#     sorted by timestamp from oldest to newest.


# Check for input file on command line.
ARGS=3
E_BADARGS=65
E_NOFILE=66

if [ $# -ne "$ARGS" ]  # Correct number of arguments passed to script?
then
  echo "Usage: `basename $0` orderlist-file-path order-item order-date "
  exit $E_BADARGS
fi

# read the file name input
filename=$1
if [ ! -f "$filename" ]       # Check if file exists.
then
  echo "File \"$filename\" does not exist."
  exit $E_NOFILE
fi

# read order item name input
orderItemName=$2

# read order date input
orderDate=$3

lastNOrders=3

function recentUnfulfilledOrders() {
  orderName="$2"
  orderDate=$3
  orderStatus="FALSE"

#   awk '(($1~/'$orderDate'/)&&($5~/'$orderStatus'/)){print $0}' $1 |awk '
#     BEGIN {    
#          # to ignore ignore comma  
#         FS="[\"]";
#     }
#     {    
#       if(($2~/'"$orderName"'/)||($2=='"$orderName"')){
#         print $0
#       }
      
#     }
#  ' | sort -k1 -k2|tail -$4

 grep -i "$orderName" $1 | grep "$orderDate" | grep -i "$orderStatus" | sort -k1 -k2|tail -$4
  
}

recentUnfulfilledOrders $filename "$orderItemName" $orderDate $lastNOrders
