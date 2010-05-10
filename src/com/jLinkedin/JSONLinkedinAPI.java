package com.jLinkedin;

import org.jdom.Document;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

import com.OAuth.OAuthService;
/**
 * This class aims to wrap XMLLinkedinAPI in order to return results in JSON.
 * 
 * @author Luca Adalberto Vandro
 *
 */
public class JSONLinkedinAPI 
{
	XMLLinkedinAPI xmlLinkedinAPI;
	/**
	 * @see org.jLinkedin.XMLLinkedinAPI
	 */
	public JSONLinkedinAPI(OAuthService service)throws InvalidOAuthServiceException 
	{
		xmlLinkedinAPI= new XMLLinkedinAPI(service);
	}
	/**
	 * Wrap org.jLinkedinAPI.getCurrentUserStandardProfile()
	 * @see org.jLinkedinAPI
	 * @return JSONObject
	 */
	public JSONObject getCurrentUserStandardProfile()
	{
		return parseXMLResults(xmlLinkedinAPI.getCurrentUserStandardProfile());
	}
	/**
	 * Wrap org.jLinkedinAPI.getCurrentUserPublicProfile()
	 * @see org.jLinkedinAPI
	 * @return JSONObject
	 */
	public JSONObject getCurrentUserPublicProfile()
	{
		return parseXMLResults(xmlLinkedinAPI.getCurrentUserPublicProfile());
	}
	/**
	 * Wrap org.jLinkedinAPI.getUserStandardProfileById(String id)
	 * @see org.jLinkedinAPI
	 * @return JSONObject
	 */
	public JSONObject getUserStandardProfileById(String id)
	{
		return parseXMLResults(xmlLinkedinAPI.getUserStandardProfileById(id));
	}
	/**
	 * Wrap org.jLinkedinAPI.getUserPublicProfileById(String id)
	 * @see org.jLinkedinAPI
	 * @return JSONObject
	 */
	public JSONObject getUserPublicProfileById(String id)
	{
		return parseXMLResults(xmlLinkedinAPI.getUserPublicProfileById(id));
	}
	/**
	 * Wrap org.jLinkedinAPI.getUserStandardProfileByProfileURL(String profileURL)
	 * @see org.jLinkedinAPI
	 * @return JSONObject
	 */
	public JSONObject getUserStandardProfileByProfileURL(String profileURL)
	{
		return parseXMLResults(xmlLinkedinAPI.getUserStandardProfileByProfileURL(profileURL));
	}
	/**
	 * Wrap org.jLinkedinAPI.getUserPublicProfileByProfileURL(String profileURL)
	 * @see org.jLinkedinAPI
	 * @return JSONObject
	 */
	public JSONObject getUserPublicProfileByProfileURL(String profileURL)
	{
		return parseXMLResults(xmlLinkedinAPI.getUserPublicProfileByProfileURL(profileURL));
	}
	
	/**
	 * Wrap org.jLinkedinAPI.getCurrentUserConnections()
	 * @see org.jLinkedinAPI
	 * @return JSONObject
	 */
	public JSONObject getCurrentUserConnections()
	{
		return parseXMLResults(xmlLinkedinAPI.getCurrentUserConnections());
	}
	
	/**
	 * Wrap org.jLinkedinAPI.getUserConnectionsById(String id)
	 * @see org.jLinkedinAPI
	 * @return JSONObject
	 */
	public JSONObject getUserConnectionsById(String id)
	{
		return parseXMLResults(xmlLinkedinAPI.getUserConnectionsById(id));
	}

	/**
	 * Wrap org.jLinkedinAPI.getUserConnectionsByProfileURL(String profileURL)
	 * @see org.jLinkedinAPI
	 * @return JSONObject
	 */
	public JSONObject getUserConnectionsByProfileURL(String profileURL)
	{
		return parseXMLResults(xmlLinkedinAPI.getUserConnectionsByProfileURL(profileURL));
	}
	
	public static String printJSONObject(JSONObject jsonObject)
	{
		String result=null;
		try 
		{
			result=jsonObject.toString(7);
		} 
		catch (JSONException e) 
		{

			e.printStackTrace();
		}
		return result;
	}
	
	private JSONObject parseXMLResults(Document document)
	{
		String xmlDocument=XMLLinkedinAPI.printXMLDocument(document);
		JSONObject jsonObject=null;
		try 
		{
			jsonObject = XML.toJSONObject(xmlDocument);
		} 
		catch (JSONException e) 
		{
			e.printStackTrace();
		}
		
		return jsonObject;
		
	}
	
	
}
