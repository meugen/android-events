package android.events.meugeninua.androidevents.ui.activities.base.fragment.binding;

import android.support.v4.util.SparseArrayCompat;
import android.view.View;

import java.lang.ref.WeakReference;

import javax.inject.Inject;

public class BaseBinding implements Binding {

    private WeakReference<View> rootViewRef;
    private SparseArrayCompat<WeakReference<View>> array;

    @Inject
    BaseBinding() {}

    public void attachView(final View view) {
        this.rootViewRef = new WeakReference<>(view);
        this.array = new SparseArrayCompat<>();
    }

    @Override
    public final View getView() {
        return rootViewRef.get();
    }

    public final <V extends View> V get(final int viewId) {
        final WeakReference<View> viewRef = array.get(viewId);
        View foundView = viewRef == null ? null : viewRef.get();
        if (foundView == null) {
            View rootView = rootViewRef.get();
            foundView = rootView == null ? null
                    : rootView.findViewById(viewId);
            if (foundView != null) {
                array.put(viewId, new WeakReference<>(foundView));
            }
        }
        return (V) foundView;
    }
}
