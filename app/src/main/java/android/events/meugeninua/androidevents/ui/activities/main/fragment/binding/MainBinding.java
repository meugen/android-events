package android.events.meugeninua.androidevents.ui.activities.main.fragment.binding;

import android.events.meugeninua.androidevents.R;
import android.events.meugeninua.androidevents.app.di.scopes.PerFragment;
import android.events.meugeninua.androidevents.ui.activities.base.fragment.binding.BaseBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import javax.inject.Inject;

@PerFragment
public class MainBinding extends BaseBinding {

    public RecyclerView recycler;

    @Inject
    MainBinding() {}

    @Override
    public void attachView(final View view) {
        super.attachView(view);
        recycler = get(R.id.recycler);
    }
}
