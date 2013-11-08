/**
 * 
 */
package de.saxsys.javafx.workshop.yatwicfx.view.overview.detailsview;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import de.saxsys.javafx.workshop.yatwicfx.viewmodel.detailsview.UserStatisticsViewModel;
import de.saxsys.jfx.mvvm.base.view.View;

/**
 * @author stefan.illgen
 * 
 */
public class UserStatisticsView extends View<UserStatisticsViewModel> {

	@FXML
	private PieChart userHashTagPieChart;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// bind deferred
	}

	public void bindDeferred() {
		userHashTagPieChart.dataProperty().bind(
				getViewModel().hashTagsProperty());			
	}

}
