/**
 * 
 */
package de.saxsys.javafx.workshop.yatwicfx.view.overview;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import de.saxsys.javafx.workshop.yatwicfx.model.Tweet;
import de.saxsys.javafx.workshop.yatwicfx.viewmodel.model.TweetVM;
import de.saxsys.javafx.workshop.yatwicfx.viewmodel.overview.HashTagTweetViewModel;
import de.saxsys.jfx.mvvm.base.view.View;

/**
 * @author stefan.illgen
 */
public class HashTagTweetView extends View<HashTagTweetViewModel> {

	@FXML
	private TableView<TweetVM> tweetTable;

	@FXML
	private TableColumn<TweetVM, String> tweetTableUserColumn;

	@FXML
	private TableColumn<TweetVM, String> tweetTableTweetColumn;

	@FXML
	private TableColumn<TweetVM, String> tweetTableCreatedAtColumn;

	/**
	 * <b>Known Encapsulation Problem:</b> The model class {@link Tweet} should
	 * not be used inside the view layer to preserve variability of the model
	 * layer.
	 * 
	 * @return
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		tweetTableUserColumn
		// .setCellValueFactory(new Callback<CellDataFeatures<Tweet, String>,
		// ObservableValue<String>>() {
		// public ObservableValue<String> call(
		// CellDataFeatures<Tweet, String> t) {
		// return new SimpleStringProperty(t.getValue()
		// .userProperty().get().getName());
		// }
		// });
				.setCellValueFactory(new PropertyValueFactory<TweetVM, String>("user"));

		tweetTableTweetColumn
//				.setCellValueFactory(new Callback<CellDataFeatures<Tweet, String>, ObservableValue<String>>() {
//					public ObservableValue<String> call(
//							CellDataFeatures<Tweet, String> t) {
//						return new SimpleStringProperty(t.getValue().getText());
//					}
//				});
				.setCellValueFactory(new PropertyValueFactory<TweetVM, String>("text"));

		tweetTableCreatedAtColumn
//				.setCellValueFactory(new Callback<CellDataFeatures<Tweet, String>, ObservableValue<String>>() {
//					public ObservableValue<String> call(
//							CellDataFeatures<Tweet, String> t) {
//						DateTime createdAt = t.getValue().getCreatedAt();
//						DateTimeFormatter fmt = DateTimeFormat
//								.forPattern("dd.MM.yyyy");
//						return new SimpleStringProperty(fmt.print(createdAt));
//					}
//				});
				.setCellValueFactory(new PropertyValueFactory<TweetVM, String>("createdAt"));

		// bind the table views model to tweet model
		tweetTable.itemsProperty().bind(getViewModel().tweetsProperty());
	}

}
