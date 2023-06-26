package com.joseph.Nexus.utils;

import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

/**
 * Twitter4J/ wrapper for Twitter API V2
 * Tweet object POSTs tweet to share NEXUS CRM
 */

public class Tweet {

    static final String KEY = System.getenv("API_KEY");
    static final String KEY_SECRET = System.getenv("API_KEY_SECRET");
    static final String TOKEN = System.getenv("TOKEN");
    static final String TOKEN_SECRET = System.getenv("TOKEN_SECRET");


    public String shareApp(String tw){

        System.out.println();
        System.out.println("*************** TWEET in Progress *********************");

        try {
            twitter4j.Twitter twitter = new TwitterFactory().getInstance();

             twitter.setOAuthConsumer(KEY, KEY_SECRET);

            AccessToken accessToken = new AccessToken(TOKEN, TOKEN_SECRET);

            twitter.setOAuthAccessToken(accessToken);

            twitter.updateStatus(tw);
            System.out.println();
            System.out.println("Successfully updated status in Twitter.");
        } catch (TwitterException te) {
            te.printStackTrace();
        }
        return "Successfully updated the status in Twitter.";
    }
}