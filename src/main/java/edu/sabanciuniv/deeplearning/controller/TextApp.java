package edu.sabanciuniv.deeplearning.controller;

import java.io.PrintWriter;
import java.util.List;

import edu.sabanciuniv.deeplearning.model.Tweet;
import edu.sabanciuniv.deeplearning.repo.TweetRepository;

public class TextApp {

	private static TweetRepository tweetRepo = new TweetRepository();

	public static void main(String[] args) {
		//long startTime = System.nanoTime();
		long tweetCount = tweetRepo.getTweetCount();

		try {

			PrintWriter writer = new PrintWriter("tweets.txt", "UTF-8");
			System.out.println("Tweet extraction is started...");
			
			System.out.printf("Progress % 0 ");
			long limit = tweetCount / Tweet.BATCH_SIZE;
			for (int i = 0; i <= limit; i++) {
				List<Object[]> tweetBatch = tweetRepo.getTweetsBatch(Tweet.BATCH_SIZE * i);
				//List<Object[]> tweetBatch = tweetRepo.getAllTweetTexts();
				for (Object t : tweetBatch) {
					String text = t.toString();
					text = prepareTweet(text);
					writer.println(text);
				}
				writer.flush();
				System.out.printf("\r\r%3.3f", i/limit);
			}
			writer.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		System.out.println("Tweet extraction is done...");
		tweetRepo.closeConnection();
		//long estimatedTime = System.nanoTime()- startTime;
		//System.out.println(estimatedTime);
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
