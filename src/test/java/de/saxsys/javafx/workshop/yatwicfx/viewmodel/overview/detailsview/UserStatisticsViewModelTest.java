/**
 * 
 */
package de.saxsys.javafx.workshop.yatwicfx.viewmodel.overview.detailsview;

//import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.loadui.testfx.Assertions.verifyThat;

import org.junit.Before;
import org.junit.Test;

import de.saxsys.javafx.workshop.yatwicfx.viewmodel.ViewModelTestBase;
import de.saxsys.javafx.workshop.yatwicfx.viewmodel.detailsview.UserStatisticsViewModel;

/**
 * @author stefan.illgen
 *
 */
public class UserStatisticsViewModelTest extends ViewModelTestBase {

	private UserStatisticsViewModel userStatisticsViewModel;

	@Before
	public void setUp() {
		// create model
		super.setUp();
		// create view model
		userStatisticsViewModel = new UserStatisticsViewModel();
//		userStatisticsViewModel.initialize(repo.getHashTags().get(0).getTweets().get(0)
//				.getUser().getId());
	}
	
	@Test
	public void hashTagsProperty() {
		verifyThat(userStatisticsViewModel.hashTagsProperty(), notNullValue());
//		verifyThat(userStatisticsViewModel.hashTagsProperty().get, matcher);
	}
	
}
