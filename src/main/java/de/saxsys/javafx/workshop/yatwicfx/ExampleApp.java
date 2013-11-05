package de.saxsys.javafx.workshop.yatwicfx;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyListProperty;
import javafx.beans.property.ReadOnlyListWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Callback;

import com.google.inject.Guice;
import com.google.inject.Injector;

import de.saxsys.javafx.workshop.yatwicfx.model.Repository;
import de.saxsys.javafx.workshop.yatwicfx.model.Tweet;

/**
 * Please do not use this as a blueprint for your application. It exists only for demonstration purposes.
 */
public class ExampleApp extends Application {

    private static Repository repository;

    public static void main(final String[] args) {

        final Injector injector = Guice.createInjector();
        repository = injector.getInstance(Repository.class);
        repository.messageProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> arg0, final String arg1, final String arg2) {
                System.out.println("exc: " + arg2);
            }
        });
        launch(args);
    }

    @Override
    public void start(final Stage stage) throws Exception {

        final RepoService service = new RepoService(false);
        service.start();

        final StackPane root = new StackPane();
        final ListView<Tweet> tweetsList = new ListView<>();
        tweetsList.setCellFactory(new Callback<ListView<Tweet>, ListCell<Tweet>>() {
            @Override
            public ListCell<Tweet> call(final ListView<Tweet> arg0) {
                return new ListCell<Tweet>() {
                    @Override
                    protected void updateItem(final Tweet item, final boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(new Label("no tweet"));
                        } else {
                            setGraphic(new Label(item.getCreatedAt() + ": " + item.getText()));
                        }
                    }
                };
            }
        });
        tweetsList.itemsProperty().bind(service.partialResultsProperty());
        root.getChildren().add(tweetsList);

        final Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.show();

    }

    private class RepoService extends Service<Void> {
        private final ReadOnlyListWrapper<Tweet> partialResults = new ReadOnlyListWrapper<>(
                FXCollections.<Tweet> observableArrayList());
        private final boolean loadFast;

        public RepoService(final boolean loadFast) {
            this.loadFast = loadFast;
        }

        public final ReadOnlyListProperty<Tweet> partialResultsProperty() {
            return partialResults.getReadOnlyProperty();
        }

        @Override
        protected Task<Void> createTask() {
            return new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    repository.tweetsProperty().addListener(new ListChangeListener<Tweet>() {
                        @Override
                        public void onChanged(final javafx.collections.ListChangeListener.Change<? extends Tweet> change) {
                            while (change.next()) {
                                for (final Tweet tweet : change.getAddedSubList()) {
                                    Platform.runLater(new Runnable() {
                                        @Override
                                        public void run() {
                                            partialResults.get().add(tweet);
                                        }
                                    });
                                    if (!loadFast) {
                                        try {
                                            Thread.sleep(100);
                                        } catch (final InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }
                            }
                        }
                    });
                    repository.load(true);
                    return null;
                }
            };
        }
    }
}
