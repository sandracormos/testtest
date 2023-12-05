package com.example.test;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

enum Goal{ weightLoss, bodybuilding, wellness}
enum Gender {Female, Male }
public class User {

    private static double height;
    private static double weight;
    private static Double kcalCount;

    private static Gender gender;

    private static Goal goal;

    private static int age;

     static Map<LocalDate, DayEntry> journal = new HashMap<>();

    // Private constructor to prevent instantiation
    private User() {}
    public static void setHeight(Double height) {
        User.height = height;
    }

    public static void setWeight(double weight) {
        User.weight = weight;
    }

    public static void setKcalCount(Double kcalCount){
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
    public static Double getKcalCount() {
        return kcalCount;
    }

    public static Gender getGender() {return gender;}

    public static Integer getAge(){return age;}

    public static Goal getGoal(){return goal;}

    public static Map<LocalDate, DayEntry> getJournal(){return journal;}







}

