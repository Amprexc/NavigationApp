package com.andrey.navigationapp.models;

public class Driver extends User{
    String vehicleBrand;
    String vehiclePlate;
    public Driver(String id, String name, String email) {
        super(id, name, email);
    }
}
