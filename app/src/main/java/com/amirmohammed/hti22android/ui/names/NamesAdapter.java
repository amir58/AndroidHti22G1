package com.amirmohammed.hti22android.ui.names;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amirmohammed.hti22android.R;
import com.amirmohammed.hti22android.models.MyContact;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

// 1 : Constructor -> data
// 2 : ViewHolder class -> define views
// 3 : NamesAdapter extends RecyclerView.Adapter<ViewHolder>
// 4 : onCreateViewHolder ->
// 5 : getItemCount -> return arrayList.size();
// 6 : onBindViewHolder -> setText , onClick
public class NamesAdapter extends RecyclerView.Adapter<NamesAdapter.NamesViewHolder> {

    private final List<MyContact> names;
    private final IUpdateContact iUpdateContact;

    public NamesAdapter(List<MyContact> names, IUpdateContact iUpdateContact) {
        this.names = names;
        this.iUpdateContact = iUpdateContact;
    }

    @NonNull
    @Override
    public NamesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NamesViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_names, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NamesViewHolder holder, int position) {
        MyContact myContact = names.get(position);
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
                                System.out.println("Adapter position => " + holder.getAdapterPosition());
                                ContactsDatabase.init(view.getContext())
                                        .contactsDao().deleteContact(myContact);

                                FirebaseFirestore firestore = FirebaseFirestore.getInstance();
                                firestore.collection("htiContacts")
                                        .document(String.valueOf(myContact.getId()))
                                        .delete();
                            }
                        })
                        .show();
            }
        });

        holder.imageViewUpdateContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iUpdateContact.onUpdateContactClick(currentName, currentPhone, holder.getAdapterPosition());
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
        ImageView imageViewUpdateContact;

        public NamesViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tvName);
            textViewPhone = itemView.findViewById(R.id.tvPhone);
            imageViewUpdateContact = itemView.findViewById(R.id.ivUpdateContact);
        }
    }

}
