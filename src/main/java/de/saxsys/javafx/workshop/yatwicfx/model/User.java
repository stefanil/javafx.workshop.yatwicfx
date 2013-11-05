package de.saxsys.javafx.workshop.yatwicfx.model;

import java.util.List;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class User {

	private StringProperty id = new SimpleStringProperty();

	private StringProperty name = new SimpleStringProperty();

	private StringProperty description = new SimpleStringProperty();

	private IntegerProperty followersCount = new SimpleIntegerProperty();

	private IntegerProperty friendsCount = new SimpleIntegerProperty();

	@JsonIgnore
	private ListProperty<Tweet> tweets = new SimpleListProperty<>(
			FXCollections.<Tweet> observableArrayList());

	public String getId() {
		return id.get();
	}

	public void setId(String id) {
		this.id.set(id);
	}

	public StringProperty idProperty() {
		return id;
	}

	public String getName() {
		return name.get();
	}

	public void setName(String name) {
		this.name.set(name);
	}

	public StringProperty nameProperty() {
		return name;
	}

	public String getDescription() {
		return description.get();
	}

	public void setDescription(String description) {
		this.description.set(description);
	}

	public StringProperty descriptionProperty() {
		return description;
	}

	public List<Tweet> getTweets() {
		return tweets.get();
	}

	public ListProperty<Tweet> tweetsProperty() {
		return tweets;
	}

	public int getFollowersCount() {
		return followersCount.get();
	}

	public void setFollowersCount(int followersCount) {
		this.followersCount.set(followersCount);
	}

	public IntegerProperty followersCountProperty() {
		return followersCount;
	}

	public int getFriendsCount() {
		return friendsCount.get();
	}

	public void setFriendsCount(int friendsCount) {
		this.friendsCount.set(friendsCount);
	}

	public IntegerProperty friendsCountProperty() {
		return friendsCount;
	}

	@Override
	public String toString() {
		return "User [getId()=" + getId() + ", getName()=" + getName()
				+ ", getDescription()=" + getDescription() + ", getTweets()="
				+ getTweets().size() + ", getFollowersCount()="
				+ getFollowersCount() + ", getFriendsCount()="
				+ getFriendsCount() + "]";
	}

}
