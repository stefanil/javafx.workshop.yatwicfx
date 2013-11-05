package de.saxsys.javafx.workshop.yatwicfx.view.overview;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.ReadOnlyObjectProperty;
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
		hashTagList.itemsProperty().bind(getViewModel().hashTagProperty());		
		
		// set selection model
		hashTagList.getSelectionModel()
				.setSelectionMode(SelectionMode.SINGLE);		
		
		// bind mouse selection
		
		// solution 1: bind selection 2 SelectableItemList
		// FIXME call leeds to: A bound value cannot be set.
//		getViewModel().bindSelectedItemProperty(hashTagList.getSelectionModel().selectedItemProperty());
		
		// solution 2: bind on view layer (see MainContainerView)
	}
	
	public ReadOnlyObjectProperty<String> getSelectedItemProperty() {
		return hashTagList.getSelectionModel().selectedItemProperty();
	}

}
