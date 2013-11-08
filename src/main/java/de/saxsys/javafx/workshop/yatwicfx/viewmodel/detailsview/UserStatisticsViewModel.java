/**
 * 
 */
package de.saxsys.javafx.workshop.yatwicfx.viewmodel.detailsview;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import de.saxsys.jfx.mvvm.base.viewmodel.ViewModel;

/**
 * @author stefan.illgen
 * 
 */
public class UserStatisticsViewModel implements ViewModel {

	private ObjectProperty<ObservableList<Data>> hashtags = new SimpleObjectProperty<ObservableList<Data>>(
			FXCollections.observableArrayList(new PieChart.Data("Grapefruit",
					13), new PieChart.Data("Oranges", 25), new PieChart.Data(
					"Plums", 10), new PieChart.Data("Pears", 22),
					new PieChart.Data("Apples", 30)));

	public ObjectProperty<ObservableList<Data>> hashTagsProperty() {
		return hashtags;
	}

}
