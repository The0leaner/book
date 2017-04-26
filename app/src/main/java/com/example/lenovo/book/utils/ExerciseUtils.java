package com.example.lenovo.book.utils;

import com.example.lenovo.book.AppProfile;

/**
 * Created by netease on 16/11/8.
 */

public class ExerciseUtils {


    public static int dp2px(float dipValue){
        return (int)(dipValue * AppProfile.getContext().getResources().getDisplayMetrics().density + 0.5f);
    }



}
