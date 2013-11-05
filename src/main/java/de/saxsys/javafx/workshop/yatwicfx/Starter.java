package de.saxsys.javafx.workshop.yatwicfx;

import java.util.List;

import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import com.google.inject.Inject;
import com.google.inject.Module;

import de.saxsys.javafx.workshop.yatwicfx.model.Repository;
import de.saxsys.javafx.workshop.yatwicfx.view.main.MainContainerView;
import de.saxsys.javafx.workshop.yatwicfx.view.overview.HashTagTweetView;
import de.saxsys.javafx.workshop.yatwicfx.view.overview.HashTagWeeklyStatisticsView;
import de.saxsys.javafx.workshop.yatwicfx.viewmodel.overview.HashTagTweetViewModel;
import de.saxsys.javafx.workshop.yatwicfx.viewmodel.overview.HashTagWeeklyStatisticsViewModel;
import de.saxsys.jfx.mvvm.di.guice.MvvmGuiceApplication;
import de.saxsys.jfx.mvvm.viewloader.ViewLoader;
import de.saxsys.jfx.mvvm.viewloader.ViewTuple;

/**
 * Entry point of the application.
 * 
 * @author sialcasa
 * 
 */
public class Starter extends MvvmGuiceApplication {
	
	@Inject
	private ViewLoader viewLoader;

	@Inject
	private Repository repository;
	
	public static void main(final String[] args) {
        launch(args);
	}

	@Override
	public void start(final Stage stage) throws Exception {
		
		repository.load(true);
		
		final ViewTuple tuple = viewLoader
				.loadViewTuple(MainContainerView.class);
		
		// Locate View for loaded FXML file
		final Parent view = tuple.getView();

		final Scene scene = new Scene(view);
		stage.setScene(scene);
		
		// FIXME: dirty singleton change listener remove
		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {			
			@Override
			public void handle(WindowEvent arg0) {
				HashTagTweetView.removeChangeListener();
				HashTagTweetViewModel.removeChangeListener();
				HashTagWeeklyStatisticsView.removeChangeListener();
				HashTagWeeklyStatisticsViewModel.removeChangeListener();
			}
		});
		
		stage.show();
	}

	@Override
	public void initGuiceModules(List<Module> modules) throws Exception {

	}

}
