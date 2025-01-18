package businesscomponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Properties;

public class HelperUtils {

	public static Properties prop = new Properties();
	
	public static String readProperty(String uri) {
		System.out.println("Test: "+uri);
		try {
			String[] array = uri.split("->", 2);
			String fileName = array[0];
			String propertyName = array[1];
			InputStream input = new FileInputStream("src/test/resources/data/" + fileName);
			prop.load(input);
			return prop.getProperty(propertyName);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}		
	}
	
	public static String analyzeValue(String arg1){
		String value=arg1.split("-->")[0];
		value = value.replaceAll("&nbsp;"," ");
		 if (arg1.contains("-->")) {
	        	String fileName = (arg1.split("-->")[1].split("/")[0]); 
	        	String key=arg1.split("/")[1];
	        	//System.out.println(fileName + ":" + key + "=" + value); 
	        	HelperUtils.saveData(fileName, key, value); 
	        }
        if (arg1.contains("<--")) {
        	String strPrefix = arg1.split("<--")[0];
        	String fileName = (arg1.split("<--")[1].split("/")[0]);
        	String key=arg1.split("/")[1];
        	value = HelperUtils.readProperty(fileName + "->" + key);
        	value = strPrefix + value;
        }
		return value;
	}
	
	public static void saveData(String fileName,String key,String attributeValue) {
    	try {
    		System.out.println("Executing SavedData");
    		FileOutputStream fileOut = null;
	        FileInputStream fileIn = null;
	        prop.clear();
            File file = new File("src/test/resources/data/"+fileName);
            if(!file.exists()) file.createNewFile();
            fileIn = new FileInputStream(file);
            prop.load(fileIn);
            prop.setProperty(key, attributeValue);
            fileOut = new FileOutputStream(file);
            prop.store(fileOut, "Save Properties");
            fileOut.close();
    	}	
    	catch(Exception e) {
    		System.out.println("Exception Occured"+e);
    	}
    }

}
