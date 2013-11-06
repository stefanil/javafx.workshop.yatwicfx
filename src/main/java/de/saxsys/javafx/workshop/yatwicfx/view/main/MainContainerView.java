package de.saxsys.javafx.workshop.yatwicfx.view.main;

import java.net.URL;
import java.util.ResourceBundle;

import com.cathive.fx.guice.FXMLController;

import javafx.fxml.FXML;
import de.saxsys.javafx.workshop.yatwicfx.view.overview.HashTagListView;
import de.saxsys.javafx.workshop.yatwicfx.view.overview.HashTagTweetView;
import de.saxsys.javafx.workshop.yatwicfx.view.overview.HashTagWeeklyStatisticsView;
import de.saxsys.javafx.workshop.yatwicfx.viewmodel.main.MainContainerViewModel;
import de.saxsys.jfx.mvvm.base.view.View;

@FXMLController
public class MainContainerView extends View<MainContainerViewModel> {

	@FXML
	private HashTagListView hashTagListViewController;

	@FXML
	private HashTagTweetView hashTagTweetViewController;

	@FXML
	private HashTagWeeklyStatisticsView hashTagWeeklyStatisticsViewController;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		getViewModel().setHashTagListViewModel(hashTagListViewController.getViewModel());
		getViewModel().setHashTagTweetViewModel(hashTagTweetViewController.getViewModel());
		getViewModel().setHashTagWeeklyStatisticsViewModel(hashTagWeeklyStatisticsViewController.getViewModel());
		
		getViewModel().initialize();
	}

}
