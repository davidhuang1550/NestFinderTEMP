package com.alphabgammainc.nestfinder.FirebaseConnection;

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
    private static DataBaseConnectionPresenter dataBaseConnectionPresenter;

    private DataBaseConnectionPresenter(){
        authentication= FirebaseAuth.getInstance(); // get instance of my firebase console
        dbReference = FirebaseDatabase.getInstance().getReference(); // access to database
        firebaseUser = authentication.getCurrentUser();
    }
    public void setFirebaseUser(){
        firebaseUser = authentication.getCurrentUser();

    }

    /**
     * this is a singleton object essentially wrapping a singleton inside a singleton.
     * @return instance of this class
     */
    public static synchronized  DataBaseConnectionPresenter getInstance(){
        if(dataBaseConnectionPresenter==null){
            dataBaseConnectionPresenter = new DataBaseConnectionPresenter();
        }
        return dataBaseConnectionPresenter;
    }

    public void setFirebaseUser(FirebaseUser fbu){
        firebaseUser = fbu;
    }
    public DatabaseReference getDbReference(){
        return dbReference;
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
}

