/**
 * 
 */
package de.saxsys.javafx.workshop.yatwicfx.viewmodel.detailsview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.beans.binding.ListBinding;
import javafx.beans.property.ListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

		ListBinding<Data> lb = new ListBinding<Data>() {
			{
//				super.bind(userTweetViewModel.hashTagsProperty());
				super.bind(userTweetViewModel.rawTweetsProperty());
			}

			@Override
			protected ObservableList<Data> computeValue() {

				// FilteredList<Data> result = new FilteredList<Data>(
				// FXCollections.observableList(new ArrayList<Data>()),
				// new Matcher<Data>() {
				// @Override
				// public boolean matches(Data arg0) {
				// return true;
				// }
				// });

				ObservableList<Data> result = FXCollections
						.observableArrayList(new ArrayList<Data>());
				
				ListProperty<Tweet> rawTweets = userTweetViewModel.rawTweetsProperty();
				
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

//					Data existingData = null;
//
//					for (Data data : userStatisticsViewModel.hashTagsProperty().getValue()) {
//						if (data.getName().compareTo(hashTagText) == 0) {
//							data.setPieValue(intermediate.get(hashTagText));
//							existingData = data;
//							break;
//						}
//					}
//
//					if (existingData != null)
//						result.add(existingData);
//					else
						result.add(new Data(hashTagText, intermediate
								.get(hashTagText)));
				}

				return result;
			}
		};

		userStatisticsViewModel.hashTagsProperty().bind(lb);
	}
}
