package com.jLinkedin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.jdom.Document;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

import com.OAuth.OAuthService;
/**
 * This class aims to create a simple interface to do requests to the Linkedin public API.
 * All results are XML documents wrapped in a Document object. For further informations about
 * Document object please visit <a href="http://www.jdom.org/docs/apidocs/">http://www.jdom.org/docs/apidocs/</a><br>
 * 
 * Exemple of use:
 * {@code 
 * 			String consumerKey="sod0ru0eu3eohu030e3294e32";
 * 			String consumerSecret="e98803u4324u32043248u0983"; 
 * 			OAuthService service=new OAuthService(consumerKey,consumerSecret);
 * 			....
 * 			// some code which will provied to get the access token
 * 			//e.g. String accessToken= you.setAccessToken();
 *			...
 *			service.setAccessToken(verificationCode);
 *			XMLLinkedinAPI api= new XMLLinkedinAPI(service);
 *			System.out.print(XMLLinkedinAPI.printXMLDocument(api.getCurrentUserFullProfile()));
 *	}
 * 
 * @author Luca Adalberto Vandro
 *
 */
public class XMLLinkedinAPI {
	/*
	 * Final url for making request
	 */
	protected final String currentUserURL="http://api.linkedin.com/v1/people/~";
	protected final String userProfileById="http://api.linkedin.com/v1/people/id=";
	protected final String userProfileByProfileURL="http://api.linkedin.com/v1/people/url=";
	protected final String publicModifier=":public";
	protected final String fullModifier=":(id,first-name,last-name,headline,picture-url,date-of-birth,public-profile-url,location,industry,distance,relation-to-viewer,current-status,current-status-timestamp,summary,specialties,proposal-comments,associations,honors,interests,positions,educations,phone-numbers,im-accounts,twitter-accounts,main-address,member-url-resources,num-connections,num-recommenders)";//)";
	protected final String connectionsModifier="/connections";
	/*
	 * Object properties
	 */
	protected URL requestURL;
	protected OAuthService oauthService;
	
	//CONSTRUCTOR
	
	/**
	 * 
	 * @param service: requiered a OAuthService with a valid access token
	 * @throws InvalidOAuthServiceException 
	 */
	public XMLLinkedinAPI(OAuthService service) throws InvalidOAuthServiceException
	{
		oauthService=service;
		
		if(!service.isReady())
		{
			throw new InvalidOAuthServiceException();
		}
	}
	
	//GET-PUBLIC-PROFILE METHODS
	
	/**
	 * 
	 * @return a Document object containing the XML document with the user PUBLIC profile
	 * More info about Document object on http://www.jdom.org/docs/apidocs/
	 */
	public Document getCurrentUserPublicProfile()
	{
		String xmlTextualResponse=makeRequest(currentUserURL+publicModifier);
		Document xmlDocument= XMLBuilder(xmlTextualResponse);
		
		return xmlDocument;
	}
	
	/**
	 * 
	 * @require id: require a valid id. Please check id you are looking for is an existing id
	 * @return a Document object containing the XML document with the user PUBLIC profile
	 * More info about Document object on http://www.jdom.org/docs/apidocs/
	 */
	public Document getUserPublicProfileById(String id)
	{
		String userProfileByIdURL=userProfileById+id+publicModifier;
		
		String xmlTextualResponse=makeRequest(userProfileByIdURL);
		Document xmlDocument= XMLBuilder(xmlTextualResponse);
		
		return xmlDocument;
	}
	
	/**
	 * 
	 * @require a valid url. Please check url you are looking for is an existing url
	 * @return a Document object containing the XML document with the user PUBLIC profile
	 * More info about Document object at http://www.jdom.org/docs/apidocs/
	 */
	public Document getUserPublicProfileByProfileURL(String profileURL)
	{
		String userProfileByProfileURLURL=userProfileById+profileURL+publicModifier;
		
		String xmlTextualResponse=makeRequest(userProfileByProfileURLURL);
		Document xmlDocument= XMLBuilder(xmlTextualResponse);
		
		return xmlDocument;
	}
	
	//GET-FULL-PROFILE METHODS
	
	/**
	 * 
	 * @return a Document object containing the XML document with the user FULL profile
	 * More info about Document object on http://www.jdom.org/docs/apidocs/
	 */
	public Document getCurrentUserFullProfile()
	{
		String xmlTextualResponse=makeRequest(currentUserURL+fullModifier);
		Document xmlDocument= XMLBuilder(xmlTextualResponse);
		
		return xmlDocument;
	}
	
	/**
	 * 
	 * @require id: require a valid id. Please check id you are looking for is an existing id
	 * @return a Document object containing the XML document with the user FULL profile
	 * More info about Document object on http://www.jdom.org/docs/apidocs/
	 */
	public Document getUserFullProfileById(String id)
	{
		String userProfileByIdURL=userProfileById+id+fullModifier;
		
		String xmlTextualResponse=makeRequest(userProfileByIdURL);
		Document xmlDocument= XMLBuilder(xmlTextualResponse);
		
		return xmlDocument;
	}
	
