/**
 * 
 */
package de.saxsys.javafx.workshop.yatwicfx.viewmodel.overview.detailsview;

import org.junit.Before;

import de.saxsys.javafx.workshop.yatwicfx.viewmodel.ViewModelTestBase;
import de.saxsys.javafx.workshop.yatwicfx.viewmodel.detailsview.MainContainerViewModel;
import de.saxsys.javafx.workshop.yatwicfx.viewmodel.detailsview.UserVM;

/**
 * @author stefan.illgen
 * 
 */
public class MainContainerViewModelTest extends ViewModelTestBase {

	private UserVM userInfoViewModel;
	private MainContainerViewModel mainContainerViewModel;

	@Before
	public void setUp() {
		// create model
		super.setUp();
		// create view model
		userInfoViewModel = new UserVM(repo);
		mainContainerViewModel = new MainContainerViewModel();
		mainContainerViewModel.initialize(repo.getHashTags().get(0).getTweets()
				.get(0).getUser().getId());
	}

}
