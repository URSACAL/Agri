package com.example.agri;

public class MerchantClass {
    String CustomerName, Address, ContactNumber;
    public MerchantClass() {
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getContactNumber() {
        return ContactNumber;
    }

    public void setContactNumber(String contactNumber) {
        ContactNumber = contactNumber;
    }

    public MerchantClass(String customerName, String address, String contactNumber) {
        CustomerName = customerName;
        Address = address;
        ContactNumber = contactNumber;
    }
}
