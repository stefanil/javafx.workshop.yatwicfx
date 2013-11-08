/**
 * 
 */
package de.saxsys.javafx.workshop.yatwicfx.viewmodel;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.StringPropertyBase;

import org.joda.time.DateTime;

import de.saxsys.javafx.workshop.yatwicfx.model.Tweet;

/**
 * @author stefan.illgen
 *
 */
public class TweetVM {
	
	StringPropertyBase userId = new SimpleStringProperty();
	StringProperty userName = new SimpleStringProperty();
	StringProperty text = new SimpleStringProperty();
	ObjectProperty<DateTime> createdAt = new SimpleObjectProperty<>();

	public TweetVM(Tweet tweet) {
		userId.bind(tweet.getUser().idProperty());
		userName.bind(tweet.getUser().nameProperty());
		this.text.bind(tweet.textProperty());
		createdAt.bind(tweet.createdAtProperty());
	}

	public StringProperty userProperty() {
		return userName;
	}

	public StringProperty textProperty() {
		return text;
	}

	public ObjectProperty<DateTime> createdAtProperty() {
		return createdAt;
	}

	public String getUserName() {
		return userName.get();
	}

	public String getText() {
		return text.get();
	}

	public DateTime getCreatedAt() {
		return createdAt.get();
	}
	
	public String getUserId() {
		return userId.get();
	}

}
