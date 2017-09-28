package edu.sabanciuniv.deeplearning.service;

import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.ConfigurationBuilder;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TwitterAPI {

    TwitterStream twitterStream = null;

    public TwitterAPI() {
        Properties prop = new Properties();

        try {
            InputStream input = new FileInputStream("oauth.properties");
            // load a properties file
            prop.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        /*
         * TODO: Create oauth.properties file including below keys.
         */
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey(prop.getProperty("ConsumerKey"))
                .setOAuthConsumerSecret(prop.getProperty("ConsumerSecret"))
                .setOAuthAccessToken(prop.getProperty("AccessToken"))
                .setOAuthAccessTokenSecret(prop.getProperty("AccessTokenSecret"));

        this.twitterStream = new TwitterStreamFactory(cb.build()).getInstance();
    }

    public TwitterStream getStream() {
        return twitterStream;
    }
}
