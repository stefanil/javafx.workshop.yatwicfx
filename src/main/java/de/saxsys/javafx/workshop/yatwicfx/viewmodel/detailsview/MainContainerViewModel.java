/**
 * 
 */
package de.saxsys.javafx.workshop.yatwicfx.viewmodel.detailsview;

import de.saxsys.jfx.mvvm.base.viewmodel.ViewModel;

/**
 * @author stefan.illgen
 *
 */
public class MainContainerViewModel implements ViewModel {

	private UserVM userInfoViewModel;
	private UserStatisticsViewModel userStatisticsViewModel;
	private UserTweetViewModel userTweetViewModel;

	public void setUserInfoViewModel(UserVM userInfoViewModel) {
		this.userInfoViewModel = userInfoViewModel;
	}

	public void setUserStatisticsViewModel(UserStatisticsViewModel userStatisticsViewModel) {
		this.userStatisticsViewModel = userStatisticsViewModel;
	}

	public void setUserTweetViewModel(UserTweetViewModel userTweetViewModel) {
		this.userTweetViewModel = userTweetViewModel;
	}
	
	public void initialize(String userId) {
		userInfoViewModel.initialize(userId);
	}
}
