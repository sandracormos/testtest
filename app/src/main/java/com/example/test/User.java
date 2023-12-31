package com.example.test;
enum Goal{ weightLoss, bodybuilding, wellness}
enum Gender {Female, Male }
public class User {

    private static double height;
    private static double weight;
    private static Integer kcalCount;

    private static Gender gender;

    private static Goal goal;

    private static int age;

    // Private constructor to prevent instantiation
    private User() {}
    public static void setHeight(Double height) {
        User.height = height;
    }

    public static void setWeight(double weight) {
        User.weight = weight;
    }

    public static void setKcalCount(Integer kcalCount){
        User.kcalCount = kcalCount;
    }
    public static void setGender(Gender gender) {
        User.gender = gender;
    }
    public static void setAge(Integer age) {
        User.age = age;
    }

    public static void setGoal(Goal goal) {User.goal = goal;}




    public static double getWeight() {
        return weight;
    }

    public static Double getHeight() {
        return height;
    }
    public static Integer getKcalCount() {
        return kcalCount;
    }

    public static Gender getGender() {return gender;}

    public static Integer getAge(){return age;}

    public static Goal getGoal(){return goal;}



}

