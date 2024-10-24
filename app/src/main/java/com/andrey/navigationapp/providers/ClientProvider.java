package com.andrey.navigationapp.providers;

import com.andrey.navigationapp.models.Client;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ClientProvider {
    DatabaseReference mDataBase;

    public ClientProvider(){
        mDataBase = FirebaseDatabase.getInstance().getReference().child("Users").child("Clients");
    }

    public Task<Void> create(Client client){
        return mDataBase.child(client.getId()).setValue(client);
    }
}
