package android.events.meugeninua.androidevents.ui.activities.base.fragment.binding;

import android.support.v4.util.SparseArrayCompat;
import android.view.View;

import java.lang.ref.WeakReference;

public class BaseBinding implements Binding {

    private View view;
    private SparseArrayCompat<WeakReference<View>> array;

    public void attachView(final View view) {
        this.view = view;
        this.array = new SparseArrayCompat<>();
    }

    @Override
    public final View getView() {
        return view;
    }

    public final <V extends View> V get(final int viewId) {
        final WeakReference<View> ref = array.get(viewId);
        View foundView = ref == null ? null : ref.get();
        if (foundView == null) {
            foundView = view.findViewById(viewId);
            if (foundView != null) {
                array.put(viewId, new WeakReference<>(foundView));
            }
        }
        return (V) foundView;
    }
}
