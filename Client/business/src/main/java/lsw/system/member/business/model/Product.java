package lsw.system.member.business.model;

import io.realm.RealmObject;

/**
 * Created by swli on 10/12/2016.
 */
public class Product extends RealmObject{

    private double productPrice;
    private String productName;
    private ProductCategory productCategory;

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }
}
