package com.joseph.Nexus.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class TweetTest {

    @Mock
    private TwitterFactory twitterFactory;

    @Mock
    private Twitter twitter;

    @InjectMocks
    private Tweet tweet;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        when(twitterFactory.getInstance()).thenReturn(twitter);
    }

    @Test
    void shareApp_ValidTweet_ReturnsSuccessMessage() throws TwitterException {
        // Arrange
        String tweetMessage = "Testing Nexus CRM";

        // Act
        String result = tweet.shareApp(tweetMessage);

        // Assert
        assertEquals("Successfully updated the status in Twitter.", result);
        verify(twitterFactory, times(1)).getInstance();
        verify(twitter, times(1)).setOAuthConsumer(Tweet.KEY, Tweet.KEY_SECRET);
        verify(twitter, times(1)).setOAuthAccessToken(new AccessToken(Tweet.TOKEN, Tweet.TOKEN_SECRET));
        verify(twitter, times(1)).updateStatus(tweetMessage);
    }

    @Test
    void shareApp_TwitterException_PrintsStackTrace() throws TwitterException {
        // Arrange
        String tweetMessage = "Testing Nexus CRM";
        doThrow(new TwitterException("Error updating status")).when(twitter).updateStatus(tweetMessage);

        // Act
        String result = tweet.shareApp(tweetMessage);

        // Assert
        assertEquals("Successfully updated the status in Twitter.", result);
        verify(twitterFactory, times(1)).getInstance();
        verify(twitter, times(1)).setOAuthConsumer(Tweet.KEY, Tweet.KEY_SECRET);
        verify(twitter, times(1)).setOAuthAccessToken(new AccessToken(Tweet.TOKEN, Tweet.TOKEN_SECRET));
        verify(twitter, times(1)).updateStatus(tweetMessage);
    }
}
