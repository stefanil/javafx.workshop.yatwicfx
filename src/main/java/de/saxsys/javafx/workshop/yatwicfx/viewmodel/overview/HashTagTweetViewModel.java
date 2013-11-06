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

	public ListProperty<Tweet> tweetsProperty() {
		return tweets;
	}

}
