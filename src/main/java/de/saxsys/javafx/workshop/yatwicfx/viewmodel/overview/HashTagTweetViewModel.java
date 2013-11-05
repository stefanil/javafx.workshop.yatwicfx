package de.saxsys.javafx.workshop.yatwicfx.viewmodel.overview;

import java.util.List;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;

import com.google.inject.Inject;

import de.saxsys.javafx.workshop.yatwicfx.model.Repository;
import de.saxsys.javafx.workshop.yatwicfx.model.Tweet;
import de.saxsys.jfx.mvvm.base.viewmodel.ViewModel;

public class HashTagTweetViewModel implements ViewModel {

	ListProperty<Tweet4View> tweets = new SimpleListProperty<Tweet4View>(
			FXCollections.<Tweet4View> observableArrayList());

	private Repository repo;

	@Inject
	public HashTagTweetViewModel(final Repository repo) {
		
		this.repo = repo;
		
		// FIXME: dirty change listening
		// Binding US 04 (Update View)
		listChangeListener = new ListChangeListener<Tweet>() {
			@Override
			public void onChanged(
					javafx.collections.ListChangeListener.Change<? extends Tweet> change) {
				
				while(change.next()) {
					if(change.wasAdded())
						reloadTweets();
				}
			}			
		};
		
		tweetsProperty = repo.tweetsProperty();
		tweetsProperty.addListener(listChangeListener);
	}

	public ListProperty<Tweet4View> tweetProperty() {
		return tweets;
	}
	
	private void reloadTweets() {
		if(cachedHashTag != null)
			reloadTweets(cachedHashTag);
	}
	
	private String cachedHashTag = null;

	public void reloadTweets(String hashTag) {

		// cache it for US 04
		cachedHashTag = hashTag;
		
		tweets.get().clear();

		// this sets all the tweets
		List<Tweet> allTweets = repo.getTweets();
		for (int i = 0; i < allTweets.size(); i++) {
			Tweet tweet = allTweets.get(i);
			String tweetText = tweet.getText();
			if (tweetText.indexOf(hashTag) != -1)
				tweets.get().add(
						new Tweet4View(tweet.getUser().getName(), tweet
								.getText()));
		}

	}
	
	private static ListChangeListener<Tweet> listChangeListener;
	private static ListProperty<Tweet> tweetsProperty;
	
	public static void removeChangeListener() {
		tweetsProperty.removeListener(listChangeListener);
	}

}
