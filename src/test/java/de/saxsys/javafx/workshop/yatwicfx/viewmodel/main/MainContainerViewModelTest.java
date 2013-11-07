/**
 * 
 */
package de.saxsys.javafx.workshop.yatwicfx.viewmodel.main;

import static org.hamcrest.core.Is.is;
import static org.loadui.testfx.Assertions.verifyThat;
import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart.Series;

import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.saxsys.javafx.workshop.yatwicfx.model.Tweet;
import de.saxsys.javafx.workshop.yatwicfx.model.User;
import de.saxsys.javafx.workshop.yatwicfx.viewmodel.ViewModelTestBase;
import de.saxsys.javafx.workshop.yatwicfx.viewmodel.overview.HashTagListViewModel;
import de.saxsys.javafx.workshop.yatwicfx.viewmodel.overview.HashTagTweetViewModel;
import de.saxsys.javafx.workshop.yatwicfx.viewmodel.overview.HashTagWeeklyStatisticsViewModel;

/**
 * @author stefan.illgen
 * 
 */
public class MainContainerViewModelTest extends ViewModelTestBase {

	private HashTagListViewModel htlvm;
	private HashTagTweetViewModel httvm;
	private MainContainerViewModel mcvm;
	private HashTagWeeklyStatisticsViewModel htwsvm;

	@Before
	public void setup() {
		createModel();
		// create view model
		htlvm = new HashTagListViewModel(repo);
		// ##### initiate selection > mainfest binding (simulate click)
		htlvm.selectedHashTagIndexProperty().set(1);
		// #####
		httvm = new HashTagTweetViewModel();
		htwsvm = new HashTagWeeklyStatisticsViewModel();
		mcvm = new MainContainerViewModel();
		mcvm.setHashTagListViewModel(htlvm);
		mcvm.setHashTagTweetViewModel(httvm);
		mcvm.setHashTagWeeklyStatisticsViewModel(htwsvm);
		mcvm.initialize();
	}

	/**
	 * Verify, that after the selection of the first hash tag in the list and
	 * the subsequent adding of a {@link Tweet} to the model the
	 * {@link HashTagTweetViewModel#tweetsProperty()} gets updated properly.
	 */
	@Test
	public void hashTagUpdateTweetsPropertyTest() {

		// PRECONDITION (do the tests on the first selected hashtag)
		htlvm.selectedHashTagIndexProperty().set(0);
		int sizeBefore = httvm.tweetsProperty().get().size();
		verifyThat(sizeBefore, is(1));

		// ACTION
		addTweet2HashTag();

		// VERIFICATION: Check size, and properties of the tweet
		int sizeAfter = httvm.tweetsProperty().get().size();
		verifyThat(sizeAfter, is(sizeBefore + 1));
		verifyThat(httvm.tweetsProperty().get(sizeAfter - 1).getUser(),
				is("Stefan"));
		verifyThat(httvm.tweetsProperty().get(sizeAfter - 1).getCreatedAt(),
				is(new DateTime(1682916379000L)));
		verifyThat(httvm.tweetsProperty().get(sizeAfter - 1).getText(),
				is("New Blahhhhh #JavaFX"));
	}

	/**
	 * Verify, that after the selection of the first hash tag in the list and
	 * the subsequent adding of a {@link Tweet} to the model the
	 * {@link HashTagWeeklyStatisticsViewModel#hashTagStatisticsProperty()},
	 * gets updated properly (update from 1 to 2 tweets on MONDAY).
	 */
	@Test
	public void hashTagUpdateStatisticsPropertyTest() {

		// PRECONDITION
		htlvm.selectedHashTagIndexProperty().set(0);
		verifyThat(htwsvm.hashTagStatisticsProperty().get().get(0).getData()
				.get(DateTimeConstants.MONDAY - 1).getYValue(),
				is((Number) Integer.valueOf(1)));

		// ACTION
		addTweet2HashTag();		

		// VERIFICATION
		verifyThat(htwsvm.hashTagStatisticsProperty().get().get(0).getData()
				.get(DateTimeConstants.MONDAY - 1).getYValue(),
				is((Number) Integer.valueOf(2)));
	}

	@After
	public void tearDown() {
		repo = null;
	}
	
	private void addTweet2HashTag() {
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
	}

}
