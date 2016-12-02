package edu.sabanciuniv.deeplearning.controller;

import java.io.PrintWriter;
import java.util.List;

import edu.sabanciuniv.deeplearning.model.Tweet;
import edu.sabanciuniv.deeplearning.repo.TweetRepository;

public class TextApp {

	private static TweetRepository tweetRepo = new TweetRepository();

	public static void main(String[] args) {
		
		long processTime = 0;
		long tweetCount = tweetRepo.getTweetCount();
		try {

			PrintWriter writer = new PrintWriter("tweets.txt", "UTF-8");
			System.out.println("Tweet extraction is started...");
			
			long limit = tweetCount / Tweet.BATCH_SIZE;
			Long lastId = (long) 0;
			long startTimeTotal = System.currentTimeMillis();
			long startTime = System.currentTimeMillis();
			for (int i = 0; i <= limit; i++) {
				//System.out.println(lastId);
				List<Object[]> tweetBatch = tweetRepo.getTweetsBatch(lastId);
				//List<Object[]> tweetBatch = tweetRepo.getTweetsBatch(Tweet.BATCH_SIZE * i);
				//List<Object[]> tweetBatch = tweetRepo.getAllTweetTexts();
				for (Object [] t : tweetBatch) {
					lastId = Long.valueOf(t[0].toString());
					String text = t[1].toString();
					text = prepareTweet(text);
					writer.println(text.trim());
				}
				writer.flush();
				if(i % 16 == 0){
					System.out.println((i+1)*Tweet.BATCH_SIZE + " is done!");
					long currentTime = System.currentTimeMillis();
					//long estimatedTime = currentTime - startTime;
					startTime = currentTime;
					//System.out.println(estimatedTime);
				}
			}
			writer.close();
			processTime = System.currentTimeMillis() - startTimeTotal;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		System.out.println("Tweet extraction is done...");
		tweetRepo.closeConnection();
		System.out.println("It takes " + processTime / 1000 + " seconds!");
	}

	private static String prepareTweet(String text) {
		text = text.replaceAll("\\r\\n|\\r|\\n|\\t", " "); // remove all
															// newlines
		text = text.replaceAll("@\\S+", ""); // remove usernames starting with @
		text = text.replaceAll("https?://\\S+\\s?", " "); // remove urls
		text = text.replaceAll("[\\[\\]\\{\\}\\/,\"-.!|?:;‘’“”…`)(]", " "); // remove
																			// punc
		text = normalizeTurkishLetters(text).toLowerCase();
		text = text.replaceAll("[^\\x00-\\x7F]", " "); // remove unicode chars
		text = removeWordsShorterThan3(text);
		return text;
	}

	private static String normalizeTurkishLetters(String s) {

		s = s.replaceAll("ı", "i");

		s = s.replaceAll("ö", "o");

		s = s.replaceAll("ü", "u");

		s = s.replaceAll("ş", "s");

		s = s.replaceAll("ğ", "g");

		s = s.replaceAll("ç", "c");

		s = s.replaceAll("İ", "i");

		s = s.replaceAll("Ö", "o");

		s = s.replaceAll("Ü", "u");

		s = s.replaceAll("Ş", "s");

		s = s.replaceAll("Ğ", "g");

		s = s.replaceAll("Ç", "c");

		return s;

	}

	private static String removeWordsShorterThan3(String passage) {

		passage = passage.replaceAll("\\b[\\w']{1,2}\\b", "");
		passage = passage.replaceAll("\\s{2,}", " ");
		return passage;
	}

}
