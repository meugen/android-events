package android.events.meugeninua.androidevents.ui.activities.main.adapters;

import android.content.Context;
import android.events.meugeninua.androidevents.app.di.qualifiers.ActivityContext;
import android.events.meugeninua.androidevents.databinding.ItemMessageBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * @author meugen
 */
public class MessagesAdapter extends RecyclerView.Adapter<MessagesAdapter.MessageHolder> {

    private final LayoutInflater inflater;
    private List<String> messages;

    @Inject
    MessagesAdapter(@ActivityContext final Context context) {
        this.inflater = LayoutInflater.from(context);
        this.messages = new ArrayList<>();
    }

    public void addMessage(final String message) {
        messages.add(message);
        notifyItemInserted(messages.size() - 1);
    }

    @NonNull
    @Override
    public MessageHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {
        final ItemMessageBinding binding = ItemMessageBinding.inflate(
                inflater, parent, false);
        return new MessageHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull final MessageHolder holder, final int position) {
        holder.binding.message.setText(messages.get(position));
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public static class MessageHolder extends RecyclerView.ViewHolder {

        final ItemMessageBinding binding;

        MessageHolder(final ItemMessageBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

    }
}
