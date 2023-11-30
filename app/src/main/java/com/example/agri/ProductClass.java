package com.example.agri;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ProductClass {
    String ProductSKU, ProductName, ProductDesc;
    @NotNull
    public String imageURL;
    @Nullable
    public String key;

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

    public String getImageURL() {
        return imageURL;
    }


    public ProductClass(String productSKU, String productName, String productDesc) {
        ProductSKU = productSKU;
        ProductName = productName;
        ProductDesc = productDesc;
    }

}
