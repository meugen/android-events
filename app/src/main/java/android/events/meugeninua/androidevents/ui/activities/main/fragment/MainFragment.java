package android.events.meugeninua.androidevents.ui.activities.main.fragment;

import android.content.Context;
import android.events.meugeninua.androidevents.R;
import android.events.meugeninua.androidevents.app.di.qualifiers.ActivityContext;
import android.events.meugeninua.androidevents.app.managers.events.AppEventsManager;
import android.events.meugeninua.androidevents.app.managers.events.SimpleEvent;
import android.events.meugeninua.androidevents.ui.activities.base.fragment.BaseFragment;
import android.events.meugeninua.androidevents.ui.activities.main.fragment.adapters.MessagesAdapter;
import android.events.meugeninua.androidevents.ui.activities.main.fragment.binding.MainBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.UUID;

import javax.inject.Inject;

import timber.log.Timber;

public class MainFragment extends BaseFragment<MainBinding> {

    @Inject @ActivityContext Context context;
    @Inject AppEventsManager eventsManager;
    @Inject MessagesAdapter adapter;

    private UUID key;

    @Nullable
    @Override
    public View onCreateView(
            @NonNull final LayoutInflater inflater,
            @Nullable final ViewGroup container,
            @Nullable final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main,
                container, false);
    }

    @Override
    public void onActivityCreated(
            @Nullable final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        binding.recycler.addItemDecoration(new DividerItemDecoration(
                context, DividerItemDecoration.VERTICAL));
        binding.recycler.setAdapter(adapter);

        key = eventsManager.subscribeToEvent(SimpleEvent.class, this::onSimpleEvent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        eventsManager.unsubscribe(key);
    }

    private void onSimpleEvent(final SimpleEvent event) {
        Timber.d(Thread.currentThread().getName());
        adapter.addMessage(event.message);
    }
}
