package de.saxsys.javafx.workshop.yatwicfx.view.overview;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import de.saxsys.javafx.workshop.yatwicfx.viewmodel.overview.HashTagListViewModel;
import de.saxsys.jfx.mvvm.base.view.View;

public class HashTagListView extends View<HashTagListViewModel> {

	@FXML
	private ListView<String> hashTagList;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		// bind view model content to view list
		hashTagList.itemsProperty().bind(getViewModel().hashTagsProperty());

		// set selection model
		hashTagList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

		// bind mouse selection of ListView 2 artificial selectedHashTagIndex
		getViewModel().selectedHashTagIndexProperty().bind(
				hashTagList.getSelectionModel().selectedIndexProperty());
	}

}
