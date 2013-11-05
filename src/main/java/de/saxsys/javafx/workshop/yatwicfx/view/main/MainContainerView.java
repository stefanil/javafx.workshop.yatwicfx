package de.saxsys.javafx.workshop.yatwicfx.view.main;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.fxml.FXML;
import de.saxsys.javafx.workshop.yatwicfx.view.overview.HashTagListView;
import de.saxsys.javafx.workshop.yatwicfx.view.overview.HashTagTweetView;
import de.saxsys.javafx.workshop.yatwicfx.view.overview.HashTagWeeklyStatisticsView;
import de.saxsys.jfx.mvvm.base.view.ViewWithoutViewModel;

public class MainContainerView extends ViewWithoutViewModel {

	@FXML
	private HashTagListView hashTagListViewController;

	@FXML
	private HashTagTweetView hashTagTweetViewController;

	@FXML
	private HashTagWeeklyStatisticsView hashTagWeeklyStatisticsViewController;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		ReadOnlyObjectProperty<String> selectedItemProperty = hashTagListViewController.getSelectedItemProperty();
		hashTagTweetViewController.bindSelection(selectedItemProperty);
		hashTagWeeklyStatisticsViewController.bindSelection(selectedItemProperty);

	}

}
