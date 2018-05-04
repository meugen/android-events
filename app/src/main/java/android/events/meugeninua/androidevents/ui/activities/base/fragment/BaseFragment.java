package android.events.meugeninua.androidevents.ui.activities.base.fragment;

import android.events.meugeninua.androidevents.ui.activities.base.fragment.binding.Binding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public abstract class BaseFragment<B extends Binding> extends Fragment {

    @Inject protected B binding;

    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        AndroidSupportInjection.inject(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(
            @NonNull final View view,
            @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.binding.attachView(view);
    }
}
