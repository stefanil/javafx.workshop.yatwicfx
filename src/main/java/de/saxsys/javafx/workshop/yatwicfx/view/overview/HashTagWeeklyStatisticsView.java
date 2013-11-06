/**
 * 
 */
package de.saxsys.javafx.workshop.yatwicfx.view.overview;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

import javafx.beans.property.ListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.StackedBarChart;
import de.saxsys.javafx.workshop.yatwicfx.model.Tweet;
import de.saxsys.javafx.workshop.yatwicfx.viewmodel.overview.HashTagWeeklyStatisticsViewModel;
import de.saxsys.jfx.mvvm.base.view.View;

/**
 * @author stefan.illgen
 */
public class HashTagWeeklyStatisticsView extends
		View<HashTagWeeklyStatisticsViewModel> {

	@FXML
	private StackedBarChart<String, Number> hashTagWeeklyStatisticBarChart;

	@FXML
	private CategoryAxis categoryAxis;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		categoryAxis.setCategories(FXCollections
				.<String> observableArrayList(Arrays.asList(
						HashTagWeeklyStatisticsViewModel.MONDAY,
						HashTagWeeklyStatisticsViewModel.TUESDAY,
						HashTagWeeklyStatisticsViewModel.WEDNESDAY,
						HashTagWeeklyStatisticsViewModel.THURSDAY,
						HashTagWeeklyStatisticsViewModel.FRIDAY,
						HashTagWeeklyStatisticsViewModel.SATURDAY,
						HashTagWeeklyStatisticsViewModel.SUNDAY)));

		// bind ui chart to it model
		hashTagWeeklyStatisticBarChart.dataProperty().bind(
				getViewModel().hashTagStatisticsProperty());
	}

	// dirty change listsner stuff
	
	private static ListChangeListener<Tweet> weeklyStatisticsChangeListener;
	
	public void bindSelection(final ListProperty<Tweet> tweetsProperty) {

		weeklyStatisticsChangeListener = new ListChangeListener<Tweet>() {
			@Override
			public void onChanged(
					javafx.collections.ListChangeListener.Change<? extends Tweet> tweetChange) {

				if (tweetChange.next()) {
					
					// removed
					if (tweetChange.wasRemoved())
						getViewModel().removeAllTweets();
					
					// tweet was added
					if (tweetChange.wasAdded())
						getViewModel().loadAddedTweet(
								tweetChange.getAddedSubList());
				}
			}
		};
		
		tweetsProperty.addListener(weeklyStatisticsChangeListener);
	}
}
