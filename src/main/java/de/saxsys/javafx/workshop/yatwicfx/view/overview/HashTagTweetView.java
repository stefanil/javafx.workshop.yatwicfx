/**
 * 
 */
package de.saxsys.javafx.workshop.yatwicfx.view.overview;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;

import com.google.inject.Inject;

import de.saxsys.javafx.workshop.yatwicfx.model.Tweet;
import de.saxsys.javafx.workshop.yatwicfx.view.overview.detailsview.MainContainerView;
import de.saxsys.javafx.workshop.yatwicfx.viewmodel.TweetVM;
import de.saxsys.javafx.workshop.yatwicfx.viewmodel.detailsview.MainContainerViewModel;
import de.saxsys.javafx.workshop.yatwicfx.viewmodel.overview.HashTagTweetViewModel;
import de.saxsys.jfx.mvvm.base.view.View;
import de.saxsys.jfx.mvvm.viewloader.ViewLoader;
import de.saxsys.jfx.mvvm.viewloader.ViewTuple;

/**
 * @author stefan.illgen
 */
public class HashTagTweetView extends View<HashTagTweetViewModel> {

	// dirty injection
	@Inject
	private ViewLoader viewLoader;

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

		// Cell Factory

		Callback<TableColumn<TweetVM, String>, TableCell<TweetVM, String>> userCellFactory = new Callback<TableColumn<TweetVM, String>, TableCell<TweetVM, String>>() {
			public TextFieldTableCell<TweetVM, String> call(
					TableColumn<TweetVM, String> p) {
				TextFieldTableCell<TweetVM, String> cell = new TextFieldTableCell<TweetVM, String>();

				cell.addEventFilter(MouseEvent.MOUSE_CLICKED,
						new EventHandler<MouseEvent>() {
							@Override
							@SuppressWarnings("unchecked")
							public void handle(MouseEvent event) {
								// :D
								if (event.getClickCount() == 3) {
									System.out.println("triple clicked!");
									TableCell<TweetVM, String> c = (TableCell<TweetVM, String>) event
											.getSource();
									TweetVM tweetVM = (TweetVM) c.getTableRow()
											.getItem();
									openWindow(tweetVM.getUserId());
								}
							}

						});
				return cell;
			}
		};

		tweetTableUserColumn.setCellFactory(userCellFactory);

		// Cell Value Factories

		tweetTableUserColumn
				.setCellValueFactory(new PropertyValueFactory<TweetVM, String>(
						"user"));

		tweetTableTweetColumn
				.setCellValueFactory(new PropertyValueFactory<TweetVM, String>(
						"text"));

		tweetTableCreatedAtColumn
				.setCellValueFactory(new PropertyValueFactory<TweetVM, String>(
						"createdAt"));

		// bind the table views model to tweet model
		tweetTable.itemsProperty().bind(getViewModel().tweetsProperty());

	}

	private void openWindow(String userId) {

		Stage stage = new Stage();
		final ViewTuple<MainContainerViewModel> tuple = viewLoader
				.loadViewTuple(MainContainerView.class);
		// FIXME Alex: There is no way to give the user id to the view in the
		// load view tuple process.
		tuple.getCodeBehind().getViewModel().initialize(userId);
		((MainContainerView) tuple.getCodeBehind()).bindDeferred();

		// Locate View for loaded FXML file
		final Parent control = tuple.getView();
		final Scene scene = new Scene(control);
		stage.setScene(scene);
		stage.show();
	}
}
