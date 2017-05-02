package com.alphabgammainc.nestfinder.FirebaseConnection;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.Snackbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by davidhuang on 2017-04-25.
 * DBReference for general db querying
 * FireBaseAuth check if user is logged in
 * FirebaseUser to check the specific user
 *
 * @IMPORTANT make sure to log the user out unless u intend them to stay logged in even if the app is closed
 */

public class DataBaseConnectionPresenter {
    private DatabaseReference dbReference;
    private FirebaseAuth authentication;
    private FirebaseUser firebaseUser;
    private ConnectivityManager connectivityManager;
    private static DataBaseConnectionPresenter dataBaseConnectionPresenter;
    private Activity mActivity;
    private NetworkInfo activeNetworkInfo;


    private DataBaseConnectionPresenter(Activity activity){
        this.authentication= FirebaseAuth.getInstance(); // get instance of my firebase console
        this.dbReference = FirebaseDatabase.getInstance().getReference(); // access to database
        this.firebaseUser = authentication.getCurrentUser();
        this.mActivity = activity;
    }
    public void setFirebaseUser(){
        firebaseUser = authentication.getCurrentUser();

    }

    /**
     * this is a singleton object essentially wrapping a singleton inside a singleton.
     * @return instance of this class
     */
    public static synchronized  DataBaseConnectionPresenter getInstance(Activity activity){
        if(dataBaseConnectionPresenter==null){
            dataBaseConnectionPresenter = new DataBaseConnectionPresenter(activity);
        }

        return dataBaseConnectionPresenter;
    }

    public void setFirebaseUser(FirebaseUser fbu){
        firebaseUser = fbu;
    }
    // make sure that everytime u call this you check if it returns null because there could be no internet connection
    public DatabaseReference getDbReference(){
        if(isNetworkAvailable()){
            return dbReference;
        }
        Snackbar.make(mActivity.getCurrentFocus(), "No Internet Connection", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
        return null;
    }
    public FirebaseAuth getFireBaseAuth(){
        return authentication;
    }
    public FirebaseUser getFirebaseUser(){
        return firebaseUser;
    }
    public String getUID(){
        return firebaseUser.getUid();
    }

    public void freeuserData(){
        FirebaseAuth.getInstance().signOut();
    }

    public boolean isNetworkAvailable() {
        if(connectivityManager==null){
            connectivityManager
                    = (ConnectivityManager) mActivity.getSystemService(Context.CONNECTIVITY_SERVICE);
        }
        activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}

