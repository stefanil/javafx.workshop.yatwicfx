package de.saxsys.javafx.workshop.yatwicfx.viewmodel.overview;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import de.saxsys.javafx.workshop.yatwicfx.model.Tweet;
import de.saxsys.jfx.mvvm.base.viewmodel.ViewModel;

public class HashTagTweetViewModel implements ViewModel {

	/*
	 * List property used for model selection bindings.
	 */
	private ListProperty<Tweet> tweets = new SimpleListProperty<Tweet>(
			FXCollections.<Tweet> observableArrayList());

	/**
	 * <b>Known Encapsulation Problem:</b> The model class {@link Tweet} should
	 * not be made publicly visible to the view layer to preserve variability of
	 * the model layer.
	 * 
	 * @return
	 */
	public ListProperty<Tweet> tweetsProperty() {
		return tweets;
	}

}
