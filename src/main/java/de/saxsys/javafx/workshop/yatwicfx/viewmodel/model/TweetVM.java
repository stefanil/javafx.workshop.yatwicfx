/**
 * 
 */
package de.saxsys.javafx.workshop.yatwicfx.viewmodel.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import org.joda.time.DateTime;

import de.saxsys.javafx.workshop.yatwicfx.model.Tweet;

/**
 * @author stefan.illgen
 *
 */
public class TweetVM {
	
	StringProperty user = new SimpleStringProperty();
	StringProperty text = new SimpleStringProperty();
	ObjectProperty<DateTime> createdAt = new SimpleObjectProperty<>();

	public TweetVM(Tweet tweet) {
		user.bind(tweet.getUser().nameProperty());
		this.text.bind(tweet.textProperty());
		createdAt.bind(tweet.createdAtProperty());
	}

	public StringProperty userProperty() {
		return user;
	}

	public StringProperty textProperty() {
		return text;
	}

	public ObjectProperty<DateTime> createdAtProperty() {
		return createdAt;
	}

	public String getUser() {
		return user.get();
	}

	public String getText() {
		return text.get();
	}

	public DateTime getCreatedAt() {
		return createdAt.get();
	}

}
