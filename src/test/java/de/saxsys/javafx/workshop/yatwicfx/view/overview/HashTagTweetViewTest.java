/**
 * 
 */
package de.saxsys.javafx.workshop.yatwicfx.view.overview;

import static org.hamcrest.core.Is.is;
//import static org.hamcrest.core.IsNot.not;
import static org.loadui.testfx.Assertions.verifyThat;

import javafx.scene.control.ListView;
import javafx.scene.control.TableView;

import org.junit.Test;

import de.saxsys.javafx.workshop.yatwicfx.model.HashTag;
import de.saxsys.javafx.workshop.yatwicfx.model.Tweet;
import de.saxsys.javafx.workshop.yatwicfx.view.AcceptanceTest;

//import static org.hamcrest.core.IsNot.not;

/**
 * @author stefan.illgen
 * 
 */
public class HashTagTweetViewTest extends AcceptanceTest {

	/**
	 * Click on the 1st item of the {@link ListView} for showing {@link HashTag}
	 * s and verify the item property change in the {@link TableView} for
	 * representing the {@link Tweet}s of the selected {@link HashTag}.
	 */
	@Test
	public void clickOnHashTagTweetViewTest() {
		// // GIVEN
		// ListView<String> hashTagListView = find("#hashTagList");
		// TableView<Tweet> hashTagTweetTable = find("#tweetTable");
		// verifyThat(hashTagTweetTable.itemsProperty().get().size(), is(0));
		// // WHEN
		// click(hashTagListView.getChildrenUnmodifiable().get(0));
		// // THEN
		// for (int i = 0; i < 3; i++) {
		// verifyThat(hashTagTweetTable.itemsProperty().get().get(0).getName(),is("PDFsam"));
		// }
	}

//	/**
//	 * Verify, that a triple click on the user column inside the tweets view
//	 * cause a new window to be opened for the detauls view.
//	 */
//	@Test
//	public void clickOnUserColumnOpensNewWindow() {
//		
//		// GIVEN
//		ListView<String> hashTagListView = find("#hashTagList");
//		TableView<Tweet> hashTagTweetTable = find("#tweetTable");
//		verifyThat(hashTagTweetTable.itemsProperty().get().size(), is(0));
//		
//		// WHEN
//		click();
//		
//		
//		// THEN
//		Scene newWindow = 
//		verifyThat(is());
//	}

}
