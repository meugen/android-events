package android.events.meugeninua.androidevents.ui.activities.main;

import android.databinding.DataBindingUtil;
import android.events.meugeninua.androidevents.R;
import android.events.meugeninua.androidevents.app.managers.events.AppEventsManager;
import android.events.meugeninua.androidevents.app.managers.events.SimpleEvent;
import android.events.meugeninua.androidevents.app.services.simpleevents.SimpleEventsService;
import android.events.meugeninua.androidevents.databinding.ActivityMainBinding;
import android.events.meugeninua.androidevents.ui.activities.base.BaseActivity;
import android.events.meugeninua.androidevents.ui.activities.main.adapters.MessagesAdapter;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;

import java.util.UUID;

import javax.inject.Inject;

import timber.log.Timber;

public class MainActivity extends BaseActivity {

    @Inject AppEventsManager eventsManager;
    @Inject MessagesAdapter adapter;

    private UUID key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SimpleEventsService.start(this);

        final ActivityMainBinding binding = DataBindingUtil.setContentView(
                this, R.layout.activity_main);
        binding.recycler.addItemDecoration(new DividerItemDecoration(
                this, DividerItemDecoration.VERTICAL));
        binding.recycler.setAdapter(adapter);

        key = eventsManager.subscribeToEvent(SimpleEvent.class, this::onSimpleEvent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        eventsManager.unsubscribe(key);
    }

    private void onSimpleEvent(final SimpleEvent event) {
        Timber.d(Thread.currentThread().getName());
        adapter.addMessage(event.message);
    }
}
