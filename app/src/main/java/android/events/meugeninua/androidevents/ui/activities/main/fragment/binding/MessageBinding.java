package android.events.meugeninua.androidevents.ui.activities.main.fragment.binding;

import android.events.meugeninua.androidevents.R;
import android.events.meugeninua.androidevents.ui.activities.base.fragment.binding.BaseBinding;
import android.view.View;
import android.widget.TextView;

import javax.inject.Inject;

public class MessageBinding extends BaseBinding {

    public TextView message;

    @Inject
    MessageBinding() {}

    @Override
    public void attachView(final View view) {
        super.attachView(view);
        message = get(R.id.message);
    }
}
