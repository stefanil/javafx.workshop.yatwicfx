/**
 * 
 */
package de.saxsys.javafx.workshop.yatwicfx.viewmodel.overview.detailsview;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

import com.google.inject.Inject;

import de.saxsys.javafx.workshop.yatwicfx.model.Repository;
import de.saxsys.javafx.workshop.yatwicfx.model.User;
import de.saxsys.jfx.mvvm.base.viewmodel.ViewModel;

/**
 * @author stefan.illgen
 * 
 */
public class UserVM implements ViewModel {

	private StringProperty name;
	private StringProperty description;
	private IntegerProperty followersCount;
	private IntegerProperty friendsCount;
	private Repository repository;

	@Inject
	public UserVM(Repository repository) {
		this.repository = repository;
	}

	public void initialize(String userId) {
		User user = repository.getUserById(userId);
		name = user.nameProperty();
		description = user.descriptionProperty();
		followersCount = user.followersCountProperty();
		friendsCount = user.friendsCountProperty();
	}

	public StringProperty nameProperty() {
		return name;
	}

	public StringProperty descriptionProperty() {
		return description;
	}

	public IntegerProperty followersCountProperty() {
		return followersCount;
	}

	public IntegerProperty friendsCountProperty() {
		return friendsCount;
	}

	public String getName() {
		return name.get();
	}

	public String getDescription() {
		return description.get();
	}

	public Integer getFollowersCount() {
		return followersCount.get();
	}

	public Integer getFriendsCount() {
		return friendsCount.get();
	}

}
