/**
 * 
 */
package de.saxsys.javafx.workshop.yatwicfx.viewmodel.overview.detailsview;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.loadui.testfx.Assertions.verifyThat;

import org.junit.Before;
import org.junit.Test;

import de.saxsys.javafx.workshop.yatwicfx.viewmodel.ViewModelTestBase;
import de.saxsys.javafx.workshop.yatwicfx.viewmodel.detailsview.UserVM;

/**
 * @author stefan.illgen
 * 
 */
public class UserVMTest extends ViewModelTestBase {

	private UserVM userVM;

	@Before
	public void setUp() {
		super.setUp();

		// create view model
		userVM = new UserVM(repo);
		userVM.initialize(repo.
				getHashTags().
				get(0).
				getTweets()
				.get(0).
				getUser().
				getId());
	}

	/**
	 * Verify that the name property gets initialized well.
	 */
	@Test
	public void nameTest() {
		verifyThat(userVM.nameProperty(), notNullValue());
		verifyThat(userVM.getName(), is("Pedro Duque Vieira"));
	}

	/**
	 * Verify that the description property gets initialized well.
	 */
	@Test
	public void descriptionTest() {
		verifyThat(userVM.descriptionProperty(), notNullValue());
		verifyThat(
				userVM.getDescription(),
				is("Freelance Software Engineer who loves front-end/user experience development"));
	}

	/**
	 * Verify that the followersCount property gets initialized well.
	 */
	@Test
	public void followersCountTest() {
		verifyThat(userVM.followersCountProperty(), notNullValue());
		verifyThat(userVM.getFollowersCount(), is(3));
	}

	/**
	 * Verify that the friendsCount property gets initialized well.
	 */
	@Test
	public void friendsCountTest() {
		verifyThat(userVM.friendsCountProperty(), notNullValue());
		verifyThat(userVM.getFriendsCount(), is(1));
	}

}
