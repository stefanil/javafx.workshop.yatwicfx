package de.saxsys.javafx.workshop.yatwicfx.viewmodel.overview;

import java.util.ArrayList;

import javafx.beans.binding.ListBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ReadOnlyListProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;

import com.google.inject.Inject;

import de.saxsys.javafx.workshop.yatwicfx.model.HashTag;
import de.saxsys.javafx.workshop.yatwicfx.model.Repository;
import de.saxsys.javafx.workshop.yatwicfx.model.Tweet;
import de.saxsys.javafx.workshop.yatwicfx.viewmodel.model.TweetVM;
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
	private ListProperty<TweetVM> tweets = new SimpleListProperty<TweetVM>(
			javafx.collections.FXCollections
					.observableList(new ArrayList<TweetVM>()));

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
			public void changed(
					ObservableValue<? extends Number> observableValue,
					Number oldValue, Number newValue) {

				if (newValue.intValue() >= 0) {

					// manually select it on SelectableItemList
					hashTags.select(newValue.intValue());

					// then rebind:
					ListBinding<TweetVM> lb = new ListBinding<TweetVM>() {
						{
							super.bind(hashTags.selectedItemProperty().get()
									.tweetsProperty());
						}

						/*
						 * Is only called initially and when get() is called on
						 * an invalid binding.
						 */
						@Override
						protected ObservableList<TweetVM> computeValue() {

							ListProperty<TweetVM> result = new SimpleListProperty<TweetVM>(
									javafx.collections.FXCollections
											.observableList(new ArrayList<TweetVM>()));

							// assemble TweetVM
							ObservableList<Tweet> newTweetsValue = hashTags
									.selectedItemProperty().get()
									.tweetsProperty().get();

							for (Tweet tweet : newTweetsValue) {
								ObservableList<TweetVM> l = result.get();
								l.add(new TweetVM(tweet));
							}

							return result;
						}
					};

					tweets.bind(lb);
				}
			}
		});
	}

	public ReadOnlyListProperty<String> hashTagsProperty() {
		return hashTags.stringListProperty();
	}

	/**
	 * <b>Known Encapsulation Problem:</b> The model class {@link Tweet} should
	 * not be made publicly visible to the view layer to preserve variability of
	 * the model layer. But its so elegant ;)
	 * 
	 * @return
	 */
	public ReadOnlyListProperty<TweetVM> tweetsProperty() {
		return tweets;
	}

	public IntegerProperty selectedHashTagIndexProperty() {
		return selectedHashTagIndex;
	}

}
