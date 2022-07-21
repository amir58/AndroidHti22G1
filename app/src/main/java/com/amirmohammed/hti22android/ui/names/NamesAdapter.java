package com.amirmohammed.hti22android.ui.names;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amirmohammed.hti22android.R;

import java.util.ArrayList;

// 1 : Constructor -> data
// 2 : ViewHolder class -> define views
// 3 : NamesAdapter extends RecyclerView.Adapter<ViewHolder>
// 4 : onCreateViewHolder ->
// 5 : getItemCount -> return arrayList.size();
// 6 : onBindViewHolder -> setText , onClick
public class NamesAdapter extends RecyclerView.Adapter<NamesAdapter.NamesViewHolder> {

    private final ArrayList<String> names;

    public NamesAdapter(ArrayList<String> names) {
        this.names = names;
    }

    @NonNull
    @Override
    public NamesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NamesViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_names, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NamesViewHolder holder, int position) {
        String currentName = names.get(position);

        holder.textView.setText(currentName);
    }

    @Override
    public int getItemCount() {
        return names.size(); // 6
    }

    class NamesViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public NamesViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tvName);
        }
    }

}
