/**
 * 
 */
package de.saxsys.javafx.workshop.yatwicfx.view.overview;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import de.saxsys.javafx.workshop.yatwicfx.viewmodel.overview.HashTagTweetViewModel;
import de.saxsys.javafx.workshop.yatwicfx.viewmodel.overview.Tweet4View;
import de.saxsys.jfx.mvvm.base.view.View;

/**
 * @author stefan.illgen
 */
public class HashTagTweetView extends View<HashTagTweetViewModel> {

	@FXML
	private TableView<Tweet4View> tweetTable;

	@FXML
	private TableColumn<Tweet4View, String> tweetTableUserColumn;

	@FXML
	private TableColumn<Tweet4View, String> tweetTableTweetColumn;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		tweetTable.setItems(getViewModel().tweetProperty());

		tweetTableUserColumn
				.setCellValueFactory(new PropertyValueFactory<Tweet4View, String>(
						"name"));
		tweetTableTweetColumn
				.setCellValueFactory(new PropertyValueFactory<Tweet4View, String>(
						"tweet"));
	}

	public void bindSelection(ReadOnlyObjectProperty<String> selectedItemProperty) {

		// bind the table views model to tweet model
		tweetTable.itemsProperty().bind(getViewModel().tweetProperty());

		// on change the selection in hash tag list, reload tweets
		htChangeListener = new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0,
					String oldHashTag, String newHashTag) {
				
				// reload tweets
				if (oldHashTag == null || !oldHashTag.equals(newHashTag)) {
					getViewModel().reloadTweets(newHashTag);
				}
			}		
		};
		listenedProperty = selectedItemProperty;
		selectedItemProperty.addListener(htChangeListener);
	}
	
	// ######################
	
	private static ReadOnlyObjectProperty<String> listenedProperty;
	private static ChangeListener<String> htChangeListener;
	
	public static void removeChangeListener() {
		listenedProperty.removeListener(htChangeListener);
	}

}
