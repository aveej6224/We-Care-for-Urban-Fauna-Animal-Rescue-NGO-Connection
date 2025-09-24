package Util;

public class Utilities {

	
 public static boolean hasValue(String data)
 {
	 if(data==null)
		 return false;
	 if(data.equalsIgnoreCase("undefined"))
		 return false;
	 return true;
 }
}
