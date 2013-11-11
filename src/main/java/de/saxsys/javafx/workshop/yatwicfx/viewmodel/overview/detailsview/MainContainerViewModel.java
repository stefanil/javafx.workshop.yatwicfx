/**
 * 
 */
package de.saxsys.javafx.workshop.yatwicfx.viewmodel.overview.detailsview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.beans.binding.ListBinding;
import javafx.beans.property.ListProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.chart.PieChart;
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

		// bind HashTag Pie Chart 2 Tweets View Model
		
//		userStatisticsViewModel.hashTagsProperty().addListener(
//				new ChangeListener<ObservableList<PieChart.Data>>() {
//					@Override
//					public void changed(
//							ObservableValue<? extends ObservableList<Data>> observable,
//							ObservableList<Data> oldValue,
//							ObservableList<Data> newValue) {
//
//						for (final Data data : userStatisticsViewModel
//								.hashTagsProperty().get()) {
//							data.getNode().setOnMouseClicked(
//									new EventHandler<Event>() {
//										@Override
//										public void handle(Event event) {
//											userTweetViewModel
//													.filterTweets(data
//															.getName());
//										}
//									});
//						}
//					}
//
//				});
		
		ListBinding<Data> lb = new ListBinding<Data>() {
			{
				super.bind(userTweetViewModel.rawTweetsProperty());
			}

			@Override
			protected ObservableList<Data> computeValue() {

				// FIXME use filtered List instead of updating item list
				// FilteredList<Data> result = new FilteredList<Data>(
				// FXCollections.observableList(new ArrayList<Data>()),
				// new Matcher<Data>() {
				// @Override
				// public boolean matches(Data arg0) {
				// return true;
				// }
				// });

				ObservableList<Data> result = FXCollections
						.observableList(new ArrayList<Data>());

				ListProperty<Tweet> rawTweets = userTweetViewModel
						.rawTweetsProperty();

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

					// Data existingData = null;
					//
					// FIXME stefan cannot access (causes StackOverFlow due to
					// control flow cycle)
					// for (Data data :
					// userStatisticsViewModel.hashTagsProperty().getValue()) {
					// if (data.getName().compareTo(hashTagText) == 0) {
					// data.setPieValue(intermediate.get(hashTagText));
					// existingData = data;
					// break;
					// }
					// }
					//
					// if (existingData != null)
					// result.add(existingData);
					// else {

					result.add(new Data(hashTagText, intermediate
							.get(hashTagText)));
					// }
				}

				return result;
			}
		};

		userStatisticsViewModel.hashTagsProperty().bind(lb);

		
	}
}
