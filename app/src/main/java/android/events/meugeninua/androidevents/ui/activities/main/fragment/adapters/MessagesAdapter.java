package android.events.meugeninua.androidevents.ui.activities.main.fragment.adapters;

import android.content.Context;
import android.events.meugeninua.androidevents.R;
import android.events.meugeninua.androidevents.app.di.qualifiers.ActivityContext;
import android.events.meugeninua.androidevents.app.di.scopes.PerFragment;
import android.events.meugeninua.androidevents.ui.activities.main.fragment.binding.MessageBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Provider;

@PerFragment
public class MessagesAdapter extends RecyclerView.Adapter<MessagesAdapter.MessageHolder> {

    private final LayoutInflater inflater;
    private final Provider<MessageBinding> bindingProvider;
    private List<String> messages;

    @Inject
    MessagesAdapter(
            @ActivityContext final Context context,
            final Provider<MessageBinding> bindingProvider) {
        this.inflater = LayoutInflater.from(context);
        this.bindingProvider = bindingProvider;
        this.messages = new ArrayList<>();
    }

    public void addMessage(final String message) {
        messages.add(message);
        notifyItemInserted(messages.size() - 1);
    }

    @NonNull
    @Override
    public MessagesAdapter.MessageHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {
        final MessageBinding binding = bindingProvider.get();
        binding.attachView(inflater.inflate(R.layout.item_message,
                parent, false));
        return new MessagesAdapter.MessageHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull final MessagesAdapter.MessageHolder holder, final int position) {
        holder.binding.message.setText(messages.get(position));
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    static class MessageHolder extends RecyclerView.ViewHolder {

        final MessageBinding binding;

        MessageHolder(final MessageBinding binding) {
            super(binding.getView());
            this.binding = binding;
        }

    }
}