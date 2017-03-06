package com.offcasoftware.shop2.model;

/**
 * @author maciej.pachciarek on 2017-02-18.
 */

public class Product {

    private final int mId;
    private String mName;
    private String mPrice;
    private String mImageResId;

    public Product(final int id, final String name,
            final String price, final String imageResId) {
        mId = id;
        mName = name;
        mPrice = price;
        mImageResId = imageResId;
    }

    public int getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public String getPrice() {
        return mPrice;
    }

    public String getImageResId() {
        return mImageResId;
    }
}
