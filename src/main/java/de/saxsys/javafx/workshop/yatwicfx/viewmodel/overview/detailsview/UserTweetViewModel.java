/**
 * 
 */
package de.saxsys.javafx.workshop.yatwicfx.viewmodel.overview.detailsview;

import java.util.ArrayList;

import javafx.beans.property.ListProperty;
import javafx.beans.property.ReadOnlyListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import com.google.inject.Inject;

import de.saxsys.javafx.workshop.yatwicfx.model.HashTag;
import de.saxsys.javafx.workshop.yatwicfx.model.Repository;
import de.saxsys.javafx.workshop.yatwicfx.model.Tweet;
import de.saxsys.javafx.workshop.yatwicfx.model.User;
import de.saxsys.jfx.mvvm.base.viewmodel.ViewModel;
import de.saxsys.jfx.mvvm.base.viewmodel.util.itemlist.ItemList;
import de.saxsys.jfx.mvvm.base.viewmodel.util.itemlist.ModelToStringMapper;

/**
 * @author stefan.illgen
 * 
 */
public class UserTweetViewModel implements ViewModel {

	private Repository repository;
	private ItemList<Tweet> allTweets;
	private ItemList<Tweet> tweets;

	@Inject
	public UserTweetViewModel(Repository repository) {
		this.repository = repository;
	}

	public void initialize(String userId) {

		final User user = repository.getUserById(userId);

		ModelToStringMapper<Tweet> m2sm = new ModelToStringMapper<Tweet>() {
			@Override
			public String toString(Tweet tweet) {
				return tweet.getText();
			}
		};

		allTweets = new ItemList<Tweet>(user.tweetsProperty(), m2sm);
		tweets = new ItemList<Tweet>(user.tweetsProperty(), m2sm);
	}

	public ReadOnlyListProperty<String> allTweetsProperty() {
		return allTweets.stringListProperty();
	}

	public ReadOnlyListProperty<String> tweetsProperty() {
		return tweets.stringListProperty();
	}

	ListProperty<Tweet> rawAllTweetsProperty() {
		return allTweets.itemListProperty();
	}

	public void filterTweets(String fHashTag) {
		ObservableList<Tweet> tweets2Set = FXCollections
				.observableList(new ArrayList<Tweet>());
		for (Tweet tweet : allTweets.itemListProperty()) {
			for (HashTag hashTag : tweet.getHashTags()) {
				if (hashTag.getText().compareTo(fHashTag) == 0) {
					tweets2Set.add(tweet);
					break;
				}
			}
		}
		tweets.itemListProperty().set(tweets2Set);
	}

}
