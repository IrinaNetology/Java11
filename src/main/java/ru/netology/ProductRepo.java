package ru.netology;

import java.io.FileNotFoundException;

public class ProductRepo {

    private Product[] products = new Product[0];

    public Product[] findAll() {
        return products;
    }

    public void deleteProduct(int id) {

        if (findById(id) == null) {
            throw new NotFoundException (
                    " Element with Id:" + id + " not found"
            );
        }
        Product[] tmp = new Product[products.length - 1];
        int newIndex = 0;
        for (int i = 0; i < products.length; i++) {
            if (products[i].id == id)
                continue;

            tmp[newIndex] = products[i];
            newIndex++;
        }
        products = tmp;
    }

    public void addProduct(Product newProduct) {
        Product[] tmp = new Product[products.length + 1];
        for (int i = 0; i < products.length; i++) {
            tmp[i] = products[i];
        }
        tmp[tmp.length - 1] = newProduct;
        products = tmp;
    }

    public Product findById (int id) {
        for (int i = 0; i < products.length; i++) {
            if (products[i].id == id) {
                return products [i];
            }
        }
        return null;
    }
}