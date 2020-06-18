package ru.javawebinar.topjava.web;

import static ru.javawebinar.topjava.util.MealsUtil.DEFAULT_CALORIES_PER_DAY;

public class SecurityUtil {
    private static int userID;

    public static int getAuthUserID() {
        return userID;
    }

    public static void setAuthUserID(int userID) {
        SecurityUtil.userID = userID;
    }

    public static int authUserId() {
        return 1;
    }

    public static int authUserCaloriesPerDay() {
        return DEFAULT_CALORIES_PER_DAY;
    }
}