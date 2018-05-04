package android.events.meugeninua.androidevents.app.managers.events;

import android.support.annotation.MainThread;

/**
 * @author meugen
 */
public interface TypedObserver<T> {

    @MainThread
    void onUpdate(T event);
}
