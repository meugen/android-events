package android.events.meugeninua.androidevents.ui.activities.main;

import android.events.meugeninua.androidevents.app.di.scopes.PerActivity;
import android.events.meugeninua.androidevents.ui.activities.base.BaseActivity;
import android.events.meugeninua.androidevents.ui.activities.base.BaseActivityModule;

import dagger.Binds;
import dagger.Module;

/**
 * @author meugen
 */
@Module(includes = BaseActivityModule.class)
public abstract class MainActivityModule {

    @Binds @PerActivity
    abstract BaseActivity bindActivity(final MainActivity activity);
}
