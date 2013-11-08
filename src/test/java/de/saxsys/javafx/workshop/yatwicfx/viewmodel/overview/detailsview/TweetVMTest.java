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
import de.saxsys.javafx.workshop.yatwicfx.viewmodel.detailsview.UserTweetViewModel;

/**
 * @author stefan.illgen
 * 
 */
public class TweetVMTest extends ViewModelTestBase {

	private UserTweetViewModel tweetVM;

	@Before
	public void setUp() {
		// create model
		super.setUp();
		// create view model
		tweetVM = new UserTweetViewModel(repo);
		tweetVM.initialize(repo.getHashTags().get(0).getTweets().get(0)
				.getUser().getId());
	}

	@Test
	public void tweetTextTest() {
		verifyThat(tweetVM.tweetsProperty(), notNullValue());
	}

}
