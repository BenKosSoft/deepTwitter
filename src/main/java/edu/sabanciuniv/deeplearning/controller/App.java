package edu.sabanciuniv.deeplearning.controller;

import edu.sabanciuniv.deeplearning.model.Tweet;
import edu.sabanciuniv.deeplearning.repo.TweetRepository;
import edu.sabanciuniv.deeplearning.service.TwitterAPI;
import twitter4j.FilterQuery;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterStream;

/**
 * This app reads tweets from twitter and writes into database.
 */
public class App 
{
	private static TweetRepository tweetRepo = new TweetRepository();
	
    public static void main( String[] args )
    {
    	TwitterAPI twitterAPI = new TwitterAPI();
    	TwitterStream stream = twitterAPI.getStream();
    	tweetRepo.startScheduler();
    	
    	StatusListener listener = new StatusListener() {
    		public void onStatus(Status status) {
            	String lang = status.getLang();
            	String text = status.getText().replaceAll("\\r\\n|\\r|\\n|\\t", " "); //replace end line and tabs to space
            	String username = status.getUser().getScreenName();
            	Long date = status.getCreatedAt().getTime();
            	Long id = status.getId();
            	Integer followers = status.getUser().getFollowersCount();
            	//System.out.println(lang + " " + text);
            	if(lang != null && lang.equals("tr")){ //check lang is tr for security
            		Tweet tweet = new Tweet();
            		tweet.setId(id);
            		tweet.setText(text);
            		tweet.setUsername(username);
            		tweet.setCreatedat(date);
            		tweet.setFollowers(followers);
            		tweet.setLang(lang);
            		tweetRepo.addTweet(tweet);
            	}
            }

            public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
                //System.out.println("Got a status deletion notice id:" + statusDeletionNotice.getStatusId());
            }

            public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
                //System.out.println("Got track limitation notice:" + numberOfLimitedStatuses);
            }

            public void onScrubGeo(long userId, long upToStatusId) {
               // System.out.println("Got scrub_geo event userId:" + userId + " upToStatusId:" + upToStatusId);
            }

            public void onStallWarning(StallWarning warning) {
                //System.out.println("Got stall warning:" + warning);
            }

            public void onException(Exception ex) {
                //ex.printStackTrace();
            }
        };
        stream.addListener(listener);
        FilterQuery filtre = new FilterQuery();
        String[] keywordsArray = {"a","e","o","u","i"};
        filtre.track(keywordsArray);
        stream.filter(filtre);
        filtre.language(new String[]{"tr"});
    }
}
