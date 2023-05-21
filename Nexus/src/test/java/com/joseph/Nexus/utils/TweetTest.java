package com.joseph.Nexus.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
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

    /**
     * Method under test: {@link Tweet#shareApp(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testShareApp() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: consumer key is null
        //       at twitter4j.TwitterBaseImpl.setOAuthConsumer(TwitterBaseImpl.java:265)
        //       at com.joseph.Nexus.utils.Tweet.shareApp(Tweet.java:28)
        //   In order to prevent shareApp(String)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   shareApp(String).
        //   See https://diff.blue/R013 to resolve this issue.

        (new Tweet()).shareApp("Tw");
    }

    /**
     * Method under test: {@link Tweet#shareApp(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testShareApp2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: consumer key is null
        //       at twitter4j.TwitterBaseImpl.setOAuthConsumer(TwitterBaseImpl.java:265)
        //       at com.joseph.Nexus.utils.Tweet.shareApp(Tweet.java:28)
        //   In order to prevent shareApp(String)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   shareApp(String).
        //   See https://diff.blue/R013 to resolve this issue.

        (new Tweet()).shareApp("foo");
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
