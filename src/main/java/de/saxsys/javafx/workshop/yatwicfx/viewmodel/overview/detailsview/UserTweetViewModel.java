/**
 * 
 */
package de.saxsys.javafx.workshop.yatwicfx.viewmodel.overview.detailsview;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.ListProperty;
import javafx.beans.property.ReadOnlyListProperty;

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
	private ItemList<Tweet> tweets;

	@Inject
	public UserTweetViewModel(Repository repository) {
		this.repository = repository;
	}

	public void initialize(String userId) {

		final User user = repository.getUserById(userId);

		tweets = new ItemList<Tweet>(user.tweetsProperty(),
				new ModelToStringMapper<Tweet>() {
					@Override
					public String toString(Tweet tweet) {
						return tweet.getText();
					}
				});
	}

	public ReadOnlyListProperty<String> tweetsProperty() {
		return tweets.stringListProperty();
	}

	ListProperty<Tweet> rawTweetsProperty() {
		return tweets.itemListProperty();
	}

	public void filterTweets(String fHashTag) {
		List<Tweet> tweets2Remove = new ArrayList<Tweet>();
		for (Tweet tweet : tweets.itemListProperty()) {
			// should be removed if there was at least one hashTag with the same
			// name found
			boolean rFlag = false;
			for (HashTag hashTag : tweet.getHashTags()) {
				rFlag = rFlag || !(hashTag.getText().compareTo(fHashTag) == 0);

			}
			if (rFlag)
				tweets2Remove.add(tweet);
		}
		
		for (Tweet tweet : tweets2Remove) {
			tweets.itemListProperty().remove(tweet);
		}
	}

}