	/**
	 * 
	 * @require a valid url. Please check url you are looking for is an existing url
	 * @return a Document object containing the XML document with the user FULL profile
	 * More info about Document object at http://www.jdom.org/docs/apidocs/
	 */
	public Document getUserFullProfileByProfileURL(String profileURL)
	{
		String userProfileByProfileURLURL=userProfileById+profileURL+fullModifier;
		
		String xmlTextualResponse=makeRequest(userProfileByProfileURLURL);
		Document xmlDocument= XMLBuilder(xmlTextualResponse);
		
		return xmlDocument;
	}
	
	//GET-STANDARD-PROFILE METHODS
	/**
	 * 
	 * @return a Document object containing the XML document with the user STANDARD profile
	 * More info about Document object on http://www.jdom.org/docs/apidocs/
	 */
	public Document getCurrentUserStandardProfile()
	{
		String xmlTextualResponse=makeRequest(currentUserURL);
		Document xmlDocument= XMLBuilder(xmlTextualResponse);
		
		return xmlDocument;
	}
	
	/**
	 * 
	 * @require id: require a valid id. Please check id you are looking for is an existing id
	 * @return a Document object containing the XML document with the user STANDARD profile
	 * More info about Document object on http://www.jdom.org/docs/apidocs/
	 */
	public Document getUserStandardProfileById(String id)
	{
		String userProfileByIdURL=userProfileById+id;
		
		String xmlTextualResponse=makeRequest(userProfileByIdURL);
		Document xmlDocument= XMLBuilder(xmlTextualResponse);
		
		return xmlDocument;
	}
	
	/**
	 * 
	 * @require a valid url. Please check url you are looking for is an existing url
	 * @return a Document object containing the XML document with the user STANDARD profile
	 * More info about Document object at http://www.jdom.org/docs/apidocs/
	 */
	public Document getUserStandardProfileByProfileURL(String profileURL)
	{
		String userProfileByProfileURLURL=userProfileById+profileURL;
		
		String xmlTextualResponse=makeRequest(userProfileByProfileURLURL);
		Document xmlDocument= XMLBuilder(xmlTextualResponse);
		
		return xmlDocument;
	}
	
	//USER CONNECTIONS METHODS
	
	/**
	 * @require id: require a valid id. Please check id you are looking for is an existing id
	 * @return a Document object containing the XML document with the user connections
	 * More info about Document object at http://www.jdom.org/docs/apidocs/ 
	 */
	public Document getCurrentUserConnections()
	{
		String xmlTextualResponse=makeRequest(currentUserURL+connectionsModifier);
		Document xmlDocument= XMLBuilder(xmlTextualResponse);
		
		return xmlDocument;
	}
	
	/**
	 * 
	 * @return a Document object containing the XML document with the user connections
	 * More info about Document object at http://www.jdom.org/docs/apidocs/ 
	 */
	public Document getUserConnectionsById(String id)
	{
		String userProfileByIdURL=userProfileById+id+connectionsModifier;
		
		String xmlTextualResponse=makeRequest(userProfileByIdURL);
		Document xmlDocument= XMLBuilder(xmlTextualResponse);
		
		return xmlDocument;
	}
	
	/**
	 * @require a valid url. Please check url you are looking for is an existing url
	 * @return a Document object containing the XML document with the user connections
	 * More info about Document object at http://www.jdom.org/docs/apidocs/ 
	 */
	public Document getUserConnectionsByProfileURL(String profileURL)
	{
		String userProfileByProfileURLURL=userProfileById+profileURL+connectionsModifier;
		
		String xmlTextualResponse=makeRequest(userProfileByProfileURLURL);
		Document xmlDocument= XMLBuilder(xmlTextualResponse);
		
		return xmlDocument;
	}
	
	//STATIC METHODS
	
	/**
	 * @return a string with your XML Document
	 */
	public static String printXMLDocument(Document xmlDocument)
	{
		XMLOutputter xmlPrinter= new XMLOutputter();
		return xmlPrinter.outputString(xmlDocument);
	}
	
	//PROTECTED METHODS
	
	protected String makeRequest(String requestURL)
	{	
		
		String response=null;
		
		try 
		{
			this.requestURL= new URL(requestURL);
			HttpURLConnection request= (HttpURLConnection)this.requestURL.openConnection();
			oauthService.signRequest(request);
			request.connect();
			response=getResponseBody(request);
			request.disconnect();
			
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		return response;
	}
	
	protected String getResponseBody(HttpURLConnection request)
	{
		InputStreamReader inputStreamReader;
		BufferedReader reader;
		String responseBody= new String();
		
		try 
		{
			inputStreamReader = new InputStreamReader(request.getInputStream(),"UTF-8");
			reader=new BufferedReader(inputStreamReader);
			String line;
			
			while((line=reader.readLine())!=null)
			{
				responseBody+=line+"\n";
			}
		} 
		catch (IOException e)
		{
		
			e.printStackTrace();
		}
		
		return responseBody;
		
	}
	
	protected Document XMLBuilder (String response)
	{
		Document resultDocument=null;
		StringReader responseReader= new StringReader(response);
		
		SAXBuilder xmlBuilder= new SAXBuilder();
		
		try 
		{
			resultDocument=xmlBuilder.build(responseReader);
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return resultDocument;
	}
	
	
}
