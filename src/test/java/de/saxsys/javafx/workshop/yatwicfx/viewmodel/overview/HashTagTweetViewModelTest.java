package de.saxsys.javafx.workshop.yatwicfx.viewmodel.overview;

import static org.hamcrest.core.Is.is;
//import static org.hamcrest.core.IsNot.not;
import static org.loadui.testfx.Assertions.verifyThat;

import java.util.ArrayList;

import javafx.beans.binding.ListBinding;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.ObservableList;

import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.saxsys.javafx.workshop.yatwicfx.model.Tweet;
import de.saxsys.javafx.workshop.yatwicfx.model.User;
import de.saxsys.javafx.workshop.yatwicfx.viewmodel.TweetVM;
import de.saxsys.javafx.workshop.yatwicfx.viewmodel.ViewModelTestBase;

/**
 * This test has a minimal minimal code coverage:
 * 
 * new SimpleListProperty<TweetVM>(
			FXCollections.<TweetVM> observableArrayList());
 * 
 * @author stefan.illgen
 * 
 */
public class HashTagTweetViewModelTest extends ViewModelTestBase {

	private HashTagTweetViewModel httvm;


	@Before
	public void setup() {
		
		createModel();

		// create view model
		httvm = new HashTagTweetViewModel();

		// bind model 2 view model
		ListBinding<TweetVM> lb = new ListBinding<TweetVM>() {
			{
				super.bind(repo.hashTagsProperty().get(0).tweetsProperty());
			}

			@Override
			protected ObservableList<TweetVM> computeValue() {

				ListProperty<TweetVM> result = new SimpleListProperty<TweetVM>(
						javafx.collections.FXCollections
								.observableList(new ArrayList<TweetVM>()));

				// assemble TweetVM
				ObservableList<Tweet> newTweetsValue = repo.hashTagsProperty()
						.get(0).tweetsProperty().get();

				for (Tweet tweet : newTweetsValue) {
					ObservableList<TweetVM> l = result.get();
					l.add(new TweetVM(tweet));
				}

				return result;
			}
		};
		httvm.tweetsProperty().bind(lb);
	}

	

	/**
	 * Verify, that when adding a new {@link Tweet} to the model the view models
	 * {@link HashTagTweetViewModel#tweetsProperty()} does contain a new
	 * {@link TweetVM} instance, thus the size increases by one.
	 */
	@Test
	public void updateTweetsPropertyTest() {
		// PRECONDITION
		int sizeBefore = httvm.tweetsProperty().get().size();
		verifyThat(sizeBefore, is(1));
		// ACTION
		repo.hashTagsProperty().get(0).tweetsProperty().get().add(new Tweet() {
			{
				setCreatedAt(new DateTime(1682916379000L));
				setText("New Blahhhhh #JavaFX");
				setUser(new User() {
					{
						setName("Stefan");
						setDescription("");
						setFollowersCount(0);
						setFriendsCount(0);
					}
				});
			}
		});
		// VERIFICATION
		int sizeAfter = httvm.tweetsProperty().get().size();
		verifyThat(sizeAfter, is(sizeBefore + 1));
		verifyThat(httvm.tweetsProperty().get(sizeAfter - 1).getUser(),
				is("Stefan"));
		verifyThat(httvm.tweetsProperty().get(sizeAfter - 1).getCreatedAt(),
				is(new DateTime(1682916379000L)));
		verifyThat(httvm.tweetsProperty().get(sizeAfter - 1).getText(),
				is("New Blahhhhh #JavaFX"));
	}

	@After
	public void tearDown() {
		repo = null;
	}

}
