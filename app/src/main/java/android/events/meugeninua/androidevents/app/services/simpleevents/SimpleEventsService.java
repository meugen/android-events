package android.events.meugeninua.androidevents.app.services.simpleevents;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.events.meugeninua.androidevents.app.managers.events.AppEventsManager;
import android.events.meugeninua.androidevents.app.managers.events.SimpleEvent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.math.BigInteger;
import java.util.Random;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import timber.log.Timber;

/**
 * @author meugen
 */
public class SimpleEventsService extends Service {

    private static final long MIN_DELAY = TimeUnit.MINUTES.toMillis(0);
    private static final long MAX_DELAY = TimeUnit.MINUTES.toMillis(1);
    private static final Random RANDOM = new Random();

    public static void start(final Context context) {
        final Intent intent = new Intent(context, SimpleEventsService.class);
        context.startService(intent);
    }

    @Inject AppEventsManager eventsManager;
    @Inject ScheduledExecutorService service;

    @Override
    public void onCreate() {
        AndroidInjection.inject(this);
        super.onCreate();

        runNext();
    }

    private void runNext() {
        final long delay = Math.abs(RANDOM.nextLong())
                % (MAX_DELAY - MIN_DELAY)
                + MIN_DELAY;
        Timber.d("Delay until next schedule is ~%d sec",
                TimeUnit.MILLISECONDS.toSeconds(delay));
        service.schedule(this::sendEvent, delay,
                TimeUnit.MILLISECONDS);
    }

    private void sendEvent() {
        eventsManager.post(new SimpleEvent(new BigInteger(
                100, RANDOM).toString(26)));
        runNext();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        service.shutdown();
    }

    @Nullable
    @Override
    public IBinder onBind(final Intent intent) {
        return null;
    }
}
