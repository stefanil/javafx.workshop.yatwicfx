package de.saxsys.javafx.workshop.yatwicfx.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;

public class HashTag {

	private StringProperty text = new SimpleStringProperty();

	@JsonIgnore
	private ListProperty<Tweet> tweets = new SimpleListProperty<>(
			FXCollections.<Tweet> observableArrayList());

	public String getText() {
		return text.get();
	}

	public void setText(String text) {
		this.text.set(text);
	}

	public StringProperty textProperty() {
		return text;
	}
	
	public List<Tweet> getTweets() {
		return tweets.get();
	}
	
	public ListProperty<Tweet> tweetsProperty() {
		return tweets;
	}

	@Override
	public String toString() {
		return "HashTag [getText()=" + getText() + ", getTweets()="
				+ getTweets().size() + "]";
	}

}
