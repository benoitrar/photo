package hu.qben.balinthirling.shared;

/**
 * @author Benedek
 *
 * This class contains necessary native support for jsons.
 */
public class JsUtils {

	protected JsUtils() {
		
	}
	
	/**
	 * @param e. g. [{"I":["1maidan_village","2bodybuilders","3alone","4war_in_a_peaceful_country"],"V":["veteran_wrestler"]}]
	 * @return directory names from the json representation
	 */
	public static final native JsFolders asArrayOfDirectoryNames(String json) /*-{
		return eval(json);
	}-*/;
	
}
