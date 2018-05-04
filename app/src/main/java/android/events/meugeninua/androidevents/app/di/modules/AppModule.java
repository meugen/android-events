package android.events.meugeninua.androidevents.app.di.modules;

import android.content.Context;
import android.events.meugeninua.androidevents.app.EventsApp;
import android.events.meugeninua.androidevents.app.di.qualifiers.AppContext;
import android.events.meugeninua.androidevents.app.di.scopes.PerApplication;

import dagger.Binds;
import dagger.Module;

/**
 * @author meugen
 */
@Module
public abstract class AppModule {

    @Binds @AppContext @PerApplication
    abstract Context bindAppContext(final EventsApp app);
}
