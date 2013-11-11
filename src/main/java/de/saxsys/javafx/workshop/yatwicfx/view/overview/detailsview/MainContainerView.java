/**
 * 
 */
package de.saxsys.javafx.workshop.yatwicfx.view.overview.detailsview;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import de.saxsys.javafx.workshop.yatwicfx.viewmodel.overview.detailsview.MainContainerViewModel;
import de.saxsys.jfx.mvvm.base.view.View;

/**
 * @author stefan.illgen
 *
 */
public class MainContainerView extends View<MainContainerViewModel> {

	@FXML
	private UserInfoView userInfoViewController;
	
	@FXML
	private UserStatisticsView userStatisticsViewController;
	
	@FXML
	private UserTweetView userTweetViewController;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		getViewModel().
			setUserInfoViewModel(
					userInfoViewController.getViewModel());
		getViewModel().setUserStatisticsViewModel(userStatisticsViewController.getViewModel());
		getViewModel().setUserTweetViewModel(userTweetViewController.getViewModel());
	}
	
	public void bindDeferred() {
		userInfoViewController.bindDeferred();
		userTweetViewController.bindDeferred();
		userStatisticsViewController.bindDeferred();
	}

}
