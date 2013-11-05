/**
 * 
 */
package de.saxsys.javafx.workshop.yatwicfx.view;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javafx.scene.Parent;

import org.junit.BeforeClass;
import org.loadui.testfx.GuiTest;

import com.cathive.fx.guice.fxml.FXMLLoadingModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;

import de.saxsys.javafx.workshop.yatwicfx.model.Repository;
import de.saxsys.javafx.workshop.yatwicfx.view.main.MainContainerView;
import de.saxsys.jfx.mvvm.di.guice.modules.FXMLLoaderWrapperModule;
import de.saxsys.jfx.mvvm.di.guice.modules.InjectionWrapperModule;
import de.saxsys.jfx.mvvm.di.guice.modules.NotificationCenterModule;
import de.saxsys.jfx.mvvm.viewloader.ViewLoader;

/**
 * @author stefan.illgen
 *
 */
public class AcceptanceTest extends GuiTest {

	/**
	 * Init Guice for the DI and display the {@link MainContainerView} in a stage.
	 * 
	 * @throws IOException
	 *             Guice setup or loading FXML failed.
	 */
	@BeforeClass
	public static void setup() throws IOException {
		
		final Set<Module> modules = new HashSet<>();
		modules.add(new NotificationCenterModule());
		modules.add(new FXMLLoaderWrapperModule());
		modules.add(new InjectionWrapperModule());
		modules.add(new FXMLLoadingModule());
		
		final Injector injector = Guice.createInjector(modules);		
		final ViewLoader viewLoader = injector.getInstance(ViewLoader.class);
		
		// load the repository before view tuple !!!
		Repository repo = injector.getInstance(Repository.class);
		repo.load(false);
		
		final Parent load = viewLoader.loadViewTuple(MainContainerView.class)
				.getView();
		
		showNodeInStage(load);
	}
	
}
