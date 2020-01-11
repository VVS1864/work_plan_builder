package work_plan_builder.calc;

public class Column_name {
	// Java program to find Excel  
	// column name from a given  
	// column number 
	  
	
	public static String calc_name(int columnNumber){ 
	        // To store result (Excel column name) 
	        StringBuilder columnName = new StringBuilder(); 
	  
	        while (columnNumber > 0) 
	        { 
	            // Find remainder 
	            int rem = columnNumber % 26; 
	  
	            // If remainder is 0, then a  
	            // 'Z' must be there in output 
	            if (rem == 0) 
	            { 
	                columnName.append("Z"); 
	                columnNumber = (columnNumber / 26) - 1; 
	            } 
	            else // If remainder is non-zero 
	            { 
	                columnName.append((char)((rem - 1) + 'A')); 
	                columnNumber = columnNumber / 26; 
	            } 
	        } 
	  
	        // Reverse the string and print result 
	        return columnName.reverse().toString(); 
	    } 
}
