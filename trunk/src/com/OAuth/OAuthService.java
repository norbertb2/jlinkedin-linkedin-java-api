package com.OAuth;

import java.net.HttpURLConnection;


import oauth.signpost.*;
import oauth.signpost.basic.*;



/**
 * This class implements OAuth service to access to LinkedIn API<br>
 * It's useful to get and to handle OAuth requestToken, accessToken, authorizeToken<br>
 * Most of the complexity of OAuth authorization process is hidden to the client.<br>
 * The steps to have a working OAuth are the follows:<br>
 * 1. Instantiate an OAuthService passing your consumerKey and your consumerSecret 
 * 	  (and you callback url too, if you are developing a web-application);<br>
 * 2. Get the authorization url calling getAuthorizationURL();<br>
 * 3. Once you have a oauth_verifier call getAccessToken(String oauth_verifier);<br>
 * 4. OAuthService is ready! You can use it for sign you request using signRequest();<br>
 * 
 * To use this class you need Signpost a Java library for OAuth<br>
 * You can find it at <a href="http://code.google.com/p/oauth-signpost/">http://code.google.com/p/oauth-signpost/</><br>
 * 
 * @author Luca Vandro
 *
 */
public class OAuthService {
	/**
	 * Following constants are URL for linkedin OAuth service.
	 * You can find more information about them on http://developer.linkedin.com/docs/DOC-1008 
	 */
	private final String requestTokenURL="https://api.linkedin.com/uas/oauth/requestToken";
	private final String accessTokenURL="https://api.linkedin.com/uas/oauth/accessToken";
	private final String authorizeURL="https://api.linkedin.com/uas/oauth/authorize";
	
	/** 
	 * Parameters passed by user
	 */
	private String consumerKey;
	private String consumerSecret;
	private String callbackURL;
	
	/**
	 * Variables used to have the job done
	 */
	private OAuthProvider oauthProvider;
	private OAuthConsumer oauthConsumer;
	private String oauthToken;
	private boolean hasAccessToken=false;
	
	/**
	 * Constructor recommended for desktop applications
	 */
	public OAuthService(String consumerKey, String consumerSecret)
	{
		this.setConsumerKey(consumerKey);
		this.setConsumerSecret(consumerSecret);
		this.callbackURL=OAuth.OUT_OF_BAND;
		oauthProvider= new DefaultOAuthProvider(requestTokenURL, accessTokenURL, authorizeURL);
		oauthConsumer= new DefaultOAuthConsumer(consumerKey, consumerSecret); 
	}
	
	/**
	 * Constructor recommended for web applications
	 */
	public OAuthService(String consumerKey, String consumerSecret, String callbackURL)
	{
		this.setConsumerKey(consumerKey);
		this.setConsumerSecret(consumerSecret);
		this.callbackURL=callbackURL;
		oauthProvider= new DefaultOAuthProvider(requestTokenURL, accessTokenURL, authorizeURL);
		oauthConsumer= new DefaultOAuthConsumer(consumerKey, consumerSecret); 
	}
	
	/**
	 * @return a string content the url where the user have to be redirected to
	 */
	public String getAuthorizationURL() 
	{
		String authorizationURL=null;
		
		try 
		{
			authorizationURL = oauthProvider.retrieveRequestToken(oauthConsumer, callbackURL);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		} 
		/*
		 * The following instructions get oauthToken code from the authorization url
		 */
		int oauthTokenPosition=authorizationURL.lastIndexOf("oauth_token=")+"oauth_token=".length();
		setOauthToken(authorizationURL.substring(oauthTokenPosition));
		
		return authorizationURL;
	}
	
	/**
	 * 
	 * @param verificationCode : OAuth verifier code.<br>
	 * If the user is redirected to your server via the OAuth callback URL, 
	 * you'll get it back into field oauth_verifier. <br>
	 * If you're working on a desktop application it will hand-enter by the user.
	 */
	public void getAccessToken(String verficationCode)
	{
		try 
		{
			oauthProvider.retrieveAccessToken(oauthConsumer, verficationCode);
			hasAccessToken=true;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * This method is use to make signed connections
	 * with linkedin API
	 * @param request: the request to verify
	 */
	public void signRequest(HttpURLConnection request)
	{
		try 
		{
			oauthConsumer.sign(request);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	/**
	 * @return true if and only if you've got an access token
	 */
	public boolean isReady()
	{
		return hasAccessToken;
	}

	/**
	 * @param consumerKey the consumerKey to set
	 */
	public void setConsumerKey(String consumerKey) {
		this.consumerKey = consumerKey;
	}

	/**
	 * @return the consumerKey
	 */
	public String getConsumerKey() {
		return consumerKey;
	}

	/**
	 * @param consumerSecret the consumerSecret to set
	 */
	public void setConsumerSecret(String consumerSecret) {
		this.consumerSecret = consumerSecret;
	}

	/**
	 * @return the consumerSecret
	 */
	public String getConsumerSecret() {
		return consumerSecret;
	}

	/**
	 * @param oauthToken the oauthToken to set
	 */
	public void setOauthToken(String oauthToken) {
		this.oauthToken = oauthToken;
	}

	/**
	 * @return the oauthToken
	 */
	public String getOauthToken() {
		return oauthToken;
	}
}
