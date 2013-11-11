/**
 * 
 */
package de.saxsys.javafx.workshop.yatwicfx.viewmodel.overview.detailsview;

import java.util.ArrayList;
import java.util.List;

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
			FXCollections.observableList(new ArrayList<PieChart.Data>()));

	public ObjectProperty<ObservableList<Data>> hashTagsProperty() {
		return hashtags;
	}

	private List<Data> hashtags2 = new ArrayList<Data>(new ArrayList<Data>());

	public List<Data> getHashTags2() {
		return hashtags2;
	}

}
