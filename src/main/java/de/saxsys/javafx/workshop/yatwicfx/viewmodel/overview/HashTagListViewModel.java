package de.saxsys.javafx.workshop.yatwicfx.viewmodel.overview;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyListProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.util.StringConverter;

import com.google.inject.Inject;

import de.saxsys.javafx.workshop.yatwicfx.model.HashTag;
import de.saxsys.javafx.workshop.yatwicfx.model.Repository;
import de.saxsys.jfx.mvvm.base.viewmodel.ViewModel;
import de.saxsys.jfx.mvvm.base.viewmodel.util.itemlist.SelectableItemList;

public class HashTagListViewModel implements ViewModel {

	/*
	 * The SelectableItemList View model.
	 */
	private SelectableItemList<HashTag> selectableItemList;

	@Inject
	public HashTagListViewModel(final Repository repository) {
		
		selectableItemList = new SelectableItemList<HashTag>(
				repository.hashTagsProperty(), new StringConverter<HashTag>() {

					@Override
					public HashTag fromString(String arg0) {
						return repository.getHashTagByText(arg0);
					}

					@Override
					public String toString(HashTag arg0) {
						return "#" + arg0.getText();
					}
				});

	}

	public ReadOnlyListProperty<String> hashTagProperty() {
		return selectableItemList.stringListProperty();
	}

	public void bindSelectedItemProperty(
			ReadOnlyObjectProperty<HashTag> selectedItemProperty) {

		// FIXME call leeds to: A bound value cannot be set.
		// selectableItemList.selectedItemProperty().bind(selectedItemProperty);
	}

	public ObjectProperty<HashTag> getSelectedItemProperty() {
		return selectableItemList.selectedItemProperty();
	}

}
