package com.example.test;

import java.util.List;

public class ResponseProducts {

    public List<Product> products;

    public class Store {
        public String name;
        public String price;
        public String link;
        public String currency;
        public String currency_symbol;
    }

    public class Review {
        public String name;
        public String rating;
        public String title;
        public String review;
        public String date;
    }

    public class Product {
        public String barcode_number;
        public String barcode_formats;
        public String mpn;
        public String model;
        public String asin;
        public String title;
        public String category;
        public String manufacturer;
        public String brand;
        public String[] contributors;
        public String age_group;
        public String ingredients;
        public String nutrition_facts;
        public String color;
        public String format;
        public String multipack;
        public String size;
        public String length;
        public String width;
        public String height;
        public String weight;
        public String release_date;
        public String description;
        public Object[] features;
        public String[] images;
        public Store[] stores;
        public Review[] reviews;
    }
}

