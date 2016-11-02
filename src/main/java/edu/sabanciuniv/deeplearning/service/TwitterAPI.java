package edu.sabanciuniv.deeplearning.service;

import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterAPI{

	TwitterStream twitterStream = null;
	
	public TwitterAPI() {
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
		.setOAuthConsumerKey("MLqXW009U88KN80WGAk7zv8sL")
		.setOAuthConsumerSecret("JMObbzTpaWM5qBFFO868ca6y7jWCJkAV7vqns9OO7wH7kK1uaF")
		.setOAuthAccessToken("331240558-IRYnBdoENrTc9b2RaNGAZL1VA4m3x3RWcUrkKgt4")
		.setOAuthAccessTokenSecret("lyEEJ4jIFs7rnTt47jqJc5oGXIMNGLk6lvOVkG4uXGCpw");
		
		this.twitterStream = new TwitterStreamFactory(cb.build()).getInstance();
	}
	
	public TwitterStream getStream(){
		return twitterStream;
	}
}
