package de.saxsys.javafx.workshop.yatwicfx.viewmodel;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Before;

import de.saxsys.javafx.workshop.yatwicfx.model.HashTag;
import de.saxsys.javafx.workshop.yatwicfx.model.Repository;
import de.saxsys.javafx.workshop.yatwicfx.model.Tweet;
import de.saxsys.javafx.workshop.yatwicfx.model.User;

public class ViewModelTestBase {

	protected Repository repo;

	/**
	 * Creates the model.
	 */
	@Before
	public void setUp() {
		createModel();
	}

	/**
	 * Leave the model to the garbage collector.
	 */
	@After
	public void tearDown() {
		repo = null;
	}

	private void createModel() {

		repo = new Repository();

		final List<User> users = new ArrayList<User>() {
			private static final long serialVersionUID = 8616411392712918060L;
			{
				add(new User() {
					{
						setId("106156594");
						setName("Pedro Duque Vieira");
						setDescription("Freelance Software Engineer who loves front-end/user experience development");
						setFollowersCount(3);
						setFriendsCount(1);
					}
				});
				add(new User() {
					{
						setName("Padre");
						setDescription("");
						setFollowersCount(0);
						setFriendsCount(0);
					}
				});
				add(new User() {
					{
						setName("Pjotr");
						setDescription("");
						setFollowersCount(0);
						setFriendsCount(0);
					}
				});
			}
		};

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
								setUser(users.get(0));
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
								setUser(users.get(1));
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
								setUser(users.get(2));
							}
						});
					}
				});
			}
		};
		repo.getHashTags().addAll(hts);
		repo.getUsers().addAll(users);
	}

}