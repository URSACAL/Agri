package com.example.agri;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ProductClass {
    String ProductSKU, ProductName, ProductDesc, ProductType, ProductQuantity;
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
    public String getProductType() {
        return ProductType;
    }

    public void setProductType(String productType) {
        ProductType = productType;
    }
    public String getProductQuantity() {
        return ProductQuantity;
    }

    public void setProductQuantity(String productQuantity) {
        ProductQuantity = productQuantity;
    }

    public String getImageURL() {
        return imageURL;
    }


    public ProductClass(String productSKU, String productName, String productDesc,  String productType,  String productQuantity) {
        ProductSKU = productSKU;
        ProductName = productName;
        ProductDesc = productDesc;
        ProductType = productType;
        ProductQuantity = productQuantity;
    }

}
