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

/**
 * @author stefan.illgen
 * 
 */
public class TweetsVMTest extends ViewModelTestBase {

	private UserTweetViewModel tweetsVM;

	@Before
	public void setUp() {
		// create model
		super.setUp();
		// create view model
		tweetsVM = new UserTweetViewModel(repo);
		tweetsVM.initialize(repo.getHashTags().get(0).getTweets().get(0)
				.getUser().getId());
	}

	@Test
	public void tweetTextTest() {
		verifyThat(tweetsVM.tweetsProperty(), notNullValue());
		verifyThat(
				tweetsVM.tweetsProperty().get().get(0),
				is("New blog post: Context Menu for Java 8 (Revisited) - http://t.co/ZnEfuYhij3 … #Java8 #JMetro #JavaFX"));
	}

}
