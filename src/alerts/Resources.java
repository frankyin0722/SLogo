package alerts;

import java.util.ResourceBundle;

public class Resources {
	public static final ResourceBundle RESOURCEKEYS = ResourceBundle.getBundle("alerts/resourceKeys");
	public static String getString(String key) {
	        return RESOURCEKEYS.getString(key);
	}
}
