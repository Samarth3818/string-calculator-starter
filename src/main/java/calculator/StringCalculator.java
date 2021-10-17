package calculator;

class StringCalculator {

private static int count =0;
	
    public int add(String text) {
    	count++;
    	int sum = 0;
    	 if (!text.isEmpty()) {
             if (text.startsWith("//[")) {
                 String[] temp = text.split("\n", 2);
                 String delimiter = getDelimiters(temp[0]);
                 String numList[]  = splitNumbers(temp[1], delimiter);
                 sum = sum(numList);
             } else if (text.startsWith("//")) {
                 String[] temp = text.split("\n", 2);
                 String del = temp[0].substring(2);
                 String numList[]  = splitNumbers(temp[1], escapeChars(del));
                 sum = sum(numList);
             } else {
            	 String numList[]  = splitNumbers(text, "[,\n]");
            	 sum = sum(numList);
            	 
             }
         }
  			return sum;
    		
    	  }
    public static int GetCalledCount() {
    	return count;
    }
    
    private String getDelimiters(String s) {
        String[] delimiters = s.substring(3, s.length() - 1).split("]\\[");

        StringBuilder delimiter = new StringBuilder();
        for (String del : delimiters) {
            delimiter.append(escapeChars(del)).append("|");
        }
        delimiter.deleteCharAt(delimiter.length() - 1);
        return delimiter.toString();
    }
    private  String[] splitNumbers(String numbers, String divider){
	    return numbers.split(divider);
	}
    private  int sum(String[] numbers){
		 int total = 0;
	 	    String negString = "";
	        for(String number : numbers){
	        	if(toInt(number) < 0){
	        		if(negString.equals(""))
	        			negString = number;
	        		else
	        			negString += ("," + number);
	        	}
	       if(toInt(number) < 1000)
	    	total += toInt(number);
			}
			if(!negString.equals("")){
				throw new IllegalArgumentException("Negatives not allowed: " + negString);
			}
			return total;
	}
    
    private  int toInt(String number){
		  return Integer.parseInt(number);
		}

}