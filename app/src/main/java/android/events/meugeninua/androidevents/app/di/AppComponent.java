package android.events.meugeninua.androidevents.app.di;

import android.events.meugeninua.androidevents.app.EventsApp;
import android.events.meugeninua.androidevents.app.di.modules.AppModule;
import android.events.meugeninua.androidevents.app.di.modules.ContributorsModule;
import android.events.meugeninua.androidevents.app.di.scopes.PerApplication;

import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * @author meugen
 */
@Component(modules = {AndroidSupportInjectionModule.class,
        ContributorsModule.class, AppModule.class})
@PerApplication
public interface AppComponent extends AndroidInjector<EventsApp> {

    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<EventsApp> {}
}
