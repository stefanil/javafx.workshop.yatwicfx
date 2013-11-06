package de.saxsys.javafx.workshop.yatwicfx.viewmodel.overview;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.saxsys.javafx.workshop.yatwicfx.model.Repository;

public class HashTagListViewModelTest {

	private Repository repo;
	private HashTagListViewModel htlvm;

	@Before
	public void setup() {
		// do not use google guice
		repo = new Repository();
		repo.load(false);
		htlvm = new HashTagListViewModel(repo);
	}

	@Test
	public void showAllHashTags() {
		Assert.assertEquals(htlvm.hashTagsProperty().get(0), "#Java8");
		Assert.assertEquals(htlvm.hashTagsProperty().get(1), "#JMetro");
		Assert.assertEquals(htlvm.hashTagsProperty().get(2), "#JavaFX");
	}

	@After
	public void tearDown() {
		repo = null;
	}

}
