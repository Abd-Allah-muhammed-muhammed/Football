package com.example.football.helper;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.WindowManager;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class HelperMethods {



    public static void replace(Fragment fragment, int id, FragmentTransaction fragmentTransaction, String tag) {
        FragmentTransaction transaction = fragmentTransaction;
        transaction.replace(id, fragment, tag);
        transaction.addToBackStack(null);
        transaction.commit();

    }


    public static void fullScreen(Activity activity){

        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }




    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
