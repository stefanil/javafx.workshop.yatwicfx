/**
 * 
 */
package de.saxsys.javafx.workshop.yatwicfx.view.overview.detailsview;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import de.saxsys.javafx.workshop.yatwicfx.viewmodel.detailsview.UserTweetViewModel;
import de.saxsys.jfx.mvvm.base.view.View;

/**
 * @author stefan.illgen
 *
 */
public class UserTweetView extends View<UserTweetViewModel> {

	@FXML
    private ListView<String> userTweetList;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
				
	}
	
	public void bindDeferred() {
		userTweetList.itemsProperty().bind(getViewModel().tweetsProperty());
	}

}
