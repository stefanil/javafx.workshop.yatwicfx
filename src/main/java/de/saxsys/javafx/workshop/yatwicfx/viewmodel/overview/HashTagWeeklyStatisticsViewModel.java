/**
 * 
 */
package de.saxsys.javafx.workshop.yatwicfx.viewmodel.overview;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;

import com.google.inject.Inject;

import de.saxsys.javafx.workshop.yatwicfx.model.Tweet;
import de.saxsys.javafx.workshop.yatwicfx.viewmodel.model.TweetVM;
import de.saxsys.jfx.mvvm.base.viewmodel.ViewModel;

/**
 * @author stefan.illgen
 * 
 */
public class HashTagWeeklyStatisticsViewModel implements ViewModel {

	private static final String SERIES_TITLE = "Anzahl der Tweets pro Tag";
	public static final String SUNDAY = "Sonntag";
	public static final String SATURDAY = "Samstag";
	public static final String FRIDAY = "Freitag";
	public static final String THURSDAY = "Donnerstag";
	public static final String WEDNESDAY = "Mittwoch";
	public static final String TUESDAY = "Dienstag";
	public static final String MONDAY = "Montag";

	private ObjectProperty<ObservableList<Series<String, Number>>> hashTagStatistics = new SimpleObjectProperty<ObservableList<Series<String, Number>>>(
			FXCollections
					.observableArrayList(new ArrayList<Series<String, Number>>()));

	@Inject
	public HashTagWeeklyStatisticsViewModel() {

		// create and write initial series data
		Series<String, Number> series = new Series<>();
		series.setName(SERIES_TITLE);

		ObservableList<Data<String, Number>> dates = series.getData();
		dates.add(new XYChart.Data<String, Number>(MONDAY, 0.0));
		dates.add(new XYChart.Data<String, Number>(TUESDAY, 0.0));
		dates.add(new XYChart.Data<String, Number>(WEDNESDAY, 0.0));
		dates.add(new XYChart.Data<String, Number>(THURSDAY, 0.0));
		dates.add(new XYChart.Data<String, Number>(FRIDAY, 0.0));
		dates.add(new XYChart.Data<String, Number>(SATURDAY, 0.0));
		dates.add(new XYChart.Data<String, Number>(SUNDAY, 0.0));

		hashTagStatistics.get().add(series);
	}

	public ObjectProperty<ObservableList<Series<String, Number>>> hashTagStatisticsProperty() {
		return hashTagStatistics;
	}

	/**
	 * 
	 * <b>Known Encapsulation Problem:</b> The model class {@link Tweet} should
	 * not be made publicly visible to the view layer to preserve variability of
	 * the model layer.
	 * 
	 * @param addedSubList
	 */
	public void loadAddedTweet(List<? extends TweetVM> addedSubList) {

		Series<String, Number> series = hashTagStatistics.getValue().get(0);
		ObservableList<Data<String, Number>> dates = series.getData();

		for (TweetVM tweet : addedSubList) {
			Data<String, Number> data = dates.get(tweet.getCreatedAt()
					.getDayOfWeek() - 1);
			data.setYValue(Integer.valueOf(data.getYValue().intValue() + 1));
		}
	}

	public void removeAllTweets() {

		ObservableList<Data<String, Number>> dates = hashTagStatistics
				.getValue().get(0).getData();
		for (int i = 0; i < 7; i++) {
			dates.get(i).setYValue(0.0);
		}
	}

}
