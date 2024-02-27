package Helper;

import javax.swing.JOptionPane;

public class Properties {

	// MADDE 5: INNER CLASSES 
	
	    public static class Property
	{
	    	public static boolean StudentP(String str) {

	    			String msg;
	    			switch (str) {
	    			case "sure":
	    				msg = "Bu Ýþlemi Gerçekleþtirmek Ýstiyor musun?";
	    				break;
	    			default:
	    				msg = str;
	    				break;
	    			}
	    			int res = JOptionPane.showConfirmDialog(null, msg, "Dikkat !", JOptionPane.YES_NO_OPTION);
	    			if (res == 0) {
	    				return true;
	    			} else {
	    				return false;
	    			}
	    		}
	    	
	}
	
}
