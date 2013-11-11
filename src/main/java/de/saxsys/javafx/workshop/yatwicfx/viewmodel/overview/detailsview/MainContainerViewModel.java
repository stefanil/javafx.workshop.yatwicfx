/**
 * 
 */
package de.saxsys.javafx.workshop.yatwicfx.viewmodel.overview.detailsview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.beans.property.ListProperty;
import javafx.collections.ListChangeListener;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.chart.PieChart.Data;
import de.saxsys.javafx.workshop.yatwicfx.model.HashTag;
import de.saxsys.javafx.workshop.yatwicfx.model.Tweet;
import de.saxsys.jfx.mvvm.base.viewmodel.ViewModel;

/**
 * @author stefan.illgen
 * 
 */
public class MainContainerViewModel implements ViewModel {

	private UserVM userInfoViewModel;
	private UserStatisticsViewModel userStatisticsViewModel;
	private UserTweetViewModel userTweetViewModel;

	public void setUserInfoViewModel(UserVM userInfoViewModel) {
		this.userInfoViewModel = userInfoViewModel;
	}

	public void setUserStatisticsViewModel(
			UserStatisticsViewModel userStatisticsViewModel) {
		this.userStatisticsViewModel = userStatisticsViewModel;
	}

	public void setUserTweetViewModel(UserTweetViewModel userTweetViewModel) {
		this.userTweetViewModel = userTweetViewModel;
	}

	public void initialize(String userId) {
		userInfoViewModel.initialize(userId);
		userTweetViewModel.initialize(userId);
	}

	public void bindDeferred() {

		// ############# bind HashTag Pie Chart 2 Tweets View Model #############
		
		userStatisticsViewModel.hashTagsProperty().get().addListener(new ListChangeListener<Data>(){
			@Override
			public void onChanged(
					javafx.collections.ListChangeListener.Change<? extends Data> c) {
				
				if(c.next()) {
					if(c.wasAdded()) {
						for (final Data data : c.getAddedSubList()) {
							data.getNode().setOnMouseClicked(
									new EventHandler<Event>() {
										@Override
										public void handle(Event event) {
											userTweetViewModel
													.filterTweets(data
															.getName());

										}
									});
						}
					}
				}
			}});

		// ############# load data (no binding) ###############
		
		ListProperty<Tweet> rawTweets = userTweetViewModel
				.rawAllTweetsProperty();
		
		// extract all HashTags ever used by the user
		List<HashTag> hashTags = new ArrayList<HashTag>();
		for (Tweet tweet : rawTweets.get()) {
			hashTags.addAll(tweet.getHashTags());
		}

		Map<String, Integer> intermediate = new HashMap<String, Integer>();

		// count hash tags
		for (HashTag hashTag : hashTags) {
			String key = hashTag.getText();
			if (intermediate.containsKey(key))
				intermediate.put(key,
						intermediate.get(key).intValue() + 1);
			else
				intermediate.put(key, 1);
		}
		
		// .. and turn into pie chart data
		for (String hashTagText : intermediate.keySet()) {			
			userStatisticsViewModel.
				hashTagsProperty().
					get().
						add(new Data(hashTagText, intermediate.get(hashTagText)));
		}
	}
}
