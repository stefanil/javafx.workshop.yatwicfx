/**
 * 
 */
package de.saxsys.javafx.workshop.yatwicfx.view.overview;

import static org.hamcrest.core.Is.is;
//import static org.hamcrest.core.IsNot.not;
import static org.loadui.testfx.Assertions.verifyThat;
import javafx.collections.ObservableList;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.ListView;

import org.junit.Test;

import de.saxsys.javafx.workshop.yatwicfx.model.HashTag;
import de.saxsys.javafx.workshop.yatwicfx.view.AcceptanceTest;

/**
 * @author stefan.illgen
 * 
 */
public class HashTagStatisticsChartTest extends AcceptanceTest {
	
	/**
	 * Click on the 1st item of the {@link ListView} for showing {@link HashTag}
	 * s and verify the data property change in the {@link StackedBarChart} for
	 * representing the weekly frequency for the selected {@link HashTag}.
	 */
	@Test
	public void clickOnHashTagWeeklyStatisticsViewTest() {
		// GIVEN
		ListView<String> hashTagListView = find("#hashTagList");
		StackedBarChart<String, Number> hashTagWeeklyStatisticBarChart = find("#hashTagWeeklyStatisticBarChart");
		ObservableList<Series<String, Number>> chartSeriesList = hashTagWeeklyStatisticBarChart.dataProperty().get();
		Series<String, Number> series = chartSeriesList.get(0);
		verifyThat(series.getData().size(), is(7));
		for (int i = 0; i < chartSeriesList.size(); i++) {
			verifyThat(series.getData().get(i).getYValue(), is((Number)0));		
		}
		// WHEN
		click(hashTagListView.getChildrenUnmodifiable().get(0));
		// THEN
		series = chartSeriesList.get(0);
		verifyThat(series.getData().get(1).getYValue(), is((Number)3.0));	
		verifyThat(series.getData().get(6).getYValue(), is((Number)1.0));	
		
	}

}
