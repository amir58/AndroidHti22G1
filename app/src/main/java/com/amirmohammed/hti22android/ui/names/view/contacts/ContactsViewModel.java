package com.amirmohammed.hti22android.ui.names.view.contacts;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.amirmohammed.hti22android.ui.names.db.ContactsDatabase;
import com.amirmohammed.hti22android.ui.names.model.MyContact;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ContactsViewModel extends ViewModel {


    private final FirebaseFirestore firestore = FirebaseFirestore.getInstance();

    private final MutableLiveData<List<MyContact>> names = new MutableLiveData<>();
    private final MutableLiveData<Integer> contactChangedState = new MutableLiveData<>();
    private Context context;

    void getNamesFromFirebase() {
        firestore.collection("htiContacts").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot query) {
                        for (DocumentSnapshot document : query.getDocuments()) {
                            MyContact myContact = document.toObject(MyContact.class);
//                            names.add(myContact);
                        }
                    }
                });
    }

    LiveData<List<MyContact>> getContactsFromRoomDB(Context context) {
        this.context = context;
        List<MyContact> myContacts = ContactsDatabase.init(context).contactsDao().getContacts();
        names.postValue(myContacts);
        return names;
    }

    public void addNewContact(MyContact myContact) {
        List<MyContact> myContacts = names.getValue();
        myContacts.add(myContact);

        names.postValue(myContacts);

        long contactId = ContactsDatabase.init(context)
                .contactsDao().insertContact(myContact);

        myContact.setId((int) contactId);

        firestore.collection("htiContacts")
                .document(String.valueOf(contactId))
                .set(myContact)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.i("NamesActivity", "onSuccess: ");
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.i("NamesActivity", "onFailure: " + e);
                    }
                });

    }

    public void updateContact(int contactPosition, MyContact myContact) {
        List<MyContact> myContacts = names.getValue();
        myContacts.remove(contactPosition);
        myContacts.add(contactPosition, myContact);

        names.postValue(myContacts);

//        namesAdapter.notifyItemChanged(contactPosition);
        contactChangedState.postValue(contactPosition);

        ContactsDatabase.init(context)
                .contactsDao().updateContact(myContact);

        Map<String, Object> map = new HashMap<>();
        map.put("name", myContact.getName());
        map.put("phone", myContact.getPhone());

        firestore.collection("htiContacts")
                .document(String.valueOf(myContact.getId()))
                .update(map);
    }

    LiveData<Integer> getContactChangedState() {
        return contactChangedState;
    }
}
