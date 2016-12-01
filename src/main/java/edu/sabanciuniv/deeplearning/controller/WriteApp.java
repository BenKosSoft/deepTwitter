package edu.sabanciuniv.deeplearning.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import edu.sabanciuniv.deeplearning.model.Tweet;
import edu.sabanciuniv.deeplearning.repo.TweetRepository;

//this app writes text file to database...
public class WriteApp {

	public static void main(String args[]) throws FileNotFoundException{
		
		TweetRepository twRepo = new TweetRepository();
		twRepo.startScheduler();
		
		BufferedReader br = new BufferedReader(new FileReader("tweets_26M_AllTurkish.txt"));
		try {
		    Integer count = 0;
		    
		    for(String line = br.readLine(); line != null; line = br.readLine()){
		    	count++;
		    	if(count % 100000 == 0) System.out.println(count);
		    	Tweet tweet = new Tweet();
		    	tweet.setCreatedat(null);
		    	tweet.setFollowers(0);
		    	tweet.setId(count.longValue());
		    	tweet.setUsername("mertkosan");
		    	tweet.setLang("tr");
		    	tweet.setText(line);
		    	twRepo.addTweet(tweet);
		    }
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}finally {
		    try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
	}
}
