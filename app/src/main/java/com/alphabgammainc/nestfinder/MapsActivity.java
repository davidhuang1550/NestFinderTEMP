package com.alphabgammainc.nestfinder;

import android.app.FragmentManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.alphabgammainc.nestfinder.FirebaseConnection.DataBaseConnectionPresenter;
import com.alphabgammainc.nestfinder.FrontPage.FrontPage;
import com.alphabgammainc.nestfinder.Ulilities.FragmentCallback;
import com.alphabgammainc.nestfinder.Ulilities.ImageCallBack;
import com.alphabgammainc.nestfinder.Ulilities.ImageManager;
import com.google.firebase.database.DatabaseReference;

import java.lang.ref.Reference;

public class MapsActivity extends AppCompatActivity implements ImageCallBack{
    private FragmentCallback fragmentCallback;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().add(R.id.content_frame,new FrontPage()).commit();

    }

    /*
        Permissions
     */

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults[0]== PackageManager.PERMISSION_GRANTED){
            //resume tasks needing this permission
        }
    }
    public boolean isExternalStorageWritable() {
        String permission = "android.permission.WRITE_EXTERNAL_STORAGE"; // get permissions
        int res= this.checkCallingOrSelfPermission(permission);
        return (res== PackageManager.PERMISSION_GRANTED);
    }
    public boolean checkReadExternalPermission(){
        String permission = "android.permission.READ_EXTERNAL_STORAGE"; // get permissions
        int res= this.checkCallingOrSelfPermission(permission);
        return (res== PackageManager.PERMISSION_GRANTED);
    }
    public void requestForSpecificPermission() {
        ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, 101);
    }

    public void setFragmentCallback(FragmentCallback fragmentCallback){
        this.fragmentCallback = fragmentCallback;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode==RESULT_OK) {
            switch (requestCode){
                case 1:
                    String path = ImageManager.getPath(this, data.getData());

                    DatabaseReference reference = DataBaseConnectionPresenter.getInstance().getDbReference()
                            .child("temp").push();

                    ImageManager.uploadImage(path,reference.getKey(), this);
                    fragmentCallback.callback(reference.getKey());

                    break;
            }
        }
    }

    @Override
    public void ImageOnReady(Uri downloadUrl) {
        // we dont need to display it
    }


}
