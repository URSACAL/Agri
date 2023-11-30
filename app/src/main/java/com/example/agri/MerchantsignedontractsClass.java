package com.example.agri;

public class MerchantsignedontractsClass {
    private String ownerName;
    private String ownerAddress;
    private String ownerContact;

    public MerchantsignedontractsClass(String ownerName, String ownerAddress, String ownerContact) {
        this.ownerName = ownerName;
        this.ownerAddress = ownerAddress;
        this.ownerContact = ownerContact;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public String getOwnerAddress() {
        return ownerAddress;
    }

    public String getOwnerContact() {
        return ownerContact;
    }
}
