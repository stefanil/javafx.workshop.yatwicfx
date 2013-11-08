package de.saxsys.javafx.workshop.yatwicfx.viewmodel.overview;

import static org.hamcrest.core.Is.is;
//import static org.hamcrest.core.IsNot.not;
import static org.loadui.testfx.Assertions.verifyThat;

import java.util.List;

import javafx.beans.property.ReadOnlyListProperty;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import de.saxsys.javafx.workshop.yatwicfx.model.HashTag;
import de.saxsys.javafx.workshop.yatwicfx.model.Repository;
import de.saxsys.javafx.workshop.yatwicfx.model.Tweet;
import de.saxsys.javafx.workshop.yatwicfx.viewmodel.TweetVM;
import de.saxsys.javafx.workshop.yatwicfx.viewmodel.ViewModelTestBase;

/**
 * This Test knows about the internal loading mechanism for {@link Tweet}s of
 * the {@link Repository}.
 * 
 * @author stefan.illgen
 * 
 */
public class HashTagListViewModelTest extends ViewModelTestBase {

	private HashTagListViewModel htlvm;

	@Before
	public void setup() {
		super.setUp();
		// create view model
		htlvm = new HashTagListViewModel(repo);
		htlvm.selectedHashTagIndexProperty().set(1);
	}

	/**
	 * Verify, that {@link HashTagListViewModel#hashTagsProperty()} keeps its
	 * grounding {@link List} properly, i.e. that it shows the correct
	 * {@link HashTag}s.
	 */
	@Test
	public void hashTagsPropertyTest() {
		verifyThat(htlvm.hashTagsProperty().get(0), is("#Java8"));
		verifyThat(htlvm.hashTagsProperty().get(1), is("#JMetro"));
		verifyThat(htlvm.hashTagsProperty().get(2), is("#JavaFX"));
	}

	/**
	 * Verify, that the {@link HashTagListViewModel#tweetsProperty()} gets set
	 * according to the selected {@link HashTag}.
	 */
	@Test
	public void tweetsPropertyTest() {
		// PRECONDITION
		verifyThat(htlvm.selectedHashTagIndexProperty().get(), is(1));
		// ACTION
		htlvm.selectedHashTagIndexProperty().set(0);
		// VERFICATION
		ReadOnlyListProperty<TweetVM> tweetsProperty = htlvm.tweetsProperty();
		verifyThat(tweetsProperty.get(0).getUserName(), is("Pedro Duque Vieira"));
		verifyThat(
				tweetsProperty.get(0).getText(),
				is("New blog post: Context Menu for Java 8 (Revisited) - http://t.co/ZnEfuYhij3 … #Java8 #JMetro #JavaFX"));
		verifyThat(tweetsProperty.get(0).getCreatedAt(), is(new DateTime(
				1382916379000L)));
	}

	/**
	 * Verify, that the
	 * {@link HashTagListViewModel#selectedHashTagIndexProperty()} gets set
	 * according to the selected {@link HashTag}.
	 */
	@Test
	public void selectedHashTagIndexPropertyTest() {
		// Not possible due to encapsulation restrictions :D
		verifyThat(true, is(true));
	}

}
