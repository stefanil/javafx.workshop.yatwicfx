package de.saxsys.javafx.workshop.yatwicfx.viewmodel.overview;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.saxsys.javafx.workshop.yatwicfx.model.Repository;

public class HashTagTweetViewModelTest {

	private Repository repo;
	private HashTagTweetViewModel httvm;

	@Before
	public void setup() {
		// do not use google guice
		repo = new Repository();
		repo.load(false);
		httvm = new HashTagTweetViewModel();
	}
	
	/**
	 * Test for User and Text of Tweet.
	 * 
	 */
	@Test
	public void showAllHashTagTweets() {
		Assert.assertEquals(httvm.tweetsProperty().get(0), "Montse García");
		Assert.assertEquals(httvm.tweetsProperty().get(1), "Suricattus");
		Assert.assertEquals(httvm.tweetsProperty().get(2), "Java");
	}
	
	@After
	public void tearDown() {
		repo = null;
	}
	
}
