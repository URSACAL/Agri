package com.example.agri;

public class MerchantprofileClass {
    private String OwnersName;
    private String Address;
    private String ContactNumber;
    private String BusinessName;

    public MerchantprofileClass(String OwnersName, String Address, String ContactNumber, String BusinessName) {
        this.OwnersName = OwnersName;
        this.Address = Address;
        this.ContactNumber = ContactNumber;
        this.BusinessName = BusinessName;

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

    public String getDataBusinessName() {
        return BusinessName;
    }


}
