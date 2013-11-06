/**
 * 
 */
package de.saxsys.javafx.workshop.yatwicfx.view.overview;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.util.Callback;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import de.saxsys.javafx.workshop.yatwicfx.model.Tweet;
import de.saxsys.javafx.workshop.yatwicfx.viewmodel.overview.HashTagTweetViewModel;
import de.saxsys.jfx.mvvm.base.view.View;

/**
 * @author stefan.illgen
 */
public class HashTagTweetView extends View<HashTagTweetViewModel> {

	@FXML
	private TableView<Tweet> tweetTable;

	@FXML
	private TableColumn<Tweet, String> tweetTableUserColumn;

	@FXML
	private TableColumn<Tweet, String> tweetTableTweetColumn;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		tweetTableUserColumn
				.setCellValueFactory(new Callback<CellDataFeatures<Tweet, String>, ObservableValue<String>>() {
					public ObservableValue<String> call(
							CellDataFeatures<Tweet, String> t) {
						return new SimpleStringProperty(t.getValue()
								.userProperty().get().getName());
					}
				});

		tweetTableTweetColumn
				.setCellValueFactory(new Callback<CellDataFeatures<Tweet, String>, ObservableValue<String>>() {
					public ObservableValue<String> call(
							CellDataFeatures<Tweet, String> t) {

//						return new SimpleStringProperty(t.getValue().getText());

						// just for testing
						 DateTime createdAt = t.getValue().getCreatedAt();
						 DateTimeFormatter fmt = DateTimeFormat
						 .forPattern("dd.MM.yyyy");
						 return new
						 SimpleStringProperty(fmt.print(createdAt));
					}
				});

		// bind the table views model to tweet model
		tweetTable.itemsProperty().bind(getViewModel().tweetsProperty());
	}

}
