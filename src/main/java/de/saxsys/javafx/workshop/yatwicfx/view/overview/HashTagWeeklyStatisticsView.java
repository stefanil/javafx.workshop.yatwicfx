/**
 * 
 */
package de.saxsys.javafx.workshop.yatwicfx.view.overview;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.StackedBarChart;
import de.saxsys.javafx.workshop.yatwicfx.viewmodel.overview.HashTagWeeklyStatisticsViewModel;
import de.saxsys.jfx.mvvm.base.view.View;

/**
 * @author stefan.illgen
 * 
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
	}

	public void bindSelection(
			ReadOnlyObjectProperty<String> selectedItemProperty) {

		// bind ui chart to it model
		hashTagWeeklyStatisticBarChart.dataProperty().bind(
				getViewModel().hashTagStatisticsProperty());

		// on change the selection in hash tag list, reload tweets
		htChangeListener = new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0,
					String oldHashTag, String newHashTag) {

				// reload tweets
				if (oldHashTag == null || !oldHashTag.equals(newHashTag)) {
					getViewModel().reloadStatistics(newHashTag);
				}
			}
		};
		listenedProperty = selectedItemProperty;
		selectedItemProperty.addListener(htChangeListener);

	}

	// ######################

	private static ReadOnlyObjectProperty<String> listenedProperty;
	private static ChangeListener<String> htChangeListener;

	public static void removeChangeListener() {
		listenedProperty.removeListener(htChangeListener);
	}

}
