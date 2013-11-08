/**
 * 
 */
package de.saxsys.javafx.workshop.yatwicfx.viewmodel.detailsview;

import java.util.ArrayList;

import javafx.beans.binding.ListBinding;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ReadOnlyListProperty;
import javafx.beans.property.SimpleListProperty;
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
	private ItemList<Tweet> tweets;
//	private ListProperty<HashTag> hashTags = new SimpleListProperty<HashTag>(
//			FXCollections.observableList(new ArrayList<HashTag>()));

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

//		ListBinding<HashTag> lb = new ListBinding<HashTag>() {
//			{
//				super.bind(tweets.itemListProperty());
//			}
//
//			@Override
//			protected ObservableList<HashTag> computeValue() {
//
//				ObservableList<HashTag> result = FXCollections
//						.observableArrayList(new ArrayList<HashTag>());
//
//				ListProperty<Tweet> rawTweets = tweets.itemListProperty();
//
//				for (Tweet rawTweet : rawTweets) {
//					result.addAll(rawTweet.getHashTags());
//				}
//
//				return result;
//			}
//		};
//
//		hashTags.bind(lb);
	}

	public ReadOnlyListProperty<String> tweetsProperty() {
		return tweets.stringListProperty();
	}

	 ListProperty<Tweet> rawTweetsProperty() {
	 return tweets.itemListProperty();
	 }

//	/**
//	 * Represents a list of all {@link HashTag}s ever user by a User (contains
//	 * duplicates).
//	 * 
//	 * @return
//	 */
//	public ListProperty<HashTag> hashTagsProperty() {
//		return hashTags;
//	}

}
