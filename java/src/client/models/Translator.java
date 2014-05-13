/**
 * 
 */
package client.models;
import com.google.gson.Gson;

/**
 * This class receives either JSON or Java objects and converts it to the other format
 * @author scottdaly
 *
 */
public class Translator {
	
	private Gson gson = new Gson();

	public Translator() {}
	
	public String convertToJSON(Object obj){
		String json = gson.toJson(obj);
		return json;
	}
	
	public Object convertToObject(String json){
		return json;
//		Object obj = gson.fromJson(json, CientModel.class);
//		return obj;
	}

}
