package de.saxsys.javafx.workshop.yatwicfx.model;

import java.util.List;

import org.joda.time.DateTime;

import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class Tweet {

	private StringProperty id = new SimpleStringProperty();

	private ObjectProperty<DateTime> createdAt = new SimpleObjectProperty<>();

	private StringProperty text = new SimpleStringProperty();

	private ObjectProperty<User> user = new SimpleObjectProperty<>();

	private ListProperty<HashTag> hashTags = new SimpleListProperty<>(
			FXCollections.<HashTag> observableArrayList());

	public String getId() {
		return id.get();
	}

	public void setId(String id) {
		this.id.set(id);
	}

	public StringProperty idProperty() {
		return id;
	}

	public DateTime getCreatedAt() {
		return createdAt.get();
	}

	public void setCreatedAt(DateTime createdAt) {
		this.createdAt.set(createdAt);
	}

	public ObjectProperty<DateTime> createdAtProperty() {
		return createdAt;
	}

	public String getText() {
		return text.get();
	}

	@JacksonInject("text")
	public void setText(String text) {
		this.text.set(text);
	}

	public StringProperty textProperty() {
		return text;
	}

	public User getUser() {
		return user.get();
	}

	@JsonIgnore
	public void setUser(User user) {
		this.user.set(user);
	}

	public ObjectProperty<User> userProperty() {
		return user;
	}

	public List<HashTag> getHashTags() {
		return hashTags.get();
	}

	public ListProperty<HashTag> hashTagsProperty() {
		return hashTags;
	}

	@Override
	public String toString() {
		return "Tweet [getId()=" + getId() + ", getCreatedAt()="
				+ getCreatedAt() + ", getText()=" + getText() + ", getUser()="
				+ getUser() + ", getHashTags()=" + getHashTags() + "]";
	}

}
