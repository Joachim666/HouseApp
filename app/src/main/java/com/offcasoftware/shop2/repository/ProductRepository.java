package com.offcasoftware.shop2.repository;

import com.offcasoftware.shop2.AndroidApp;
import com.offcasoftware.shop2.database.Database;
import com.offcasoftware.shop2.model.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author maciej.pachciarek on 2017-02-18.
 */

public class ProductRepository implements ProductRepositoryInterface {

    private static ProductRepository mInstance = new ProductRepository();

    private final Map<Integer, Product> mPorducts = new HashMap<>();

    //15
    private final Database mDatabase;

    // TODO: 2017-02-21 test
    private ProductRepository() {

        //16
        mDatabase = AndroidApp.getDatabase();

        List<Product> products = new ArrayList<>();
        Product product1 = new Product(1, "dom 1", "1000", "dom1");
        Product product2 = new Product(2, "dom 2", "2000", "dom2");
        Product product3 = new Product(3, "dom 3", "3000", "dom3");

        mPorducts.put(1, product1);
        mPorducts.put(2, product2);
        mPorducts.put(3, product3);

        //17
        mDatabase.saveProducts(products);
        mPorducts.put(1,product1);
        mPorducts.put(2,product2);
        mPorducts.put(3,product3);
    }

    public static ProductRepositoryInterface getInstance() {
        return mInstance;
    }

    @Override
    public List<Product> getProducts() {
        return mDatabase.getProducts();
    } // tutaj zmien po dodatniu funckji getProduct w interface

    @Override
    public Product getProduct(final int productId) {
        return mPorducts.get(productId);
    }
}
