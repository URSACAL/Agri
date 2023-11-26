package com.example.agri;

public class ProductClass {
    String ProductSKU, ProductName, ProductDesc;
    public ProductClass() {
    }

    public String getProductSKU() {
        return ProductSKU;
    }

    public void setProductSKU(String productSKU) {
        ProductSKU = productSKU;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getProductDesc() {
        return ProductDesc;
    }

    public void setProductDesc(String productDesc) {
        ProductDesc = productDesc;
    }

    public ProductClass(String productSKU, String productName, String productDesc) {
        ProductSKU = productSKU;
        ProductName = productName;
        ProductDesc = productDesc;
    }
}
