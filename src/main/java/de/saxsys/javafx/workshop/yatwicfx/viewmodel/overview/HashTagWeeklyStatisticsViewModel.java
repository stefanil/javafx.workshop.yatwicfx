/**
 * 
 */
package de.saxsys.javafx.workshop.yatwicfx.viewmodel.overview;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;

import org.joda.time.DateTimeConstants;

import com.google.inject.Inject;

import de.saxsys.javafx.workshop.yatwicfx.model.Repository;
import de.saxsys.javafx.workshop.yatwicfx.model.Tweet;
import de.saxsys.jfx.mvvm.base.viewmodel.ViewModel;

/**
 * @author stefan.illgen
 * 
 */
public class HashTagWeeklyStatisticsViewModel implements ViewModel {

	private Repository repo;

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
	public HashTagWeeklyStatisticsViewModel(Repository repo) {

		this.repo = repo;

		// write initial series data
		hashTagStatistics.get().add(
				createSeriesData("Kein HashTag ausgewählt",
						getInitialHashStatisticsArray()));

		// FIXME: dirty change listening
		// Binding US 04 (Update View)
		listChangeListener = new ListChangeListener<Tweet>() {
			@Override
			public void onChanged(
					javafx.collections.ListChangeListener.Change<? extends Tweet> change) {

				while (change.next()) {
					if (change.wasAdded())
						reloadStatistics();
				}
			}
		};

		tweetsProperty = repo.tweetsProperty();
		tweetsProperty.addListener(listChangeListener);
	}

	public ObjectProperty<ObservableList<Series<String, Number>>> hashTagStatisticsProperty() {
		return hashTagStatistics;
	}

	public void reloadStatistics(String hashTag) {

		// cache it for US 04
		cachedHashTag = hashTag;
		// clear statistics
		hashTagStatistics.get().clear();

		int[] hashTagStatisticsArray = getInitialHashStatisticsArray();

		// this sets all the hashTagStatistics
		List<Tweet> allTweets = repo.getTweets();
		for (int i = 0; i < allTweets.size(); i++) {
			Tweet tweet = allTweets.get(i);
			String tweetText = tweet.getText();
			if (tweetText.indexOf(hashTag) != -1) {

				switch (tweet.getCreatedAt().getDayOfWeek()) {

				case DateTimeConstants.MONDAY:
					hashTagStatisticsArray[0] += 1;
					break;
				case DateTimeConstants.TUESDAY:
					hashTagStatisticsArray[1] += 1;
					break;
				case DateTimeConstants.WEDNESDAY:
					hashTagStatisticsArray[2] += 1;
					break;
				case DateTimeConstants.THURSDAY:
					hashTagStatisticsArray[3] += 1;
					break;
				case DateTimeConstants.FRIDAY:
					hashTagStatisticsArray[4] += 1;
					break;
				case DateTimeConstants.SATURDAY:
					hashTagStatisticsArray[5] += 1;
					break;
				case DateTimeConstants.SUNDAY:
					hashTagStatisticsArray[6] += 1;
					break;
				}
			}
		}

		hashTagStatistics.get().add(
				createSeriesData(hashTag, hashTagStatisticsArray));
	}

	/**
	 * Creates an initial Array, which is used for writing {@link Series} data.
	 * 
	 * @return
	 */
	private int[] getInitialHashStatisticsArray() {

		int[] result = new int[7];
		for (int i = 0; i < 7; i++)
			result[i] = 0;

		return result;
	}

	/**
	 * Sets the name of the {@link Series} instance and writes its data.
	 */
	private Series<String, Number> createSeriesData(String name,
			int[] hashTagStatisticsMap) {

		// create new instance
		Series<String, Number> series = new Series<>();

		// set series name
		series.setName(name);

		// write data
		ObservableList<Data<String, Number>> data = series.getData();
		data.add(new XYChart.Data<String, Number>(MONDAY,
				hashTagStatisticsMap[0]));
		data.add(new XYChart.Data<String, Number>(TUESDAY,
				hashTagStatisticsMap[1]));
		data.add(new XYChart.Data<String, Number>(WEDNESDAY,
				hashTagStatisticsMap[2]));
		data.add(new XYChart.Data<String, Number>(THURSDAY,
				hashTagStatisticsMap[3]));
		data.add(new XYChart.Data<String, Number>(FRIDAY,
				hashTagStatisticsMap[4]));
		data.add(new XYChart.Data<String, Number>(SATURDAY,
				hashTagStatisticsMap[5]));
		data.add(new XYChart.Data<String, Number>(SUNDAY,
				hashTagStatisticsMap[6]));

		return series;
	}
	
	// dirty update stuff
	
	private static ListProperty<Tweet> tweetsProperty;
	private static ListChangeListener<Tweet> listChangeListener;
	
	private String cachedHashTag = null;
	
	private void reloadStatistics() {
		if(cachedHashTag != null)
			reloadStatistics(cachedHashTag);		
	}

	public static void removeChangeListener() {
		tweetsProperty.removeListener(listChangeListener);
	}

}
