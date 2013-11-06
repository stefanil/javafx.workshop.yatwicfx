package de.saxsys.javafx.workshop.yatwicfx.model;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Queue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.inject.Singleton;

/**
 * Contains all entities for yatwicFX. All other references, e.g., from Tweet to
 * User are referencing entities which are defined here in the
 * {@link Repository}.
 */
@Singleton
public class Repository {

	private final ListProperty<Tweet> tweets = new SimpleListProperty<>(
			FXCollections.<Tweet> observableArrayList());

	private final ListProperty<User> users = new SimpleListProperty<>(
			FXCollections.<User> observableArrayList());

	private final ListProperty<HashTag> hashTags = new SimpleListProperty<>(
			FXCollections.<HashTag> observableArrayList());

	private final StringProperty messageProperty = new SimpleStringProperty();

	public List<Tweet> getTweets() {
		return tweets.get();
	}

	public ListProperty<Tweet> tweetsProperty() {
		return tweets;
	}

	public Tweet getTweetById(final String id) {
		return Iterables.find(getTweets(), new Predicate<Tweet>() {
			@Override
			public boolean apply(final Tweet input) {
				return input.getId().equals(id);
			}
		}, null);
	}

	public List<User> getUsers() {
		return users.get();
	}

	public ListProperty<User> usersProperty() {
		return users;
	}

	public User getUserById(final String userId) {
		return Iterables.find(getUsers(), new Predicate<User>() {
			@Override
			public boolean apply(final User input) {
				return input.getId().equals(userId);
			}
		}, null);
	}

	public List<HashTag> getHashTags() {
		return hashTags.get();
	}

	public ListProperty<HashTag> hashTagsProperty() {
		return hashTags;
	}

	public HashTag getHashTagByText(final String hashTagText) {
		return Iterables.find(getHashTags(), new Predicate<HashTag>() {
			@Override
			public boolean apply(final HashTag input) {
				return input.getText().equalsIgnoreCase(hashTagText);
			}
		}, null);
	}

	/**
	 * To indicate errors while loading data.
	 * 
	 * @return the {@link StringProperty} that can be used to display errors
	 *         that occurred while loading
	 */
	public StringProperty messageProperty() {
		return messageProperty;
	}

	/**
	 * Loads a given set of Tweets, Users and HashTags into the repository. This
	 * may take a while.
	 * 
	 * @param addTweetsOverTime
	 *            when <code>true</code>, not all Tweets are loaded at startup,
	 *            but are added periodically added to the {@link Repository}
	 */
	public void load(final boolean addTweetsOverTime) {

		final Queue<Tweet> tweetsLater = new LinkedList<>();

		final ObjectMapper mapper = new ObjectMapper();
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		try {
			final URL tweets = Repository.class.getResource("/tweets.json");
			final byte[] encoded = Files
					.readAllBytes(Paths.get(tweets.toURI()));
			final String json = Charset.defaultCharset()
					.decode(ByteBuffer.wrap(encoded)).toString();

			final JsonNode rootNode = mapper.readTree(json);
			final ArrayNode statuses = (ArrayNode) rootNode.get("root");
			final Iterator<JsonNode> statusesIterator = statuses.iterator();

			int day = 0;
			while (statusesIterator.hasNext()) {
				final JsonNode status = statusesIterator.next();
				final ArrayNode jsonTweets = (ArrayNode) status.get("statuses");
				final Iterator<JsonNode> iterator = jsonTweets.iterator();

				while (iterator.hasNext()) {
					final Tweet tweet = extractTweet(mapper, iterator);

					if (addTweetsOverTime && day < 3) {
						tweetsLater.offer(tweet);
					} else {
						insertTweet(tweet);
					}
				}
				day++;
				if (day > 6) {
					day = 0;
				}
			}

			if (addTweetsOverTime) {
				addNewTweets(tweetsLater);
			}

		} catch (IOException | URISyntaxException e) {
			messageProperty.set(Arrays.toString(e.getStackTrace())
					+ e.getMessage());
		}

	}

	private Tweet extractTweet(final ObjectMapper mapper,
			final Iterator<JsonNode> iterator) throws IOException,
			JsonParseException, JsonMappingException, JsonProcessingException {

		final Tweet tweet = new Tweet();
		final JsonNode jsonTweet = iterator.next();
		tweet.setId("" + jsonTweet.get("id").asInt());
		tweet.setCreatedAt(DateTime.parse(jsonTweet.get("created_at").asText(),
				getDateTimeFormatter()));
		tweet.setText(jsonTweet.get("text").asText());
		final ArrayNode hashTagsJson = (ArrayNode) jsonTweet.get("entities")
				.get("hashtags");
		final Iterator<JsonNode> hashTagsIterator = hashTagsJson.iterator();

		extractHashTags(tweet, hashTagsIterator);

		final User foundUser = extractUser(mapper, jsonTweet);
		tweet.setUser(foundUser);

		return tweet;
	}

	private void extractHashTags(final Tweet tweet,
			final Iterator<JsonNode> hashTagsIterator) {
		while (hashTagsIterator.hasNext()) {
			final JsonNode hashTagJson = hashTagsIterator.next();
			final String hashTagText = hashTagJson.get("text").asText();
			HashTag foundHashTag = getHashTagByText(hashTagText);
			if (foundHashTag == null) {
				foundHashTag = new HashTag();
				foundHashTag.setText(hashTagText);
				getHashTags().add(foundHashTag);
			}
			tweet.getHashTags().add(foundHashTag);
		}
	}

	private User extractUser(final ObjectMapper mapper, final JsonNode jsonTweet)
			throws IOException, JsonParseException, JsonMappingException,
			JsonProcessingException {
		final int userId = jsonTweet.get("user").get("id").asInt();
		User foundUser = getUserById("" + userId);
		if (foundUser == null) {
			foundUser = mapper.readValue(
					mapper.writeValueAsString(jsonTweet.get("user")),
					User.class);
			getUsers().add(foundUser);
		}
		return foundUser;
	}

	private void addNewTweets(final Queue<Tweet> tweetsLater) {
		final ScheduledExecutorService scheduledExecutor = Executors
				.newSingleThreadScheduledExecutor();
		scheduledExecutor.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				final Tweet tweet = tweetsLater.poll();
				if (tweet != null) {
					insertTweet(tweet);
					System.out.println("added tweet: " + tweet);
				}
			}
		}, 20, 3, TimeUnit.SECONDS);
	}

	private void insertTweet(final Tweet tweet) {
		getTweets().add(tweet);
		// set back references
		tweet.getUser().getTweets().add(tweet);
		for (final HashTag hashTag : tweet.getHashTags()) {
			hashTag.getTweets().add(tweet);
		}
	}

	private DateTimeFormatter getDateTimeFormatter() {
		return new DateTimeFormatterBuilder().appendDayOfWeekShortText()
				.appendLiteral(" ").appendMonthOfYearShortText()
				.appendLiteral(" ").appendDayOfMonth(2).appendLiteral(" ")
				.appendPattern("HH:mm:ss").appendLiteral(" ")
				.appendTimeZoneOffset(null, false, 4, 4).appendLiteral(" ")
				.appendYear(4, 4).toFormatter().withLocale(Locale.US);
	}
	
}
