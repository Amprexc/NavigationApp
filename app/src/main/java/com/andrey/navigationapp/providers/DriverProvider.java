package com.andrey.navigationapp.providers;

import com.andrey.navigationapp.models.Client;
import com.andrey.navigationapp.models.Driver;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DriverProvider {
    DatabaseReference mDataBase;

    public DriverProvider(){
        mDataBase = FirebaseDatabase.getInstance().getReference().child("Users").child("Drivers");
    }

    public Task<Void> create(Driver driver){
        return mDataBase.child(driver.getId()).setValue(driver);
    }
}