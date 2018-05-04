package android.events.meugeninua.androidevents.app.managers.events;

import android.events.meugeninua.androidevents.app.di.scopes.PerApplication;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.AnyThread;
import android.support.annotation.MainThread;
import android.support.v4.util.ArrayMap;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.UUID;

import javax.inject.Inject;

import timber.log.Timber;

/**
 * @author meugen
 */
@PerApplication
public class AppEventsManager {

    private final Map<UUID, ObserverWrapper<?>> observers;
    private final Handler handler;

    @Inject
    AppEventsManager() {
        observers = new ArrayMap<>();
        handler = new Handler(Looper.getMainLooper());
    }

    @AnyThread
    public void post(final Object event) {
        handler.post(() -> postInMainThread(event));
    }

    private void checkMainThread() {
        if (Looper.getMainLooper() != Looper.myLooper()) {
            throw new IllegalStateException("Wrong thread. Should be main (UI) thread only.");
        }
    }

    @MainThread
    private void postInMainThread(final Object event) {
        checkMainThread();

        final List<ObserverWrapper<?>> wrappers
                = new ArrayList<>(observers.values());
        for (ObserverWrapper<?> wrapper : wrappers) {
            wrapper.update(this, event);
        }
    }

    @MainThread
    public <T> UUID subscribeToEvent(
            final Class<T> clazz,
            final TypedObserver<T> observer) {
        checkMainThread();

        UUID key = null;
        while (key == null || observers.containsKey(key)) {
            key = UUID.randomUUID();
        }
        final ObserverWrapper<T> impl = new ObserverWrapper<>(
                clazz, observer, key);
        observers.put(key, impl);
        return key;
    }

    @MainThread
    public void unsubscribe(final UUID... keys) {
        unsubscribe(Arrays.asList(keys));
    }

    @MainThread
    public void unsubscribe(final Collection<UUID> keys) {
        checkMainThread();
        for (UUID key : keys) {
            Timber.d("Unsubscribed %s", key);
            observers.remove(key);
        }
    }

    private static class ObserverWrapper<T> {

        private final Class<T> clazz;
        private final WeakReference<TypedObserver<T>> ref;

        private final UUID key;

        ObserverWrapper(
                final Class<T> clazz,
                final TypedObserver<T> observer,
                final UUID key) {
            this.clazz = clazz;
            this.key = key;
            this.ref = new WeakReference<>(observer);
        }

        @MainThread
        void update(
                final AppEventsManager manager,
                final Object arg) {
            final TypedObserver<T> observer = ref.get();
            if (observer == null) {
                manager.unsubscribe(key);
                return;
            }
            if (clazz.isInstance(arg)) {
                observer.onUpdate(clazz.cast(arg));
            }
        }
    }
}
