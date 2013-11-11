/**
 * 
 */
package de.saxsys.javafx.workshop.yatwicfx.view.overview.detailsview;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.binding.StringBinding;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import de.saxsys.javafx.workshop.yatwicfx.viewmodel.overview.detailsview.UserVM;
import de.saxsys.jfx.mvvm.base.view.View;

/**
 * @author stefan.illgen
 */
public class UserInfoView extends View<UserVM> {

	@FXML
	private Label description;

	@FXML
	private Label followersCount;

	@FXML
	private Label friendsCount;

	@FXML
	private Label name;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// bind deferred
	}
	
	public void bindDeferred() {
		
		description.textProperty().bind(getViewModel().descriptionProperty());
		StringBinding foC = new StringBinding() {
			{
				super.bind(getViewModel().followersCountProperty());
			}
			@Override
			protected String computeValue() {
				return String.valueOf(getViewModel().followersCountProperty()
						.get());
			}
		};
		followersCount.textProperty().bind(foC);
		StringBinding frC = new StringBinding() {
			{
				super.bind(getViewModel().friendsCountProperty());
			}
			@Override
			protected String computeValue() {
				return String.valueOf(getViewModel().friendsCountProperty()
						.get());
			}
		};
		friendsCount.textProperty().bind(frC);
		name.textProperty().bind(getViewModel().nameProperty());
	}

}
