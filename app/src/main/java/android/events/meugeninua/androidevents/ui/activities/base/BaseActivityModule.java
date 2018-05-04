package android.events.meugeninua.androidevents.ui.activities.base;

import android.content.Context;
import android.events.meugeninua.androidevents.app.di.qualifiers.ActivityContext;
import android.events.meugeninua.androidevents.app.di.scopes.PerActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import dagger.Binds;
import dagger.Module;

/**
 * @author meugen
 */
@Module
public abstract class BaseActivityModule {

    @Binds @PerActivity
    abstract AppCompatActivity bindCompatActivity(final BaseActivity activity);

    @Binds @ActivityContext @PerActivity
    abstract Context bindContext(final AppCompatActivity activity);
}
