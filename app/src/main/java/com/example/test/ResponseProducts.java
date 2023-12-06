package com.example.test;

import java.util.List;

public class ResponseProducts {

    public List<Product> products;

    public class Product {
        public String barcode_number;
        public String title;
        public String ingredients;
        public String nutrition_facts;
    }
}

