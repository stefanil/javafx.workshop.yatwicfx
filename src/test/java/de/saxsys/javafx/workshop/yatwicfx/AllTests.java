package de.saxsys.javafx.workshop.yatwicfx;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import de.saxsys.javafx.workshop.yatwicfx.view.overview.HashTagTweetViewTest;
import de.saxsys.javafx.workshop.yatwicfx.view.overview.HashTagWeeklyStatisticsViewTest;
import de.saxsys.javafx.workshop.yatwicfx.viewmodel.main.MainContainerViewModelTest;
import de.saxsys.javafx.workshop.yatwicfx.viewmodel.overview.HashTagListViewModelTest;
import de.saxsys.javafx.workshop.yatwicfx.viewmodel.overview.HashTagTweetViewModelTest;
import de.saxsys.javafx.workshop.yatwicfx.viewmodel.overview.detailsview.UserVMTest;
import de.saxsys.javafx.workshop.yatwicfx.viewmodel.overview.detailsview.UserStatisticsViewModelTest;
import de.saxsys.javafx.workshop.yatwicfx.viewmodel.overview.detailsview.TweetVMTest;

@RunWith(Suite.class)
@SuiteClasses({ HashTagListViewModelTest.class,
		HashTagTweetViewModelTest.class, MainContainerViewModelTest.class,
		HashTagTweetViewTest.class, HashTagWeeklyStatisticsViewTest.class,
		MainContainerViewModelTest.class, UserVMTest.class,
		UserStatisticsViewModelTest.class, TweetVMTest.class})
public class AllTests {

}
