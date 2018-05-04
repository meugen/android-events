package android.events.meugeninua.androidevents.ui.activities.main;

import android.events.meugeninua.androidevents.app.di.scopes.PerActivity;
import android.events.meugeninua.androidevents.app.di.scopes.PerFragment;
import android.events.meugeninua.androidevents.ui.activities.base.BaseActivity;
import android.events.meugeninua.androidevents.ui.activities.base.BaseActivityModule;
import android.events.meugeninua.androidevents.ui.activities.main.fragment.MainFragment;
import android.events.meugeninua.androidevents.ui.activities.main.fragment.MainFragmentModule;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * @author meugen
 */
@Module(includes = BaseActivityModule.class)
public abstract class MainActivityModule {

    @Binds @PerActivity
    abstract BaseActivity bindActivity(final MainActivity activity);

    @ContributesAndroidInjector(modules = MainFragmentModule.class)
    @PerFragment
    abstract MainFragment contributeMainFragment();
}
