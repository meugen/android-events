package android.events.meugeninua.androidevents.ui.activities.base.fragment.binding;

import android.view.View;

public interface Binding {

    void attachView(View view);

    View getView();

    <V extends View> V get(int viewId);
}
