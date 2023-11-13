package com.example.test;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NutritionIxItemResponse {

    public List<FoodItem> foods;

    public class FoodItem {
        @SerializedName("food_name")
        private String foodName;

        @SerializedName("brand_name")
        private String brandName;

        @SerializedName("serving_qty")
        private int servingQty;

        @SerializedName("serving_unit")
        private String servingUnit;

        @SerializedName("serving_weight_grams")
        private Integer servingWeightGrams;

        @SerializedName("nf_metric_qty")
        private int nfMetricQty;

        @SerializedName("nf_metric_uom")
        private String nfMetricUom;

        @SerializedName("nf_calories")
        private int nfCalories;

        @SerializedName("nf_total_fat")
        private int nfTotalFat;

        @SerializedName("nf_saturated_fat")
        private int nfSaturatedFat;

        @SerializedName("nf_cholesterol")
        private int nfCholesterol;

        @SerializedName("nf_sodium")
        private int nfSodium;

        @SerializedName("nf_total_carbohydrate")
        private int nfTotalCarbohydrate;

        @SerializedName("nf_dietary_fiber")
        private int nfDietaryFiber;

        @SerializedName("nf_sugars")
        private int nfSugars;

        @SerializedName("nf_protein")
        private int nfProtein;

        @SerializedName("nf_potassium")
        private Integer nfPotassium;

        @SerializedName("nf_p")
        private Integer nfP;

        @SerializedName("full_nutrients")
        private List<Nutrient> fullNutrients;

        // Add other fields as needed

        public class Nutrient {
            @SerializedName("attr_id")
            private int attrId;

            @SerializedName("value")
            private int value;

            // Add other fields as needed
        }

        // Getter methods go here

        public String getFoodName() {
            return foodName;
        }

        public String getBrandName() {
            return brandName;
        }

        // Add other getter methods as needed
    }
}

