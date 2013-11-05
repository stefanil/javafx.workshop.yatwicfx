/**
 * 
 */
package de.saxsys.javafx.workshop.yatwicfx.viewmodel.overview;

/**
 * @author stefan.illgen
 *
 */
public class Tweet4View {

	private String name;
	private String tweet;

	public Tweet4View(String name, String tweet) {
		this.name = name;
		this.tweet = tweet;
	}

	public String getName() {
		return name;
	}

	public String getTweet() {
		return tweet;
	}
}
