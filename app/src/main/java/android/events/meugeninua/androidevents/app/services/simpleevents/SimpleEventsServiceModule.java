package android.events.meugeninua.androidevents.app.services.simpleevents;

import android.events.meugeninua.androidevents.app.di.scopes.PerService;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import dagger.Module;
import dagger.Provides;

/**
 * @author meugen
 */
@Module
public abstract class SimpleEventsServiceModule {

    @Provides @PerService
    static ScheduledExecutorService provideService() {
        return Executors.newScheduledThreadPool(2);
    }
}
