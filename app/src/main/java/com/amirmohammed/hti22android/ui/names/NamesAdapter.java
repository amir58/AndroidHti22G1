package com.amirmohammed.hti22android.ui.names;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amirmohammed.hti22android.R;
import com.amirmohammed.hti22android.models.MyContact;

import java.util.ArrayList;

// 1 : Constructor -> data
// 2 : ViewHolder class -> define views
// 3 : NamesAdapter extends RecyclerView.Adapter<ViewHolder>
// 4 : onCreateViewHolder ->
// 5 : getItemCount -> return arrayList.size();
// 6 : onBindViewHolder -> setText , onClick
public class NamesAdapter extends RecyclerView.Adapter<NamesAdapter.NamesViewHolder> {

    private final ArrayList<MyContact> names;

    public NamesAdapter(ArrayList<MyContact> names) {
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
        String currentName = names.get(position).getName();
        String currentPhone = names.get(position).getPhone();

        holder.textView.setText(currentName);
        holder.textViewPhone.setText(currentPhone);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 1 : show Alert dialog
                // 2 : Delete -> {
                // 2.1 remove names by position
                // 2.2 notify adapter for remove item
                // }

                new AlertDialog.Builder(view.getContext())
                        .setMessage("Are you sure to delete " + currentName)
                        .setNegativeButton("Cancel", null)
                        .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                names.remove(holder.getAdapterPosition());
                                notifyItemRemoved(holder.getAdapterPosition());
                            }
                        })
                        .show();
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return names.size(); // 6
    }

    class NamesViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        TextView textViewPhone;

        public NamesViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tvName);
            textViewPhone = itemView.findViewById(R.id.tvPhone);
        }
    }

}
