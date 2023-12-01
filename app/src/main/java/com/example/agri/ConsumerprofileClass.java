package com.example.agri;

public class ConsumerprofileClass {
    private String OwnersName;
    private String Address;
    private String ContactNumber;

    public ConsumerprofileClass(String OwnersName, String Address, String ContactNumber) {
        this.OwnersName = OwnersName;
        this.Address = Address;
        this.ContactNumber = ContactNumber;


    }

    public String getDataOwnersName() {
        return OwnersName;
    }

    public String getDataAddress() {
        return Address;
    }

    public String getDataContactNumber() {
        return ContactNumber;
    }




}
