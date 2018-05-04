package android.events.meugeninua.androidevents.app.di.modules;

import android.events.meugeninua.androidevents.app.di.scopes.PerActivity;
import android.events.meugeninua.androidevents.app.di.scopes.PerService;
import android.events.meugeninua.androidevents.app.services.simpleevents.SimpleEventsService;
import android.events.meugeninua.androidevents.app.services.simpleevents.SimpleEventsServiceModule;
import android.events.meugeninua.androidevents.ui.activities.main.MainActivity;
import android.events.meugeninua.androidevents.ui.activities.main.MainActivityModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * @author meugen
 */
@Module
public abstract class ContributorsModule {

    @ContributesAndroidInjector(modules = MainActivityModule.class)
    @PerActivity
    abstract MainActivity contributeMainActivity();

    @ContributesAndroidInjector(modules = SimpleEventsServiceModule.class)
    @PerService
    abstract SimpleEventsService contributeSimpleEventsService();
}
