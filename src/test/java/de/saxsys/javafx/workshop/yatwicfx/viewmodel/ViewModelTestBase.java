package de.saxsys.javafx.workshop.yatwicfx.viewmodel;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import de.saxsys.javafx.workshop.yatwicfx.model.HashTag;
import de.saxsys.javafx.workshop.yatwicfx.model.Repository;
import de.saxsys.javafx.workshop.yatwicfx.model.Tweet;
import de.saxsys.javafx.workshop.yatwicfx.model.User;

public class ViewModelTestBase {

	protected Repository repo;

	public ViewModelTestBase() {
		super();
	}

	protected void createModel() {

		repo = new Repository();

		List<HashTag> hts = new ArrayList<HashTag>() {

			private static final long serialVersionUID = 390400182329029458L;

			{
				add(new HashTag() {
					{
						setText("Java8");
						getTweets().add(new Tweet() {
							{
								setCreatedAt(new DateTime(1382916379000L));
								setText("New blog post: Context Menu for Java 8 (Revisited) - http://t.co/ZnEfuYhij3 … #Java8 #JMetro #JavaFX");
								setUser(new User() {
									{
										setName("Pedro Duque Vieira");
										setDescription("");
										setFollowersCount(0);
										setFriendsCount(0);
									}
								});
							}
						});
					}
				});
				add(new HashTag() {
					{
						setText("JMetro");
						getTweets().add(new Tweet() {
							{
								setCreatedAt(new DateTime(1482916379000L));
								setText("Blahhhhh #JMetro");
								setUser(new User() {
									{
										setName("Padre");
										setDescription("");
										setFollowersCount(0);
										setFriendsCount(0);
									}
								});
							}
						});
					}
				});
				add(new HashTag() {
					{
						setText("JavaFX");
						getTweets().add(new Tweet() {
							{
								setCreatedAt(new DateTime(1582916379000L));
								setText("Blahhhhh #JavaFX");
								setUser(new User() {
									{
										setName("Pjotr");
										setDescription("");
										setFollowersCount(0);
										setFriendsCount(0);
									}
								});
							}
						});
					}
				});
			}
		};
		repo.getHashTags().addAll(hts);
	}

}