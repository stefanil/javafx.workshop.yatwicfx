/**
 * 
 */
package de.saxsys.javafx.workshop.yatwicfx.viewmodel.main;

import javafx.collections.ListChangeListener;
import de.saxsys.javafx.workshop.yatwicfx.viewmodel.model.TweetVM;
import de.saxsys.javafx.workshop.yatwicfx.viewmodel.overview.HashTagListViewModel;
import de.saxsys.javafx.workshop.yatwicfx.viewmodel.overview.HashTagTweetViewModel;
import de.saxsys.javafx.workshop.yatwicfx.viewmodel.overview.HashTagWeeklyStatisticsViewModel;
import de.saxsys.jfx.mvvm.base.viewmodel.ViewModel;

/**
 * @author stefan.illgen
 * 
 */
public class MainContainerViewModel implements ViewModel {

	private HashTagListViewModel hashTagListViewModel = null;
	private HashTagTweetViewModel hashTagTweetViewModel = null;
	private HashTagWeeklyStatisticsViewModel hashTagWeeklyStatisticsViewModel = null;

	public HashTagListViewModel getHashTagListViewModel() {
		return hashTagListViewModel;
	}

	public void setHashTagListViewModel(
			HashTagListViewModel hashTagListViewModel) {
		this.hashTagListViewModel = hashTagListViewModel;
	}

	public HashTagTweetViewModel getHashTagTweetViewModel() {
		return hashTagTweetViewModel;
	}

	public void setHashTagTweetViewModel(
			HashTagTweetViewModel hashTagTweetViewModel) {
		this.hashTagTweetViewModel = hashTagTweetViewModel;
	}

	public HashTagWeeklyStatisticsViewModel getHashTagWeeklyStatisticsViewModel() {
		return hashTagWeeklyStatisticsViewModel;
	}

	public void setHashTagWeeklyStatisticsViewModel(
			HashTagWeeklyStatisticsViewModel hashTagWeeklyStatisticsViewModel) {
		this.hashTagWeeklyStatisticsViewModel = hashTagWeeklyStatisticsViewModel;
	}

	/**
	 * Initializes the model bindings.
	 */
	public void initialize() {

		// bind tweets property of hash tag list view model 2 tweet view model
		hashTagTweetViewModel.tweetsProperty().bind(hashTagListViewModel.tweetsProperty());

		// bind tweets property of hash tag list view model 2 statistic charts
		// model
		hashTagTweetViewModel.tweetsProperty().addListener(new ListChangeListener<TweetVM>() {
			@Override
			public void onChanged(
					javafx.collections.ListChangeListener.Change<? extends TweetVM> tweetChange) {

				if (tweetChange.next()) {
					
					if (tweetChange.wasRemoved())
						hashTagWeeklyStatisticsViewModel.removeAllTweets();
					
					if (tweetChange.wasAdded())
						hashTagWeeklyStatisticsViewModel.loadAddedTweet(
								tweetChange.getAddedSubList());
				}
			}
		});
	}

}
