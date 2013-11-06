package de.saxsys.javafx.workshop.yatwicfx.viewmodel.overview;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ReadOnlyListProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import com.google.inject.Inject;

import de.saxsys.javafx.workshop.yatwicfx.model.HashTag;
import de.saxsys.javafx.workshop.yatwicfx.model.Repository;
import de.saxsys.javafx.workshop.yatwicfx.model.Tweet;
import de.saxsys.jfx.mvvm.base.viewmodel.ViewModel;
import de.saxsys.jfx.mvvm.base.viewmodel.util.itemlist.ModelToStringMapper;
import de.saxsys.jfx.mvvm.base.viewmodel.util.itemlist.SelectableItemList;

public class HashTagListViewModel implements ViewModel {

	/*
	 * The SelectableItemList View model.
	 */
	private SelectableItemList<HashTag> hashTags;

	/*
	 * List property used for model selection bindings.
	 */
	private ListProperty<Tweet> tweets = new SimpleListProperty<Tweet>();

	/*
	 * Integer property which can be bound to the ListView.
	 */
	private IntegerProperty selectedHashTagIndex = new SimpleIntegerProperty();

	@Inject
	public HashTagListViewModel(final Repository repository) {

		hashTags = new SelectableItemList<HashTag>(
				repository.hashTagsProperty(),
				new ModelToStringMapper<HashTag>() {
					@Override
					public String toString(HashTag ht) {
						return "#" + ht.getText();
					}
				});

		selectedHashTagIndex.addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> ov,
					Number old_val, Number new_val) {
				if (new_val.intValue() >= 0) {
					hashTags.select(new_val.intValue());
					// does it has to be destroyed?
//					tweets = new SimpleListProperty<Tweet>();
					// rebind
					tweets.bind(hashTags.selectedItemProperty().get()
							.tweetsProperty());
				}
			}
		});
	}

	public ReadOnlyListProperty<String> hashTagsProperty() {
		return hashTags.stringListProperty();
	}
	
	public ReadOnlyListProperty<Tweet> tweetsProperty() {
		return tweets;
	}
	
	public IntegerProperty selectedHashTagIndexProperty() {
		return selectedHashTagIndex;
	}

}
